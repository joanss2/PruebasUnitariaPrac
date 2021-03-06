package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import Exceptions.eSignatureException;

class DigitalSignatureTest {

    DigitalSignature eSign1;                                                // Declarations
    DigitalSignature eSign2;
    DigitalSignature eSign3;
    DigitalSignature eSign11;
    DigitalSignature eSign21;
    DigitalSignature eSign31;

    @BeforeEach
    void setUp() throws eSignatureException {                               // Inicializations
        eSign1 = new DigitalSignature("12353");
        eSign2 = new DigitalSignature("12abz345");
        eSign3 = new DigitalSignature("abcde");
        eSign11 = new DigitalSignature("12353");
        eSign21 = new DigitalSignature("12abz345");
        eSign31 = new DigitalSignature("abcde1");
    }

    @Test
    void checkSign() throws eSignatureException {                                           // Comprovation if the signature  is valid or not?¿
        assertTrue(eSign1.checkSign("12353"));
        assertTrue(eSign2.checkSign("12abz345"));
        assertTrue(eSign3.checkSign("abcde"));
        eSignatureException thrown = assertThrows(eSignatureException.class, () -> new DigitalSignature(""), "Invalid Signature");
        assertTrue(thrown.getMessage().contains("Invalid Signature"));
        thrown = assertThrows(eSignatureException.class, () -> new DigitalSignature(null), "Invalid Signature");
        assertTrue(thrown.getMessage().contains("Invalid Signature"));
        thrown = assertThrows(eSignatureException.class, () -> new DigitalSignature("Ó"), "Invalid Signature");
        assertTrue(thrown.getMessage().contains("Invalid Signature"));
    }

    @Test
    void getsignature() {                                                               // Return the signature in bytes
        byte[] bytesESign1 = new byte[]{49, 50, 51, 53, 51};
        byte[] bytesESign2 = new byte[]{49, 50, 97, 98, 122, 51, 52, 53};
        byte[] bytesESign3 = new byte[]{97, 98, 99, 100, 101};
        for (int i = 0; i < eSign1.getsignature().length; i++) {
            assertEquals(eSign1.getsignature()[i], bytesESign1[i]);
        }
        for (int i = 0; i < eSign2.getsignature().length; i++) {
            assertEquals(eSign2.getsignature()[i], bytesESign2[i]);
        }
        for (int i = 0; i < eSign3.getsignature().length; i++) {
            assertEquals(eSign3.getsignature()[i], bytesESign3[i]);
        }
        assertNotEquals(eSign2.getsignature(), new byte[]{});
        assertNotEquals(eSign2.getsignature(), new byte[]{61, 62, 67, 65, 66, 68});
    }

    @Test
    void testEquals() {
        assertNotEquals(eSign2, eSign1);
        assertNotEquals(eSign3, eSign2);
        assertNotEquals(eSign3, eSign1);
        assertEquals(eSign11, eSign1);
        assertEquals(eSign2, eSign21);
        assertNotEquals(eSign31, eSign3);
    }
}