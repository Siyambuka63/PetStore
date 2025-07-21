package za.ac.cput.service;

import za.ac.cput.domain.Product;

import java.util.List;

public interface IProductService extends IService<Product, Integer> {
    List<Product> getAll();

    Product findByProductName(String productName);

    Product findByProductID(Integer productID);
}
