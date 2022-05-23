package de.kirill.minervaabsence;

import java.util.List;

public interface AbsenceService {

  List<Absence> getAllAbsence();

  Absence createNewAbsence(Absence absence);

  void deleteAbsence(Long absenceId);

}
