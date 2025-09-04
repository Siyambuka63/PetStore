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

<style scoped>
.cart-container {
  max-width: 800px;
  margin: 40px auto;
  padding: 20px;
  background: #fefafa; /* matches your palette */
  border-radius: 12px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  font-family: "Roboto", sans-serif;
}

.cart-title {
  font-family: "Open Sans", sans-serif;
  font-size: 28px;
  font-weight: 700;
  color: #352d2d;
  margin-bottom: 20px;
  text-align: center;
}

.cart-items {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.cart-item {
  display: flex;
  align-items: center;
  background: #ffffff;
  border-radius: 10px;
  padding: 12px 16px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.05);
  transition: transform 0.2s;
}

.cart-item:hover {
  transform: scale(1.01);
}

.cart-img {
  width: 80px;
  height: 80px;
  object-fit: cover;
  border-radius: 8px;
  margin-right: 16px;
}

.cart-details {
  flex: 1;
}

.cart-details h3 {
  margin: 0;
  font-size: 18px;
  color: #352d2d;
}

.cart-details p {
  margin: 4px 0;
  color: #555;
}

.remove-btn {
  background: #ff0207;
  color: #fff;
  border: none;
  padding: 8px 14px;
  border-radius: 6px;
  cursor: pointer;
  transition: background 0.3s;
}

.remove-btn:hover {
  background: #c40004;
}

.empty-cart {
  text-align: center;
  font-size: 18px;
  color: #888;
  margin: 40px 0;
}

.cart-summary {
  margin-top: 24px;
  padding-top: 16px;
  border-top: 2px solid #d4d4d4;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.cart-summary p {
  font-size: 18px;
  font-weight: bold;
  color: #352d2d;
}

.checkout-btn {
  background: #00ccff;
  color: #fff;
  font-weight: 600;
  border: none;
  padding: 10px 20px;
  border-radius: 8px;
  cursor: pointer;
  transition: background 0.3s;
}

.checkout-btn:hover {
  background: #009ac1;
}
</style>

