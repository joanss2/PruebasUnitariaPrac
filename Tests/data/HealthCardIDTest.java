package data;

import Exceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthCardIDTest {

    HealthCardID healthcard1, healthcard2, healthcard3, healthcard11;
    String healthcard1code, healthcard2code, healthcard3code, healthcard11code;

    {
        try {
            healthcard1 = new HealthCardID("BBBBBBBBQR648597807024000012");
            healthcard2 = new HealthCardID("BBBBBBBBWF64859780705400001");
            healthcard3 = new HealthCardID("BBBBBBBBR64859780702400F012");
            healthcard11 = new HealthCardID("BBBBBBBBQR648597807024000012");
        } catch (FormatException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getPersonalID() {
        assertEquals(healthcard1.getPersonalID(), "BBBBBBBBQR648597807024000012");
        assertNotEquals(healthcard1.getPersonalID(), "BBBBBBBBRQ648597807024000011");
    }

    @Test
    void testEquals() {
        assertTrue(healthcard1.equals(healthcard1));
        assertTrue(healthcard1.equals(healthcard11));
        assertFalse(healthcard1.equals(healthcard2));
        assertFalse(healthcard2.equals(healthcard3));
    }

    @Test
    void isFormatValid() {
        assertTrue(healthcard1.isFormatValid(healthcard1.getPersonalID()));
        assertFalse(healthcard2.isFormatValid(healthcard2.getPersonalID()));
        assertFalse(healthcard3.isFormatValid(healthcard3.getPersonalID()));
        assertTrue(healthcard11.isFormatValid(healthcard11.getPersonalID()));
    }

    @Test
    void allDigits() {
        assertTrue(healthcard1.AllDigits(initArray(healthcard1code), 10, 28));
        assertFalse(healthcard1.AllDigits(initArray(healthcard3code), 10, 28));
        assertTrue(healthcard11.AllDigits(initArray(healthcard2code), 10, 28));
        assertTrue(healthcard2.AllDigits(initArray(healthcard11code), 10, 28));
        assertTrue(healthcard3.AllDigits(initArray(healthcard3code), 11, 20));
    }

    @Test
    void testToString() {
        assertEquals(healthcard1.toString(), "HealthCardID{ " + "personal code= " + healthcard1.getPersonalID() + "}");
        assertEquals(healthcard11.toString(), "HealthCardID{ " + "personal code= " + healthcard1.getPersonalID() + "}");
        assertEquals(healthcard1.toString(), "HealthCardID{ " + "personal code= " + healthcard11.getPersonalID() + "}");
        assertNotEquals(healthcard2.toString(), "HealthCardID{ " + "personal code= " + healthcard1.getPersonalID() + "}");
    }

    /*
    @Test
    void testHashCode() {
    }
    */

    private char[] initArray(String code) {
        char[] array = new char[28];
        for (int i = 0; i < 28; i++) {
            array[i] = code.charAt(i);
        }
        return array;
    }
}