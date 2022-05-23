package de.kirill.minervaabsence;

public class IdAlreadyTakenException extends RuntimeException {

  public IdAlreadyTakenException(String message) {
    super(message);
  }

}
