package za.ac.cput.factory.order;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.order.OrderItem;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrderItemFactoryTest {
    private static OrderItem orderItem = OrderItemFactory.createOrderItem(1,1,123,4);
    @Test
    void createOrderItem() {
        assertNotNull(orderItem);
        System.out.println(orderItem);
    }
}