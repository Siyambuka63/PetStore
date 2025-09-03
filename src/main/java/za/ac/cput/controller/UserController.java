package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.user.User;
import za.ac.cput.service.user.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
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
    public User read(@PathVariable long id) {
        return service.read(id);
    }

    @PostMapping("/update")
    public User update(@RequestBody User user) {
        return service.update(user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) { service.delete(id); }

    @GetMapping("/getAll")
    public List<User> getAll() {
        return service.getAll();
    }

PostMapping("/login")
public ResponseEntity<?> login(@RequestBody User user) {
    User foundUser = service.login(user.getEmail(), user.getPassword());
    if (foundUser != null) {
        return ResponseEntity.ok(foundUser);
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\":\"Invalid credentials\"}");
}
}
