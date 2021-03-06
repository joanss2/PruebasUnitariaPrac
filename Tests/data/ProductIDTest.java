package data;

import Exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductIDTest {

    ProductID product1, product2;                                               // Declarations

    @BeforeEach
    void setUp() throws FormatException {                                           // Inicializations
        product1 = new ProductID("AABEC");
        product2 = new ProductID("AURON");
    }

    @Test
    void getProductID() {                                                               // Getter
        assertEquals(product1.getProductID(), "AABEC");
        FormatException thrown = assertThrows(FormatException.class, () -> new ProductID("aabec"), "UPCcode from  ProductID is invalid");
        assertTrue(thrown.getMessage().contains("UPCcode from  ProductID is invalid"));
        thrown = assertThrows(FormatException.class, () -> new ProductID("12345"), "UPCcode from  ProductID is invalid");
        assertTrue(thrown.getMessage().contains("UPCcode from  ProductID is invalid"));
        thrown = assertThrows(FormatException.class, () -> new ProductID("BRRR0"), "UPCcode from  ProductID is invalid");
        assertTrue(thrown.getMessage().contains("UPCcode from  ProductID is invalid"));
    }

    @Test
    void comprovar_upc() {                                                      // Comprove code
        assertTrue(product1.comprovarUpc(product1.getProductID()));
        assertFalse(product1.comprovarUpc("XQC23"));
    }

    @Test
    void testEquals() {
        assertEquals(product1, product1);
        assertEquals(product2, product2);
        assertNotEquals(product2, product1);
    }

    @Test
    void testToString() {
        assertEquals(product1.toString(), "Product{ ProductID= " + product1.getProductID() + " }");
        assertNotEquals(product2.toString(), "Product{ ProductID= " + product1.getProductID() + " }");

    }
}