package de.kirill.minervaabsence;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("absence")
@RequiredArgsConstructor
public class AbsenceController {

  private final AbsenceService absenceService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping
  List<Absence> getAbsence() {
    return absenceService.getAllAbsence();
  }

}
