NKHESO
package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.order.Order;
import za.ac.cput.domain.product.Product;
import static org.junit.jupiter.api.Assertions.*;

class OrderItemFactoryTest {

    private Order validOrder = new Order() {{ setId(1L); }};
    private Product validProduct = new Product() {{ setId(10L); }};

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
