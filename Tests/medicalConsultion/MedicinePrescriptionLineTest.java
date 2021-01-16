package medicalConsultion;

import Exceptions.FormatException;
import data.ProductID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicinePrescriptionLineTest {

    MedicinePrescriptionLine medLine1, medLine2;

    @BeforeEach
    void setUp() throws FormatException {
        medLine1 = new MedicinePrescriptionLine(new ProductID("ABCDE"), new TakingGuideline(dayMoment.AFTERLUNCH, 40f, "Mal de panxa", 2f, 4f, FqUnit.DAY));
        medLine2 = new MedicinePrescriptionLine(new ProductID("BBSIT"), new TakingGuideline(dayMoment.AFTERDINNER, 40f, "Mal de cap", 20f, 10f, FqUnit.DAY));
    }

    @Test
    void getId() throws FormatException {
        assertEquals(medLine1.getId(), new ProductID("ABCDE"));
        assertNotEquals(medLine1.getId(), new ProductID("ABCDF"));
        assertEquals(medLine2.getId(), new ProductID("BBSIT"));
        assertNotEquals(medLine1.getId(), medLine2.getId());
    }

    @Test
    void equals() throws FormatException {
        assertEquals(new MedicinePrescriptionLine(new ProductID("ABCDE"), new TakingGuideline(dayMoment.AFTERLUNCH, 40f, "Mal de panxa", 2f, 4f, FqUnit.DAY)), medLine1);
        assertNotEquals(new MedicinePrescriptionLine(new ProductID("ABCDF"), new TakingGuideline(dayMoment.AFTERLUNCH, 40f, "Mal de panxa", 2f, 4f, FqUnit.DAY)), medLine1);
        assertNotEquals(new MedicinePrescriptionLine(new ProductID("ABCDE"), new TakingGuideline(dayMoment.DURINGLUNCH, 30f, "Mal de panxa", 2f, 4f, FqUnit.DAY)), medLine1);
        assertNotEquals(new MedicinePrescriptionLine(new ProductID("ABCDE"), new TakingGuideline(dayMoment.AFTERLUNCH, 40f, "Mal de cap", 2f, 4f, FqUnit.DAY)), medLine1);
        assertNotEquals(new MedicinePrescriptionLine(new ProductID("ABCDE"), new TakingGuideline(dayMoment.AFTERLUNCH, 40f, "Mal de panxa", 1f, 4f, FqUnit.DAY)), medLine1);
        assertNotEquals(new MedicinePrescriptionLine(new ProductID("ABCDE"), new TakingGuideline(dayMoment.AFTERLUNCH, 40f, "Mal de panxa", 2f, 5f, FqUnit.DAY)), medLine1);
        assertNotEquals(new MedicinePrescriptionLine(new ProductID("ABCDE"), new TakingGuideline(dayMoment.AFTERLUNCH, 40f, "Mal de panxa", 2f, 4f, FqUnit.HOUR)), medLine1);
    }

    @Test
    void getLine() {
        assertEquals(new TakingGuideline(dayMoment.AFTERLUNCH, 40f, "Mal de panxa", 2f, 4f, FqUnit.DAY), medLine1.getLine());
        assertEquals(new TakingGuideline(dayMoment.AFTERDINNER, 40f, "Mal de cap", 20f, 10f, FqUnit.DAY), medLine2.getLine());
    }
}