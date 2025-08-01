package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.List;
/*Oluhle Makhaye
* 222419636
* product class*/

@Entity
public class Product {
    @Id
    private long productID;
    private String productName;
    private String imageAddress;
    private String description;
    private float rating;
    private double price;
    private double salePrice;
    private boolean onSale;
    private int stock;
    private double weight;
    private String brand;
    private String lifeStage;
    private String foodType;
    private String petType;
    private String flavour;
    @OneToMany
    private List<String> categories;

    protected Product () {
    }

    private Product(Builder builder) {
        productID = builder.productID;
        productName = builder.productName;
        imageAddress = builder.imageAddress;
        description = builder.description;
        rating = builder.rating;
        price = builder.price;
        salePrice = builder.salePrice;
        onSale = builder.onSale;
        stock = builder.stock;
        weight = builder.weight;
        brand = builder.brand;
        lifeStage = builder.lifeStage;
        foodType = builder.foodType;
        petType = builder.petType;
        flavour = builder.flavour;
        categories = builder.categories;
    }

    public long getProductID() {
        return productID;
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
    public double getSalePrice() {
        return salePrice;
    }
    public boolean isOnSale() {
        return onSale;
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
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", imageAddress='" + imageAddress + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                ", salePrice=" + salePrice +
                ", onSale=" + onSale +
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

    public static class Builder {
        private long productID;
        private String productName;
        private String imageAddress;
        private String description;
        private float rating;
        private double price;
        private double salePrice;
        private boolean onSale;
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

        public Builder setSalePrice(double salePrice) {
            this.salePrice = salePrice;
            return this;
        }

        public Builder setOnSale(boolean onSale) {
            this.onSale = onSale;
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
            this.productID = product.productID;
            this.productName = product.productName;
            this.imageAddress = product.imageAddress;
            this.description = product.description;
            this.rating = product.rating;
            this.price = product.price;
            this.salePrice = product.salePrice;
            this.onSale = product.onSale;
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
        public Product build() {return new Product(this);}
    }
}




