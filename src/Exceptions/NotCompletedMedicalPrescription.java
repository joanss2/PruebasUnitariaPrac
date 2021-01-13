package Exceptions;

public class NotCompletedMedicalPrescription extends Exception {
    public NotCompletedMedicalPrescription(String errorMessage) {
        super(errorMessage);
    }
}