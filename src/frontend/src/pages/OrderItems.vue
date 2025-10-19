<script setup>
import HeaderComponent from "@/components/HeaderComponent.vue";
import SidebarComponent from "@/components/SidebarComponent.vue";
import FooterComponent from "@/components/FooterComponent.vue";
</script>
<template>
  <HeaderComponent/>
  <div class="container">
    <SidebarComponent/>
    <!-- Main Content -->
    <div class="content">
      <div v-if="order">
        <h2><strong>Order #{{ order.id }}</strong></h2>
        <p><strong>Order date: </strong>{{ formatDate(order.orderDate) }}</p>
        <p><strong>Delivery date: </strong>{{ formatDate(order.deliveryDate) }}</p>
        <p><strong>Total price: </strong>R{{ order.price.toFixed(2) }}</p>
        <p><strong>Status:</strong> {{ order.status }}</p>

        <div v-if="orderItems.length" class="list">
          <router-link
              v-for="orderItem in orderItems"
              :key="orderItem.id"
              :to="`/products/${orderItem.product.id}`"
              class="orders-list"
          >
            <img :src="`/productImages/${orderItem.product.imageAddress}`" alt="product">
            <div>
              <p><strong>Product name: </strong> {{ orderItem.product.productName }}</p>
              <p><strong>Price Per Item:</strong> R{{ orderItem.pricePerItem.toFixed(2) }}</p>
              <p><strong>Quantity:</strong> {{ orderItem.quantity }}</p>
              <p><strong>Subtotal:</strong> R{{ orderItem.totalPrice.toFixed(2) }}</p>
            </div>
          </router-link>
        </div>

        <div v-else class="no-orders">
          <p>No order items found.</p>
        </div>
      </div>

      <div v-else>
        <p>Loading order...</p>
      </div>
    </div>
  </div>
  <FooterComponent/>
</template>

<script>
import axiosInstance from "@/api/AxiosInstance";

export default {
  name: "UserOrders",
  data() {
    return {
      order: null,
      orderItems: [],
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
    async getOrder() {
      const orderId = this.$route.params.id;
      let response = await axiosInstance.get(`/order/read/${orderId}`);
      this.order = await response.data;

      console.log(this.order);
      console.log(localStorage.getItem("email"));

      if (this.order.user.email !== localStorage.getItem("email")) {
        window.location.href = "/orders"
      }

      response = await axiosInstance.get(`/order-item/getByOrderId/${orderId}`);
      const items = response.data;

      for (let item of items) {
        const productResponse = await axiosInstance.get(`/product/read/${item.id.productId}`);
        item.product = productResponse.data;
      }

      this.orderItems = items;
    }
  },
  mounted() {
    this.getOrder();
  },
};
</script>

<style scoped>
.container {
  display: flex;
  padding: 10px 30px;
  overflow-x: hidden;
}

/* Main Content */
.content {
  width: 100%;
}

.content h1 {
  text-align: center;
  justify-content: center;
}

.content footer {
  padding: 30px;
  background-color: #0652DD;
  color: white;
}

.orders-list {
  text-decoration: none;
  color: black;
  width: auto;
  display: flex;
  flex-direction: row;
  align-items: center;
  border-radius: 10px;
  padding: 12px 16px;
  border: 2px solid #ccc;
  margin-bottom: 20px;
  cursor: pointer;
}

.orders-list button {
  border: none;
  color: white;
  border-radius: 8px;
  padding: 10px;
  font-weight: bold;
  background: #0984e3;
}

.orders-list button:hover {
  background: #0652DD;
}

.content img {
  float: right;
  height: 100px;
  margin: 0 50px 10px 0;
}

.no-orders {
  text-align: center;
  font-size: 1.1em;
  margin-top: 30px;
  color: #777;
}
</style>