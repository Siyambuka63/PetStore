<script setup>
import HeaderComponent from "@/components/HeaderComponent.vue";
import SidebarComponent from "@/components/SidebarComponent.vue";
</script>
<template>
  <HeaderComponent/>
  <div class="container">
    <sidebar-component/>
    <!-- Main Content -->
    <div class="content">
      <h1>Order details:</h1>
      <h2><strong>Order #{{order.id}}</strong></h2>
      <p><strong>Order date: </strong>{{ formatDate(order.orderDate) }}</p>
      <p><strong>Delivery date: </strong>{{ formatDate(order.deliveryDate)}}</p>
      <p><strong>Total price: </strong>R{{ order.price.toFixed(2) }}</p>
      <p><strong>Status:</strong> {{ order.status }}</p>

      <div v-if="orderItems.length" class="list"><!--check if a orderitem exists-->
        <div v-for="orderItem in orderItems" :key="orderItem.id" class="orders-list">
          <!-- Order Items -->
          <div v-for="product in getProductById(orderItem.id.productId)" :key="product.id">
            <img :src="`/productImages/${product.imageAddress}`" alt="product">
            <p><strong>Product name: </strong> {{ product.productName }}</p>
            <p><strong>Price Per Item:</strong> R{{ orderItem.pricePerItem.toFixed(2) }}</p>
            <p><strong>Quantity:</strong> {{ orderItem.quantity }}</p>
            <p><strong>Subtotal:</strong> R{{ orderItem.totalPrice.toFixed(2) }}</p>
          </div>
        </div>
      </div>
      <!--else statement for check-->
      <div v-else class="no-orders">
        <p>No order items found.</p>
      </div>
    </div>
  </div>
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
    async getOrder() {
      const orderId = this.$route.params.id;
      let response = await axiosInstance.get(`/order/read/${orderId}`);
      this.orders = await response.data;

      response = await axiosInstance.get(`/order-item/getByOrderId/${orderId}`);
      this.orderItems = await response.data;
    },
    async getProductById(productId) {
      const response = await axiosInstance.get(`/product/read/${productId}`);
      this.orderItems = await response.data;
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
  width: auto;
  overflow-x: hidden;
  display: flex;
  justify-content: space-between;
  flex-direction: row;
  align-items: center;
  border-radius: 10px;
  padding: 12px 16px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
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
  width: 90%;
  height: 150px;
  object-fit: contain;
  margin-bottom: 10px;
}

.no-orders {
  text-align: center;
  font-size: 1.1em;
  margin-top: 30px;
  color: #777;
}
</style>