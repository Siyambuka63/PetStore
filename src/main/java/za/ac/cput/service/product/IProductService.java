package za.ac.cput.service.product;

import za.ac.cput.domain.product.Product;
import za.ac.cput.service.IService;

import java.util.List;

public interface IProductService extends IService<Product, Integer> {
    List<Product> getAll();

    Product findByProductName(String productName);

    Product findByProductID(Integer productID);
}
