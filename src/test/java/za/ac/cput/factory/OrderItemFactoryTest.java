//NKHESO
package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.order.Status;
import za.ac.cput.domain.orderItem.OrderItem;
import za.ac.cput.domain.order.Order;
import za.ac.cput.domain.Product;
import za.ac.cput.domain.review.Review;
import za.ac.cput.domain.user.*;
import za.ac.cput.factory.user.AddressFactory;
import za.ac.cput.factory.user.CardFactory;
import za.ac.cput.factory.user.UserFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemFactoryTest {
    LocalDate orderDate = LocalDate.now();
    LocalDate deliveryDate = LocalDate.parse("2025-05-10");
    Status status = Status.Busy;
    Card card = CardFactory.createCard(987554456, "Ozow", "Visa_4456", "4456", "Visa");
    Address shippingAddress = AddressFactory.createAddress(3453, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);
    Address billingAddress = AddressFactory.createAddress(3454, "apartment", "Cape Town", "237 Nkani Street", "7894", "7570", Type.Both);
    List<Review> reviews = new ArrayList<Review>();

    User user = UserFactory.createUser("Name", "Middle", "Last", "password123", reviews, card, shippingAddress, billingAddress, "test@gmail.com", "0987654321");

    private Order validOrder =OrderFactory.createOrder(1,user,orderDate,deliveryDate,8000,status);

    private List<String> categories = new ArrayList<>();

    private Product validProduct = ProductFactory.createProduct(1,"MissDog", "Nibbles","placeholder.jpg", 4f, 249.99f, 19L, 23, 1.34f, "SaveMor", "Adult", "Dry", "Dog", "Chicken", categories);

    private OrderItem orderItemWithNullOrder = OrderItemFactory.createOrderItem(null, validProduct, 10f, 1);
    private OrderItem orderItemWithNullProduct = OrderItemFactory.createOrderItem(validOrder, null, 10f, 1);
    private OrderItem orderItemWithInvalidPrice = OrderItemFactory.createOrderItem(validOrder, validProduct, -5f, 1);
    private OrderItem orderItemWithInvalidQuantity = OrderItemFactory.createOrderItem(validOrder, validProduct, 10f, 0);
    private OrderItem validOrderItem = OrderItemFactory.createOrderItem(validOrder, validProduct, 10f, 1);

    @Test
    void testCreateOrderItemWithNullOrder() {
        assertNull(orderItemWithNullOrder);
        System.out.println(orderItemWithNullOrder);
    }

    @Test
    void testCreateOrderItemWithNullProduct() {
        assertNull(orderItemWithNullProduct);
        System.out.println(orderItemWithNullProduct);
    }

    @Test
    void testCreateOrderItemWithInvalidPrice() {
        assertNull(orderItemWithInvalidPrice);
        System.out.println(orderItemWithInvalidPrice);
    }

    @Test
    void testCreateOrderItemWithInvalidQuantity() {
        assertNull(orderItemWithInvalidQuantity);
        System.out.println(orderItemWithInvalidQuantity);
    }

    @Test
    void testCreateValidOrderItem() {
        assertNotNull(validOrderItem);
        System.out.println(validOrderItem);
    }
}
