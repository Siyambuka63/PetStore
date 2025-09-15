<script setup>
import {orderStore} from "@/services/OrderStore";
import {useAuth} from "@/Auth";
const userEmail = useAuth().getEmail();
const store = orderStore();
</script>
<template>

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


  <div v-if="orders.length" class="order-content">
    <h1 v-for="user in getUserByEmail(userEmail)" :key="user.id"> {{user.firstName}}'s Orders</h1>
    <div v-for="order in getOrderByEmail(userEmail)" v-bind:key="order.id" id="orders">

      <p>delivery date: {{order.deliveryDate}}</p>
      <p>order date:{{order.orderDate}}</p>
      <p>price:{{order.price}}</p>
      <p>status:{{order.status}}</p>
      <router-link to="/orderItem">
      <button @click="store.setOrderId(order.id)">View order</button>
      </router-link>
    </div>
  </div>
  <div v-else class="no-orders">
    <p v-for="user in getUserByEmail(userEmail)" :key="user.id">no orders yet, start now {{user.firstName}}!</p>

  </div>
</template>


<script>
import OrderService from "@/services/OrderService";

export default {
  name: "UserOrders",
  data() {
    return { orders: [],
      orderItems: [],
      pickedSort:"",
      buttonText: "View Order Detail",
      users: []};
  },
  methods: {
    getOrder() {
      OrderService.getOrder().then(response => {
        this.orders = response.data;
      });
    },
    getUser(){
      OrderService.getUser().then(response => {
        this.users = response.data;
      })
    },
    getUserByEmail(email) {
      return this.users.filter(user => user.email === email);
    },
    getOrderByEmail(email) {
      return this.orders.filter(order => order.user?.email === email);
    }
  },
  mounted() {
    this.getOrder();
    this.getUser();
    this.getOrderByEmail();
    this.getUserByEmail();
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
  height: 80px;
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
.order-content{
  display: flex;
  flex-direction: column;
  align-items: center;
  overflow-y: auto;
  gap: 10px;
}
#orders{
  border: darkgray solid 2px;
  padding: 20px;
  font-weight: bold;
  width: 50%;
}
#orders button{
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
