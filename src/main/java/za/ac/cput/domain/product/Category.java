package za.ac.cput.domain.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Category {
    @Id
    private int categoryID;
    private String categoryName;

    protected Category() {}

    private Category(Category.Builder builder){
        this.categoryID = builder.categoryID;
        this.categoryName = builder.categoryName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryID=" + categoryID +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    public static class Builder {
        private int categoryID;
        private String categoryName;

        public Builder setCategoryID(int categoryID) {
            this.categoryID = categoryID;
            return this;
        }

        public Builder setCategoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        public Category copy(Category category){
            this.categoryID = category.categoryID;
            this.categoryName = category.categoryName;
            return this.build();
        }

        public Category build(){return new Category(this);}
    }

}
