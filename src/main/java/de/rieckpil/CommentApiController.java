package de.rieckpil;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Develop a REST API to retrieve and create comments. Everybody should be able to retrieve comments
 * but only logged-in users with the role ADMIN can create a comment.
 */
@RestController
@RequestMapping("/api/comments")
public class CommentApiController {

  private final CommentService commentService;

  public CommentApiController(CommentService commentService) {
    this.commentService = commentService;
  }

  @GetMapping
  public List<Comment> getAllComments() {
    return commentService.findAll();
  }

  @GetMapping(value = "/export", produces = "text/csv")
  public ResponseEntity<String> exportCommentsAsCsv() {
    List<Comment> comments = commentService.findAll();

    String csvContent =
        Stream.concat(
                Stream.of("id,authorId,content,creationDate"),
                comments.stream()
                    .map(
                        comment ->
                            comment.id()
                                + ","
                                + comment.authorId()
                                + ","
                                + comment.content()
                                + ","
                                + comment.creationDate()))
            .collect(Collectors.joining("\n"));

    return ResponseEntity.ok().contentType(MediaType.parseMediaType("text/csv")).body(csvContent);
  }

  @PostMapping
  public ResponseEntity<Void> createComment(
      @Valid @RequestBody CommentCreationRequest request,
      Authentication authentication,
      UriComponentsBuilder uriComponentsBuilder) {

    UUID newCommentId = commentService.createComment(request.content(), authentication.getName());

    UriComponents uriComponents =
        uriComponentsBuilder.path("/api/comments/{id}").buildAndExpand(newCommentId);

    return ResponseEntity.created(uriComponents.toUri()).build();
  }
}
