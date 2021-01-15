package medicalConsultion;

import Exceptions.FormatException;
import data.HealthCardID;
import data.ProductID;
import medicalConsultion.ProductSpecification;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductSpecificationTest {

    ProductSpecification productSpec1;
    ProductSpecification productSpec2;
    ProductID productID1;
    ProductID productID2;

    {
        try {
            productSpec1 = new ProductSpecification((productID1 = new ProductID("BBCCA")), new BigDecimal("23.5"), "Per a mals de panxa");
            productSpec2 = new ProductSpecification((productID2 = new ProductID("BUBAK")), new BigDecimal("45"), "Per a mals de cap");

        } catch (FormatException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getId() {
        assertEquals(productSpec1.getId(), productID1);
        assertNotEquals(productID1.getProductID(), productID2);
    }

    @Test
    public void getPrice() {
        assertEquals(productSpec1.getPrice(), new BigDecimal("23.5"));
        assertNotEquals(productSpec2.getPrice(), new BigDecimal("0"));
    }

    @Test
    public void getDescription() {
        assertEquals(productSpec2.getDescription(), "Per a mals de cap");
        assertEquals(productSpec1.getDescription(), "Per a mals de panxa");
    }




}