package medicalConsultion;

import Exceptions.FormatException;
import data.ProductID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductSpecificationTest {

    ProductSpecification productSpec1;
    ProductSpecification productSpec2;
    ProductSpecification productSpec3;
    ProductSpecification productSpec11;
    ProductSpecification productSpec12;
    ProductSpecification productSpec13;
    ProductID productID1;
    ProductID productID2;
    ProductID productID3;

    @BeforeEach
    void setUp() throws FormatException {
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
        assertEquals(productSpec1,productSpec11);
        assertEquals(productSpec2, productSpec12);
        assertEquals(productSpec3,productSpec13);

    }

    @Test
    public void setId(){
        productSpec1.setId(productID2);
        assertEquals(productSpec1, productSpec2);
    }

    @Test
    public void setPrice(){
        productSpec1.setId(productID3);
        productSpec1.setPrice(new BigDecimal("24.5"));
        assertEquals(productSpec1, productSpec3);
    }

    @Test
    public void setDescription(){
        productSpec1.setId(productID2);
        productSpec1.setDescription("Mal de cap");
        assertEquals(productSpec1, productSpec2);
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