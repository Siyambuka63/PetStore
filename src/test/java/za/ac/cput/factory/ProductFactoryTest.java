package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.user.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductFactoryTest {

    private static Product product;

    private final List<String> categories = new ArrayList<>();

    /*   private Product productWithInvalidRating = ProductFactory.createProduct(2,"MissDog", "Nibbles","placeholder.jpg", 6f, 249.99, 199.99, true, 23, 1.34, "SaveMor", "Adult", "Dry", "Dog", "Chicken", categories);*/
    private final Product productWithInvalidName = ProductFactory.createProduct(1, "", "imageAddress", "placeholder.jpg", 4.5f, 100.0f, 10L, 50, 1.0f, "Canin Cuisine", "Adult", "Dry", "Dog", "Peas", categories);
    private final Product productWithNoImage = ProductFactory.createProduct(3, "MissDog", "Nutritional bites","", 4.5f, 100.0f, 15L,  50, 1.0f, "Canin Cuisine", "Adult", "Dry", "Dog", "Chicken", categories);
    private final Product testProduct = ProductFactory.createProduct(4, "Montego Classic", "Dry Dog Food", "placeholder.jpg", 4f, 189.00f, 12L, 30, 5.0f, "Montego", "Puppy", "Dry", "Dog", "Beef", categories);


    @Test
    void testCreateProductWithInvalidName() {
        assertNull(productWithInvalidName);
        System.out.println(productWithInvalidName);
    }

    @Test
    void testCreateProductWithNoImage() {
        assertNotNull(productWithNoImage);
        System.out.println(productWithNoImage);
    }

    @Test
    void testCreateTestProduct() {
        assertNotNull(testProduct);
        System.out.println(testProduct);
    }

    @Test
    void testCreateProductWithInvalidRating_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ProductFactory.createProduct(
                    4, "Montego Classic", "Dry Dog Food", "placeholder.jpg", 7f, 189.00f, 18L,
                    30, 5.0f, "Montego", "Puppy", "Dry", "Dog", "Beef", categories
            );
        });

        assertEquals("Rating should be between 0 and 5", exception.getMessage());
    }

    @Test
    void createProductWithInvalidDiscount_TooHigh() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                ProductFactory.createProduct(
                        1L,
                        "Test Dog Food",
                        "dog.jpg",
                        "Invalid discount example",
                        4.5f,
                        200.00f,
                        150L,
                        20,
                        1.2f,
                        "Canine Choice",
                        "Adult",
                        "Dry Food",
                        "Dog",
                        "Beef",categories
                )
        );

        assertEquals("Discount must be between 0 and 100", exception.getMessage());
    }

    @Test
    void testCreateProductWithValidRating() {
        Product product = ProductFactory.createProduct(
                5, "Bulldog eats", "Test Product", "placeholder.jpg", 4.5f, 100.00f, 15L,
                10, 1.5f, "Dogmor", "Adult", "Dry", "Dog", "Beef", categories
        );
        assertNotNull(product);
        assertEquals(4.5f, product.getRating());
    }

    @Test
    void testCreateProductWithSalePriceHigherThanPrice_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ProductFactory.createProduct(
                    7, "Special Kibble", "Premium cat food", "placeholder.jpg",
                    3.5f, 150.00f, 16L, 10, 1.0f, "No Name",
                    "Puppy", "Dry", "Cat", "pea", categories
            );
        });

        assertEquals("Sale price must be less than original price", exception.getMessage());
    }

    @Test
    void testCreateProductWithValidSalePrice() {
        Product product = ProductFactory.createProduct(
                8, "Healthy Bites", "Low fat dog snacks", "placeholder.jpg",
                4.2f, 100.00f, 15L, 15, 0.5f, "BiteCo",
                "Senior", "Dry", "Dog", "Beef", categories
        );

        assertNotNull(product);
        assertEquals(100.00, product.getPrice());
        System.out.println(product);
    }

    @Test
    void testCreateProductWithZeroStock_shouldLogWarningButCreateProduct() {
        Product product = ProductFactory.createProduct(
                12, "Low Stock Product", "This item is out of stock",
                "placeholder.jpg", 4.0f, 300.00f, 250L, 0,
                1.0f, "BrandZero", "Adult", "Dry", "Dog",
                "Duck", categories
        );

        assertNotNull(product);
        assertEquals(0, product.getStock());
        System.out.println(product);
    }

    @Test
    void testCreateProductWithLowStock() {
        Product lowStockProduct = ProductFactory.createProduct(
                8, "Montego Small", "Affordable Dog Food",
                "placeholder.jpg", 2f, 120.00f, 99L,
                3, 3.0f, "Montego", "Adult",
                "Dry", "Dog", "Chicken", categories
        );

        assertNotNull(lowStockProduct);
        assertTrue(lowStockProduct.getStock() <= 5);
        System.out.println("Low stock product created with stock: " + lowStockProduct.getStock());
    }
}