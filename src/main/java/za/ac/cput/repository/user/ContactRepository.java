package za.ac.cput.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.user.Contact;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    @Query("select c from Contact c where c.email = ?1")
    Optional<Contact> findByEmail(String email);

    @Query("select c from Contact c where c.phone = ?1")
    Optional<Contact> findByPhone(String phone);
}