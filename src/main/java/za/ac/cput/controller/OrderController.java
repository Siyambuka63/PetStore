package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.order.Order;
import za.ac.cput.domain.order.Status;
import za.ac.cput.domain.user.User;
import za.ac.cput.service.impl.OrderServiceImpl;
import za.ac.cput.service.user.impl.UserServiceImpl;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderServiceImpl service;
    private UserServiceImpl userService;

    @Autowired
    public OrderController(OrderServiceImpl service, UserServiceImpl userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping("/create")
    public Order create(@RequestBody Order order){
        return service.create(order);
    }

    @GetMapping("/read/{id}")
    public Order read(@PathVariable long id){
        return service.read(id);
    }

    @PostMapping("/update")
    public Order update(@RequestBody Order order){
        return service.update(order);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){service.delete(id);}

    @GetMapping("/getAll")
    public List<Order> getAll(){
        return service.getAll();
    }

    @GetMapping("getCart/{email}")
    public Order getCart(@PathVariable String email){
        Order cart = service.getCart(email);
        if (cart == null) {
            User user = userService.read(email);
            System.out.println(user);
            if (user == null) {
                return null;
            }

            cart = service.create(new Order.Builder()
                    .setOrderDate(LocalDate.now())
                    .setStatus(Status.Cart)
                    .setUser(user)
                    .build());
        }
        return cart;
    }
}
