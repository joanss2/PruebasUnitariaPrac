package data;

import Exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthCardIDTest {

    HealthCardID healthcard1, healthcard11,healthcard12;


    {
        try {
            healthcard1 = new HealthCardID("BBBBBBBBQR648597807024000012");
            healthcard11 = new HealthCardID("BBBBBBBBQR648597807024000013");
            healthcard12 = new HealthCardID("BBBBBBBBQR648597807024000011");
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
    void isFormatValid() {
        assertTrue(healthcard1.isFormatValid(healthcard1.getPersonalID()));
        assertTrue(healthcard11.isFormatValid(healthcard11.getPersonalID()));
        assertTrue(healthcard12.isFormatValid(healthcard12.getPersonalID()));
        FormatException thrown = assertThrows(FormatException.class, () -> new HealthCardID("BBBBBBBBWF64859780705400001"), "PersonalID code from HealthCardID is invalid");
        assertTrue(thrown.getMessage().contains("PersonalID code from HealthCardID is invalid"));
        thrown = assertThrows(FormatException.class, () -> new HealthCardID("BBBBBBBBWF64859780705GG001"), "PersonalID code from HealthCardID is invalid");
        assertTrue(thrown.getMessage().contains("PersonalID code from HealthCardID is invalid"));
    }


    @Test
    void testEquals() {
        assertEquals(healthcard1, healthcard1);
        assertNotEquals(healthcard11, healthcard1);
        assertNotEquals(healthcard11, healthcard12);
    }


    @Test
    void allDigits() {
        assertTrue(healthcard1.AllDigits(initArray("BBBBBBBBQR648597807024000012"), 10, 28));
        assertFalse(healthcard11.AllDigits(initArray("BBBBBBBBQR648597807Ff24000013"), 10, 28));
        assertTrue(healthcard12.AllDigits(initArray("BBBBBBBBQR648597807024000011"), 10, 28));
        FormatException thrown = assertThrows(FormatException.class, () -> new HealthCardID("BBBBBBBBWF6485978705GG001"), "PersonalID code from HealthCardID is invalid");
        assertTrue(thrown.getMessage().contains("PersonalID code from HealthCardID is invalid"));
    }

    @Test
    void testToString() {
        assertEquals(healthcard1.toString(), "HealthCardID{" + "personal code='" + healthcard1.getPersonalID() + "'}");
        assertEquals(healthcard11.toString(), "HealthCardID{" + "personal code='" + healthcard11.getPersonalID() + "'}");
        assertEquals(healthcard12.toString(), "HealthCardID{" + "personal code='" + healthcard12.getPersonalID() + "'}");
        assertNotEquals(healthcard1.toString(), "HealthCardID{ " + "personal code= " + healthcard11.getPersonalID() + "}");
        assertNotEquals(healthcard11.toString(), "HealthCardID{ " + "personal code= " + healthcard12.getPersonalID() + "}");
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