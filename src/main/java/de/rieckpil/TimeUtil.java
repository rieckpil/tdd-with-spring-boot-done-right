package de.rieckpil;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;

/**
 * Develop a feature to display information when a comment was made (one day ago, 3 days ago, 6
 * month ago, etc.) in a human-readable format: - A comment that is older than 365 days, should
 * return 'more than a year'. - A comment within today should return 'today'. - A date in the future
 * is invalid and should throw an exception.
 */
@Component
public class TimeUtil {

  private final TimeProvider timeProvider;

  public TimeUtil(TimeProvider timeProvider) {
    this.timeProvider = timeProvider;
  }

  public String getDiffBetweenCreationDate(LocalDate creationDate) {

    LocalDate currentDate = timeProvider.getCurrentDate();

    Period periodBetween = Period.between(creationDate, currentDate);

    if (periodBetween.isNegative()) {
      throw new IllegalArgumentException("Creation date must not be in the future");
    }

    if (periodBetween.getYears() > 0) {
      return "more than a year ago";
    }
    else if (periodBetween.getMonths() > 0) {
      return formatTimeAgo(periodBetween.getMonths(), "month");
    }
    else if (periodBetween.getDays() > 0) {
      return formatTimeAgo(periodBetween.getDays(), "day");
    }
    else {
      return "today";
    }
  }

  private String formatTimeAgo(int amount, String unit) {
    if (amount == 1) {
      return "one " + unit + " ago";
    }
    return amount + " " + unit + "s ago";
  }
}
