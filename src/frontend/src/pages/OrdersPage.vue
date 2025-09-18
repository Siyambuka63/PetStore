<script setup>
import {orderStore} from "@/services/OrderStore";
import {useAuth} from "@/Auth";
import HeaderComponent from "@/components/HeaderComponent.vue";
import SidebarComponent from "@/components/SidebarComponent.vue";

const userEmail = useAuth().getEmail();
const store = orderStore();
</script>
<template>
  <HeaderComponent/>
  <div class="container">
    <sidebar-component/>
    <div v-if="orders.length" class="order-content">
      <h1 v-for="user in getUserByEmail(userEmail)" :key="user.id"><strong>{{ user.firstName }}'s Orders</strong> </h1>
      <div v-for="order in getOrderByEmail(userEmail)" v-bind:key="order.id" id="orders">

        <h2><strong>Order #{{order.id}}</strong></h2>
        <p><strong>Order date: </strong>{{ formatDate(order.orderDate) }}</p>
        <p><strong>Delivery date: </strong>{{ formatDate(order.deliveryDate)}}</p>
        <p><strong>Total price: </strong>R{{ order.price.toFixed(2) }}</p>
        <p><strong>Status:</strong> {{ order.status }}</p>
          <router-link to="/orderItem">
            <button @click="store.setOrderId(order.id)">View order</button>
          </router-link>

      </div>
    </div>
    <div v-else class="no-orders">
      <p v-for="user in getUserByEmail(userEmail)" :key="user.id">no orders yet, start now {{ user.firstName }}!</p>
    </div>
  </div>
</template>


<script>
import OrderService from "@/services/OrderService";

export default {
  name: "UserOrders",
  data() {
    return {
      orders: [],
      orderItems: [],
      pickedSort: "",
      buttonText: "View Order Detail",
      users: []
    };
  },
  methods: {
    formatDate(dateString) {
      const date = new Date(dateString);
      return date.toLocaleDateString('en-GB', {
        day: 'numeric',
        month: 'long',
        year: 'numeric'
      });
    },
    getOrder() {
      OrderService.getOrder().then(response => {
        this.orders = response.data;
      });
    },
    getUser() {
      OrderService.getUser().then(response => {
        this.users = response.data;
      })
    },
    getUserByEmail(email) {
      return this.users.filter(user => user.email === email);
    },
    getOrderByEmail(email) {
      return this.orders.filter(order => order.user?.email === email && order.status !== 'Cart');
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
.container {
  display: flex;
  width: 100%;

}

.order-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  overflow-y: auto;
  width: 70%;
  padding: 10px 30px;
  overflow-x: hidden;
}

#orders {
  border-radius: 10px;
  padding: 12px 16px;
  width: 100%;
  margin-bottom: 20px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.05);
}

#orders button {
  border: none;
  color: white;
  border-radius: 8px;
  padding: 10px;
  font-weight: bold;
  background: #0984e3;
  margin-right: 5px;
}

#orders button:hover {
  background: #0652DD;
}

.no-orders {
  text-align: center;
  font-size: 1.1em;
  margin-top: 30px;
  color: #777;
  width: 70%;
}
</style>
