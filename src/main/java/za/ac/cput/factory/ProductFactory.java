package za.ac.cput.factory;
/*Oluhle Makhaye
 * 222419636*/

import za.ac.cput.domain.Product;
import za.ac.cput.domain.user.User;
import za.ac.cput.util.Helper;

import java.util.List;

public class ProductFactory {
    public static Product createProduct(long productID,
                                        String productName,
                                        String description,
                                        String imageAddress,
                                        float rating,
                                        float price,
                                        long discountPercent,
                                        int stock,
                                        float weight,
                                        String brand,
                                        String lifeStage,
                                        String foodType,
                                        String petType,
                                        String flavour,
                                        List<String> categories) {
        if (Helper.isNullOrEmpty(productName) || Helper.isNullOrEmpty(description)) return null;
        if (!Helper.isValidRating(rating)) return null;
        if (!Helper.isValidPrice(price)) return null;
        //if (!Helper.isValidPriceVsSale(price, salePrice)) return null;
        if (!Helper.isValidDiscountPercent(discountPercent)) throw new IllegalArgumentException("Discount must be between 0 and 100");
        if (stock < 0) return null;

        return new Product.Builder()
                .setProductID(productID)
                .setProductName(productName)
                .setDescription(description)
//                .setImageAddress(imageAddress)
                .setRating(rating)
                .setPrice(price)
                .setDiscountPercent(discountPercent)
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
