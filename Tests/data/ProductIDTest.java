package data;

import Exceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductIDTest {

    ProductID product1, product2;


    {
        try {
            product1 = new ProductID("AABEC");
            product2 = new ProductID("AURON");


        } catch (FormatException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getProductID() {
        assertEquals(product1.getProductID(), "AABEC");
        FormatException thrown = assertThrows(FormatException.class, () -> new ProductID("aabec"), "UPCcode from  ProductID is invalid");
        assertTrue(thrown.getMessage().contains("UPCcode from  ProductID is invalid"));
        thrown = assertThrows(FormatException.class, () -> new ProductID("12345"), "UPCcode from  ProductID is invalid");
        assertTrue(thrown.getMessage().contains("UPCcode from  ProductID is invalid"));
        thrown = assertThrows(FormatException.class, () -> new ProductID("BRRR0"), "UPCcode from  ProductID is invalid");
        assertTrue(thrown.getMessage().contains("UPCcode from  ProductID is invalid"));

    }

    @Test
    void comprovar_upc() {
        assertTrue(product1.comprovar_upc(product1.getProductID()));
        assertFalse(product1.comprovar_upc("XQC23"));
    }

    @Test
    void testEquals() {
        assertTrue(product1.equals(product1));
        assertTrue(product2.equals(product2));
        assertFalse(product1.equals(product2));
    }

    @Test
    void testToString() {
        assertEquals(product1.toString(), "Product{ ProductID= " + product1.getProductID() + " }");
        assertNotEquals(product2.toString(), "Product{ ProductID= " + product1.getProductID() + " }");

    }
    /*
    @Test
    void testHashCode() {
    }
    */
}