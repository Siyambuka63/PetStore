package za.ac.cput.factory;

import za.ac.cput.domain.order.Order;
import za.ac.cput.domain.order.Status;
import za.ac.cput.domain.orderItem.OrderItem;
import za.ac.cput.domain.user.User;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.List;

public class OrderFactory {
    public static Order createOrder(
            long orderID,
            User user,
            LocalDate orderDate,
            LocalDate deliveryDate,
            float price,
            Status status
    ) {
        if (orderDate == null
                || deliveryDate == null
                || status == null
                || user == null
                || !Helper.isValidPrice(price)) {
            return null;
        }
        return new Order.Builder()
                .setOrderID(orderID)
                .setUser(user)
                .setOrderDate(orderDate)
                .setDeliveryDate(deliveryDate)
                .setPrice(price)
                .setStatus(status)
                .build();
    }
}