package medicalConsultion;

public class IncorrectTakingGuidelinesException extends Exception {
    public IncorrectTakingGuidelinesException(String errorMessage) {
        super(errorMessage);
    }
}
