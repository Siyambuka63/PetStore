package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.order.Order;
import za.ac.cput.service.impl.OrderServiceImpl;

import java.util.List;
@CrossOrigin("http://localhost:8082/")
@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderServiceImpl service;

    @Autowired
    public OrderController(OrderServiceImpl service) {
        this.service = service;
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
}
