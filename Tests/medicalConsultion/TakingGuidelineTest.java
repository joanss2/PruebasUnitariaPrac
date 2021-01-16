package medicalConsultion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TakingGuidelineTest {

    TakingGuideline instruc1;
    TakingGuideline instruc2;

    @BeforeEach
    public void setUp() {
        instruc1 = new TakingGuideline(dayMoment.AFTERLUNCH, 40f, "Mal de panxa", 2f, 4f, FqUnit.DAY);
        instruc2 = new TakingGuideline(dayMoment.BEFOREDINNER, 69f, "Mal de cap", 2f, 5f, FqUnit.MONTH);
    }

    @Test
    void setdMoment() {
        instruc1.setdMoment(dayMoment.DURINGLUNCH);
        assertEquals(instruc1.getdMoment(), dayMoment.DURINGLUNCH);
        assertNotEquals(instruc2.getdMoment(), dayMoment.DURINGLUNCH);
    }

    @Test
    void setDuration() {
        instruc1.setDuration(15f);
        assertEquals(instruc1.getDuration(), 15f);
        assertNotEquals(instruc2.getDuration(), 4f);
    }

    @Test
    void setInstructions() {
        instruc1.setInstructions("Mal de cap");
        assertNotEquals(instruc1.getInstructions(), "Mal de panxa");
        assertEquals(instruc1.getInstructions(), "Mal de cap");
    }

    @Test
    void setPosology() {
        instruc2.setPosology(new Posology(3f, 4f, FqUnit.MONTH));
        assertEquals(new Posology(3f, 4f, FqUnit.MONTH), instruc2.getPosology());
        assertNotEquals(instruc1.getPosology(), new Posology(1f, 3, FqUnit.HOUR));
    }

    @Test
    void getdMoment() {
        assertEquals(instruc1.getdMoment(), dayMoment.AFTERLUNCH);
        assertEquals(instruc2.getdMoment(), dayMoment.BEFOREDINNER);
    }

    @Test
    void getDuration() {
        assertEquals(instruc1.getDuration(), 40f);
        assertEquals(instruc2.getDuration(), 69f);
    }

    @Test
    void getInstructions() {
        assertEquals(instruc1.getInstructions(), "Mal de panxa");
        assertEquals(instruc2.getInstructions(), "Mal de cap");
    }

    @Test
    void getPosology() {
        assertEquals(new Posology(2f, 4f, FqUnit.DAY), instruc1.getPosology());
        assertEquals(new Posology(2f, 5f, FqUnit.MONTH), instruc2.getPosology());

    }

    @Test
    void equals() {
        assertEquals(new TakingGuideline(dayMoment.AFTERLUNCH, 40f, "Mal de panxa", 2f, 4f, FqUnit.DAY), instruc1);
        assertEquals(new TakingGuideline(dayMoment.BEFOREDINNER, 69f, "Mal de cap", 2f, 5f, FqUnit.MONTH), instruc2);
        assertNotEquals(new TakingGuideline(dayMoment.BEFOREDINNER, 69f, "Mal de cap", 2f, 5f, FqUnit.MONTH), instruc1);
        assertNotEquals(new TakingGuideline(dayMoment.AFTERLUNCH, 40f, "Mal de panxa", 2f, 4f, FqUnit.DAY), instruc2);
    }
}