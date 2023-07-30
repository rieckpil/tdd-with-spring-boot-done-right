package de.rieckpil;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TimeUtilTest {

  @Mock private TimeProvider timeProvider;

  @InjectMocks private TimeUtil cut;

  @Test
  void shouldThrowExceptionWhenDateIsInFuture() throws Exception {

    when(timeProvider.getCurrentDate()).thenReturn(LocalDate.of(2020, 12, 24));

    LocalDate creationDateInTheFuture = LocalDate.now().plusDays(1);

    assertThrows(
        IllegalArgumentException.class,
        () -> cut.getDiffBetweenCreationDate(creationDateInTheFuture));
  }

  @Test
  void shouldReturnTodayWhenCommentWasCreatedToday() throws Exception {

    when(timeProvider.getCurrentDate()).thenReturn(LocalDate.now());

    LocalDate today = LocalDate.now();

    String result = cut.getDiffBetweenCreationDate(today);

    assertEquals("today", result);
  }

  @Test
  void shouldReturnMoreThanAYearWhenCommentWasCreatedLastYear() throws Exception {

    when(timeProvider.getCurrentDate()).thenReturn(LocalDate.now());

    LocalDate lastYear = LocalDate.now().minusDays(370);

    String result = cut.getDiffBetweenCreationDate(lastYear);

    assertEquals("more than a year ago", result);
  }

  @Test
  void shouldReturnOneMonthAgoWhenCommentWasCreatedLastMonth() throws Exception {

    when(timeProvider.getCurrentDate()).thenReturn(LocalDate.now());

    LocalDate lastMonth = LocalDate.now().minusDays(40);

    String result = cut.getDiffBetweenCreationDate(lastMonth);

    assertEquals("one month ago", result);
  }

  @Test
  void shouldReturnPluralOfMonthsWhenCommentIsCreatedMoreThanAMonthAgo() {

    when(timeProvider.getCurrentDate()).thenReturn(LocalDate.now());

    LocalDate lastMonth = LocalDate.now().minusDays(70);

    String result = cut.getDiffBetweenCreationDate(lastMonth);

    assertEquals("2 months ago", result);
  }

  @Test
  void shouldReturnOneDayAgoWhenCommentWasMadeYesterday() throws Exception {

    when(timeProvider.getCurrentDate()).thenReturn(LocalDate.now());

    LocalDate yesterday = LocalDate.now().minusDays(1);

    String result = cut.getDiffBetweenCreationDate(yesterday);

    assertEquals("one day ago", result);
  }

  @Test
  void shouldReturnPluralOfDaysAgoWhenCommentWasMadeAfterYesterday() throws Exception {

    when(timeProvider.getCurrentDate()).thenReturn(LocalDate.now());

    LocalDate twentyDaysAgo = LocalDate.now().minusDays(20);

    String result = cut.getDiffBetweenCreationDate(twentyDaysAgo);

    assertEquals("20 days ago", result);
  }
}
