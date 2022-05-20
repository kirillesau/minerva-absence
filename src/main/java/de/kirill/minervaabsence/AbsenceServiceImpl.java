package de.kirill.minervaabsence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AbsenceServiceImpl implements AbsenceService {

  AbsenceRepository absenceRepository;

  @Override
  public List<Absence> getAllAbsence() {
    return absenceRepository.findAll();
  }

}
