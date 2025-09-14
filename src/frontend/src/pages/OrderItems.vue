<script setup>
import {orderStore} from "@/services/OrderStore";

const store = orderStore();

let id = store.getOrderId();
</script>
<template>
    <!-- Header part -->
    <div class="header">
      <img src="@/assets/logo5-removebg-preview.png" alt="logo 3" >
    </div>
    <div class="topnav">
      <p><router-link to="/profile">My Profile</router-link></p>
      <p><router-link to="/wishlist">My Wishlist</router-link></p>
      <p><router-link to="/reviews">My Reviews</router-link></p>
      <p><router-link to="/orders">My Orders</router-link></p>
      <p><router-link to="/settings">Settings</router-link></p>
      <p><router-link to="/logout">Logout</router-link></p>
    </div>

    <!-- Main Content -->
    <div class="orderItems-content">

      <div v-if="orderItems.length" ><!--check if a orderitem exists-->
        <div v-for="orderItem in getOrderItems(id)" :key="orderItem.id" class="orders-list">
          <div class="order-details">
            <!-- Product details-->
            <div v-for="product in getProductById(orderItem.id.productId)" :key="product.id">
              <h1>Product Details</h1>
              <p>{{product.imageAddress}}</p>
              <p>{{product.brand}}</p>
              <p>{{product.description}}</p>
            </div>

            <!-- Order Items -->
            <h1>Order Items</h1>
            <p><strong>id:</strong> {{ orderItem.id }}</p>
            <p><strong>Price Per Item:</strong> R{{ orderItem.pricePerItem }}</p>
            <p><strong>Quantity:</strong> {{ orderItem.quantity }}</p>
            <p><strong>Total Price:</strong> R{{ orderItem.totalPrice }}</p>
            <router-link to="/orders">
              <button>Order details</button>
            </router-link>
          </div>
        </div>
      </div>

      <!--else statement for check-->
      <div v-else class="no-orders">
        <p>No order items found.</p>
      </div>
    </div>

</template>

<script>
import OrderService from "@/services/OrderService";
import OrderItemService from "@/services/OrderItemService";
import ProductService from "@/services/ProductService";
export default {
  name: "UserOrders",
  data() {
    return { orders: [],
      orderItems: [],
      products: [],
      pickedSort:"",
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
      ProductService.getProduct().then(response => {
        this.products = response.data;
      })
    },
    getProductById(productId) {
      return this.products.filter(product => product.id === productId);
    }
  },
  created() {
    this.getOrder();
    this.getOrderItem();
    this.getProducts();
    this.getProductById();
  },



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
.header img{
  height: 120px;
}
.topnav{
  display: flex;
  gap: 10%;
  align-items: center;
  justify-content: center;
  background: #0984e3;
}
.topnav a{
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}
.topnav a:hover {
  background-color: #ddd;
  color: darkblue;
}

/* Main Content */
.orderItems-content {
  padding: 30px 30px;
  align-items: center;
  justify-content: center;
  overflow-y: auto;
  gap: 10px;
}

/* Orders */
.orders-list{
  border: darkgray solid 2px;
  padding: 20px 20px;
  font-weight: bold;
  width: 90%;
}
.orders-list button{
  border-radius: 5px;
  padding: 10px;
  font-weight: bold;
}
.no-orders {
  text-align: center;
  font-size: 1.1em;
  margin-top: 30px;
  color: #777;
}
</style>