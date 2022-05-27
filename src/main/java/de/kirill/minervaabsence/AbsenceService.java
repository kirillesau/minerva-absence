package de.kirill.minervaabsence;

import java.util.List;

public interface AbsenceService {

  List<Absence> getAllAbsence();

  Absence createOrUpdateAbsence(Absence absence);

  void deleteAbsence(Long absenceId);

}
