package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.user.Address;
import za.ac.cput.domain.user.Contact;
import za.ac.cput.service.user.impl.AddressServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressServiceImpl service;

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
    @DeleteMapping("/delete/{contactId}")
    public void delete(@PathVariable Long addressId) {
        service.delete(addressId);
    }

    @GetMapping("/getAll")
    public List<Address> getAll() {
        return service.getAll();
    }
    @GetMapping("/findByAddressId/{addressId}")
    public Address findByAddressId(@PathVariable Long addressId) {
        return this.service.findByAddressId(addressId); }

    @GetMapping("findByPostalCode/{postalCode}")
    public Address findByPostalCode(@PathVariable String postalCode) {
        return this.service.findByPostalCode(postalCode); }
}


