package de.kirill.minervaabsence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.containsString;


@SpringBootTest
@AutoConfigureMockMvc
class AbsenceControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void getAbsenceShouldReturn401WithoutAuthenticatedUser() throws Exception {
    this.mockMvc.perform(get("/absence"))
        .andDo(print())
        .andExpect(status().isUnauthorized());
  }

  @WithMockUser("spring")
  @Test
  public void shouldReturnDefaultMessage() throws Exception {
    this.mockMvc.perform(get("/absence"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("absence")));
  }

}