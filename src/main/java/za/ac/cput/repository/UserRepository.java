package za.ac.cput.repository;

import za.ac.cput.domain.User;
import za.ac.cput.repository.interfaces.IUserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {

    private static UserRepository repository;

    private List<User> users;

    private UserRepository() {
        users = new ArrayList<User>();
    }

    public static UserRepository getRepository() {
        if (repository == null) {
            repository = new UserRepository();
        }
        return repository;
    }

    @Override
    public User create(User user) {
        if (users.add(user)) return user;
        return null;
    }

    @Override
    public User read(Integer id) {
        return users.stream()
                .filter(user -> user.getUserID() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public User update(User user) {
        int id = user.getUserID();
        User oldUser = read(id);
        if (oldUser == null) return null;
        User deletedUser = delete(id);
        if (deletedUser == null) return null;
        return create(user);
    }

    @Override
    public User delete(Integer id) {
        User user = read(id);
        if (user == null) return null;
        if (users.remove(user)) return user;
        return null;
    }

    @Override
    public List<User> getAll() {
        return users;
    }
}
