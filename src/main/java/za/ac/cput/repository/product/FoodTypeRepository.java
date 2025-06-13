package za.ac.cput.repository.product;

/*Oluhle Makhaye
 * 222419636*/


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.product.FoodType;

import java.util.Optional;

@Repository
public interface FoodTypeRepository extends JpaRepository<FoodType, Integer> {
    Optional<FoodType> findByFoodTypeName(String foodTypeName);
    Optional<FoodType> findByFoodTypeID(Integer foodTypeID);
}
