package za.ac.cput.domain;

import za.ac.cput.domain.enums.Category;
import za.ac.cput.domain.enums.Flavour;

import java.sql.Blob;
import java.util.List;

/*Oluhle Makhaye
* 222419636
* product class*/
public class Product {
    private int productID;
    private String name;
    private String description;
    private Blob image;
    private double rating;
    private double price;
    private int stock;
    private double weight;
    //private Brand brand;
    private int lifeStageID;
    private int foodtypeID;
    private int petTypeID;
    private List<Flavour> flavours;
    private List<Category> categories;

    private Product () {
    }

    private Product(Builder builder) {
        productID = builder.productID;
        name = builder.name;
        description = builder.description;
        image = builder.image;
        rating = builder.rating;
        price = builder.price;
        stock = builder.stock;
        weight = builder.weight;
        lifeStageID = builder.lifeStageID;
        foodtypeID = builder.foodtypeID;
        petTypeID = builder.petTypeID;
        flavours = builder.flavours;
        categories = builder.categories;
    }

    public int getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Blob getImage() {
        return image;
    }

    public double getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public double getWeight() {
        return weight;
    }

    public int getLifeStageID() {
        return lifeStageID;
    }

    public int getFoodtypeID() {
        return foodtypeID;
    }

    public int getPetTypeID() {
        return petTypeID;
    }

    public List<Flavour> getFlavours() {
        return flavours;
    }

    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                ", rating=" + rating +
                ", price=" + price +
                ", stock=" + stock +
                ", weight=" + weight +
                ", lifeStageID=" + lifeStageID +
                ", foodtypeID=" + foodtypeID +
                ", petTypeID=" + petTypeID +
                ", flavours=" + flavours +
                ", categories=" + categories +
                '}';
    }

    public static class Builder {
        private int productID;
        private String name;
        private String description;
        private Blob image;
        private double rating;
        private double price;
        private int stock;
        private double weight;
        private int lifeStageID;
        private int foodtypeID;
        private int petTypeID;
        private List<Flavour> flavours;
        private List<Category> categories;


        public void setProductID(int productID) {
            this.productID = productID;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setImage(Blob image) {
            this.image = image;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public void setLifeStageID(int lifeStageID) {
            this.lifeStageID = lifeStageID;
        }

        public void setFoodtypeID(int foodtypeID) {
            this.foodtypeID = foodtypeID;
        }

        public void setPetTypeID(int petTypeID) {
            this.petTypeID = petTypeID;
        }

        public void setFlavours(List<Flavour> flavours) {
            this.flavours = flavours;
        }

        public void setCategories(List<Category> categories) {
            this.categories = categories;
        }

        public Product copy(Product product) {
            this.productID = product.productID;
            this.name = product.name;
            this.description = product.description;
            this.image = product.image;
            this.rating = product.rating;
            this.price = product.price;
            this.stock = product.stock;
            this.weight = product.weight;
            this.lifeStageID = product.lifeStageID;
            this.foodtypeID = product.foodtypeID;
            this.petTypeID = product.petTypeID;
            this.flavours = product.flavours;
            this.categories = product.categories;
            return this.build();
        }

        public Product build() {
            return new Product(this);
        }
    }
}
