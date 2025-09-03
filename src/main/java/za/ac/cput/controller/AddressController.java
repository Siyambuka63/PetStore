package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.user.Address;
import za.ac.cput.service.user.impl.AddressServiceImpl;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/address")
public class    AddressController {

    private final AddressServiceImpl service;

    @Autowired
    public AddressController(AddressServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Address create(@RequestBody Address address) {
        return service.create(address);
    }

    @GetMapping("/read/{addressId}")
    public Address read(@PathVariable Long addressId) {
        return service.read(addressId);
    }

    @PutMapping("/update")
    public Address update(@RequestBody Address address) {
        return service.update(address);
    }

    @DeleteMapping("/delete/{addressId}")
    public void delete(@PathVariable Long addressId) {
        service.delete(addressId);
    }

    @GetMapping("/getAll")
    public List<Address> getAll() {
        return service.getAll();
    }

    @GetMapping("findByPostalCode/{postalCode}")
    public List<Address> findByPostalCode(@PathVariable String postalCode) {
        return this.service.findByPostalCode(postalCode);
    }
}