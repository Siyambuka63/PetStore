package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;

public class ProductFactoryTest {

    private static Product product;

    private List<String> categories = new ArrayList<>();

    private Product productWithValidDetails = ProductFactory.createProduct(2,"MissDog", "Nibbles","placeholder.jpg", 4f, 249.99, 199.99, true, 23, 1.34, "SaveMor", "Adult", "Dry", "Dog", "Chicken", categories);
    private Product productWithInvalidName = ProductFactory.createProduct(1, "", "imageAddress", "placeholder.jpg", 4.5f, 100.0, 90.0, true, 50, 1.0, "Canin Cuisine", "Adult", "Dry", "Dog", "Peas", categories);
    private Product productWithInvalidImage = ProductFactory.createProduct(3, "MissDog", "", "placeholder.jpg", 4.5f, 100.0, 90.0, true, 50, 1.0, "Canin Cuisine", "Adult", "Dry", "Dog", "Chicken", categories);
    private Product testProduct = ProductFactory.createProduct(4, "Montego Classic", "Dry Dog Food","placeholder.jpg", 4f, 189.00, 189.00, false, 30, 5.0, "Montego", "Puppy", "Dry", "Dog", "Beef", categories);


    @Test
    void testCreateProductWithInvalidName() {
        assertNull(productWithInvalidName);
        System.out.println(productWithInvalidName);
    }

    @Test
    void testCreateProductWithInvalidImage() {
        assertNull(productWithInvalidImage);
        System.out.println(productWithInvalidImage);
    }

    @Test
    void testCreateProductWithValidDetails() {
        assertNull(productWithValidDetails);
        System.out.println(productWithValidDetails);
    }

    @Test
    void testCreateTestProduct() {
        assertNull(testProduct);
        System.out.println(testProduct);
    }

}
