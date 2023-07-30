package de.rieckpil;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class CommentService {
  public List<Comment> findAll() {
    return List.of();
  }

  public UUID createComment(String content, String authorName) {
    return UUID.randomUUID();
  }
}
