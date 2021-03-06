package medicalConsultion;

import Exceptions.IncorrectTakingGuidelinesException;
import Exceptions.ProductNotInPrescription;
import Exceptions.ProductAlreadyInPrescription;
import data.DigitalSignature;
import data.ProductID;
import Exceptions.*;

import java.util.*;


public class MedicalPrescription {
    private int prescCode;
    private Date prescDate;
    private Date endDate;
    private final List<MedicinePrescriptionLine> liniesDePrescripcio;
    public DigitalSignature eSign;


    public MedicalPrescription(int prescCode, Date prescDate, Date endDate) throws IncorrectEndingDateException {
        this.prescCode = prescCode;
        this.prescDate = prescDate;
        this.endDate = endDate;

        if (this.prescDate.after(this.endDate))                                                 // Comprove that the date is correct
            throw new IncorrectEndingDateException("End date is after prescription date");
        liniesDePrescripcio = new ArrayList<>();
    }

    public TakingGuideline fromString(String[] instruc) {                                   // Function that pass an instruction with string format to TakingGuideline object
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
        if (listContainsKey(liniesDePrescripcio, prodID) != -1) {
            throw new ProductAlreadyInPrescription("The product with ID:" + prodID + "is already in the prescription");
        }
        liniesDePrescripcio.add(new MedicinePrescriptionLine(prodID, fromString(instruc)));
    }

    public void modifyLine(ProductID prodID, String[] instruc) throws ProductNotInPrescription, ProductAlreadyInPrescription, IncorrectTakingGuidelinesException {
        int index;
        if ((index = listContainsKey(liniesDePrescripcio, prodID)) != -1) {
            liniesDePrescripcio.remove(index);
            addLine(prodID, instruc);
        } else {
            throw new ProductNotInPrescription("Product is not in the lines of the prescription");
        }
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

    public boolean validInstruc(String[] pautes) {                                              // Function witch comprove that the instructions are valid
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

    // Getters and setters
    public Date getPrescDate() {
        return this.prescDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public int getPrescCode() {
        return this.prescCode;
    }

    public List<MedicinePrescriptionLine> getMedicinePrescriptionLineList() {
        return liniesDePrescripcio;
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

    public void setSignature(DigitalSignature eSign) {
        this.eSign = eSign;
    }

}