package za.ac.cput.repository.product;
/*Oluhle Makhaye
* 222419636*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.product.Brand;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    Brand findByBrandID(Integer brandID);
    Brand findByBrandName(String brandName);
}
