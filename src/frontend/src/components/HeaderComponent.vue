<template>
  <div class="header">
    <!-- Left Section -->
    <div class="left-section">
      <router-link to="/" class="logo">PetShop</router-link>
    </div>

    <!-- Center Section -->
    <div class="center-section">
      <input
          type="text"
          placeholder="Search..."
          class="search-bar"
      />
    </div>

    <!-- Right Section -->
    <div class="right-section">
    <router-link class="tab" to="/profile">Account</router-link>
    <router-link class="tab" to="/wishlist">Wishlist</router-link>
    <div class="tab cart" @click="toggleCart"> Cart ({{ cart.length }}) </div>
    </div>

    <!-- Cart Dropdown -->
    <div v-if="showCart" class="cart-dropdown">
      <h3>Cart Items</h3>
      <div v-if="cart.length === 0">No items in cart</div>
      <div v-else class="cards">
        <div v-for="(item, index)  in cart" :key="index" class="card">
          <img
              :src="`http://localhost:8080/product/image/${item.product.id}`
              ? '/productImages/' + item.product.imageAddress
              : '/productImages/placeholder.jpg'"
              :alt="item.product.productName"
          />
          <div>
            <p>{{ item.product.productName }}</p>
            <p>Quantity: {{ item.quantity }}</p>
          </div>
        </div>
      </div>

      <button v-if="cart.length > 0" @click="viewCart">View Cart</button>
    </div>
  </div>
</template>

<script>
import {getCartItems} from "@/services/CartService";

export default {
  name: "HeaderComponent",
  data() {
    return {
      showCart: false,
      cart: []
    };
  },
  methods: {
    toggleCart() {
      this.showCart = !this.showCart;
    },
    viewCart() {
      this.$router.push("/cart");
    }
  },
  async mounted() {
    const email = localStorage.getItem("email");

    if (email) {
      this.cart = await getCartItems(email);
    }
  }
};
</script>

<style scoped>
.header {
  display: flex;
  gap: 10%;
  align-items: center;
  justify-content: center;
  background: #0984e3;
  color: white;
  padding: 0 30px;
}

.right-section {
  display: flex;
  gap: 15px;
  align-items: center;
}

.tab {
  cursor: pointer;
  color: white;
  text-decoration: none;
  font-size: 14px;
}

.tab:hover {
  color: #0652DD;
}

.center-section {
  text-align: center;
}

.search-bar {
  width: 500px;
  padding: 6px 10px;
  border-radius: 8px;
  border: none;
}

.left-section {
  display: flex;
  justify-content: flex-end;
  align-items: center;
}

.logo {
  font-size: 40px;
  padding: 20px 0;
  text-decoration: none;
  color: white;
  cursor: pointer;
}

/* Cart dropdown */
.cart-dropdown {
  position: absolute;
  top: 60px;
  right: 20px;
  background: #fff;
  color: #333;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
  padding: 15px;
  width: 260px;
  z-index: 1;
}

.cards {
  display: flex;
  flex-direction: column;
  padding: 10px
}

.card {
  text-align: center;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.card img {
  height: 50px;
  align-self: center;
}

.cart-dropdown button {
  border: none;
  border-radius: 8px;
  margin: 5px;
  padding: 10px;
  color: white;
  width: 100px;
  background: #0984e3;
}

.cart-dropdown button:active, .cart-dropdown button:hover {
  background: #0652DD;
}

.cart-dropdown h3 {
  margin: 0 0 10px;
}
</style>