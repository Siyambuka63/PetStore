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
          v-model="searchQuery"
          placeholder="Search..."
          class="search-bar"
      />
    </div>

    <!-- Right Section -->
    <div class="right-section">
      <router-link class="tab" to="/profile">Account</router-link>
      <router-link class="tab" to="/wishlist">Wishlist</router-link>
      <div class="tab cart" @click="toggleCart">
        Cart ({{ cart.length }})
      </div>
    </div>

    <!-- Cart Dropdown -->
    <div v-if="showCart" class="cart-dropdown">
      <h3>Cart Items</h3>
      <div v-if="cart.length === 0">No items in cart</div>
      <ul>
        <li v-for="(item, index) in cart" :key="index">
          {{ item.product.productName }} - {{ item.quantity }}
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import {useAuth} from "@/Auth";
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
    }
  },
  async mounted() {
    const authUser = useAuth();

    if (authUser.getEmail()) {
      this.cart = await getCartItems(authUser.getEmail());
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

.cart-dropdown h3 {
  margin: 0 0 10px;
}
</style>