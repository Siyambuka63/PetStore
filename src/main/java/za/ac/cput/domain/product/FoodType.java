package za.ac.cput.domain.product;
/*Oluhle Makhaye
* 222419636*/

public class FoodType {
    private  int foodTypeID;
    private  String foodTypeName;

    private FoodType() {

    }

    private FoodType(FoodType.Builder builder){

    }

    public int getFoodTypeID() {
        return foodTypeID;
    }

    public String getFoodTypeName() {
        return foodTypeName;
    }

    @Override
    public String toString() {
        return "FoodType{" +
                "foodTypeID=" + foodTypeID +
                ", foodTypeName='" + foodTypeName + '\'' +
                '}';
    }

    public static class Builder {
        private int foodTypeID;
        private String foodTypeName;

        public Builder setFoodTypeID(int foodTypeID) {
            this.foodTypeID = foodTypeID;
            return this;
        }

        public Builder setFoodTypeName(String FoodTypeName) {
            this.foodTypeName = foodTypeName;
            return this;
        }

        public FoodType copy(FoodType foodType){
            this.foodTypeID = foodType.foodTypeID;
            this.foodTypeName = foodType.foodTypeName;
            return this.build();
        }

        public FoodType build(){return new FoodType(this);}
    }
}
