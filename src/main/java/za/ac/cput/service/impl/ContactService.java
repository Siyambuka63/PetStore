package za.ac.cput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Contact;
import za.ac.cput.repository.ContactRepository;
import za.ac.cput.service.IContactService;

import java.util.List;

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
}
