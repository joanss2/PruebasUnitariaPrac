package Exceptions;

public class IncorrectEndingDateException extends Exception {
    public IncorrectEndingDateException(String errorMessage) {
        super(errorMessage);
    }
}