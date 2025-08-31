<template>
  <HeaderComponent />

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
            <li><a href="#">My Profile</a></li>
            <li><a href="#">My Wishlist</a></li>
            <li><a href="#">My Reviews</a></li>
            <li><a href="#" class="active">My Orders</a></li>
            <li><a href="#">Settings</a></li>
            <li><a href="#">Logout</a></li>
          </ul>
        </div>
      </div>

      <!-- Sort Orders -->
      <div class="order_sort">
        <div class="header">
          <h2>Sort Orders</h2>
        </div>
        <div class="list">
          <ul>
            <li>
              <label>
                <input type="radio" value="deliveryDate" v-model="pickedSort" />
                Delivery Date
              </label>
            </li>
            <li>
              <label>
                <input type="radio" value="orderDate" v-model="pickedSort" />
                Order Date
              </label>
            </li>
            <li>
              <label>
                <input type="radio" value="Price" v-model="pickedSort" />
                Price
              </label>
            </li>
          </ul>
        </div>
      </div>
    </div>

    <!-- Main Content -->
    <main class="main-content">
      <!-- Title -->
      <h1 v-if="sortedOrders.length">{{ sortedOrders[0].user.firstName }}'s Orders</h1>

      <!-- Orders -->
      <div v-if="sortedOrders.length" class="orders-list">
        <div v-for="order in sortedOrders" :key="order" class="order-card">
          <div class="order-details">
            <p><strong>Order ID:</strong> {{ order.id }}</p>
            <p><strong>Delivery Date:</strong> {{ order.deliveryDate }}</p>
            <p><strong>Order Date:</strong> {{ order.orderDate }}</p>
            <p><strong>Price:</strong> R{{ order.price }}</p>
            <p>
              <strong>Status:</strong>
              <span :class="statusClass(order.status)">
                {{ order.status }}
              </span>
            </p>
            <button @click="ShowOrderDetail(order.id)">{{buttonText}}</button>
            <!-- Display "1" when this specific order's detail is shown -->
            <div v-if="showOrderItem === order.id" class="order-detail-content">
              <div v-if="orderItems.length" class="orders-list">
              <div v-for="orderItem in getOrderItems(order.id)" :key="orderItem.id" class="order-card">
                <p><strong>Order:</strong> {{sortedOrders.findIndex(o => o.id === orderItem.id.orderId)+1}}</p>
                <p><strong>Price Per Item:</strong> {{orderItem.pricePerItem}}</p>
                <p><strong>Quantity:</strong> {{orderItem.quantity}}</p>
                <p><strong>Total Price:</strong> {{orderItem.totalPrice}}</p>
              </div>
              </div>
            </div>

          </div>
        </div>
      </div>

      <!-- No Orders -->
      <div v-else class="no-orders">
        <p>No orders found.</p>
      </div>
    </main>
  </div>
</template>

<script>
import HeaderComponent from "@/components/HeaderComponent.vue";
import OrderService from "@/services/OrderService";
import OrderItemService from "@/services/OrderItemService";
export default {
  name: "UserOrders",
  components: { HeaderComponent },
  data() {
    return { orders: [],
      orderItems: [],
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
    statusClass(status) {
      switch (status.toLowerCase()) {
        case "delivered": return "status-delivered";
        case "busy": return "status-busy";
        case "cart": return "status-cart";
        case "cancelled": return "status-cancelled";
        case "returned": return "status-returned";
        case "ready": return "status-ready";
        default: return "status-cart";
      }
    },
    ShowOrderDetail(orderId) {
      // Toggle the detail view - if clicking the same order, hide it; otherwise show the new one
      this.showOrderItem = this.showOrderItem === orderId ? null : orderId;
    }
  },
  created() {
    this.getOrder();
    this.getOrderItem();
  },

  computed: {
    sortedOrders() {
      if (!this.pickedSort) return this.orders;

      let sorted = [...this.orders];

      switch (this.pickedSort) {
        case "deliveryDate":
          sorted.sort((a, b) => new Date(a.deliveryDate) - new Date(b.deliveryDate));
          break;
        case "orderDate":
          sorted.sort((a, b) => new Date(a.orderDate) - new Date(b.orderDate));
          break;
        case "price":
          sorted.sort((a, b) => a.price - b.price);
          break;
      }

      return sorted;
    }
  }
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
