package za.ac.cput.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.user.Contact;
import za.ac.cput.repository.user.ContactRepository;
import za.ac.cput.service.user.ContactService;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private static ContactService contactService;

    @Autowired
    private ContactRepository repository;

    @Override
    public Contact create(Contact contact) {
        return repository.save(contact);
    }

    @Override
    public Contact read(Long contactId) {
        return repository.getReferenceById(contactId);
    }
  
    @Override
    public Contact update(Contact contact) {
        return repository.save(contact);
    }

    @Override
    public void delete(Long contactId) {
        repository.deleteById(contactId);
    }

    @Override
    public List<Contact> getAll() {
        return repository.findAll();
    }

    @Override
    public Contact findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Contact findByPhone(String phone) {
        return repository.findByPhone(phone);
    }
}
