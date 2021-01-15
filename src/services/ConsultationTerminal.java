package services;

import Exceptions.*;
import data.DigitalSignature;
import data.HealthCardID;
import medicalConsultion.*;

import java.net.ConnectException;
import java.util.Date;
import java.util.List;

public class ConsultationTerminal {

    private HealthNationalService HNS;
    private ScheduledVisitAgenda VisitesProgramades;
    private DigitalSignature eSign;
    private HealthCardID pacient;
    private MedicalPrescription medicalPrescription;
    private List<ProductSpecification> busqueda;
    private ProductSpecification medicament;


    public ConsultationTerminal(HealthNationalService HNS, ScheduledVisitAgenda VisitesProgramades, DigitalSignature eSign) {
        this.HNS = HNS;
        this.VisitesProgramades = VisitesProgramades;
        this.eSign = eSign;
    }


    public void initRevision() throws
            NotValidePrescriptionException, ConnectException, FormatException, HealthCardException {

        pacient = VisitesProgramades.getHealthCardID();
        if (!pacient.isFormatValid(pacient.getPersonalID()))
            throw new FormatException("PersonalID code from HealthCardID is invalid");      // HealthCardException --> FormatException that we use in other classes

        medicalPrescription = HNS.getePrescription(pacient);
        if (medicalPrescription == null)
            throw new NotValidePrescriptionException("Patient does not have prescriptions assigned");
    }

    public void initPrescriptionEdition() throws
            AnyCurrentPrescriptionException, NotFinishedTreatmentException {
        if (medicalPrescription == null)
            throw new AnyCurrentPrescriptionException("There is no medicalPrescription currently");

        if (medicalPrescription.getEndDate().after(new Date()))
            throw new NotFinishedTreatmentException("Medical prescription is in progress");
    }

    public void searchForProducts(String keyWord) throws
            AnyKeyWordMedicineException, ConnectException {
        busqueda = HNS.getProductsByKW(keyWord);
        if (busqueda.isEmpty())
            throw new AnyKeyWordMedicineException("Results not found in the list returned by HNS");
    }

    public void selectProduct(int option) throws
            AnyMedicineSearchException, ConnectException {
        if (busqueda == null)
            throw new AnyMedicineSearchException("Search has not been started");
        if (option <= busqueda.size())                   // The doctor chooses an option between 1 and busqueda size
            medicament = busqueda.get(option - 1);
    }


    public void enterMedicineGuidelines(String[] instruc) throws
            AnySelectedMedicineException, IncorrectTakingGuidelinesException, ProductAlreadyInPrescription {
        if (medicament.getId() == null)
            throw new AnySelectedMedicineException("No Medicine selected");
        medicalPrescription.addLine(medicament.getId(), instruc);
    }

    public void enterTreatmentEndingDate(Date date) throws IncorrectEndingDateException {
        if (date == null || date.before(medicalPrescription.getPrescDate()))
            throw new IncorrectEndingDateException("End date comes before PrescDate");
        medicalPrescription.setPrescDate(new Date());
        medicalPrescription.setEndDate(date);
    }


    public void sendePrescription() throws ConnectException,
            NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription {

        if (medicalPrescription == null)
            throw new NotValidePrescription("HNS cannot validate prescription");
        if (eSign == null)
            throw new eSignatureException("No signature");

        HNS.sendePrescription(medicalPrescription);
    }

    public void printePresc() throws printingException {
        if (medicalPrescription == null) {
            throw new printingException("No prescription object");
        }
        System.out.println(medicalPrescription.toString());

    }
    /*public DigitalSignature geteSign(){
        return this.eSign;
    }*/
    // Other methods, apart from the input events}
}