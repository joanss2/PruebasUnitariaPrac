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

    private static class HNSDoble implements HealthNationalService {

        private static List<ProductSpecification> searchResults = new ArrayList<>();

       /* public void add_product_to_catalog(ProductSpecification product){                     //MIRAR SI IMPLEMENTAR O NO
            if(!cataleg.contains(product)){
                cataleg.add(product);
            }
        }

        public void add_register(HealthCardID id, MedicalPrescription medLine){
            if(!Database.containsKey(id)){
                Database.put(id,medLine);
            }
        }*/


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

    private static class ScheduledAgendaDoble implements ScheduledVisitAgenda {

        public List<HealthCardID> listPacients;

        public ScheduledAgendaDoble() {
            listPacients = new ArrayList<>();
        }

        @Override
        public boolean empty_agenda(){
            return listPacients.isEmpty();
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
    public void setUp() throws FormatException, IncorrectEndingDateException {
        Database = new HashMap<>();
        cataleg = new ArrayList<>();
        visites = new ScheduledAgendaDoble();
        Health = new HNSDoble();
        consultationTerminal = new ConsultationTerminal();
        consultationTerminal.VisitesProgramades = visites;
        consultationTerminal.HNS = Health;
        setVisites();
        setCataleg();
        setDatabase();
    }

    @Test
    public void setVisites() throws FormatException {
        visites.add_pacient(new HealthCardID("BBBBBBBBSI123456111111111111"));
        visites.add_pacient(new HealthCardID("BBBBBBBBSI000345111181111111"));
        visites.add_pacient(new HealthCardID("BBBBBBBBSI123226111111111011"));
        visites.add_pacient(new HealthCardID("BBBBBBBBRR456111111149311111"));
        visites.add_pacient(new HealthCardID("BBBBBBBBXI123006111111111111"));
    }

    //CREAR BASE DE DADES HNS

    @Deprecated
    public void setDatabase() throws FormatException, IncorrectEndingDateException {
        Database.put(new HealthCardID("BBBBBBBBSI123456111111111111"), new MedicalPrescription(1,
                new Date(2021 - 1900, Calendar.JANUARY, 1),
                new Date(2021 - 1900, Calendar.JANUARY, 10)));
        Database.put(new HealthCardID("BBBBBBBBSI000345111181111111"), new MedicalPrescription(1,
                new Date(2021 - 1900, Calendar.JANUARY, 1),
                new Date(2021 - 1900, Calendar.FEBRUARY, 1)));
        Database.put(new HealthCardID("BBBBBBBBSI123226111111111011"), new MedicalPrescription(1,
                new Date(2021 - 1900, Calendar.FEBRUARY, 1),
                new Date(2021 - 1900, Calendar.MARCH, 1)));
        Database.put(new HealthCardID("BBBBBBBBRR456111111149311111"), new MedicalPrescription(1,
                new Date(2021 - 1900, Calendar.MARCH, 1),
                new Date(2021 - 1900, Calendar.MARCH, 15)));
        Database.put(new HealthCardID("BBBBBBBBXI123006111111111111"), new MedicalPrescription(1,
                new Date(2021 - 1900, Calendar.APRIL, 1),
                new Date(2021 - 1900, Calendar.MAY, 1)));
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
        consultationTerminal.initRevision();
        assertEquals(consultationTerminal.getPacient(), new HealthCardID("BBBBBBBBSI000345111181111111"));
        consultationTerminal.initRevision();
        assertEquals(consultationTerminal.getPacient(), new HealthCardID("BBBBBBBBSI123226111111111011"));
        consultationTerminal.initRevision();
        consultationTerminal.initRevision();
        if(visites.empty_agenda())
            System.out.println("Empty agenda, no revisions available");


        //S'HAURIA D'HAVER COMPROBAT A INITREVISION QUE L'AGENDA DE LA QUAL VOLÍEM COMENÇAR SUPERVISIONS NO ESTAVA BUIDA
        //no hem pogut accedir a la llista que creem a la classe estatica del test.
        //hem fet la trampa per poder comprobar que si es buida no agafa pacients nomes al test
    }

    @Test
    void initPrescriptionEdition() throws HealthCardException, ConnectException, NotValidePrescriptionException, FormatException, NotFinishedTreatmentException, AnyCurrentPrescriptionException {
        consultationTerminal.initRevision();
        consultationTerminal.initPrescriptionEdition();
        consultationTerminal.initRevision();
        NotFinishedTreatmentException thrown = assertThrows(NotFinishedTreatmentException.class, () -> consultationTerminal.initPrescriptionEdition(),
                "Medical prescription is in progress");
        assertTrue(thrown.getMessage().contains("Medical prescription is in progress"));
    }


    @Test
    void searchForProducts() {


    }
    /*
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
    */


}