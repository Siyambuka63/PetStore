package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.persistence.*;

import java.util.List;
/*Oluhle Makhaye
 * 222419636
 * product class*/

@JsonDeserialize(builder = Product.Builder.class)
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String productName;
    private String imageAddress;
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

    @ElementCollection
    private List<String> categories;

    protected Product() {
    }

    private Product(Builder builder) {
        id = builder.productID;
        productName = builder.productName;
        imageAddress = builder.imageAddress;
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

    public long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public String getDescription() {
        return description;
    }

    public float getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }


    @Transient // <== prevents this field from being stored in DB
    @JsonProperty
    public double getDiscountedPrice() {
        if (discountPercent <= 0) return price;
        return price - (price * discountPercent / 100);
    }

    public int getStock() {
        return stock;
    }

    public double getWeight() {
        return weight;
    }

    public String getBrand() {
        return brand;
    }

    public String getLifeStage() {
        return lifeStage;
    }

    public String getFoodType() {
        return foodType;
    }

    public String getPetType() {
        return petType;
    }

    public String getFlavour() {
        return flavour;
    }

    public List<String> getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", imageAddress='" + imageAddress + '\'' +
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
                ", categories=" + categories +
                '}';
    }

    @JsonPOJOBuilder(withPrefix = "set") // matches your builder methods
    public static class Builder {
        private long productID;
        private String productName;
        private String imageAddress;
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

        public Builder setImageAddress(String imageAddress) {
            this.imageAddress = imageAddress;
            return this;
        }

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
            this.imageAddress = product.imageAddress;
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