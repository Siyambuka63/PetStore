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
