package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.user.User;
import za.ac.cput.service.user.impl.UserServiceImpl;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/read/{email}")
    public User read(@PathVariable String email) {
        return service.read(email);
    }

    @PostMapping("/update")
    public User update(@RequestBody User user) {
        return service.update(user);
    }

    @DeleteMapping("/delete/{email}")
    public void delete(@PathVariable String email) {
        service.delete(email);
    }

    @GetMapping("/getAll")
    public List<User> getAll() {
        return service.getAll();
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return service.login(user.getEmail(), user.getPassword());
    }

    @PostMapping("/verify")
    public String verify(@RequestBody User user) {
        return service.verify(user.getEmail(), user.getPassword());
    }

    @GetMapping("/email-exists/{email}")
    public boolean emailExists(@PathVariable String email) {
        User user = service.read(email);
        return user != null;
    }
}