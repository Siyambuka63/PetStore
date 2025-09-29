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

          <!-- Quantity --> 
          <p><strong>Quantity:</strong> {{ product.quantity }}</p> 

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

          <!-- Button --> 
          <button 
              @click="handleAddItem(product.id, product.onSale ? product.salePrice : product.price, product.quantity)" 
              class="cart" 
          > 
            Add to Cart 
          </button> 

          <!-- Add to wishlist -->
          <button @click="handleAddItemToWishlist(product.id)" class="wishlist">
            Add to Wishlist
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

    async handleAddItemToWishlist(productID){
      if (this.userID) {
        await addItemToWishlist(this.userID, productID);
      } else {
        this.$router.push("/login");
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

<style scoped>
.details-container {
  width: calc(100% - 60px);
  padding: 0 30px;
  display: flex;
  justify-content: center;
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
  transition: transform 0.2s, box-shadow 0.2s;
}

.product-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 15px rgba(0,0,0,0.1);
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
  margin: 5px auto;
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

.reviews {
  margin-top: 15px;
  text-align: left;
}
</style>


