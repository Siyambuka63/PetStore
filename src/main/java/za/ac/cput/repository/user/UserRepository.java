package za.ac.cput.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.user.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

        User findByEmailAndPassword(String email, String password);
    }


