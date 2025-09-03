// Nkheso Mathebula - 230762883
package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.orderItem.OrderItem;
import za.ac.cput.domain.orderItem.OrderItemId;
import za.ac.cput.service.impl.OrderItemServiceImpl;

import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/order-item")
public class OrderItemController {

    private final OrderItemServiceImpl service;

    @Autowired
    public OrderItemController(OrderItemServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/create")
    public OrderItem create(@RequestBody OrderItem orderItem) {
        return service.create(orderItem);
    }

    @GetMapping("/read/{orderId}/{productId}")
    public OrderItem read(@PathVariable Long orderId, @PathVariable Long productId) {
        return service.read(new OrderItemId(orderId, productId));
    }

    @PutMapping("/update")
    public OrderItem update(@RequestBody OrderItem orderItem) {
        return service.update(orderItem);
    }

    @DeleteMapping("/delete/{orderId}/{productId}")
    public void delete(@PathVariable Long orderId, @PathVariable Long productId) {
        service.delete(new OrderItemId(orderId, productId));
    }

    @GetMapping("/getAll")
    public List<OrderItem> getAll() {
        return service.getAll();
    }
}
