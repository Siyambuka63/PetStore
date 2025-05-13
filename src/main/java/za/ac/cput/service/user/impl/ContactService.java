package za.ac.cput.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.user.Contact;
import za.ac.cput.repository.user.ContactRepository;
import za.ac.cput.service.user.IContactService;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService implements IContactService {

    @Autowired
    private static IContactService service;

    @Autowired
    private ContactRepository repository;

    @Override
    public Contact create(Contact contact) {
        return repository.save(contact);
    }

    @Override
    public Contact read(Integer id) {
        return repository.getReferenceById(id);
    }

    @Override
    public Contact update(Contact contact) {
        return repository.save(contact);
    }

    @Override
    public boolean delete(Integer id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Contact> getAll() {
        return repository.findAll();
    }

    public Contact findByEmail(String email) {
        Optional<Contact> optional = repository.findByEmail(email);
        return optional.orElse(null);
    }

    public Contact findByPhone(String phone) {
        Optional<Contact> optional = repository.findByPhone(phone);
        return optional.orElse(null);
    }
}
