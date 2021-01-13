package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import Exceptions.eSignatureException;

class DigitalSignatureTest {

    DigitalSignature eSign1;
    DigitalSignature eSign2;
    DigitalSignature eSign3;
    DigitalSignature eSign4;
    DigitalSignature eSign5;
    DigitalSignature eSign11;
    DigitalSignature eSign21;

    {
        try {
            eSign1 = new DigitalSignature("12353");
            eSign2 = new DigitalSignature("");
            eSign3 = new DigitalSignature("abcde");
            eSign4 = new DigitalSignature("12a53");
            eSign5 = new DigitalSignature("12a53");
            eSign11 = new DigitalSignature("12353");
            eSign21 = new DigitalSignature("127568");
        } catch (eSignatureException e) {
            e.printStackTrace();
        }
    }

    @Test
    void checkSign() {
        assertTrue(eSign1.checkSign("12353"));
        assertFalse(eSign2.checkSign(""));
        assertFalse(eSign3.checkSign("abcde"));
        assertFalse(eSign4.checkSign("12a53"));
    }

    @Test
    void getsignature() {
        assertNotEquals(eSign1.getsignature(),  new byte[]{61, 62, 63, 65, 63});
        //assertNotEquals(eSign5.getsignature(), new byte[]{61,62,67,65,66,68});
    }

    @Test
    void testEquals() {
        assertFalse(eSign1.equals(eSign2));
        assertFalse(eSign2.equals(eSign3));
        assertFalse(eSign1.equals(eSign3));
        assertFalse(eSign1.equals(eSign4));
        assertTrue(eSign21.equals(eSign2));
        assertTrue(eSign1.equals(eSign11));
    }
}