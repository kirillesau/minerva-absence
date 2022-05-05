package de.kirill.minervaabsence;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("absence")
public class AbsenceController {

  @GetMapping
  String getAbsence() {
    return "absence";
  }

}
