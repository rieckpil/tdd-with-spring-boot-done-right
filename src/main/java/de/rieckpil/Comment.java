package de.rieckpil;

import java.time.LocalDate;
import java.util.UUID;

public record Comment(UUID id, String authorId, String content, LocalDate creationDate) { }
