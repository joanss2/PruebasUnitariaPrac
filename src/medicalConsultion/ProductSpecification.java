package medicalConsultion;

import Exceptions.FormatException;
import data.ProductID;

import java.math.BigDecimal;

public class ProductSpecification {
    private ProductID id;
    private BigDecimal price;
    private String description;

    public ProductSpecification(ProductID id, BigDecimal price, String description) throws NullPointerException, FormatException {
        if (!comprovar_upc(id.getProductID()))
            throw new FormatException("UPCcode from  ProductID is invalid");
        if (price == null || description == null || description.isEmpty())
            throw new FormatException("Price null or description null, Wrong format of ProductSpecification");

        this.id = id;
        this.price = price;
        this.description = description;

    }

    public boolean comprovar_upc(String upc) {
        if (upc.length() != 5)
            return false;
        char[] stringToArray = upc.toCharArray();
        for (int i = 0; i < stringToArray.length; i++) {
            if (!Character.isUpperCase(stringToArray[i]) || Character.isDigit(stringToArray[i])) {
                return false;
            }
        }
        return true;
    }

    public void setId(ProductID id) {
        this.id = id;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductID getId() {
        return this.id;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getDescription() {
        return this.description;
    }

}
