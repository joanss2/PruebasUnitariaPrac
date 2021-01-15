package services;

import Exceptions.*;
import data.HealthCardID;
import medicalConsultion.MedicalPrescription;
import medicalConsultion.MedicinePrescriptionLine;
import medicalConsultion.ProductSpecification;
import medicalConsultion.dayMoment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ConsultationTerminalTest {


    public static Map<HealthCardID,MedicalPrescription> Database;
    public static List<ProductSpecification> cataleg;
    ScheduledVisitAgenda visites;
    HealthNationalService Health;

    @BeforeEach
    public void setUp(){
        Database = new HashMap<>();
        cataleg = new ArrayList<>();
        visites = new ScheduledAgendaDoble();
        Health = new HNSdoble();
    }

    @BeforeEach
    public void setVisites() throws FormatException {
        visites.add_pacient(new HealthCardID("BBBBBBBBSI123456111111111111"));
        visites.add_pacient(new HealthCardID("BBBBBBBBSI000345111181111111"));
        visites.add_pacient(new HealthCardID("BBBBBBBBSI123226111111111011"));
        visites.add_pacient(new HealthCardID("BBBBBBBBRR456111111149311111"));
        visites.add_pacient(new HealthCardID("BBBBBBBBXI123006111111111111"));
        System.out.println();
    }

    //CREAR BASE DE DADES HNS
    @BeforeEach
    public void setDatabase(){

    }


    private MedicalPrescription actual;

    private static class HNSdoble implements HealthNationalService{

        static List<ProductSpecification> searchResults = new ArrayList<>();

        public void add_product_to_catalog(ProductSpecification product){
            if(!cataleg.contains(product)){
                cataleg.add(product);
            }
        }

        public void add_register(HealthCardID id, MedicalPrescription medLine){
            if(!Database.containsKey(id)){
                Database.put(id,medLine);
            }
        }

        @Override
        public MedicalPrescription getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException {
            return Database.get(hcID);
        }

        @Override
        public List<ProductSpecification> getProductsByKW(String keyWord) throws AnyKeyWordMedicineException, ConnectException {
            List<ProductSpecification> result = new ArrayList<>();
            for (ProductSpecification pr : cataleg) {
                if (pr.getId().getProductID().contains(keyWord))
                    result.add(pr);
            }
            return result;
        }

        @Override
        public ProductSpecification getProductSpecific(int opt) throws AnyMedicineSearchException, ConnectException {

        }

        @Override
        public MedicalPrescription sendePrescription(MedicalPrescription ePresc) throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription {
            return null;
        }
    }

    public static class ScheduledAgendaDoble implements ScheduledVisitAgenda{

        public List<HealthCardID> listPacients = new ArrayList<>();

        @Override
        public void add_pacient(HealthCardID nouID) {
            listPacients.add(nouID);
        }

        @Override
        public HealthCardID getHealthCardID() {
            return listPacients.remove(0);
        }
    }

    @Test
    void initRevision() {
    }

    @Test
    void initPrescriptionEdition() {
    }

    @Test
    void searchForProducts() {
    }

    @Test
    void selectProduct() {
    }

    @Test
    void enterMedicineGuidelines() {
    }

    @Test
    void enterTreatmentEndingDate() {
    }

    @Test
    void sendePrescription() {
    }

    @Test
    void printePresc() {
    }
}