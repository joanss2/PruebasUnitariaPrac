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
            productSpec1 = new ProductSpecification((productID1 = new ProductID("TONTO")),new BigDecimal("23.5"),"Solo apto para gilipollas");
            productSpec2 = new ProductSpecification((productID2= new ProductID("BUBAK")),new BigDecimal("45"),"Solo apto para gilipollas");

        } catch (FormatException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void comprovar_upc() throws NullPointerException {
        assertTrue(productSpec1.comprovar_upc(productID1.getProductID()));
        assertFalse(productSpec2.comprovar_upc("BBSITA"));
        assertFalse(productSpec2.comprovar_upc("434aa"));
        FormatException thrown = assertThrows(FormatException.class, () -> new ProductSpecification(productID2,new BigDecimal("0"),""));
        assertTrue(thrown.getMessage().contains("Price null or description null, Wrong format of ProductSpecification"));
        thrown = assertThrows(FormatException.class, () -> new ProductSpecification(new ProductID("aabcQ"),new BigDecimal("4.5"),""));
        assertTrue(thrown.getMessage().contains("UPCcode from  ProductID is invalid"));
    }

    @Test
    public void getId(){
        assertEquals(productSpec1.getId(),productID1);
        assertNotEquals(productID1.getProductID(),productID2);
    }
    @Test
    public void getPrice(){
        assertEquals(productSpec1.getPrice(),new BigDecimal("23.5"));
        assertNotEquals(productSpec2.getPrice(),new BigDecimal("0"));
    }

    public void getDescription(){
        assertTrue(productSpec2.getDescription().equals("EL DANDY DE BARCELONA, QUE POR DONDE PASA ENAMORA"));
        assertEquals(productSpec1.getDescription(),"Solo apto para gilipollas");
    }

}