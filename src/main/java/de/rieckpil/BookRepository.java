package de.rieckpil;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

  /** There's no need to write automated tests to verify derived Spring Data queries */
  Book findByIdOrderByPublishedDateAsc(Long id);

  /**
   * PostgreSQL-specific: Full text search on book titles with ranking. Uses PostgreSQL's
   * to_tsvector and to_tsquery for sophisticated text searching with ranking based on relevance.
   *
   * @param searchTerms the search terms (e.g. "adventure dragons fantasy")
   * @return list of books matching the search terms, ordered by relevance
   */
  @Query(
      value =
          """
    SELECT * FROM books
    WHERE to_tsvector('english', title) @@ plainto_tsquery('english', :searchTerms)
    ORDER BY ts_rank(to_tsvector('english', title), plainto_tsquery('english', :searchTerms)) DESC
    """,
      nativeQuery = true)
  List<Book> searchBooksByTitleWithRanking(@Param("searchTerms") String searchTerms);
}
