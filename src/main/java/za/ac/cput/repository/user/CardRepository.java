package za.ac.cput.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.user.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

}
