package medicalConsultion;

public class ProductNotInPrescription extends Exception {

    public ProductNotInPrescription(String errorMessage) {
        super(errorMessage);
    }
}
