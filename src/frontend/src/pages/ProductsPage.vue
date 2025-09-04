<template>
  <div>
    <HeaderComponent />

    <div class="layout">
      <!-- Sidebar -->
      <div class="sidebar">
        <div class="categories">
          <div class="header">
            <img src="@/assets/user.png" alt="pet" />
            <h2>Categories</h2>
          </div>
          <div class="list">
            <ul>
              <li><a href="#" class="active">All</a></li>
              <li><a href="#">Dog Food</a></li>
              <li><a href="#">Cat Food</a></li>
              <li><a href="#">Fish Food</a></li>
            </ul>
          </div>
        </div>
      </div>

      <!-- Main Content -->
      <main class="main-content">
        <h1>Products</h1>

        <!-- Loading and error states -->
        <div v-if="loading">Loading products...</div>
        <div v-else-if="error">{{ error }}</div>

        <!-- Product list -->
        <div v-else class="products-grid">
          <div class="product-card" v-for="product in products" :key="product.id">
            <img v-if="product.imageAddress"
                :src="product.imageAddress ? `http://localhost:8082/productImages/${product.imageAddress}` : '/productImages/placeholder.jpg'"
                :alt="product.productName"
            />
            <img v-else src="@/assets/logo.png" v-bind:alt="product.productName">
            <h2 v-if="product.onSale">
              Was: <s>R{{ product.price.toFixed(2) }}</s>
              Now: R{{ product.salePrice.toFixed(2) }}
            </h2>
            <h2 v-else v-text="'R' + product.price.toFixed(2)"></h2>
            <p>{{ product.description }}</p>
            <span class="price">R{{ product.price.toFixed(2) }}</span>
            <button v-if="product.stock > 0 && product.on_Sale" class="cart" @click="handleAddItem(userID, product.id, product.salePrice, 1)">Add to Cart</button>
            <button v-else-if="product.stock > 0" class="cart" @click="handleAddItem(userID, product.id, product.price, 1)">Add to Cart</button>
            <button class="wishlist" @click="handleAddItemToWishlist(userID, product.id)">Add to Wishlist</button>
          </div>
        </div>
      </main>
    </div>
  </div>
</template>

<script>
import HeaderComponent from "@/components/HeaderComponent.vue";
import axiosInstance from "@/api/AxiosInstance";
import {addItemToWishlist} from "@/services/WishlistService";
import {addItem} from "@/services/CartService";
import {useAuth} from "@/Auth";

export default {
  name: "ProductsPage",
  components: {
    HeaderComponent,
  },
  data() {
    return {
      products: [],
      loading: true,
      error: null,
      userID: null,
    };
  },
  methods: {
    async handleAddItem(userID, productID, price, quantity){
      //const router = useRouter();

      if (userID) {
        await addItem(userID, productID, price, quantity);
      } else {
        await this.$router.push("/login");
      }
    },
    async handleAddItemToWishlist(userID, productID){
      //const router = useRouter();

      if (userID) {
        await addItemToWishlist(userID, productID);
      } else {
        await this.$router.push("/login");
      }
    },
    async fetchProducts() {
      try {
        const response = await axiosInstance.get("/petstore/product/getAll");

        // Handle different backend response shapes
        if (Array.isArray(response.data)) {
          this.products = response.data;
        } else if (response.data.products) {
          this.products = response.data.products;
        } else {
          this.error = "Unexpected response format from server.";
        }
      } catch (err) {
        console.error(err);
        this.error = "Failed to load products.";
      } finally {
        this.loading = false;
      }
    },

  },
  mounted() {
    const auth = useAuth();

    this.fetchProducts();
    this.userID = auth.userID;
  },
};
</script>

<style scoped>
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
}

.categories {
  border-radius: 8px;
  border: 2px solid #dfe6e9;
}

.categories .header {
  border-bottom: 2px solid #dfe6e9;
  padding-left: 20px;
  display: flex;
  gap: 10%;
  align-items: center;
  background: #0984e3;
}

.categories .header h2 {
  font-size: 16px;
  color: white;
}

.categories .header img {
  height: 20px;
}

.sidebar ul {
  list-style: none;
  padding: 5px;
  margin: 0;
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
  width: 70%;
}

.products-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr)); /* responsive grid */
  gap: 20px;
  margin-top: 20px;
}

.product-card {
  border: 1px solid #dfe6e9;
  border-radius: 8px;
  padding: 15px;
  text-align: center;
  background: #fff;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  transition: transform 0.2s, box-shadow 0.2s;
}

.product-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 15px rgba(0,0,0,0.1);
}

.product-card img {
  width: 100%;
  height: 150px;
  object-fit: contain;
  margin-bottom: 10px;
}

.product-card h2 {
  font-size: 16px;
  margin: 5px 0;
  color: #2d3436;
}

.product-card p {
  font-size: 14px;
  color: #636e72;
  margin: 5px 0 10px;
  height: 40px;
  overflow: hidden;
}

.product-card .price {
  font-weight: bold;
  font-size: 16px;
  margin: 10px 0;
  color: #0984e3;
}

.product-card .cart {
  margin: 5px;
  border: none;
  border-radius: 5px;
  padding: 8px 12px;
  background: #0984e3;
  color: white;
  cursor: pointer;
  font-weight: bold;
}

.product-card .cart:hover {
  background: #0652DD;
}

.product-card .wishlist {
  margin: 5px;
  border: 2px solid #0984e3;
  border-radius: 5px;
  padding: 8px 12px;
  background: white;
  color: #0984e3;
  cursor: pointer;
  font-weight: bold;
}

.product-card .wishlist:hover {
  background: #f1f1f1;
}
</style>
