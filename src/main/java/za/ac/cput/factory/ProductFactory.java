package za.ac.cput.factory;
/*Oluhle Makhaye
 * 222419636*/

import za.ac.cput.domain.Product;
import za.ac.cput.util.Helper;

import java.util.List;

public class ProductFactory {
    public static Product createProduct(long productID, String productName, String description,
                                        String imageAddress, float rating, double price, double salePrice,
                                        boolean onSale, int stock, double weight, String brand,
                                        String lifeStage, String foodType, String petType, String flavour, List<String> categories) {

        if (Helper.isNullOrEmpty(productName) || Helper.isNullOrEmpty(description)) return null;
        if (!Helper.isValidRating(rating)) {
            throw new IllegalArgumentException("Rating should be between 0 and 5");
        }
        if (!Helper.isValidPriceVsSale(price, salePrice)) {
            throw new IllegalArgumentException("Sale price must be less than original price");
        }
        if (stock == 0) {
            System.err.println("⚠ Warning: Creating product with 0 stock: " + productName);
        } else if (stock <= 5) {
            System.out.println("⚠ Info: Low stock warning for product: " + productName + " (stock: " + stock + ")");
        }



        return new Product.Builder()
                .setProductID(productID)
                .setProductName(productName)
                .setDescription(description)
                .setImageAddress(imageAddress)
                .setRating(rating)
                .setPrice(price)
                .setSalePrice(salePrice)
                .setOnSale(onSale)
                .setStock(stock)
                .setWeight(weight)
                .setBrand(brand)
                .setLifeStage(lifeStage)
                .setFoodType(foodType)
                .setPetType(petType)
                .setFlavour(flavour)
                .setCategories(categories)
                .build();
    }
}
