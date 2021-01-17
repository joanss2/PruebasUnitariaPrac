package medicalConsultion;

import Exceptions.FormatException;
import data.ProductID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductSpecificationTest {

    ProductSpecification productSpec1;                                                              // Declarations
    ProductSpecification productSpec2;
    ProductSpecification productSpec3;
    ProductSpecification productSpec11;
    ProductSpecification productSpec12;
    ProductSpecification productSpec13;
    ProductID productID1;
    ProductID productID2;
    ProductID productID3;

    @BeforeEach
    void setUp() throws FormatException {                                                               // Inicializations
        productID1 = new ProductID("BUBAK");
        productID2 = new ProductID("BBSIT");
        productID3 = new ProductID("BBLYN");
        productSpec1 = new ProductSpecification(productID1, new BigDecimal("23.5"), "Mal de panxa");
        productSpec2 = new ProductSpecification(productID2, new BigDecimal("23.5"), "Mal de cap");
        productSpec3 = new ProductSpecification(productID3, new BigDecimal("24.5"), "Mal de panxa");
        productSpec11 = new ProductSpecification(productID1, new BigDecimal("23.5"), "Mal de panxa");
        productSpec12 = new ProductSpecification(productID2, new BigDecimal("23.5"), "Mal de cap");
        productSpec13 = new ProductSpecification(productID3, new BigDecimal("24.5"), "Mal de panxa");
    }

    @Test
    void testEquals() {
        assertNotEquals(productSpec1, productSpec2);
        assertNotEquals(productSpec1, productSpec3);
        assertNotEquals(productSpec2, productSpec3);
        assertNotEquals(productSpec2, null);
        assertEquals(productSpec1, productSpec11);
        assertEquals(productSpec2, productSpec12);
        assertEquals(productSpec3, productSpec13);
    }

    @Test
    public void setId() {                                                               // Setter
        productSpec1.setId(productID2);
        assertNotEquals(productSpec1, productSpec2);
    }

    @Test
    public void setPrice() {                                                                // Setter
        productSpec1.setId(productID3);
        productSpec1.setPrice(new BigDecimal("24.5"));
        assertEquals(productSpec1, productSpec3);
    }

    @Test
    public void setDescription() {                                                          // Setter
        productSpec1.setId(productID2);
        productSpec1.setDescription("Mal de cap");
        assertEquals(productSpec1, productSpec2);
    }

    @Test
    public void getId() {                                                                       // Getter
        assertEquals(productSpec1.getId(), productID1);
        assertEquals(productSpec2.getId(), productID2);
        assertNotEquals(productSpec1.getId(), productSpec2.getId());
    }

    @Test
    public void getPrice() {                                                                    // Getter
        assertEquals(productSpec1.getPrice(), new BigDecimal("23.5"));
        assertNotEquals(productSpec2.getPrice(), new BigDecimal("0"));
        assertNotEquals(productSpec1.getPrice(), productSpec3.getPrice());
    }

    @Test
    public void getDescription() {                                                          // Getter
        assertEquals(productSpec2.getDescription(), "Mal de cap");
        assertEquals(productSpec1.getDescription(), "Mal de panxa");
        assertNotEquals(productSpec1.getDescription(), productSpec2.getDescription());
    }
}