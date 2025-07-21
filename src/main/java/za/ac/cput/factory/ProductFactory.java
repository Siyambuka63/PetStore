package za.ac.cput.factory;
/*Oluhle Makhaye
 * 222419636*/

import za.ac.cput.domain.Product;
import za.ac.cput.util.Helper;

public class ProductFactory {
    public static Product createProduct(long productID, String productName, String description) {
        if (Helper.isNullOrEmpty(productName) || Helper.isNullOrEmpty(description)) return null;
        return new Product.Builder()
                .setProductID(productID)
                .setProductName(productName)
                .setDescription(description)
                .build();
    }
}
