package za.ac.cput.repository;

import za.ac.cput.domain.Order;
import za.ac.cput.repository.interfaces.IOrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IOrderRepository {
    private static IOrderRepository repository = null;
    private List<Order> orderList;

    private OrderRepository(){
        orderList = new ArrayList<Order>();
    }

    public static  IOrderRepository getRepository(){
        if (repository == null){
            repository = new OrderRepository();
        }
        return repository;
    }

    @Override
    public Order create(Order order) {
        boolean success = orderList.add(order);
        if (success) {
            return order;
        }
        return null;
    }

    @Override
    public Order read(Integer ID) {
        for(Order Order : orderList){
            if (Order.getOrderID() == ID){
                return Order;
            }
        }
        return null;
    }

    @Override
    public Order update(Order Order) {
        int id = Order.getOrderID();
        Order OrderToDelete = read(id);
        if (OrderToDelete == null) return null;
        Order deletedOrder = delete(id);
        if (deletedOrder == null) return null;
        return create(Order);
    }

    @Override
    public Order delete(Integer id) {
        Order Order = read(id);
        if (Order == null) return null;
        if (orderList.remove(Order)) return Order;
        return null;
    }

    @Override
    public  List<Order> getAll() {
        return orderList;
    }
}
