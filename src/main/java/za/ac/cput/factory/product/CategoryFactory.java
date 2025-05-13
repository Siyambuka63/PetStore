package za.ac.cput.factory.product;

import za.ac.cput.domain.product.Category;
import za.ac.cput.util.Helper;

public class CategoryFactory {
    private static Category createCategory(int id, String categoryName) {
        if (Helper.isNullOrEmpty(categoryName)) return null;
        return new Category.Builder()
                .setCategoryID(id)
                .setCategoryName(categoryName)
                .build();
    }
}
