package de.kirill.minervaabsence;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("absence")
@RequiredArgsConstructor
public class AbsenceController {

  private final AbsenceService absenceService;

  @GetMapping
  List<Absence> getAbsence() {
    return absenceService.getAbsence();
  }

}
