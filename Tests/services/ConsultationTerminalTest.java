package services;

import Exceptions.*;
import data.HealthCardID;
import data.ProductID;
import medicalConsultion.MedicalPrescription;
import medicalConsultion.MedicinePrescriptionLine;
import medicalConsultion.ProductSpecification;
import medicalConsultion.dayMoment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ConsultationTerminalTest {

    private static class HNSDoble implements HealthNationalService{

        private static List<ProductSpecification> searchResults = new ArrayList<>();

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
            //List<ProductSpecification> result = new ArrayList<>();
            for (ProductSpecification pr : cataleg) {
                if (pr.getId().getProductID().contains(keyWord))
                    //result.add(pr);
                    searchResults.add(pr);
            }
            //return result;
            return searchResults;
        }

        @Override
        public ProductSpecification getProductSpecific(int opt) throws AnyMedicineSearchException, ConnectException {
            return searchResults.get(opt);
        }

        @Override
        public MedicalPrescription sendePrescription(MedicalPrescription ePresc) throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription {
            return null;
        }
    }
    private static class ScheduledAgendaDoble implements ScheduledVisitAgenda{

        private List<HealthCardID> listPacients;

        public ScheduledAgendaDoble(){
            listPacients = new ArrayList<>();
        }

        @Override
        public void add_pacient(HealthCardID nouID) {
            listPacients.add(nouID);
        }

        @Override
        public HealthCardID getHealthCardID() {
            return listPacients.remove(0);
        }
    }

    public static Map<HealthCardID, MedicalPrescription> Database;
    public static List<ProductSpecification> cataleg;
    ScheduledVisitAgenda visites;
    HealthNationalService Health;
    ConsultationTerminal consultationTerminal;

    @BeforeEach
    public void setUp() throws FormatException {
        Database = new HashMap<>();
        cataleg = new ArrayList<>();
        visites = new ScheduledAgendaDoble();
        Health = new HNSDoble();
        consultationTerminal = new ConsultationTerminal();
        setVisites();
        setCataleg();
        //setDatabase();
    }

    public void setVisites() throws FormatException {
        visites.add_pacient(new HealthCardID("BBBBBBBBSI123456111111111111"));
        visites.add_pacient(new HealthCardID("BBBBBBBBSI000345111181111111"));
        visites.add_pacient(new HealthCardID("BBBBBBBBSI123226111111111011"));
        visites.add_pacient(new HealthCardID("BBBBBBBBRR456111111149311111"));
        visites.add_pacient(new HealthCardID("BBBBBBBBXI123006111111111111"));
        //System.out.println(visites);
    }

    //CREAR BASE DE DADES HNS

    @Deprecated
    public void setDatabase() throws FormatException {
        Database.put(new HealthCardID("BBBBBBBBSI123456111111111111"), new MedicalPrescription(1,
                new Date(2021, Calendar.JANUARY,1),
                new Date(2021, Calendar.JANUARY,10)));
        Database.put(new HealthCardID("BBBBBBBBSI000345111181111111"), new MedicalPrescription(1,
                new Date(2021, Calendar.JANUARY,1),
                new Date(2021, Calendar.FEBRUARY,1)));
        Database.put(new HealthCardID("BBBBBBBBSI123226111111111011"), new MedicalPrescription(1,
                new Date(2021, Calendar.FEBRUARY,1),
                new Date(2021, Calendar.MARCH,1)));
        Database.put(new HealthCardID("BBBBBBBBRR456111111149311111"), new MedicalPrescription(1,
                new Date(2021, Calendar.MARCH,1),
                new Date(2021, Calendar.MARCH,15)));
        Database.put(new HealthCardID("BBBBBBBBXI123006111111111111"), new MedicalPrescription(1,
                new Date(2021, Calendar.APRIL,1),
                new Date(2021, Calendar.MAY,1)));
    }

    //CREAR CATÀLEG PRODUCTES

    public void setCataleg() throws FormatException {
        cataleg.add(new ProductSpecification(new ProductID("ABCDE"), new BigDecimal("13.25"),
                "Método anticonceptivo"));
        cataleg.add(new ProductSpecification(new ProductID("VIAGR"), new BigDecimal("19.99"),
                "Antinflamatorio"));
        cataleg.add(new ProductSpecification(new ProductID("INTEL"), new BigDecimal("15.00"),
                "Crema"));
        cataleg.add(new ProductSpecification(new ProductID("DUREX"), new BigDecimal("9.25"),
                "Para la piel atópica"));
        cataleg.add(new ProductSpecification(new ProductID("MONTS"), new BigDecimal("25.99"),
                "No consumir más de una vez al dia"));
        cataleg.add(new ProductSpecification(new ProductID("BBSIT"), new BigDecimal("5.99"),
                "Supositorio"));
        cataleg.add(new ProductSpecification(new ProductID("DALSY"), new BigDecimal("8.75"),
                "Antidepresivo"));
        cataleg.add(new ProductSpecification(new ProductID("PROFE"), new BigDecimal("6.99"),
                "Derivado de la morfina"));
    }

    @Test
    void initRevision() throws HealthCardException, ConnectException, NotValidePrescriptionException, FormatException {
        consultationTerminal.initRevision();
        assertEquals(consultationTerminal.getPacient(), new HealthCardID("BBBBBBBBSI123456111111111111"));
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