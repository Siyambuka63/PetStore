package za.ac.cput.repository.interfaces;

import za.ac.cput.domain.User;

import java.util.List;

public interface IUserRepository extends IRepository<User, Integer> {
    List<User> getAll();
}
