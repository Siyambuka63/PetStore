<script setup>
import {orderStore} from "@/services/OrderStore";
import HeaderComponent from "@/components/HeaderComponent.vue";
import SidebarComponent from "@/components/SidebarComponent.vue";

const store = orderStore();

let id = store.getOrderId();
</script>
<template>
  <HeaderComponent/>
  <div class="container">
    <sidebar-component/>
    <!-- Main Content -->
    <div class="orderItems-content">
      <div v-if="orderItems.length" class="list"><!--check if a orderitem exists-->
        <div v-for="orderItem in getOrderItems(id)" :key="orderItem.id" class="orders-list">
          <h1>Order item #{{orderItem.id.orderId}}{{orderItem.id.productId}}</h1>
          <div class="order-details">
            <!-- Order Items -->
            <div v-for="product in getProductById(orderItem.id.productId)" :key="product.id" >
            <p><strong>Product Name:</strong> {{product.productName}}</p>
            <p><strong>Price Per Item:</strong> R{{ orderItem.pricePerItem.toFixed(2) }}</p>
            <p><strong>Quantity:</strong> {{ orderItem.quantity }}</p>
            <p><strong>Total Price:</strong> R{{ orderItem.totalPrice.toFixed(2) }}</p>
              </div>
            <!-- Product details-->
<!--            <div v-for="product in getProductById(orderItem.id.productId)" :key="product.id" >-->
<!--              <h1>Product Summary of {{product.productName}}</h1>-->
<!--              <img-->
<!--                  :src="product?.imageAddress-->
<!--              ? '/productImages/' + product.imageAddress-->
<!--              : '/productImages/placeholder.jpg'"-->
<!--                  :alt="product.productName"-->
<!--              />-->
<!--              <p>Brand: {{ product.brand }}</p>-->
<!--              <p>Product Description: {{ product.description }}</p>-->
<!--              <p>Flavour: {{ product.flavour }}</p>-->
<!--              <p>Food type: {{ product.foodType }}</p>-->
<!--              <p>Life stage: {{ product.lifeStage }}</p>-->
<!--              <p>Rating: {{ product.rating }}</p>-->
<!--            </div>-->
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
  </div>
</template>

<script>
import OrderService from "@/services/OrderService";
import OrderItemService from "@/services/OrderItemService";
import ProductService from "@/services/ProductService";

export default {
  name: "UserOrders",
  data() {
    return {
      orders: [],
      orderItems: [],
      products: [],
      pickedSort: "",
      buttonText: "View Order Detail",
    };
  },
  methods: {
    getOrder() {
      OrderService.getOrder().then(response => {
        this.orders = response.data;
      });
    },
    getOrderItem() {
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
.container {
  display: flex;
  width: 100%;
}

/* Main Content */
.orderItems-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  overflow-y: auto;
  width: 70%;
  padding: 10px 30px;
}

.list {
  width: 100%;
}

/* Orders */
.orders-list {
  border: 2px solid #ccc;
  border-radius: 8px;
  padding: 20px;
  font-weight: bold;
  width: calc(100% - 20px);
  margin-bottom: 20px;
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
.orders-list img{
  width: 100%;
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