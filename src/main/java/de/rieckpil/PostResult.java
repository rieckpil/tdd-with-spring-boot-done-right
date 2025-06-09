package de.rieckpil;

import java.util.List;

public record PostResult(List<Post> posts, long total, long skip, long limit) {}
