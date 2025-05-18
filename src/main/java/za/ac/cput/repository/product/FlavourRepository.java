package za.ac.cput.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.product.Flavour;

public interface FlavourRepository extends JpaRepository<Flavour, Integer> { }
