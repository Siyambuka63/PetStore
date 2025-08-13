package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductFactoryTest {

    private static Product product;

    private List<String> categories = new ArrayList<>();

 /*   private Product productWithInvalidRating = ProductFactory.createProduct(2,"MissDog", "Nibbles","placeholder.jpg", 6f, 249.99, 199.99, true, 23, 1.34, "SaveMor", "Adult", "Dry", "Dog", "Chicken", categories);*/
    private Product productWithInvalidName = ProductFactory.createProduct(1, "", "imageAddress", "placeholder.jpg", 4.5f, 100.0, 90.0, true, 50, 1.0, "Canin Cuisine", "Adult", "Dry", "Dog", "Peas", categories);
    private Product productWithNoImage = ProductFactory.createProduct(3, "MissDog", "Nutritional bites", "", 4.5f, 100.0, 90.0, true, 50, 1.0, "Canin Cuisine", "Adult", "Dry", "Dog", "Chicken", categories);
    private Product testProduct = ProductFactory.createProduct(4, "Montego Classic", "Dry Dog Food","placeholder.jpg", 4f, 189.00, 189.00, false, 30, 5.0, "Montego", "Puppy", "Dry", "Dog", "Beef", categories);

 /*   @Test
    void testCreateProductWithInvalidRating() {

        assertNull(productWithInvalidRating);
        System.out.println(productWithInvalidRating);
    }*/

    @Test
    void testCreateProductWithInvalidName() {
        assertNull(productWithInvalidName);
        System.out.println(productWithInvalidName);
    }

    @Test
    void testCreateProductWithNoImage() {
        assertNull(productWithNoImage);
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
                    4, "Montego Classic", "Dry Dog Food", "placeholder.jpg", 7f, 189.00, 189.00,
                    false, 30, 5.0, "Montego", "Puppy", "Dry", "Dog", "Beef", categories
            );
        });

        assertEquals("Rating should be between 0 and 5", exception.getMessage());
    }

    @Test
    void testCreateProductWithValidRating() {
        Product product = ProductFactory.createProduct(
                5, "Bulldog eats", "Test Product", "placeholder.jpg", 4.5f, 100.00, 95.00, true,
                10, 1.5, "Dogmor", "Adult", "Dry", "Dog", "Beef", categories
        );
        assertNotNull(product);
        assertEquals(4.5f, product.getRating());
    }

    @Test
    void testCreateProductWithSalePriceHigherThanPrice_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ProductFactory.createProduct(
                    7, "Special Kibble", "Premium cat food", "placeholder.jpg",
                    3.5f, 150.00, 160.00, true, 10, 1.0, "No Name",
                    "Puppy", "Dry", "Cat", "pea", categories
            );
        });

        assertEquals("Sale price must be less than original price", exception.getMessage());
    }

    @Test
    void testCreateProductWithValidSalePrice() {
        Product product = ProductFactory.createProduct(
                8, "Healthy Bites", "Low fat dog snacks", "placeholder.jpg",
                4.2f, 100.00, 75.00, true, 15, 0.5, "BiteCo",
                "Senior", "Dry", "Dog", "Beef", categories
        );

        assertNotNull(product);
        assertEquals(75.00, product.getSalePrice());
        assertEquals(100.00, product.getPrice());
    }

    @Test
    void testCreateProductWithZeroStock_shouldLogWarningButCreateProduct() {
        Product product = ProductFactory.createProduct(
                12, "Low Stock Product", "This item is out of stock",
                "placeholder.jpg", 4.0f, 300.00, 250.00, true, 0,
                1.0, "BrandZero", "Adult", "Dry", "Dog",
                "Duck", categories
        );

        assertNotNull(product);
        assertEquals(0, product.getStock());
    }

    @Test
    void testCreateProductWithLowStock() {
        Product lowStockProduct = ProductFactory.createProduct(
                8, "Montego Small", "Affordable Dog Food",
                "placeholder.jpg", 2f, 120.00, 99.00,
                true, 3 , 3.0, "Montego", "Adult",
                "Dry", "Dog", "Chicken", categories
        );

        assertNotNull(lowStockProduct);
        assertTrue(lowStockProduct.getStock() <= 5);
        System.out.println("Low stock product created with stock: " + lowStockProduct.getStock());
    }




}
