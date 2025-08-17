// Nkheso Mathebula - 230762883
package za.ac.cput.factory;

import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.order.Order;
import za.ac.cput.domain.product.Product;
import za.ac.cput.util.Helper;

public class OrderItemFactory {

    public static OrderItem createOrderItem(Order order, Product product, float pricePerItem, int quantity) {
        if (order == null || product == null || !Helper.isValidPrice(pricePerItem) || !Helper.isValidQuantity(quantity)) {
            return null;
        }

        float totalPrice = pricePerItem * quantity;

        return new OrderItem.Builder()
                .setOrder(order)
                .setProduct(product)
                .setPricePerItem(pricePerItem)
                .setQuantity(quantity)
                .setTotalPrice(totalPrice)
                .build();
    }
}

