package de.kirill.minervaabsence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class AbsenceControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private AbsenceService absenceService;

  @Test
  public void getAbsenceShouldReturn401WithoutAuthenticatedUser() throws Exception {
    this.mockMvc.perform(get("/absence"))
        .andDo(print())
        .andExpect(status().isUnauthorized());
  }

  @WithMockUser("spring")
  @Test
  public void getAbsenceShouldReturn403WithoutAdminRole() throws Exception {
    this.mockMvc.perform(get("/absence"))
        .andDo(print())
        .andExpect(status().isForbidden());
  }

  @WithMockUser(value = "spring", roles = {"ADMIN"})
  @Test
  public void getAbsenceShouldReturnDefaultAbsence() throws Exception {
    List<Absence> expected = new ArrayList<>();
    expected.add(getDefaultAbsence());
    when(absenceService.getAllAbsence()).thenReturn(expected);

    String body = this.mockMvc.perform(get("/absence"))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    List<Absence> actual = objectMapper.readValue(body, new TypeReference<>() {});
    assertThat(actual).isEqualTo(expected);
  }

  @WithMockUser(value = "spring", roles = {"ADMIN"})
  @Test
  public void getAbsenceShouldReturn2DefaultAbsence() throws Exception {
    List<Absence> expected = new ArrayList<>();
    expected.add(getDefaultAbsence());
    expected.add(getDefaultAbsence());
    when(absenceService.getAllAbsence()).thenReturn(expected);

    String body = this.mockMvc.perform(get("/absence"))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    List<Absence> actual = objectMapper.readValue(body, new TypeReference<>() {});
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void postAbsenceShouldReturn401WithoutAuthenticatedUser() throws Exception {
    Absence givenAbsence = getDefaultAbsence();
    this.mockMvc.perform(post("/absence").with(csrf())
            .content(objectMapper.writeValueAsString(givenAbsence)))
        .andDo(print())
        .andExpect(status().isUnauthorized());
  }

  @WithMockUser(roles = "OTHERROLE")
  @Test
  void postAbsenceShouldReturn403WithoutUserRole() throws Exception {
    Absence givenAbsence = getDefaultAbsence();
    this.mockMvc.perform(post("/absence").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(givenAbsence)))
        .andDo(print())
        .andExpect(status().isForbidden());
  }

  @WithMockUser(value = "spring", roles = {"USER"})
  @Test
  void postAbsenceShouldReturnAbsenceWithUserRole() throws Exception {
    Absence givenAbsence = getDefaultAbsence();
    when(absenceService.createNewAbsence(givenAbsence)).thenReturn(givenAbsence);

    String body = this.mockMvc.perform(post("/absence").with(csrf())
            .content(objectMapper.writeValueAsString(givenAbsence))
            .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated())
        .andReturn()
        .getResponse()
        .getContentAsString();

    Absence actual = objectMapper.readValue(body, new TypeReference<>() {});
    assertThat(actual).isEqualTo(givenAbsence);
  }

  @WithMockUser("spring")
  @Test
  void postAbsenceShouldReturnAbsenceWithNewId() throws Exception {
    Absence givenAbsence = getDefaultAbsence();
    Absence expected = getDefaultAbsence();
    expected.setId(123L);

    when(absenceService.createNewAbsence(givenAbsence)).thenReturn(expected);

    String body = this.mockMvc.perform(post("/absence").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(givenAbsence)))
        .andDo(print())
        .andExpect(status().isCreated())
        .andReturn()
        .getResponse()
        .getContentAsString();

    Absence actual = objectMapper.readValue(body, new TypeReference<>() {});

    assertThat(actual).isEqualTo(expected);
    assertThat(actual.getId()).isEqualTo(expected.getId());
  }

  private Absence getDefaultAbsence() {
    Absence expected = new Absence();
    expected.setName("UNIT");
    expected.setFromDate(LocalDateTime.of(2022, 1, 1, 0, 0, 0));
    expected.setToDate(LocalDateTime.of(2022, 1, 2, 0, 0, 0));
    return expected;
  }

}