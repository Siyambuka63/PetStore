package za.ac.cput.factory;

import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.List;

public class OrderItemFactory {
    public static OrderItem createOrder(
            int orderItemID,
            int productID,
            double price
    ){
        if(Helper.isValidPrice(price)){
            return null;
        }
        return new OrderItem.Builder()
                .setOrderItemID(orderItemID)
                .setProductID(productID)
                .setPrice(price)
                .build();
    }
}
