package za.ac.cput.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.order.Order;
import za.ac.cput.domain.order.Status;
import za.ac.cput.domain.user.User;
import za.ac.cput.service.impl.OrderServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final OrderServiceImpl service;

    @Autowired
    public CartController(OrderServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/create/{id}")
    public Order create(@PathVariable Long id) {
        User user = new User.Builder().setUserID(id).build();
        return service.getActiveCart(user);
    }

    @GetMapping("/read/{orderID}")
    public Order read(@PathVariable Long orderID) {
        return service.read(orderID);
    }

    @PutMapping("/update")
    public Order update(@RequestBody Order order) {
        return service.update(order);
    }

    @DeleteMapping("/delete/{orderID}")
    public void delete(@PathVariable Long orderID) {
        service.delete(orderID);
    }

    @GetMapping("/getAll")
    public List<Order> getAll() {
        return service.getAll();
    }

    @GetMapping("/findByStatus/{status}")
    public Order findByStatus(@PathVariable String status) {
        return this.service.findByStatus(Enum.valueOf(Status.class, status)).orElse(null);
    }

    @PostMapping("/checkout/{id}")
    public Order checkoutCart(@PathVariable Long id) {
        User user = new User.Builder().setUserID(id).build();
        return service.checkoutCart(user);
    }

}
