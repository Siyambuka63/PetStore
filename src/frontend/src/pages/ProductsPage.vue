<template>
  <div>
    <!-- Simple header -->
    <HeaderComponent/>

    <main>
      <h1 style="padding-left: 30px">Products</h1>

      <!-- Show loading or error -->
      <p v-if="loading">Loading products...</p>
      <p v-else-if="error">{{ error }}</p>

      <!-- Show products -->
      <div v-else class="products">
        <div v-for="product in products" :key="product.id" class="product-card">

          <!-- Clickable Product image -->
          <router-link :to="`/products/${product.id}`">
            <img
                :src="`http://localhost:8080/product/image/${product.id}`
                ? '/productImages/' + product.imageAddress
                : '/productImages/placeholder.jpg'"
                :alt="product.productName"
            />
          </router-link>

          <!-- Clickable Product name -->
          <p>
            <router-link :to="`/products/${product.id}`" class="product-link">
              <strong>{{ product.productName }}</strong>
            </router-link>
          </p>

          <!-- Product price -->
          <p class="price">
  <span v-if="product.discountedPrice < product.price">
    Was: <s>R{{ product.price.toFixed(2) }}</s><br>
    Now: R{{ product.discountedPrice.toFixed(2) }}
  </span>
            <span v-else>
    R{{ product.price.toFixed(2) }}
  </span>
          </p>


          <!-- Description -->
          <p>{{ product.description }}</p>

          <!-- Rating -->
          <p><strong>Rating:</strong> {{ product.rating }}</p>

          <!-- Add to cart -->
          <button @click="handleAddItem(product.id, product.onSale ? product.salePrice : product.price, 1)"
                  class="cart">
            Add to Cart
          </button>

          <!-- Add to wishlist -->
          <button @click="handleAddItemToWishlist(product.id)" class="wishlist">
            Add to Wishlist
          </button>

        </div>
      </div>
    </main>
    <FooterComponent/>
  </div>
</template>

<script>
import HeaderComponent from "@/components/HeaderComponent.vue";
import FooterComponent from "@/components/FooterComponent.vue";
import axiosInstance from "@/api/AxiosInstance";
import {addItemToWishlist} from "@/services/WishlistService";
import {addItem} from "@/services/CartService";
import {useAuth} from "@/Auth";

export default {
  name: "ProductsPage",
  components: {
    HeaderComponent,
    FooterComponent,
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
    async handleAddItem(productID, price, quantity) {
      if (this.userID) {
        await addItem(this.userID, productID, price, quantity);
        this.$router.go(0);
      } else {
        this.$router.push("/login");
      }
    },
    async handleAddItemToWishlist(productID) {
      if (this.userID) {
        await addItemToWishlist(this.userID, productID);
      } else {
        this.$router.push("/login");
      }
    },
    async fetchProducts() {
      try {
        console.log(useAuth().getEmail());
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
    this.userID = auth.getEmail();
  },
};
</script>

<style scoped>
.products {
  width: calc(100% - 60px);
  padding: 0 30px;
  gap: 30px;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
}

.product-card {
  border: 2px solid #ccc;
  border-radius: 8px;
  padding: 15px;
  text-align: center;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  transition: transform 0.2s, box-shadow 0.2s;
}

.product-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 15px rgba(0, 0, 0, 0.1);
}

.product-card img {
  width: 100%;
  height: 150px;
  object-fit: contain;
  margin-bottom: 10px;
}

.product-card h2 {
  font-size: 18px;
  margin: 5px 0;
  color: #2d3436;
}

.product-card p {
  font-size: 14px;
  color: #636e72;
  margin: 5px 0 10px;
  height: auto;
  overflow: visible;
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

.product-link {
  text-decoration: none;
  color: inherit;
}

.product-link:hover {
  color: #0984e3;
}
</style>
