/* Cart.java

     Cart POJO class

     Author: Elethu Manikivana (221195521)

     Date: 09 May 2025 */

package za.ac.cput.domain;

import java.util.List;

public class Cart {
    public int cartID;
    public int userID;
    public List<CartItem> items;

    public Cart() {

    }

    public Cart (Builder builder) {
        this.cartID = builder.cartID;
        this.userID = builder.userID;
        this.items = builder.items;
    }

    public Cart(int cartID, int userID, List<CartItem> items) {
        this.cartID = cartID;
        this.userID = userID;
        this.items = items;

    }

    public int getCartID() {
        return cartID;
    }

    public int getUserID() {
        return userID;
    }
    public List<CartItem> getItems() {
        return items;
    }


    @Override
    public String toString() {
        return "Cart{" +
                "cartID=" + cartID +
                ", userID=" + userID +
                ", items=" + items +
                '}';
    }

    public static class Builder {
        private int cartID;
        private int userID;
        private List<CartItem> items;

        public Builder setCartID(int cartID) {
            this.cartID = cartID;
            return this;
        }

        public Builder setUserID(int userID) {
            this.userID = userID;
            return this;
        }
        public Builder setItems(List<CartItem> items) {
            this.items = items;
            return this;
        }

        public Builder copy(Cart cart) {
            this.cartID = cart.getCartID();
            this.userID = cart.getUserID();
            this.items = cart.getItems();
            return this;
        }

        public Cart build() {
            return new Cart(this);
        }
    }

}
