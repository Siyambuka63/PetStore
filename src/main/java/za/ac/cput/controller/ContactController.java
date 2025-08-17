package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.user.Contact;
import za.ac.cput.service.user.impl.ContactServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private ContactServiceImpl service;

    @Autowired
    public ContactController(ContactServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Contact create(@RequestBody Contact contact) {
        return service.create(contact);
    }

    @GetMapping("/read/{contactId}")
    public Contact read(@PathVariable Long contactId) {
        return service.read(contactId);
    }

    @PutMapping("/update")
    public Contact update(@RequestBody Contact contact) {
        return service.update(contact);
    }

    @DeleteMapping("/delete/{contactId}")
    public void delete(@PathVariable Long contactId) {
        service.delete(contactId);
    }

    @GetMapping("/getAll")
    public List<Contact> getAll() {
        return service.getAll();
    }

    @GetMapping("/findByEmail/{email}")
    public Contact findByEmail(@PathVariable String email) { return this.service.findByEmail(email); }

    @GetMapping("findByPhone/{phone}")
    public Contact findByPhone(@PathVariable String phone) { return this.service.findByPhone(phone); }
}