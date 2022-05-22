package de.kirill.minervaabsence;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

  @PreAuthorize("hasRole('USER')")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  Absence putAbsence(@RequestBody Absence absence) {
    return absenceService.createNewAbsence(absence);
  }

}
