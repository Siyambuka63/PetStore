package za.ac.cput.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
/*Oluhle Makhaye
 * 222419636
 * product class*/

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String productName;
    private String description;
    private float rating;
    private double price;
    private double discountPercent;
    private int stock;
    private double weight;
    private String brand;
    private String lifeStage;
    private String foodType;
    private String petType;
    private String flavour;

    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;

    @ElementCollection
    private List<String> categories;

    protected Product() {
    }

    private Product(Builder builder) {
        id = builder.productID;
        productName = builder.productName;
        description = builder.description;
        rating = builder.rating;
        price = builder.price;
        discountPercent = builder.discountPercent;
        stock = builder.stock;
        weight = builder.weight;
        brand = builder.brand;
        lifeStage = builder.lifeStage;
        foodType = builder.foodType;
        petType = builder.petType;
        flavour = builder.flavour;
        categories = builder.categories;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                ", discountPercent=" + discountPercent +
                ", stock=" + stock +
                ", weight=" + weight +
                ", brand='" + brand + '\'' +
                ", lifeStage='" + lifeStage + '\'' +
                ", foodType='" + foodType + '\'' +
                ", petType='" + petType + '\'' +
                ", flavour='" + flavour + '\'' +
                ", imageName='" + imageName + '\'' +
                ", imageType='" + imageType + '\'' +
                ", imageData=" + Arrays.toString(imageData) +
                ", categories=" + categories +
                '}';
    }

    public static class Builder {
        private long productID;
        private String productName;
        private String description;
        private float rating;
        private double price;
        private double discountPercent;
        private int stock;
        private double weight;
        private String brand;
        private String lifeStage;
        private String foodType;
        private String petType;
        private String flavour;
        private List<String> categories;

        public Builder setProductID(long productID) {
            this.productID = productID;
            return this;
        }

        public Builder setProductName(String productName) {
            this.productName = productName;
            return this;
        }

//        public Builder setImageAddress(String imageAddress) {
//            this.imageAddress = imageAddress;
//            return this;
//        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setRating(float rating) {
            this.rating = rating;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setDiscountPercent(double discountPercent) {
            this.discountPercent = discountPercent;
            return this;
        }

        public Builder setStock(int stock) {
            this.stock = stock;
            return this;
        }

        public Builder setWeight(double weight) {
            this.weight = weight;
            return this;
        }

        public Builder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder setLifeStage(String lifeStage) {
            this.lifeStage = lifeStage;
            return this;
        }

        public Builder setFoodType(String foodType) {
            this.foodType = foodType;
            return this;
        }

        public Builder setPetType(String petType) {
            this.petType = petType;
            return this;
        }

        public Builder setFlavour(String flavour) {
            this.flavour = flavour;
            return this;
        }

        public Builder setCategories(List<String> categories) {
            this.categories = categories;
            return this;
        }

        public Builder copy(Product product) {
            this.productID = product.id;
            this.productName = product.productName;
//            this.imageAddress = product.imageAddress;
            this.description = product.description;
            this.rating = product.rating;
            this.price = product.price;
            this.discountPercent = product.discountPercent;
            this.stock = product.stock;
            this.weight = product.weight;
            this.brand = product.brand;
            this.lifeStage = product.lifeStage;
            this.foodType = product.foodType;
            this.petType = product.petType;
            this.flavour = product.flavour;
            this.categories = product.categories;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}