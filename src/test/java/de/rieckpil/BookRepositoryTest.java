package de.rieckpil;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
class BookRepositoryTest {

  @Container @ServiceConnection
  static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:17-alpine");

  @Autowired private BookRepository cut;

  @Test
  void shouldFindBooksByTitleWithProperRanking() {
    Book javaBook1 =
        new Book(
            "978-1-1234-5678-1",
            "Advanced Java Programming",
            "Author 1",
            LocalDate.now().minusYears(1));
    Book javaBook2 =
        new Book(
            "978-1-1234-5678-2", "Java for Beginners", "Author 2", LocalDate.now().minusYears(2));
    Book pythonBook =
        new Book(
            "978-1-1234-5678-4", "Python Programming", "Author 4", LocalDate.now().minusYears(4));

    cut.saveAll(List.of(javaBook1, javaBook2, pythonBook));

    List<Book> results = cut.searchBooksByTitleWithRanking("java");

    assertThat(results).hasSize(2);

    assertThat(results.stream().map(Book::getTitle))
        .contains("Advanced Java Programming", "Java for Beginners");
  }

  @Test
  void shouldReturnEmptyListWhenNoBooksMatchSearchTerm() {
    Book book1 =
        new Book(
            "978-1-1234-5678-1", "Python for Beginners", "Author 1", LocalDate.now().minusYears(1));
    Book book2 =
        new Book(
            "978-1-1234-5678-2", "C# Programming Guide", "Author 2", LocalDate.now().minusYears(2));

    cut.saveAll(List.of(book1, book2));

    List<Book> results = cut.searchBooksByTitleWithRanking("javascript");

    assertThat(results).isEmpty();
  }
}
