package de.kirill.minervaabsence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping(path = "absence/demo")
public class MinervaAbsenceApplication {

  @GetMapping
  public String ping() {
    return "Success";
  }

  public static void main(String[] args) {
    SpringApplication.run(MinervaAbsenceApplication.class, args);
  }

}
