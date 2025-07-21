package za.ac.cput.service.product;

import za.ac.cput.domain.product.Brand;
import za.ac.cput.service.IService;

import java.util.List;

public interface IBrandService extends IService<Brand, Integer> {
    List<Brand> getAll();

    Brand findByBrandName(String brandName);

    Brand findByBrandID(int brandID);
}
