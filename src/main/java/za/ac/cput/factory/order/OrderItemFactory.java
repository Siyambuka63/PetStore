package za.ac.cput.factory.order;

import za.ac.cput.domain.order.OrderItem;
import za.ac.cput.util.Helper;

public class OrderItemFactory {
    public static OrderItem createOrderItem(
            long orderItemID,
            long productID,
            float totalPrice,
            float pricePerItem
    ){
        if(Helper.isValidPrice(totalPrice)){
            return null;
        }
        if(Helper.isValidPrice(pricePerItem)){
            return null;
        }
        return new OrderItem.Builder()
                .setOrderItemID(orderItemID)
                .setProductID(productID)
                .setTotalPrice(totalPrice)
                .setPricePerItem(pricePerItem)
                .build();
    }
}
