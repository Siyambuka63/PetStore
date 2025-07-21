package za.ac.cput.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.product.LifeStage;

@Repository
public interface LifeStageRepository extends JpaRepository<LifeStage, Integer> {

}
