<template>
  <HeaderComponent/>
  <div class="cart-container">
    <h2 class="cart-title">My Cart</h2>

    <!-- Cart Items -->
    <div v-if="cartItems.length > 0" class="cart-items">
      <div v-for="item in cartItems" :key="item.product.id" class="cart-item">
        <img
            :src="item.product.imageData ? `/petstore/product/image/${item.product.id}` : '/productImages/placeholder.jpg'"
            :alt="item.product.productName" class="cart-img"
        />
        <div class="cart-details">
          <h3>{{ item.product.name }}</h3>
          <p>
          <span v-if="item.product.discountPercent && item.product.discountPercent > 0">
            Was: <s>R{{ item.product.price.toFixed(2) }}</s><br/>
            Now: R{{ (item.product.price * (1 - item.product.discountPercent / 100)).toFixed(2) }}
            ({{ item.product.discountPercent }}% off)
          </span>
            <span v-else>
            R{{ item.product.price.toFixed(2) }}
          </span>
          </p>
          <p>Quantity: {{ item.quantity }}</p>
          <div class="quantity-controls">
            <input type="number"
                   v-model.number="item.quantity"
                   min="1"
                   max="{{ item.quantity }}"
                   @change="updateItemQuantity(item.product.id, item.quantity)"
                   class="quantity-input"
                   />
          </div>
        </div>
        <button v-on:click="removeFromCart(item.product.id)" class="remove-btn">Remove</button>
      </div>
    </div>

    <!-- Empty Cart -->
    <div v-else class="empty-cart">
      <p>Your cart is empty.</p>
    </div>

    <!-- Cart Summary -->
    <div class="cart-summary" v-if="cartItems.length > 0">
      <p><strong>Total:</strong> R{{ totalPrice.toFixed(2) }}</p>
      <button v-on:click="checkout" class="checkout-btn">Checkout</button>
    </div>
    <FooterComponent/>
  </div>
</template>

<script setup>
import {computed, onMounted, ref} from "vue";
import {getCartItems, makeOrder, removeItem, updateQuantity} from "@/services/CartService";
import HeaderComponent from "@/components/HeaderComponent.vue";
import {useRouter} from "vue-router";
import FooterComponent from "@/components/FooterComponent.vue";

const cartItems = ref([]);

const email = localStorage.getItem("email");
const router = useRouter();

// Load active cart from backend
const loadCart = async () => {
  try {
    cartItems.value = await getCartItems(email);
  } catch (err) {
    console.error("Error loading cart:", err);
  }
};

// Remove an item
const removeFromCart = async (productId) => {
  try {
    await removeItem(email, productId);
    //cartItems.value = await getCartItems(email);
    router.go(0);
  } catch (err) {
    console.error("Error removing item:", err);
  }
};

// Checkout
const checkout = async () => {
  try {
    await makeOrder(email, totalPrice.value);
    alert("Checkout successful!");
    cartItems.value = [];
    await router.push("/orders");
  } catch (err) {
    console.error("Error during checkout:", err);
  }
};

onMounted(loadCart);

// Total Price
const totalPrice = computed(() =>
    cartItems.value.reduce((sum, item) => sum + (item.product.price * (1 - item.product.discountPercent / 100)) * item.quantity, 0)
);

//Uodate  quantity
const updateItemQuantity = async(productId, newQuantity) => {
  try {
    if(newQuantity<1) {
      await removeItem(email, productId)
      cartItems.value = await getCartItems(email);
      return;
    }
    await updateQuantity(email,productId,newQuantity);
    cartItems.value = await getCartItems(email);
  } catch(err){
    console.error("Error updating quantity:", err);
  }
};


</script>

<style scoped>
.cart-container {
  max-width: 800px;
  margin: 40px auto;
  padding: 20px;
  background: #fefafa;
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
  height: 100px;
  object-fit: cover;
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
  border: none;
  border-radius: 8px;
  margin: 5px;
  padding: 10px;
  color: white;
  width: 100px;
  background: #FF2D2D;
}

.remove-btn:hover {
  background: red;
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
  border: none;
  border-radius: 8px;
  margin: 5px;
  padding: 10px;
  color: white;
  width: 100px;
  background: #0984e3;
}

.checkout-btn:hover {
  background: #0652DD;
}

.quantity-input {
  width: 60px;
  padding: 4px;
  border: 1px solid #ccc;
  border-radius: 6px;
  text-align: center;
}

</style>
