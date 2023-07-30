package de.rieckpil;

import jakarta.validation.constraints.NotBlank;

public record CommentCreationRequest(@NotBlank String content) {}
