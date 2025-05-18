package za.ac.cput.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.user.*;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.contact = ?1")
    Optional<User> findByContact(Contact contact);

    @Query("select u from User u where u.address = ?1")
    Optional<User> findByAddress(ShippingAddress address);

    @Query("select u from User u where u.card = ?1")
    Optional<User> findByCard(Card card);

    @Query("select u from User u where u.role = ?1")
    Optional<List<User>> findAll(Role role);
}