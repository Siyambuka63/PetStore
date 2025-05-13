package za.ac.cput.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.user.User;
import za.ac.cput.repository.user.UserRepository;
import za.ac.cput.service.user.IUserService;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private static IUserService service;

    @Autowired
    private UserRepository repository;

    @Override
    public User create(User user) { return repository.save(user); }

    @Override
    public User read(Integer id) { return repository.getReferenceById(id); }

    @Override
    public User update(User user) { return repository.save(user); }



    @Override
    public boolean delete(Integer id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<User> getAll() { return repository.findAll(); }
}
