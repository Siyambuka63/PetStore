package za.ac.cput.domain.order;
/*
     OrderItem class
     Author: Sinovuyo Mathungana (230143725)
*/
public class OrderItem {
    private int orderItemID;
    private int productID;
    private double price;

    private OrderItem(){}

    private OrderItem(Builder builder){
        this.orderItemID = builder.orderItemID;
        this.productID = builder.productID;
        this.price = builder.price;
    }

    public int getOrderItemID() {
        return orderItemID;
    }

    public int getProductID() {
        return productID;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemID=" + orderItemID +
                ", productID=" + productID +
                ", price=" + price +
                '}';
    }
    public static class Builder{
        private int orderItemID;
        private int productID;
        private double price;

        public Builder setOrderItemID(int orderItemID) {
            this.orderItemID = orderItemID;
            return this;
        }

        public Builder setProductID(int productID) {
            this.productID = productID;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }
        public Builder copy(OrderItem orderitem){
            this.orderItemID = orderitem.orderItemID;
            this.productID = orderitem.productID;
            this.price = orderitem.price;
            return this;
        }
        public OrderItem build(){return new OrderItem(this);}
    }
}
