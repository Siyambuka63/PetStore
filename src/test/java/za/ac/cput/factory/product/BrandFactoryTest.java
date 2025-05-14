package za.ac.cput.factory.product;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.product.Brand;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BrandFactoryTest {
    @Test
    void createBrand(){
        Brand brand = BrandFactory.createBrand(1,"Montego");
        assertNotNull(brand);
        System.out.println(brand.toString());
    }

    @Test
    void createBrandWithoutBrandName(){
        Brand brand = BrandFactory.createBrand(1,null);
        assertNotNull(brand);
        System.out.println(brand.toString());
    }

//    @Test
//    void createBrandWithInvalidBrandID(){
//        Brand brand = BrandFactory.createBrand(one,"Royal Canin");
//        assertNotNull(brand);
//        System.out.println(brand.toString());
//    }

}
