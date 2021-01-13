package Exceptions;

public class NotValidePrescription extends Exception {
    public NotValidePrescription(String errorMessage) {
        super(errorMessage);
    }
}
