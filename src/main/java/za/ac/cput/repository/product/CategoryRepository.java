package za.ac.cput.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.product.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> { }
