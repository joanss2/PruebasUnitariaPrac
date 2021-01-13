package Exceptions;

public class NotValidePrescriptionException extends Exception {
    public NotValidePrescriptionException(String errorMessage) {
        super(errorMessage);
    }
}
