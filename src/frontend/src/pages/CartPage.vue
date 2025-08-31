<template>
  <div class="cart-container">
    <h2 class="cart-title">My Cart</h2>

    <!-- Cart Items -->
    <div v-if="cartItems.length > 0" class="cart-items">
      <div v-for="item in cartItems" :key="item.id" class="cart-item">
        <img :src="item.imageUrl" alt="product" class="cart-img" />
        <div class="cart-details">
          <h3>{{ item.productName }}</h3>
          <p>Price: R{{ item.price.toFixed(2) }}</p>
          <p>Quantity: {{ item.quantity }}</p>
        </div>
        <button @click="removeFromCart(item.id)" class="remove-btn">Remove</button>
      </div>
    </div>

    <!-- Empty Cart -->
    <div v-else class="empty-cart">
      <p>Your cart is empty.</p>
    </div>

    <!-- Cart Summary -->
    <div class="cart-summary" v-if="cartItems.length > 0">
      <p><strong>Total:</strong> R{{ totalPrice.toFixed(2) }}</p>
      <button @click="checkout" class="checkout-btn">Checkout</button>
    </div>
  </div>
</template>


<script setup>
import { ref, computed, onMounted } from "vue";
import axios from "axios";

const cartItems = ref([]);

// Load active cart from backend
const loadCart = async () => {
  try {
    const res = await axios.get("http://localhost:8080/petstore/cart/getActiveCart/1");
    if (res.data && res.data.items) {
      cartItems.value = res.data.items;
    } else {
      cartItems.value = [];
    }
  } catch (err) {
    console.error("Error loading cart:", err);
  }
};

// Remove an item
const removeFromCart = async (itemId) => {
  try {
    await axios.delete(`http://localhost:8080/petstore/cart/removeItem/${itemId}`);
    cartItems.value = cartItems.value.filter(item => item.id !== itemId);
  } catch (err) {
    console.error("Error removing item:", err);
  }
};

// Checkout
const checkout = async () => {
  try {
    const res = await axios.post("http://localhost:8080/petstore/cart/checkout/1");
    alert("Checkout successful! Order status: " + res.data.status);
    cartItems.value = [];
  } catch (err) {
    console.error("Error during checkout:", err);
  }
};

onMounted(loadCart);

// Total Price
const totalPrice = computed(() =>
    cartItems.value.reduce((sum, item) => sum + item.price * item.quantity, 0)
);
</script>

