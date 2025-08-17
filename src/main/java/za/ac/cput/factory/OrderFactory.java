package za.ac.cput.factory;

import za.ac.cput.domain.order.Order;
import za.ac.cput.domain.order.Status;
import za.ac.cput.domain.orderItem.OrderItem;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.List;

public class OrderFactory {
    public static Order createOrder(
            long orderID,
            long userID,
            LocalDate orderDate,
            LocalDate deliveryDate,
            float price,
            List<OrderItem> items,
            Status status
    ) {
        if (!Helper.isValidDate(orderDate)
                || !Helper.isValidDate(deliveryDate)
                || items.isEmpty()
                || status == null
                || !Helper.isValidPrice(price)) {
            return null;
        }
        return new Order.Builder()
                .setOrderID(orderID)
                .setUserID(userID)
                .setOrderDate(orderDate)
                .setDeliveryDate(deliveryDate)
                .setPrice(price)
                .setItems(items)
                .setStatus(status)
                .build();
    }
}