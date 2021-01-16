package medicalConsultion;

import Exceptions.*;
import data.DigitalSignature;
import data.ProductID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MedicalPrescriptionTest {

    MedicalPrescription medP1, medP2;
    MedicinePrescriptionLine medL1, medL2;
    List<MedicinePrescriptionLine> medList;
    String[] instruc1, instruc2, instruc3, instruc4;
    String[] instruc5, instruc6, instruc7, instruc8;

    @BeforeEach
    @Deprecated
    void init() throws FormatException {
        medP1 = new MedicalPrescription(1,
                new Date(2021, Calendar.JANUARY, 1),
                new Date(2021, Calendar.JANUARY, 10));
        medP2 = new MedicalPrescription(1,
                new Date(2021, Calendar.JANUARY, 1),
                new Date(2021, Calendar.FEBRUARY, 1));
        medL1 = new MedicinePrescriptionLine(new ProductID("ABCDE"),
                new TakingGuideline(dayMoment.AFTERLUNCH, 5.5f, "Después de la comida",
                        7.5f, 4f, FqUnit.HOUR));
        medL2 = new MedicinePrescriptionLine(new ProductID("BBSIT"),
                new TakingGuideline(dayMoment.AFTERDINNER, 4.5f, "Después de la cena",
                        6.5f, 5f, FqUnit.HOUR));
        medList = new ArrayList<>();
        medList.add(new MedicinePrescriptionLine(new ProductID("BBSIT"),
                new TakingGuideline(dayMoment.AFTERDINNER, 4.5f, "Después de la cena",
                        6.5f, 5f, FqUnit.HOUR)));
        instruc1 = new String[]{"AFTERLUNCH", "5.5f", "Después de la comida", "7.5f", "4f", "HOUR"};
        instruc2 = new String[]{"AFTERDINNER", "4.5f", "Después de la cena", "6.5f", "5f", "HOUR"};
        instruc3 = new String[]{"AFTERLUNCH", "5.5f", "Después de la comida", "7.5f", "4f"};
        instruc4 = new String[]{"AFTERLUNCHs", "5.5f", "Después de la comida", "7.5f", "4f", "HOUR"};
        instruc5 = new String[]{"AFTERLUNCH", "f", "Después de la comida", "7.5f", "4f", "HOUR"};
        instruc6 = new String[]{"AFTERLUNCH", "5.5f", "Después de la comida", "f", "4f", "HOUR"};
        instruc7 = new String[]{"AFTERLUNCH", "5.5f", "Después de la comida", "7.5f", "f", "HOUR"};
        instruc8 = new String[]{"AFTERLUNCH", "5.5f", "Después de la comida", "7.5f", "4f", "HOURs"};
    }

    @Test
    void validInstruc() {
        assertTrue(medP1.validInstruc(instruc1));
        assertTrue(medP1.validInstruc(instruc2));
        assertFalse(medP1.validInstruc(instruc3));
        assertFalse(medP1.validInstruc(instruc4));
        assertFalse(medP1.validInstruc(instruc5));
        assertFalse(medP1.validInstruc(instruc6));
        assertFalse(medP1.validInstruc(instruc7));
        assertFalse(medP1.validInstruc(instruc8));
    }

    @Test
    @Deprecated
    void getPrescDate() {
        assertEquals(medP1.getPrescDate(), new Date(2021, Calendar.JANUARY, 1));
        assertNotEquals(medP1.getPrescDate(), new Date(2020, Calendar.JANUARY, 1));
        assertNotEquals(medP1.getPrescDate(), new Date(2021, Calendar.FEBRUARY, 1));
        assertNotEquals(medP1.getPrescDate(), new Date(2021, Calendar.JANUARY, 2));
        assertEquals(medP2.getPrescDate(), medP1.getPrescDate());
    }

    @Test
    @Deprecated
    void getEndDate() {
        assertEquals(medP1.getEndDate(), new Date(2021, Calendar.JANUARY, 10));
        assertNotEquals(medP1.getEndDate(), new Date(2020, Calendar.JANUARY, 1));
        assertNotEquals(medP1.getEndDate(), new Date(2021, Calendar.FEBRUARY, 1));
        assertNotEquals(medP1.getEndDate(), new Date(2021, Calendar.JANUARY, 2));
        assertNotEquals(medP2.getEndDate(), medP1.getEndDate());
    }

    @Test
    void getPrescCode() {
        assertEquals(medP1.getPrescCode(), 1);
        assertNotEquals(medP2.getPrescCode(), 2);
        assertEquals(medP1.getPrescCode(), medP2.getPrescCode());
    }

    @Test
    void getMedicinePrescriptionLineList() throws FormatException, ProductAlreadyInPrescription, IncorrectTakingGuidelinesException {
        assertNotEquals(medP1.getMedicinePrescriptionLineList(), medList);
        assertEquals(medP2.getMedicinePrescriptionLineList(), medP1.getMedicinePrescriptionLineList());
        medP2.addLine(new ProductID("BBSIT"), instruc1);
        assertNotEquals(medP1.getMedicinePrescriptionLineList(), medList);
        medP1.addLine(new ProductID("BBSIC"), instruc2);
        assertNotEquals(medP1.getMedicinePrescriptionLineList(), medList);
    }

    @Test
    void setPrescCode() {
        medP1.setPrescCode(2);
        assertEquals(medP1.getPrescCode(), 2);
        medP2.setPrescCode(2);
        assertNotEquals(medP1.getPrescCode(), 1);
        medP1.setPrescCode(medP2.getPrescCode());
        assertEquals(medP1.getPrescCode(), 2);
    }

    @Test
    @Deprecated
    void setPrescDate() {
        medP1.setPrescDate(new Date(2021, Calendar.FEBRUARY, 1));
        assertEquals(medP1.getPrescDate(), new Date(2021, Calendar.FEBRUARY, 1));
        medP2.setPrescDate(new Date(2021, Calendar.FEBRUARY, 1));
        assertNotEquals(medP1.getPrescDate(), new Date(2021, Calendar.JANUARY, 1));
        medP1.setPrescDate(medP2.getPrescDate());
        assertEquals(medP1.getPrescDate(), new Date(2021, Calendar.FEBRUARY, 1));
    }

    @Test
    @Deprecated
    void setEndDate() {
        medP1.setEndDate(new Date(2021, Calendar.FEBRUARY, 1));
        assertEquals(medP1.getEndDate(), new Date(2021, Calendar.FEBRUARY, 1));
        medP2.setEndDate(new Date(2021, Calendar.FEBRUARY, 1));
        assertNotEquals(medP1.getEndDate(), new Date(2021, Calendar.JANUARY, 1));
        medP1.setEndDate(medP2.getEndDate());
        assertEquals(medP1.getEndDate(), new Date(2021, Calendar.FEBRUARY, 1));
    }

    @Test
    void addLine() throws FormatException, ProductAlreadyInPrescription, IncorrectTakingGuidelinesException {
        medP1.addLine(new ProductID("BBSIT"), instruc2);
        assertEquals(medP1.getMedicinePrescriptionLineList(), medList);
        IncorrectTakingGuidelinesException thrown = assertThrows(IncorrectTakingGuidelinesException.class, () -> medP1.addLine(new ProductID("DUREX"), instruc6),
                "Erroneous format of posology or instructions");
        assertTrue(thrown.getMessage().contains("Erroneous format of posology or instructions"));
        ProductAlreadyInPrescription thrown2 = assertThrows(ProductAlreadyInPrescription.class, () -> medP1.addLine(new ProductID("BBSIT"), instruc2),
                "The product with ID:" + new ProductID("BBSIT") + "is already in the prescription");
        assertTrue(thrown2.getMessage().contains("The product with ID:" + new ProductID("BBSIT") + "is already in the prescription"));
    }

    @Test
    void modifyLine() throws FormatException, ProductAlreadyInPrescription, IncorrectTakingGuidelinesException, ProductNotInPrescription {
        medP1.addLine(new ProductID("BBSIT"), instruc2);
        medP2.addLine(new ProductID("BBSIT"), instruc1);
        medP1.modifyLine(new ProductID("BBSIT"), instruc1);
        assertNotEquals(medList, medP1.getMedicinePrescriptionLineList());
        assertEquals(medP1.getMedicinePrescriptionLineList(), medP2.getMedicinePrescriptionLineList());
        ProductNotInPrescription thrown = assertThrows(ProductNotInPrescription.class, () -> medP1.modifyLine(new ProductID("DUREX"), instruc2),
                "Product is not in the lines of the prescription");
        assertTrue(thrown.getMessage().contains("Product is not in the lines of the prescription"));
    }

    @Test
    void removeLine() throws FormatException, ProductAlreadyInPrescription, IncorrectTakingGuidelinesException, ProductNotInPrescription {
        ProductNotInPrescription thrown = assertThrows(ProductNotInPrescription.class, () -> medP1.removeLine(new ProductID("BBSIT")),
                "Product is not in the lines of the prescription");
        assertTrue(thrown.getMessage().contains("Product is not in the lines of the prescription"));
        medP1.addLine(new ProductID("BBSIT"), instruc2);
        medP1.addLine(new ProductID("CJGYS"), instruc1);
        assertNotEquals(medList, medP1.getMedicinePrescriptionLineList());
        medP1.removeLine(new ProductID("CJGYS"));
        assertEquals(medList, medP1.getMedicinePrescriptionLineList());
        medP1.removeLine(new ProductID("BBSIT"));
        assertNotEquals(medList, medP1.getMedicinePrescriptionLineList());
    }
}