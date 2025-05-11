package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.User;
import za.ac.cput.service.impl.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return service.create(user);
    }

    @GetMapping("/read/{id}")
    public User read(@PathVariable int id) {
        return service.read(id);
    }

    @PutMapping("/update")
    public User update(@RequestBody User user) {
        return service.update(user);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable int id) {
        return service.delete(id);
    }

    @GetMapping("/getAll")
    public List<User> getAll() {
        return service.getAll();
    }
}