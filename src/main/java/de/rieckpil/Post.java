package de.rieckpil;

import java.util.Set;

public record Post(
  long id, String title, String body, long userId, Set<String> tags, long reactions) { }
