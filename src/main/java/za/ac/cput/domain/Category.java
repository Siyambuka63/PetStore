package za.ac.cput.domain;

public class Category {
    private int categoryID;
    private String categoryName;

    private Category() {

    }

    private Category(Category.Builder builder){

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

        public void setCategoryID(int categoryID) {
            this.categoryID = categoryID;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public Category copy(Category category){
            this.categoryID = category.categoryID;
            this.categoryName = category.categoryName;
            return this.build();
        }

        public Category build(){return new Category(this);}
    }

}
