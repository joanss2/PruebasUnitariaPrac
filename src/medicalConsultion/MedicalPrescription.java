package medicalConsultion;

import Exceptions.IncorrectTakingGuidelinesException;
import Exceptions.ProductNotInPrescription;
import Exceptions.ProductAlreadyInPrescription;
import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import services.ScheduledVisitAgenda;

import java.util.*;


public class MedicalPrescription {// A class that represents medical prescription
    private int prescCode;
    private Date prescDate;
    private Date endDate;
    private HealthCardID hcID; // the healthcard ID of the patient
    private DigitalSignature eSign; // the eSignature of the doctor
    private List<MedicinePrescriptionLine> liniesDePrescripcio;
    private ScheduledVisitAgenda agenda;


    // Its components, that is, the set of medical prescription lines
    public MedicalPrescription(int prescCode , Date prescDate, Date endDate) {

        this.prescCode = prescCode;
        this.prescDate = prescDate;
        this.endDate = endDate;
        liniesDePrescripcio = new ArrayList<>();
        this.hcID = agenda.getHealthCardID();///????

    }

    public TakingGuideline fromString(String[] instruc) {

        dayMoment nou = dayMoment.valueOf(instruc[0]);
        float duracio = Float.parseFloat(instruc[1]);
        String comentari = instruc[2];
        float dose = Float.parseFloat(instruc[3]);
        float freq = Float.parseFloat(instruc[4]);
        FqUnit frqUnit = FqUnit.valueOf(instruc[5]);
        return new TakingGuideline(nou, duracio, comentari, dose, freq, frqUnit);

    }


    public void addLine(ProductID prodID, String[] instruc) throws ProductAlreadyInPrescription, IncorrectTakingGuidelinesException {
        if (!validInstruc(instruc)) {
            throw new IncorrectTakingGuidelinesException("Erroneous format of posology or instructions");
        }
        if (liniesDePrescripcio.contains(new MedicinePrescriptionLine(prodID, fromString(instruc)))) {
            throw new ProductAlreadyInPrescription("The product with ID:" + prodID + "is already in the prescription");
        }
        liniesDePrescripcio.add(new MedicinePrescriptionLine(prodID, fromString(instruc)));
    }


    public void modifyLine(ProductID prodID, String[] instruc) throws ProductNotInPrescription, ProductAlreadyInPrescription, IncorrectTakingGuidelinesException {
        int index;
        if ((index = listContainsKey(liniesDePrescripcio, prodID)) != -1) {
            liniesDePrescripcio.remove(index);
            addLine(prodID, instruc);
        }
        throw new ProductNotInPrescription("Product is not in the lines of the prescription");
    }


    public void removeLine(ProductID prodID) throws ProductNotInPrescription {
        int index;
        if ((index = listContainsKey(liniesDePrescripcio, prodID)) != -1)
            liniesDePrescripcio.remove(index);
        else
            throw new ProductNotInPrescription("Product is not in the lines of the prescription");

    }


    public int listContainsKey(List<MedicinePrescriptionLine> llista, ProductID id) {
        for (MedicinePrescriptionLine medicinePrescriptionLine : llista) {
            if (medicinePrescriptionLine.getId().equals(id))
                return llista.indexOf(medicinePrescriptionLine);
        }
        return -1;
    }

    public boolean validInstruc(String[] pautes) {
        if (pautes.length != 6)
            return false;
        boolean bool = false;
        for (dayMoment day : dayMoment.values()) {
            if (day.name().equals(pautes[0])) {
                bool = true;
                break;
            }
        }
        if (!bool)
            return false;
        try {
            Float.parseFloat(pautes[1]);
            Float.parseFloat(pautes[3]);
            Float.parseFloat(pautes[4]);
        } catch (NumberFormatException e) {
            return false;
            //throw new NumberFormatException("Duration is not a float type");
        }

        bool = false;
        for (FqUnit unit : FqUnit.values()) {
            if (unit.name().equals(pautes[5])) {
                bool = true;
                break;
            }
        }
        return bool;
    }


    // getters and setters
    public Date getPrescDate() {
        return this.prescDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public int getPrescCode() {
        return this.prescCode;
    }

    public void setPrescCode(int newPrescCode) {
        this.prescCode = newPrescCode;
    }

    public void setPrescDate(Date newPrescDate) {
        this.prescDate = newPrescDate;
    }

    public void setEndDate(Date newEndDate) {
        this.endDate = newEndDate;
    }

    public HealthCardID getHcID() {
        return this.hcID;
    }

    public DigitalSignature geteSign() {
        return this.eSign;
    }

}