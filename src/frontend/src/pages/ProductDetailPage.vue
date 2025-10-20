<template>
  <div>
    <!-- Header -->
    <HeaderComponent />

    <main class="details-container">
      <!-- Show loading or error -->
      <p v-if="loading">Loading product details...</p>
      <p v-else-if="error">{{ error }}</p>

      <!-- Product Details -->
      <div v-else-if="product" class="product-details product-card">
        <!-- Product image -->
        <img
            :src="product.imageData ? `/petstore/product/image/${product.id}` : '/productImages/placeholder.jpg'"
            :alt="product.productName" class="product-image"
        />

        <!-- Info Section -->
        <div class="info">
          <h1>{{ product.productName }}</h1>
          <p class="description">{{ product.description }}</p>

          <!-- Rating -->
          <p><strong>Rating:</strong> {{ product.rating }}</p>

          <!-- Stock -->
          <p><strong>Stock:</strong> {{ product.stock }}</p>

          <!-- Price -->
          <p class="price">
          <span v-if="product.discountPercent && product.discountPercent > 0">
            Was: <s>R{{ product.price.toFixed(2) }}</s><br/>
            Now: R{{ (product.price * (1 - product.discountPercent / 100)).toFixed(2) }}
            ({{ product.discountPercent }}% off)
          </span>
            <span v-else>
            R{{ product.price.toFixed(2) }}
          </span>
          </p>

          <!-- Reviews -->
          <div class="reviews">
            <h3>Customer Reviews</h3>
            <ul>
              <li v-for="(review, index) in product.reviews" :key="index">
                <strong>{{ review.user }}</strong>: {{ review.comment }}
                <span>⭐ {{ review.rating }}</span>
              </li>
            </ul>
            <p v-if="!product.reviews || product.reviews.length === 0">No reviews yet.</p>
          </div>

          <!-- Add to cart -->
          <button v-if="inCart" class="cart" id="remove" @click="removeFromCart(product.id)">
            In Cart
          </button>
          <button v-else @click="handleAddItem(product.id, product.onSale ? product.salePrice : product.price, 1)"
                  class="cart">
            Add to Cart
          </button>

          <!-- Add to wishlist -->
          <button v-if="wishlisted" class="wishlist" id="remove_button" @click="removeFromWishlist(product.id)">
            Wishlisted
          </button>
          <button v-else @click="handleAddItemToWishlist(product.id)" class="wishlist">
            Add to Wishlist
          </button>

          <!-- Additional Information -->
          <div class="more-info">
            <h3>Additional Information</h3>
            <p><strong>Weight: </strong>
              {{ product.weight
                  ? (product.weight >= 1000
                      ? (product.weight / 1000).toFixed(2) + ' kg'
                      : product.weight + ' g')
                  : 'N/A' }}
            </p>
            <p><strong>Brand: </strong> {{ product.brand || 'N/A' }}</p>
            <p><strong>Life Stage: </strong> {{ product.lifeStage || 'N/A' }}</p>
            <p><strong>Food Type: </strong> {{ product.foodType || 'N/A' }}</p>
            <p><strong>Pet Type: </strong> {{ product.petType || 'N/A' }}</p>
            <p><strong>Flavour: </strong> {{ product.flavour || 'N/A' }}</p>
          </div>

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
import {addItem, isCarted, removeItem} from "@/services/CartService";
import {addItemToWishlist, isWishlisted, removeWishlistItem} from "@/services/WishlistService";

export default {
  name: "ProductDetails",
  components: { FooterComponent, HeaderComponent },
  data() {
    return {
      product: null,
      loading: true,
      error: null,
      userID: null,
      inCart: false,
      wishlisted: false,
    };
  },
  methods: {
    async fetchProductDetails() {
      try {
        const productId = this.$route.params.id;
        const response = await axiosInstance.get(`/product/read/${productId}`);
        this.product = response.data;

        await this.checkCartAndWishlist(productId);
      } catch (err) {
        console.error(err);
        this.error = "Failed to load product details.";
      } finally {
        this.loading = false;
      }
    },
    async checkCartAndWishlist(productId) {
      if (!this.userID) return;
      this.inCart = await isCarted(this.userID, productId);
      this.wishlisted = await isWishlisted(this.userID, productId);
    },
    async handleAddItem(productID, price, quantity) {
      if (this.userID) {
        await addItem(this.userID, productID, price, quantity, this.$router);
        this.inCart = true;
      } else {
        this.$router.push("/login");
      }
    },
    async handleAddItemToWishlist(productID) {
      if (this.userID) {
        await addItemToWishlist(this.userID, productID);
        this.wishlisted = true;
      } else {
        this.$router.push("/login");
      }
    },
    async removeFromCart(productID) {
      if (!this.userID) return;
      await removeItem(this.userID, productID);
      this.$router.go(0);
    },
    async removeFromWishlist(productID) {
      if (!this.userID) return;
      await removeWishlistItem(this.userID, productID);
      this.wishlisted = false;
    },
  },
  async mounted() {
    this.userID = localStorage.getItem("email");
    await this.fetchProductDetails();
  },
};
</script>

<style scoped>
.details-container {
  width: calc(100% - 60px);
  padding: 0 30px;
  display: flex;
  justify-content: center;
  margin-top: 40px;
}

.product-details {
  max-width: 800px;
  width: 100%;
}

.product-card {
  border: 2px solid #ccc;
  border-radius: 8px;
  padding: 15px;
  text-align: center;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.product-card img {
  width: 100%;
  max-height: 250px;
  object-fit: contain;
  margin-bottom: 10px;
}

.product-card h1 {
  font-size: 22px;
  margin: 5px 0;
  color: #2d3436;
}

.product-card p {
  font-size: 14px;
  color: #636e72;
  margin: 5px 0 10px;
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
  padding: 10px 14px;
  background: #0984e3;
  color: white;
  cursor: pointer;
  font-weight: bold;
}

.product-card .cart:hover {
  background: #0652DD;
}

#remove {
  background: #0652DD;
}

#remove:hover {
  background: #0984e3;
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

#remove_button {
  background: #f1f1f1;
  border: 2px solid #0652DD;
  color: #0652DD;
}

#remove_button:active, #remove_button:hover {
  background: white;
}

.reviews {
  margin-top: 15px;
  text-align: left;
}

p {
  margin-top: 15px;
}

.price span {
  font-size: 20px;
}

.info {
  text-align: left;
}
</style>
