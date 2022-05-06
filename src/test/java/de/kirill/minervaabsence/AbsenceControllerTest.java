package de.kirill.minervaabsence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class AbsenceControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void getAbsenceShouldReturn401WithoutAuthenticatedUser() throws Exception {
    this.mockMvc.perform(get("/absence"))
        .andDo(print())
        .andExpect(status().isUnauthorized());
  }

  @WithMockUser("spring")
  @Test
  public void getAbsenceShouldReturnDefaultAbsence() throws Exception {
    List<Absence> expected = new ArrayList<>();
    expected.add(getDefaultAbsence());

    String body = this.mockMvc.perform(get("/absence"))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    List<Absence> actual = objectMapper.readValue(body, new TypeReference<>() {});
    assertThat(actual).isEqualTo(expected);
  }

  @WithMockUser("spring")
  @Test
  public void getAbsenceShouldReturnDefaultAbsence() throws Exception {
    List<Absence> expected = new ArrayList<>();
    expected.add(getDefaultAbsence());

    String body = this.mockMvc.perform(get("/absence"))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    List<Absence> actual = objectMapper.readValue(body, new TypeReference<>() {});
    assertThat(actual).isEqualTo(expected);
  }

  private Absence getDefaultAbsence() {
    Absence expected = new Absence();
    expected.setName("UNIT");
    expected.setFrom(LocalDateTime.of(2022, 1, 1, 0, 0, 0));
    expected.setTo(LocalDateTime.of(2022, 1, 2, 0, 0, 0));
    return expected;
  }

}