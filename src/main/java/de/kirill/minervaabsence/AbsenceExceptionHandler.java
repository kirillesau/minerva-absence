package de.kirill.minervaabsence;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AbsenceExceptionHandler {

  @ExceptionHandler(WrongUserIdException.class)
  public ResponseEntity<String> wrongUserErrorHandler(WrongUserIdException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
  }
}
