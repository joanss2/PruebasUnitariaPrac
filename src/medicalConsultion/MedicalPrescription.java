package medicalConsultion;
import Exceptions.*;
import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class MedicalPrescription {// A class that represents medical prescription
    private int prescCode;
    private Date prescDate;
    private Date endDate;
    private HealthCardID hcID; // the healthcard ID of the patient
    private DigitalSignature eSign; // the eSignature of the doctor
    private HashMap<ProductID, String[]> liniesDePrescripcio = new HashMap<>();


    // Its components, that is, the set of medical prescription lines
    @Deprecated
    public MedicalPrescription() {

        this.prescCode = 10;
        this.prescDate = new Date();
        this.endDate = new Date(2021, this.prescDate.getMonth() + 3, 7);

    } // Makes some inicialization


    public void addLine(ProductID prodID, String[] instruc) {
        liniesDePrescripcio.put(prodID, instruc);
    }


    public void modifyLine(ProductID prodID, String[] instruc) throws ProductNotInPrescription, IncorrectTakingGuidelinesException {
        if (!liniesDePrescripcio.containsKey(prodID))
            throw new ProductNotInPrescription("Product is not in the prescription lines");
        else if (!validInstruc(instruc))
            throw new IncorrectTakingGuidelinesException("Erroneous format of posology or instructions");
        liniesDePrescripcio.replace(prodID, instruc);
    }


    public void removeLine(ProductID prodID) throws ProductNotInPrescription, IncorrectTakingGuidelinesException {
        if (!liniesDePrescripcio.containsKey(prodID))
            throw new ProductNotInPrescription("Product is not in Linies de prescripcio");
        liniesDePrescripcio.remove(prodID);
    }

    public boolean validInstruc(String[] pautes) {
        boolean hola = false;
        for (dayMoment day : dayMoment.values()) {
            if (day.name().equals(pautes[0])) {
                hola = true;
                break;
            }
        }

        if (!hola)
            return false;

        try {
            Float.parseFloat(pautes[1]);
            Float.parseFloat(pautes[3]);
            Float.parseFloat(pautes[4]);
        } catch (NumberFormatException e) {
            return false;
            //throw new NumberFormatException("Duration is not a float type");
        }

        hola = false;
        for (FqUnit unit : FqUnit.values()) {
            if (unit.name().equals(pautes[5])) {
                hola = true;
                break;
            }
        }
        return hola;
    }

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


    // the getters and setters
}
