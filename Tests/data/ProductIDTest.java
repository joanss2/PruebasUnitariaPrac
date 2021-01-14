package data;

import Exceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductIDTest {

    ProductID product1, product2, product3;
    ProductID product11, product21, product31;

    {
        try {
            product1 = new ProductID("12345");
            product2 = new ProductID("123456");
            product3 = new ProductID("1234");
            product11 = new ProductID("12345");
            product21 = new ProductID("123456");
            product31 = new ProductID("1234");

        } catch (FormatException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getProductID() {
        assertEquals(product1.getProductID(), "12345");
        assertEquals(product2.getProductID(), "123456");
        assertEquals(product3.getProductID(), "123454");
        assertNotEquals(product1.getProductID(), "12435");
    }

    @Test
    void comprovar_upc() {
        assertTrue(product1.comprovar_upc(product1.getProductID()));
        assertFalse(product2.comprovar_upc(product2.getProductID()));
        assertFalse(product3.comprovar_upc(product3.getProductID()));
    }

    @Test
    void testEquals() {
        assertTrue(product1.equals(product1));
        assertTrue(product2.equals(product2));
        assertTrue(product3.equals(product3));
        assertFalse(product1.equals(product2));
        assertFalse(product2.equals(product3));
        assertFalse(product3.equals(product1));
        assertTrue(product1.equals(product11));
        assertTrue(product2.equals(product21));
        assertTrue(product3.equals(product31));
    }

    @Test
    void testToString() {
        assertEquals(product1.toString(), "Product{ ProductID= " + product1.getProductID() + " }");
        assertEquals(product2.toString(), "Product{ ProductID= " + product2.getProductID() + " }");
        assertNotEquals(product2.toString(), "Product{ ProductID= " + product3.getProductID() + " }");
    }
    /*
    @Test
    void testHashCode() {
    }
    */
}