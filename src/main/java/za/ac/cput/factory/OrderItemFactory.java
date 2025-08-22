// Nkheso Mathebula - 230762883
package za.ac.cput.factory;

import za.ac.cput.domain.Product;
import za.ac.cput.domain.orderItem.OrderItem;
import za.ac.cput.domain.order.Order;
import za.ac.cput.domain.orderItem.OrderItemId;
import za.ac.cput.util.Helper;

public class OrderItemFactory {
    public static OrderItem createOrderItem(OrderItemId orderItemId, float pricePerItem, int quantity) {
        if (orderItemId == null) return null;
        if (!Helper.isValidPrice(pricePerItem)) return null;
        if (!Helper.isValidQuantity(quantity)) return null;

        float totalPrice = pricePerItem * quantity;

        return new OrderItem.Builder()
                .setId(orderItemId)
                .setPricePerItem(pricePerItem)
                .setQuantity(quantity)
                .setTotalPrice(totalPrice)
                .build();
    }

    public static OrderItem createOrderItem(long orderId, long productId, float pricePerItem, int quantity) {
        OrderItemId orderItemId = new OrderItemId(orderId, productId);

        return createOrderItem(orderItemId, pricePerItem, quantity);
    }

    public static OrderItem createOrderItem(Order order, Product product, float pricePerItem, int quantity) {
        if (order == null) return null;
        if (product == null) return null;

        return createOrderItem(order.getId(), product.getId(), pricePerItem, quantity);
    }
}

