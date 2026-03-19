package de.rieckpil;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(WebSecurityConfiguration.class)
@WebMvcTest(CommentApiController.class)
class CommentApiControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean private CommentService commentService;

  @Test
  void shouldAllowAnonymousUsersToGetAllComments() throws Exception {

    when(commentService.findAll())
        .thenReturn(
            List.of(
                new Comment(UUID.randomUUID(), "40", "Lorem Ipsum", LocalDate.now()),
                new Comment(UUID.randomUUID(), "41", "Lorem Ipsum", LocalDate.now().minusDays(1)),
                new Comment(UUID.randomUUID(), "42", "Lorem Ipsum", LocalDate.now().minusDays(3))));

    this.mockMvc
        .perform(get("/api/comments").header(ACCEPT, APPLICATION_JSON))
        .andExpect(status().is(200))
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$.length()", is(3)))
        .andExpect(jsonPath("$[0].content", notNullValue()))
        .andExpect(jsonPath("$[0].id", notNullValue()))
        .andExpect(jsonPath("$[0].creationDate", notNullValue()))
        .andExpect(jsonPath("$[0].authorId", notNullValue()));
  }

  @Test
  void shouldRejectAnonymousUsersWhenCreatingComments() throws Exception {
    this.mockMvc
        .perform(
            post("/api/comments")
                .contentType(APPLICATION_JSON)
                .content(
                    """
               {
                  "content": "Lorem Ipsum"
               }
              """))
        .andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser(
      username = "duke",
      roles = {"VISITOR"})
  void shouldRejectAuthenticatedUserWithoutAdminRoleWhenCreatingComments() throws Exception {
    this.mockMvc
        .perform(
            post("/api/comments")
                .contentType(APPLICATION_JSON)
                .content(
                    """
               {
                  "content": "Lorem Ipsum"
               }
              """))
        .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(
      username = "duke",
      roles = {"VISITOR", "ADMIN"})
  void shouldFailOnInvalidCommentData() throws Exception {
    this.mockMvc
        .perform(
            post("/api/comments")
                .contentType(APPLICATION_JSON)
                .content(
                    """
             {
                "content": ""
             }
            """))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser(
      username = "duke",
      roles = {"VISITOR", "ADMIN"})
  void shouldCreateCommentWhenUserIsAuthenticatedAndAdmin() throws Exception {

    UUID newlyCreatedId = UUID.randomUUID();

    when(commentService.createComment(anyString(), anyString())).thenReturn(newlyCreatedId);

    this.mockMvc
        .perform(
            post("/api/comments")
                .contentType(APPLICATION_JSON)
                .content(
                    """
               {
                  "content": "Lorem Ipsum"
               }
              """))
        .andExpect(status().isCreated())
        .andExpect(header().exists("Location"))
        .andExpect(
            header()
                .string("Location", Matchers.containsString("/api/comments/" + newlyCreatedId)));
  }

  @Test
  void shouldRejectAnonymousUsersWhenExportingAllCommentsAsCsv() throws Exception {
    this.mockMvc.perform(get("/api/comments/export")).andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser(username = "duke")
  void shouldExportAllCommentsAsCsvWhenUserIsAuthenticated() throws Exception {
    UUID firstId = UUID.fromString("11111111-1111-1111-1111-111111111111");
    UUID secondId = UUID.fromString("22222222-2222-2222-2222-222222222222");

    when(commentService.findAll())
        .thenReturn(
            List.of(
                new Comment(firstId, "author-1", "Hello World", LocalDate.of(2024, 1, 15)),
                new Comment(secondId, "author-2", "Spring Boot rocks", LocalDate.of(2024, 3, 22))));

    this.mockMvc
        .perform(get("/api/comments/export"))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.parseMediaType("text/csv")))
        .andExpect(content().string(containsString("id,authorId,content,creationDate")))
        .andExpect(content().string(containsString("11111111-1111-1111-1111-111111111111")))
        .andExpect(content().string(containsString("author-1")))
        .andExpect(content().string(containsString("Hello World")))
        .andExpect(content().string(containsString("2024-01-15")))
        .andExpect(content().string(containsString("22222222-2222-2222-2222-222222222222")))
        .andExpect(content().string(containsString("author-2")))
        .andExpect(content().string(containsString("Spring Boot rocks")))
        .andExpect(content().string(containsString("2024-03-22")));
  }

  @Test
  @WithMockUser(username = "duke")
  void shouldExportEmptyCsvWithOnlyHeadersWhenNoCommentsExist() throws Exception {
    when(commentService.findAll()).thenReturn(List.of());

    this.mockMvc
        .perform(get("/api/comments/export"))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.parseMediaType("text/csv")))
        .andExpect(content().string(containsString("id,authorId,content,creationDate")));
  }
}
