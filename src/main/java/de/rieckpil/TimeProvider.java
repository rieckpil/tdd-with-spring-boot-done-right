package de.rieckpil;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class TimeProvider {
  public LocalDate getCurrentDate() {
    return LocalDate.now();
  }
}
