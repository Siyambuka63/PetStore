<script setup>
import {orderStore} from "@/services/OrderStore";

const store = orderStore();

let id = store.getOrderId();
</script>
<template>
<header-component/>
  <div class="layout">
    <!-- Sidebar -->
    <div class="sidebar">
      <!-- My Account -->
      <div class="my_account">
        <div class="header">
          <img src="@/assets/user.png" alt="user"/>
          <h2>My Account</h2>
        </div>
        <div class="list">
          <ul>
            <li><router-link to="/profile">My Profile</router-link></li>
            <li><router-link to="/wishlist">My Wishlist</router-link></li>
            <li><router-link to="/reviews">My Reviews</router-link></li>
            <li><router-link to="/orders">My Orders</router-link></li>
            <li><router-link to="/settings">Settings</router-link></li>
            <li><router-link to="/logout">Logout</router-link></li>
          </ul>
        </div>
      </div>
    </div>

    <!-- Main Content -->
    <main class="main-content">
      <!-- Title -->
      <h1 v-if="orderItems.length">Order Items</h1>
      <!-- Order Items -->
      <div v-if="orderItems.length" class="orders-list">
        <div v-for="orderItem in getOrderItems(id)" :key="orderItem.id" class="order-card">
          <div class="order-details">
            <p><strong>Price Per Item:</strong> R{{ orderItem.pricePerItem }}</p>
            <p><strong>Quantity:</strong> {{ orderItem.quantity }}</p>
            <p><strong>Total Price:</strong> R{{ orderItem.totalPrice }}</p>
            <router-link to="/orders">
              <button>Order details</button>
            </router-link>
          </div>
        </div>
      </div>

      <!-- No Order Items -->
      <div v-else class="no-orders">
        <p>No order items found.</p>
      </div>
    </main>
  </div>
</template>

<script>
import HeaderComponent from "@/components/HeaderComponent.vue";
import OrderService from "@/services/OrderService";
import OrderItemService from "@/services/OrderItemService";
//import ProductService from "@/services/ProductService";
export default {
  name: "UserOrders",
  components: { HeaderComponent },
  data() {
    return { orders: [],
      orderItems: [],
      products: [],
      pickedSort:"",
      showOrderItem: null,
      buttonText: "View Order Detail",};
  },
  methods: {
    getOrder() {
      OrderService.getOrder().then(response => {
        this.orders = response.data;
      });
    },
    getOrderItem(){
      OrderItemService.getOrderItem().then(response => {
        this.orderItems = response.data;
      })
    },
    getOrderItems(orderId) {
      return this.orderItems.filter(orderItem => orderItem.id.orderId === orderId);
    },
    getProducts() {
      // ProductService.getProduct().then(response => {
      //   this.products = response.data;
      // })
    }
  },
  created() {
    this.getOrder();
    this.getOrderItem();
    this.getProducts()
  },



};
</script>

<style scoped>
/* Layout */
.layout {
  display: flex;
  width: 100%;
}

/* Sidebar */
.sidebar {
  width: 20%;
  margin: 10px;
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.my_account {
  border-radius: 8px;
  border: 2px solid #dfe6e9;
}

.my_account .header {
  border-top-left-radius: 8px;
  border-top-right-radius: 8px;
  border-bottom: 2px solid #dfe6e9;
  padding-left: 20px;
  display: flex;
  gap: 5%;
  align-items: center;
  background: #0984e3;
}

.my_account .header h2 {
  font-size: 16px;
  color: white;
}

.my_account .header img {
  height: 20px;
}
.order_sort{
  border-radius: 8px;
  border: 2px solid #dfe6e9;
}

.order_sort .header {
  border-top-left-radius: 8px;
  border-top-right-radius: 8px;
  border-bottom: 2px solid #dfe6e9;
  padding-left: 20px;
  display: flex;
  gap: 5%;
  align-items: center;
  background: #0984e3;
}

.order_sort .header h2 {
  font-size: 16px;
  color: white;
}

.sidebar ul {
  list-style: none;
  padding: 0 5px 0 5px;
}

.sidebar ul li a {
  text-decoration: none;
  color: black;
  font-size: 15px;
  display: block;
  padding: 8px 12px;
  border-radius: 8px;
  transition: background 0.2s;
}

.sidebar ul li a:hover {
  text-decoration: underline;
  font-weight: bold;
}

.sidebar ul li a.active {
  color: #0984e3;
}

/* Main Content */
.main-content {
  padding: 10px 30px;
  display: flex;
  flex-direction: column;
  width: 100%;
  overflow-y:auto;
}

/* Orders */
.orders-list {
  display: grid;
  gap: 20px;
  margin-top: 20px;
}

.order-card {
  border: 1px solid #ddd;
  border-radius: 10px;
  padding: 18px;
  background-color: #f9f9f9;
  transition: box-shadow 0.2s ease-in-out;
}

.order-card:hover {
  box-shadow: 0 4px 12px rgba(0,0,0,0.1);
}

.order-details p {
  margin: 6px 0;
}

/* Status styles */
.status-cart { color:grey; font-weight: bold; }
.status-delivered { color: blue; font-weight: bold; }
.status-busy { color: orange; font-weight: bold; }
.status-ready { color: green; font-weight: bold; }
.status-cancelled { color: darkred; font-weight: bold; }
.status-returned { color: black; font-weight: bold; }


.no-orders {
  text-align: center;
  font-size: 1.1em;
  margin-top: 30px;
  color: #777;
}
</style>