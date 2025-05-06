package za.ac.cput.repository;

import za.ac.cput.domain.OrderItem;
import za.ac.cput.repository.interfaces.IOrderItemRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderItemRepository implements IOrderItemRepository {
    private static IOrderItemRepository repository = null;
    private List<OrderItem> orderItemList;

    private OrderItemRepository(){
        orderItemList = new ArrayList<OrderItem>();
    }

    public static  IOrderItemRepository getRepository(){
        if (repository == null){
            repository = new OrderItemRepository();
        }
        return repository;
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        boolean success = orderItemList.add(orderItem);
        if (success) {
            return orderItem;
        }
        return null;
    }

    @Override
    public OrderItem read(Integer ID) {
        for(OrderItem orderItem : orderItemList){
            if (orderItem.getOrderItemID() == ID){
                return orderItem;
            }
        }
        return null;
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        int id = orderItem.getOrderItemID();
        OrderItem orderItemToDelete = read(id);
        if (orderItemToDelete == null) return null;
        OrderItem deletedOrderItem = delete(id);
        if (deletedOrderItem == null) return null;
        return create(orderItem);
    }

    @Override
    public OrderItem delete(Integer id) {
        OrderItem orderItem = read(id);
        if (orderItem == null) return null;
        if (orderItemList.remove(orderItem)) return orderItem;
        return null;
    }

    @Override
    public List<OrderItem> getAll() {
        return orderItemList;
    }
}
