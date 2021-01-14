package services;
import Exceptions.*;
import data.DigitalSignature;
import data.HealthCardID;
import medicalConsultion.*;

import java.net.ConnectException;
import java.util.Date;
import java.util.List;

public class ConsultationTerminal {

    public static ScheduledVisitAgenda VisitesProgramades;
    HealthCardID pacient;
    DigitalSignature eSign;
    MedicalPrescription medicalPrescription;
    List<ProductSpecification> busqueda;
    ProductSpecification medicament;

    public ConsultationTerminal(){
        VisitesProgramades = new ScheduledVisitAgenda();
    }


    private HealthNationalService HNS;

    public void initRevision() throws HealthCardException,
            NotValidePrescriptionException, ConnectException, FormatException {

        pacient = VisitesProgramades.getHealthCardID();
        if(pacient.isFormatValid(pacient.getPersonalID()))
            throw new FormatException("PersonalID code from HealthCardID is invalid");
        /*if("PACIENT NOT IN HNS")
            throw new HealthCardException("Patient not in HealthNationalService");*/
        if(medicalPrescription.getPrescCode()==0)
            throw new NotValidePrescriptionException("Patient does not have prescriptions assigned");



    }

    public void initPrescriptionEdition() throws
            AnyCurrentPrescriptionException,NotFinishedTreatmentException {
        if(medicalPrescription == null)
            throw new AnyCurrentPrescriptionException("There is no medicalPrescription currently");
        if(medicalPrescription.getEndDate().after(new Date()))
            throw new NotFinishedTreatmentException("Medical prescription already over");
    }

    public void searchForProducts(String keyWord) throws
            AnyKeyWordMedicineException,ConnectException{
        busqueda = HNS.getProductsByKW(keyWord);
        if(busqueda.isEmpty())
            throw new AnyKeyWordMedicineException("Results not found");

    }

    public void selectProduct(int option) throws
            AnyMedicineSearchException,ConnectException {
        if(busqueda == null)
            throw new AnyMedicineSearchException("Search has not been started");
        medicament = busqueda.get(option);
    }


    public void enterMedicineGuidelines(String[] instruc) throws
            AnySelectedMedicineException, IncorrectTakingGuidelinesException, ProductAlreadyInPrescription {
        if(medicament.getId() == null)
            throw new AnySelectedMedicineException("No Medicine selected");
        medicalPrescription.addLine(medicament.getId(),instruc);
    }

    public void enterTreatmentEndingDate(Date date) throws IncorrectEndingDateException{
        if(!date.after(medicalPrescription.getPrescDate()))
            throw new IncorrectEndingDateException("End date comes before PrescDate");
        medicalPrescription.setPrescDate(new Date());
        medicalPrescription.setEndDate(date);
    }


    public void sendePrescription() throws ConnectException,
            NotValidePrescription,eSignatureException,NotCompletedMedicalPrescription{

        if(medicalPrescription.getPrescCode()==0)
            throw new NotValidePrescription("HNS cannot validate prescription");
        HNS.sendePrescription(medicalPrescription);

    }

    public void printePresc() throws printingException{
        System.out.println("IMPRIMO LA PRESCRIPCION");

    }
    public DigitalSignature geteSign(){
        return this.eSign;
    }
    // Other methods, apart from the input events}
}