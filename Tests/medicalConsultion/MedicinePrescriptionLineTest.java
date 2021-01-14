package medicalConsultion;

import Exceptions.FormatException;
import data.ProductID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicinePrescriptionLineTest {

    MedicinePrescriptionLine medLine1;

    @BeforeEach
    private void setUp() throws FormatException {
        medLine1 = new MedicinePrescriptionLine(new ProductID("ABCDE"), new TakingGuideline(dayMoment.AFTERLUNCH, 40f,"Mal de panxa",2f,4f,FqUnit.DAY));
    }

    @Test
    void getId() throws FormatException {
        assertEquals(medLine1.getId(), new ProductID("ABCDE"));
        assertNotEquals(medLine1.getId(), new ProductID("ABCDF"));
    }

    @Test
    void equals() throws FormatException {
        assertTrue(medLine1.equals(new MedicinePrescriptionLine(new ProductID("ABCDE"), new TakingGuideline(dayMoment.AFTERLUNCH, 40f,"Mal de panxa",2f,4f, FqUnit.DAY))));
        assertFalse(medLine1.equals(new MedicinePrescriptionLine(new ProductID("ABCDF"), new TakingGuideline(dayMoment.AFTERLUNCH, 40f,"Mal de panxa",2f,4f, FqUnit.DAY))));
        assertFalse(medLine1.equals(new MedicinePrescriptionLine(new ProductID("ABCDE"), new TakingGuideline(dayMoment.DURINGLUNCH, 30f,"Mal de panxa",2f,4f, FqUnit.DAY))));
        assertFalse(medLine1.equals(new MedicinePrescriptionLine(new ProductID("ABCDE"), new TakingGuideline(dayMoment.AFTERLUNCH, 40f,"Mal de cap",2f,4f, FqUnit.DAY))));
        assertFalse(medLine1.equals(new MedicinePrescriptionLine(new ProductID("ABCDE"), new TakingGuideline(dayMoment.AFTERLUNCH, 40f,"Mal de panxa",1f,4f, FqUnit.DAY))));
        assertFalse(medLine1.equals(new MedicinePrescriptionLine(new ProductID("ABCDE"), new TakingGuideline(dayMoment.AFTERLUNCH, 40f,"Mal de panxa",2f,5f, FqUnit.DAY))));
        assertFalse(medLine1.equals(new MedicinePrescriptionLine(new ProductID("ABCDE"), new TakingGuideline(dayMoment.AFTERLUNCH, 40f,"Mal de panxa",2f,4f, FqUnit.HOUR))));
    }

    @Test
    void getLine() {
        assertTrue(medLine1.getLine().equals(new TakingGuideline(dayMoment.AFTERLUNCH, 40f,"Mal de panxa",2f,4f, FqUnit.DAY)) );
    }


}