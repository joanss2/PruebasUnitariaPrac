package services; // Package for involved services

import data.HealthCardID;
import medicalConsultion.MedicalPrescription;
import Exceptions.*;
import medicalConsultion.ProductSpecification;

import java.net.ConnectException;
import java.util.List;

/**
 * External service for managing and storing ePrescriptions from population
 */

public interface HealthNationalService {                                    // All of this methods are implemented in  medicalConsultionTest
    MedicalPrescription getePrescription(HealthCardID hcID)
            throws HealthCardException, NotValidePrescriptionException,
            ConnectException;

    List<ProductSpecification> getProductsByKW(String keyWord)
            throws AnyKeyWordMedicineException, ConnectException;

    ProductSpecification getProductSpecific(int opt)
            throws AnyMedicineSearchException, ConnectException;

    MedicalPrescription sendePrescription(MedicalPrescription ePresc)
            throws ConnectException, NotValidePrescription, eSignatureException,
            NotCompletedMedicalPrescription;
}
