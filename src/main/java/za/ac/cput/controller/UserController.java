package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.user.User;
import za.ac.cput.service.user.impl.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserServiceImpl service;

    @Autowired
    public UserController(UserServiceImpl service) {
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