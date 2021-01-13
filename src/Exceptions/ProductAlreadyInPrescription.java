package Exceptions;

public class ProductAlreadyInPrescription extends Exception {
    public ProductAlreadyInPrescription(String errorMessage) {
        super(errorMessage);
    }
}
