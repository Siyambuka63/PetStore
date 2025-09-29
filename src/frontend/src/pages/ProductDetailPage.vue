<template>
  <div>
    <!-- Header -->
    <HeaderComponent />

    <main class="details-container">
      <!-- Show loading or error -->
      <p v-if="loading">Loading product details...</p>
      <p v-else-if="error">{{ error }}</p>

      <!-- Product Details -->
      <div v-else-if="product" class="product-details">
        <!-- Product image -->
        <img
            :src="product.imageAddress
            ? '/productImages/' + product.imageAddress
            : '/productImages/placeholder.jpg'"
            :alt="product.productName"
            class="product-image"
        />

        <!-- Info Section -->
        <div class="info">
          <h1>{{ product.productName }}</h1>
          <p class="description">{{ product.description }}</p>

          <!-- Price -->
          <p class="price">
            <span v-if="product.onSale">
              Was: <s>R{{ product.price.toFixed(2) }}</s><br />
              Now: R{{ product.salePrice.toFixed(2) }}
            </span>
            <span v-else>
              R{{ product.price.toFixed(2) }}
            </span>
          </p>

          <!-- Quality -->
          <p><strong>Quality:</strong> {{ product.quality }}</p>

          <!-- Return Policy -->
          <p><strong>Return Policy:</strong> {{ product.returnPolicy }}</p>

          <!-- Reviews -->
          <div class="reviews">
            <h3>Customer Reviews</h3>
            <ul>
              <li v-for="(review, index) in product.reviews" :key="index">
                <strong>{{ review.user }}</strong>: {{ review.comment }}
                <span>тнР {{ review.rating }}</span>
              </li>
            </ul>
            <p v-if="!product.reviews || product.reviews.length === 0">No reviews yet.</p>
          </div>

          <!-- Button -->
          <button
              @click="handleAddItem(product.id, product.onSale ? product.salePrice : product.price, 1)"
              class="cart"
          >
            Add to Cart
          </button>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import HeaderComponent from "@/components/HeaderComponent.vue";
import axiosInstance from "@/api/AxiosInstance";
import { addItem } from "@/services/CartService";
import { useAuth } from "@/Auth";

export default {
  name: "ProductDetails",
  components: { HeaderComponent },
  data() {
    return {
      product: null,
      loading: true,
      error: null,
      userID: null,
    };
  },
  methods: {
    async fetchProductDetails() {
      try {
        const productId = this.$route.params.id;
        const response = await axiosInstance.get(`/petstore/product/${productId}`);
        this.product = response.data;
      } catch (err) {
        console.error(err);
        this.error = "Failed to load product details.";
      } finally {
        this.loading = false;
      }
    },
    async handleAddItem(productID, price, quantity) {
      if (this.userID) {
        await addItem(this.userID, productID, price, quantity);
        this.$router.go(0);
      } else {
        this.$router.push("/login");
      }
    },
  },
  mounted() {
    const auth = useAuth();
    this.userID = auth.getEmail();
    this.fetchProductDetails();
  },
};
</script>

