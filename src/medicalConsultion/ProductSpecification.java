package medicalConsultion;

import Exceptions.FormatException;
import data.ProductID;

import java.math.BigDecimal;

public class ProductSpecification {
    private ProductID id;
    private BigDecimal price;
    private String description;

    public ProductSpecification(ProductID id, BigDecimal price, String description) throws NullPointerException, FormatException {
        if (!id.comprovar_upc(id.getProductID()))                                           // Comprovation of the product ID
            throw new FormatException("UPCcode from  ProductID is invalid");
        if (price == null || description == null || description.isEmpty())                      // Null comprovation
            throw new FormatException("Price null or description null, Wrong format of ProductSpecification");

        this.id = id;
        this.price = price;
        this.description = description;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSpecification prodSpec1 = (ProductSpecification) o;
        return id.equals(prodSpec1.id) && price.equals(prodSpec1.price) && description.equals(prodSpec1.description);
    }

    // Getters and setters
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
