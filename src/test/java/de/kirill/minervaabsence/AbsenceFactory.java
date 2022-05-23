package de.kirill.minervaabsence;

import java.time.LocalDateTime;

public class AbsenceFactory {

  protected static Absence getDefaultAbsence() {
    Absence expected = new Absence();
    expected.setName("UNIT");
    expected.setFromDate(LocalDateTime.of(2022, 1, 1, 0, 0, 0));
    expected.setToDate(LocalDateTime.of(2022, 1, 2, 0, 0, 0));
    return expected;
  }
}
