package za.ac.cput.repository;

import za.ac.cput.domain.Contact;
import za.ac.cput.repository.interfaces.IContactRepository;

import java.util.ArrayList;
import java.util.List;

public class ContactRepository implements IContactRepository {

    private static ContactRepository repository;

    private List<Contact> contacts;

    private ContactRepository() {
        contacts = new ArrayList<Contact>();
    }

    public static ContactRepository getRepository() {
        if (repository == null) {
            repository = new ContactRepository();
        }
        return repository;
    }

    @Override
    public Contact create(Contact contact) {
        if (contacts.add(contact)) return contact;
        return null;
    }

    @Override
    public Contact read(String email) {
        return contacts.stream()
                .filter(contact -> contact.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Contact update(Contact contact) {
        String email = contact.getEmail();
        Contact oldContact = read(email);
        if (oldContact == null) return null;
        Contact deletedContact = delete(email);
        if (deletedContact == null) return null;
        return create(contact);
    }

    @Override
    public Contact delete(String email) {
        Contact contact = read(email);
        if (contact == null) return null;
        if (contacts.remove(contact)) return contact;
        return null;
    }

    @Override
    public List<Contact> getAll() {
        return contacts;
    }
}
