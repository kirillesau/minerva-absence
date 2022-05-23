package de.kirill.minervaabsence;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AbsenceServiceImpl implements AbsenceService {

  AbsenceRepository absenceRepository;

  @Override
  public List<Absence> getAllAbsence() {
    return absenceRepository.findAll();
  }

  @Override public Absence createNewAbsence(Absence absence) {

    if (absenceRepository.findById(absence.getId()).isPresent()) {
      throw new AbsenceException("Absence with Id<%s> is already defined!".formatted(absence.getId()));
    }

    return absenceRepository.save(absence);
  }

}
