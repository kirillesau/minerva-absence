package de.kirill.minervaabsence;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
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
  @Transactional
  Absence postAbsence(@RequestBody Absence absence) {
    return absenceService.createNewAbsence(absence);
  }

  @PreAuthorize("hasRole('USER')")
  @DeleteMapping(path = "/{absenceId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Transactional
  void deleteAbsence(@PathVariable Long absenceId) {
    // TODO: Auf Credentials einschränken, so dass nur Abwesenheiten der zugehörigen UserId gelöscht werden können
    absenceService.deleteAbsence(absenceId);
  }


}
