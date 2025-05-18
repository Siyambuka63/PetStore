/* CartItem.java

     CartItem POJO class

     Author: Elethu Manikivana (221195521)

     Date: 09 May 2025 */

package za.ac.cput.domain;

public class CartItem {
    public int cartItemId;
    public int productId;
    public int quantity;

    public CartItem() {
    }
    public CartItem(Builder builder){
        this.cartItemId = builder.cartItemId;
        this.productId = builder.productId;
        this.quantity = builder.quantity;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public CartItem(int cartItemId, int productId, int quantity) {
        this.cartItemId = cartItemId;
        this.productId = productId;
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId=" + cartItemId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }

    public static class Builder {
        private int cartItemId;
        private int productId;
        private int quantity;

        public Builder setCartItemId(int cartItemId) {
            this.cartItemId = cartItemId;
            return this;
        }
        public Builder setProductId(int productId) {
            this.productId = productId;
            return this;
        }
        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder copy(CartItem cartItem) {
            this.cartItemId = cartItem.cartItemId;
            this.productId = cartItem.productId;
            this.quantity = cartItem.quantity;
            return this;
        }
        public CartItem build() {
            return new CartItem(this);
        }

    }

}
