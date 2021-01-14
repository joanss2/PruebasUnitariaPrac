package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import Exceptions.eSignatureException;

class DigitalSignatureTest {

    DigitalSignature eSign1;
    DigitalSignature eSign2;
    DigitalSignature eSign3;
    DigitalSignature eSign11;
    DigitalSignature eSign21;
    DigitalSignature eSign31;

    {
        try {
            eSign1 = new DigitalSignature("12353");
            eSign2 = new DigitalSignature("12abz345");
            eSign3 = new DigitalSignature("abcde");
            eSign11 = new DigitalSignature("12353");
            eSign21 = new DigitalSignature("12abz345");
            eSign31 = new DigitalSignature("abcde1");
        } catch (eSignatureException e) {
            e.printStackTrace();
        }
    }

    @Test
    void checkSign() throws eSignatureException {
        assertTrue(eSign1.checkSign("12353"));
        assertTrue(eSign2.checkSign("12abz345"));
        assertTrue(eSign3.checkSign("abcde"));
        eSignatureException thrown = assertThrows(eSignatureException.class, () -> new DigitalSignature (""), "Invalid Signature");
        assertTrue(thrown.getMessage().contains("Invalid Signature"));
        thrown = assertThrows(eSignatureException.class, () -> new DigitalSignature(null), "Invalid Signature");
        assertTrue(thrown.getMessage().contains("Invalid Signature"));
        thrown = assertThrows(eSignatureException.class, () -> new DigitalSignature ("Ó"), "Invalid Signature");
        assertTrue(thrown.getMessage().contains("Invalid Signature"));
    }
}