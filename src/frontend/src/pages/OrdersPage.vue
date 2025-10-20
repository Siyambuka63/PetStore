<script setup>
import HeaderComponent from "@/components/HeaderComponent.vue";
import SidebarComponent from "@/components/SidebarComponent.vue";
import FooterComponent from "@/components/FooterComponent.vue";

</script>
<template>
  <HeaderComponent/>
  <div class="container">
    <sidebar-component/>
    <div v-if="orders.length" class="order-content">
      <h1><strong>{{ firstName }}'s Orders</strong> </h1>
      <div v-for="order in orders" v-bind:key="order.id" id="orders">
        <h2><strong>Order #{{order.id}}</strong></h2>
        <p><strong>Order date: </strong>{{ formatDate(order.orderDate) }}</p>
        <p><strong>Delivery date: </strong>{{ formatDate(order.deliveryDate)}}</p>
        <p><strong>Total price: </strong>R{{ order.price.toFixed(2) }}</p>
        <p><strong>Status:</strong> {{ order.status }}</p>
        <button @click="$router.push(`/orders/${order.id}`)">View order</button>
      </div>
    </div>
    <div v-else class="no-orders">
      <p>no orders yet, start now {{ firstName }}!</p>
    </div>
  </div>
  <FooterComponent/>
</template>


<script>
import {getUser} from "@/services/ProfileService";
import axiosInstance from "@/api/AxiosInstance";

export default {
  name: "UserOrders",
  data() {
    return {
      orders: [],
      firstName: "",
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
    async getOrdersByEmail(email) {
      const response = await axiosInstance.get(`/order/findByContactEmail/${email}`);
      this.orders = response.data.filter(order => order.status !== "Cart");
    }
  },
  async mounted() {
    const email = localStorage.getItem("email");
    const user = await getUser(email);

    this.firstName = user.firstName;

     await this.getOrdersByEmail(email);
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
  border: 2px solid #ccc;
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
