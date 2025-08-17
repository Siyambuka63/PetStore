package za.ac.cput.factory.order;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.order.Order;
import za.ac.cput.domain.order.Status;
import za.ac.cput.factory.OrderFactory;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderFactoryTest {

    @Test
    void a_createOrder() {
        LocalDate orderDate = LocalDate.now();
        LocalDate deliveryDate = LocalDate.parse("2025-05-10");
        OrderItem orderItem = OrderItemFactory.createOrderItem(1,1,50,100,10);
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        Status status = Status.Busy;

        Order order = OrderFactory.createOrder(1,1,orderDate,deliveryDate,8000,orderItems,status);
        assertNotNull(order);
        System.out.println(order.toString());
    }

    @Test
    void b_createOrderWithoutOrderDate() {
        LocalDate orderDate = LocalDate.now();
        LocalDate deliveryDate = LocalDate.parse("2025-05-10");
        OrderItem orderItem = OrderItemFactory.createOrderItem(1,1,50,100,10);
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        Status status = Status.Busy;

        Order orderWithInvalidOrderDate = OrderFactory.createOrder(1,1,null,deliveryDate,8000,orderItems,status);
        assertNotNull(orderWithInvalidOrderDate);
        System.out.println(orderWithInvalidOrderDate.toString());
    }
    @Test
    void c_createOrderWithInvalidDeliveryDate() {
        LocalDate orderDate = LocalDate.now();
        LocalDate deliveryDate = LocalDate.parse("2025-05-10");
        OrderItem orderItem = OrderItemFactory.createOrderItem(1,1,50,100,10);
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        Status status = Status.Busy;
        Order orderWithInvalidDeliveryDate = OrderFactory.createOrder(1,1,orderDate,null,8000,orderItems,status);
        assertNotNull(orderWithInvalidDeliveryDate);
        System.out.println(orderWithInvalidDeliveryDate.toString());
    }
    @Test
    void d_createOrderWithoutOrderItems() {
        LocalDate orderDate = LocalDate.now();
        LocalDate deliveryDate = LocalDate.parse("2025-05-10");
        OrderItem orderItem = OrderItemFactory.createOrderItem(1,1,50,100,10);
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        Status status = Status.Busy;

        Order orderWithInvalidOrderItems = OrderFactory.createOrder(1,1,orderDate,deliveryDate,8000,null,status);
        assertNotNull(orderWithInvalidOrderItems);
        System.out.println(orderWithInvalidOrderItems.toString());
    }
    @Test
    void e_createOrderWithInvalidStatus() {
        LocalDate orderDate = LocalDate.now();
        LocalDate deliveryDate = LocalDate.parse("2025-05-10");
        OrderItem orderItem = OrderItemFactory.createOrderItem(1,1,50,100,10);
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        Status status = Status.Busy;

        Order orderWithInvalidStatus = OrderFactory.createOrder(1,1,orderDate,deliveryDate,8000,orderItems,null);
        assertNotNull(orderWithInvalidStatus);
        System.out.println(orderWithInvalidStatus.toString());
    }
}