package services;

import Exceptions.*;
import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import medicalConsultion.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ConsultationTerminalTest {

    private static class HNSDoble implements HealthNationalService {                                                // HSN doble implementation

        private static List<ProductSpecification> searchResults = new ArrayList<>();

        @Override
        public MedicalPrescription getePrescription(HealthCardID hcID){
            return Database.get(hcID);
        }

        @Override
        public List<ProductSpecification> getProductsByKW(String keyWord){
            for (ProductSpecification pr : cataleg) {
                if (pr.getId().getProductID().contains(keyWord))
                    searchResults.add(pr);
            }
            //return result;
            return searchResults;
        }

        @Override
        public ProductSpecification getProductSpecific(int opt) {
            return searchResults.get(opt - 1);
        }

        @Override
        public MedicalPrescription sendePrescription(MedicalPrescription ePresc){
            return ePresc;
        }
    }

    private static class ScheduledAgendaDoble implements ScheduledVisitAgenda {                                        // ScheduledVisitAgenda doble implementation

        public List<HealthCardID> listPacients;

        public ScheduledAgendaDoble() {
            listPacients = new ArrayList<>();
        }

        @Override
        public boolean emptyAgenda() {
            return listPacients.isEmpty();
        }

        @Override
        public void addPacient(HealthCardID nouID) {

            listPacients.add(nouID);
        }

        @Override
        public HealthCardID getHealthCardID() {
            return listPacients.remove(0);
        }
    }

    public static Map<HealthCardID, MedicalPrescription> Database;                                      // Declaration
    public static List<ProductSpecification> cataleg;
    ScheduledVisitAgenda visites;
    HealthNationalService Health;
    ConsultationTerminal consultationTerminal;

    @BeforeEach
    public void setUp() throws FormatException, IncorrectEndingDateException {                                  // Inicialization
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
    public void setVisites() throws FormatException {                                                       // Visites list
        visites.addPacient(new HealthCardID("BBBBBBBBSI123456111111111111"));
        visites.addPacient(new HealthCardID("BBBBBBBBSI000345111181111111"));
        visites.addPacient(new HealthCardID("BBBBBBBBSI123226111111111011"));
        visites.addPacient(new HealthCardID("BBBBBBBBRR456111111149311111"));
        visites.addPacient(new HealthCardID("BBBBBBBBXI123006111111111111"));
    }


    @Deprecated
    public void setDatabase() throws FormatException, IncorrectEndingDateException {                                            // DataBase patients
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


    public void setCataleg() throws FormatException {                                                               // Product Catalog
        cataleg.add(new ProductSpecification(new ProductID("ABCDE"), new BigDecimal("13.25"),
                "Método anticonceptivo"));
        cataleg.add(new ProductSpecification(new ProductID("VIAGR"), new BigDecimal("19.99"),
                "Antinflamatorio"));
        cataleg.add(new ProductSpecification(new ProductID("INTEL"), new BigDecimal("15.00"),
                "Crema"));
        cataleg.add(new ProductSpecification(new ProductID("DAREX"), new BigDecimal("9.25"),
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
        assertTrue(consultationTerminal.VisitesProgramades.emptyAgenda());
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
    void searchForProducts() throws AnyKeyWordMedicineException, ConnectException, FormatException {
        consultationTerminal.searchForProducts("DA");
        List<ProductSpecification> search = new ArrayList<>();
        search.add(new ProductSpecification(new ProductID("DAREX"), new BigDecimal("9.25"),
                "Para la piel atópica"));
        search.add(new ProductSpecification(new ProductID("DALSY"), new BigDecimal("8.75"),
                "Antidepresivo"));

        assertEquals(consultationTerminal.busqueda, search);
        AnyKeyWordMedicineException thrown = assertThrows(AnyKeyWordMedicineException.class, () -> consultationTerminal.searchForProducts("KOALA"),
                "Results not found in the list returned by HNS");
        assertTrue(thrown.getMessage().contains("Results not found in the list returned by HNS"));
    }

    @Test
    void selectProduct() throws AnyMedicineSearchException, ConnectException, AnyKeyWordMedicineException, FormatException {
        AnyMedicineSearchException thrown = assertThrows(AnyMedicineSearchException.class, () -> consultationTerminal.selectProduct(1),
                "Search has not been started");
        assertTrue(thrown.getMessage().contains("Search has not been started"));

        assertNull(consultationTerminal.busqueda);

        consultationTerminal.searchForProducts("DALSY");
        consultationTerminal.selectProduct(1);
        assertEquals(new ProductSpecification(new ProductID("DALSY"), new BigDecimal("8.75"),
                "Antidepresivo"), consultationTerminal.medicament);
    }

    @Test
    void enterMedicineGuidelines() throws AnySelectedMedicineException, AnyKeyWordMedicineException, ConnectException,
            AnyMedicineSearchException, IncorrectTakingGuidelinesException, ProductAlreadyInPrescription, FormatException,
            NotValidePrescriptionException, HealthCardException, NotFinishedTreatmentException, AnyCurrentPrescriptionException {


        AnySelectedMedicineException thrown = assertThrows(AnySelectedMedicineException.class, () ->
                        consultationTerminal.enterMedicineGuidelines(new String[]{"AFTERLUNCH", "5.5f", "Después de la comida", "7.5f", "4f", "HOUR"}),
                "No Medicine selected");
        assertTrue(thrown.getMessage().contains("No Medicine selected"));
        consultationTerminal.initRevision();
        consultationTerminal.initPrescriptionEdition();
        consultationTerminal.searchForProducts("DALSY");
        consultationTerminal.selectProduct(1);
        List<MedicinePrescriptionLine> lines = new ArrayList<>();
        lines.add(new MedicinePrescriptionLine(new ProductID("DALSY"),
                consultationTerminal.medicalPrescription.fromString(new String[]{"AFTERLUNCH", "5.5f", "Después de la comida", "7.5f", "4f", "HOUR"})));


        consultationTerminal.enterMedicineGuidelines(new String[]{"AFTERLUNCH", "5.5f", "Después de la comida", "7.5f", "4f", "HOUR"});
        assertEquals(consultationTerminal.medicalPrescription.getMedicinePrescriptionLineList(), lines);
    }

    @Deprecated
    @Test
    void enterTreatmentEndingDate() throws ProductAlreadyInPrescription, AnySelectedMedicineException, IncorrectTakingGuidelinesException,
            AnyMedicineSearchException, ConnectException, AnyKeyWordMedicineException, NotFinishedTreatmentException,
            AnyCurrentPrescriptionException, NotValidePrescriptionException, HealthCardException, FormatException, IncorrectEndingDateException {


        consultationTerminal.initRevision();
        consultationTerminal.initPrescriptionEdition();
        Date old = consultationTerminal.medicalPrescription.getEndDate();
        consultationTerminal.searchForProducts("DALSY");
        consultationTerminal.selectProduct(1);
        consultationTerminal.enterMedicineGuidelines(new String[]{"AFTERLUNCH", "5.5f", "Después de la comida", "7.5f", "4f", "HOUR"});
        consultationTerminal.enterTreatmentEndingDate(new Date(2022 - 1900, Calendar.MARCH, 3));
        assertEquals(consultationTerminal.medicalPrescription.getEndDate(), new Date(2022 - 1900, Calendar.MARCH, 3));
        assertNotEquals(old, consultationTerminal.medicalPrescription.getEndDate());
        IncorrectEndingDateException thrown = assertThrows(IncorrectEndingDateException.class, () ->
                        consultationTerminal.enterTreatmentEndingDate(new Date(2020 - 1900, Calendar.MARCH, 3)),
                "End date comes before PrescDate");
        assertTrue(thrown.getMessage().contains("End date comes before PrescDate"));

    }

    @Test
    @Deprecated
    void sendePrescription() throws HealthCardException, ConnectException, NotValidePrescriptionException, FormatException,
            NotFinishedTreatmentException, AnyCurrentPrescriptionException, AnyKeyWordMedicineException,
            AnyMedicineSearchException, ProductAlreadyInPrescription, AnySelectedMedicineException,
            IncorrectTakingGuidelinesException, IncorrectEndingDateException, NotCompletedMedicalPrescription, NotValidePrescription, eSignatureException {

        consultationTerminal.initRevision();
        HealthCardID pacientID = consultationTerminal.getPacient();
        consultationTerminal.initPrescriptionEdition();
        consultationTerminal.searchForProducts("DALSY");
        consultationTerminal.selectProduct(1);
        consultationTerminal.enterMedicineGuidelines(new String[]{"AFTERLUNCH", "5.5f", "Después de la comida", "7.5f", "4f", "HOUR"});
        consultationTerminal.enterTreatmentEndingDate(new Date(2022 - 1900, Calendar.MARCH, 3));
        consultationTerminal.eSign = new DigitalSignature("12353");
        consultationTerminal.sendePrescription();
        Database.replace(pacientID, Health.sendePrescription(consultationTerminal.medicalPrescription));
        assertEquals(Database.get(pacientID), consultationTerminal.medicalPrescription);
    }

    @Test
    void printePresc() {
        printingException thrown = assertThrows(printingException.class, () -> consultationTerminal.printePresc(),
                "No prescription object");
        assertTrue(thrown.getMessage().contains("No prescription object"));
    }
}