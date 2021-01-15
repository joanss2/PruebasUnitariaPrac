package data;

import Exceptions.*;

final public class ProductID {

    private final String UPCcode;

    public ProductID(String upc) throws FormatException {
        if (!comprovar_upc(upc)) {
            throw new FormatException("UPCcode from  ProductID is invalid");
        }
        this.UPCcode = upc;
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

    public String getProductID() {
        return UPCcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductID productID = (ProductID) o;
        return UPCcode.equals(productID.UPCcode);
    }

    public String toString() {
        return "Product{ " + "ProductID= " + UPCcode + " }";//+ " '\tPrice= "+price+" \tDescription: "+description+" }";
    }

    @Override
    public int hashCode() {
        return UPCcode.hashCode();
    }
}
