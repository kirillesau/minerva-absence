package de.kirill.minervaabsence;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static de.kirill.minervaabsence.AbsenceFactory.getDefaultAbsence;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class AbsenceServiceImplTest {

  @Autowired
  AbsenceService underTest;

  @MockBean
  AbsenceRepository absenceRepository;

  @BeforeEach
  void setUp() {
    underTest = new AbsenceServiceImpl(absenceRepository);
  }

  @Test
  void createNewAbsenceShouldSaveAbsenceAndCreateNewId() {
    Absence givenAbsence = getDefaultAbsence();
    Absence expected = getDefaultAbsence();
    expected.setId(1L);

    when(absenceRepository.save(givenAbsence)).thenReturn(expected);

    Absence actual = underTest.createOrUpdateAbsence(givenAbsence);

    assertThat(actual).isEqualTo(expected);
    assertThat(actual.getId()).isNotNull();
    assertThat(actual.getId()).isEqualTo(expected.getId());
  }

}