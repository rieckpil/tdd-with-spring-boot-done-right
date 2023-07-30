package de.rieckpil;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comments")
public class CommentViewController {

  @GetMapping
  public String getCommentOverviewPage(Model model) {

    model.addAttribute(
        "comments",
        List.of(
            new Comment(UUID.randomUUID(), "40", "Lorem Ipsum", LocalDate.now()),
            new Comment(UUID.randomUUID(), "41", "Lorem Ipsum", LocalDate.now().minusDays(700)),
            new Comment(UUID.randomUUID(), "43", "Lorem Ipsum", LocalDate.now().minusDays(3)),
            new Comment(UUID.randomUUID(), "44", "Lorem Ipsum", LocalDate.now().minusDays(56)),
            new Comment(UUID.randomUUID(), "45", "Lorem Ipsum", LocalDate.now().minusDays(200))));

    return "all-comments";
  }
}
