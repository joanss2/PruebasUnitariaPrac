package medicalConsultion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PosologyTest {

    Posology pos1, pos2, pos3;
    FqUnit time = FqUnit.WEEK;
    FqUnit time2 = FqUnit.DAY;
    FqUnit time3 = FqUnit.MONTH;

    @BeforeEach
    public void init() {
        pos1 = new Posology(2.5f, 3.8f, time);
        pos2 = new Posology(5f, 4.5f, time2);
        pos3 = new Posology(3f, 1.3f, time3);
    }

    @Test
    void setDose() {
        pos1.setDose(40.5f);
        assertEquals(pos1.getDose(), 40.5f);
        pos2.setDose(1f);
        assertEquals(pos2.getDose(), 1f);
        pos3.setDose(6f);
        assertEquals(pos3.getDose(), 6f);
    }

    @Test
    void setFreq() {
        pos1.setFreq(20);
        assertEquals(pos1.getFreq(), 20f);
        pos2.setFreq(1.8f);
        assertEquals(pos2.getFreq(), 1.8f);
        pos3.setFreq(4.4f);
        assertEquals(pos3.getFreq(), 4.4f);
    }

    @Test
    void setFreqUnit() {
        pos1.setFreqUnit(FqUnit.WEEK);
        assertEquals(pos1.getFreqUnit(), FqUnit.WEEK);
        pos2.setFreqUnit(FqUnit.DAY);
        assertEquals(pos2.getFreqUnit(), FqUnit.DAY);
        pos3.setFreqUnit(FqUnit.MONTH);
        assertEquals(pos3.getFreqUnit(), FqUnit.MONTH);
    }

    @Test
    void getDose() {
        assertEquals(pos1.getDose(), 2.5f);
        assertEquals(pos2.getDose(), 5f);
        assertEquals(pos3.getDose(), 3f);
    }

    @Test
    void getFreq() {
        assertEquals(pos1.getFreq(), 3.8f);
        assertEquals(pos2.getFreq(), 4.5f);
        assertEquals(pos3.getFreq(), 1.3f);
    }

    @Test
    void getFreqUnit() {
        assertEquals(pos1.getFreqUnit(), time);
        assertEquals(pos2.getFreqUnit(), time2);
        assertEquals(pos3.getFreqUnit(), time3);
    }

    @Test
    void equals() {
        assertEquals(new Posology(2.5f, 3.8f, time), pos1);
        assertNotEquals(new Posology(2f, 3.8f, time), pos1);
        assertNotEquals(new Posology(2.5f, 4f, time), pos1);
        assertNotEquals(new Posology(2.5f, 3.8f, FqUnit.DAY), pos1);
    }
}