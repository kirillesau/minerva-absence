package de.kirill.minervaabsence;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("absence")
public class AbsenceController {

  @GetMapping
  Absence getAbsence() {
    Absence expected = new Absence();
    expected.setName("UNIT");
    expected.setFrom(LocalDateTime.of(2022, 1, 1, 0, 0, 0));
    expected.setTo(LocalDateTime.of(2022, 1, 2, 0, 0, 0));
    return expected;
  }

}
