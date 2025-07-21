package za.ac.cput.repository.product;

/*Oluhle Makhaye
 * 222419636*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.product.PetType;

import java.util.Optional;

@Repository
public interface PetTypeRepository extends JpaRepository<PetType, Integer> {
    Optional<PetType> findByPetTypeID(int petTypeID);
    PetType findByPetTypeName(String petTypeName);

}
