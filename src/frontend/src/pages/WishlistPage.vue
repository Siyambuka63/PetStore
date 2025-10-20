<template>
  <HeaderComponent/>

  <div class="container">
    <sidebar-component/>
    <div class="main-content">
      <h1>My Wishlist</h1>
      <div class="wishlist">
        <div v-if="!items || items.length === 0"> You have no items wishlisted</div>
        <div v-for="(item, index) in items" :key="index" class="wishlist-item">
          <router-link :to="`/products/${item.id}`">
            <div class="left-section">
              <img
                  :src="item.imageData ? `/petstore/product/image/${item.id}` : '/productImages/placeholder.jpg'"
                  :alt="item.productName" class="icon"
              />
            </div>
          </router-link>

          <div class="info">
            <router-link :to="`/products/${item.id}`">
              <h4 v-text="item.productName"></h4>
            </router-link>
            <div v-if="item.onSale">
              <p>Was: <s>R{{ item.price.toFixed(2) }}</s></p>
              <p>Now: R{{ item.salePrice.toFixed(2) }}</p>
            </div>
            <p v-else v-text="'R' + item.price.toFixed(2)"></p>
          </div>

          <div class="rating">
            <img v-for="i in Math.ceil(item.rating)" :key="'filled-' + i" class="stars" src="@/assets/star_filled.png"
                 alt="Filled Star">

            <img v-for="i in Math.floor(5 - item.rating)" :key="'empty-' + i" class="stars"
                 src="@/assets/star_empty.png" alt="Empty Star">
          </div>

          <div class="buttons">
            <button v-if="item.stock > 0 && item.on_Sale" id="add_button"
                    @click="handleAddItem(item.id, item.salePrice, 1)">Add to Cart
            </button>
            <button v-else-if="item.stock > 0" id="add_button" @click="handleAddItem(item.id, item.price, 1)">Add to
              Cart
            </button>
            <p v-else>SOLD OUT</p>
            <button id="remove_button" @click="removeItem(item.id)">Remove from Wishlist</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <FooterComponent/>
</template>

<script>
import HeaderComponent from "@/components/HeaderComponent.vue";
import SidebarComponent from "@/components/SidebarComponent.vue";
import {getUserWishlistItems, removeItemFromWishlist} from "@/services/WishlistService";
import FooterComponent from "@/components/FooterComponent.vue";
import {addItem} from "@/services/CartService";

export default {
  name: "WishlistPage",
  components: {
    FooterComponent,
    SidebarComponent,
    HeaderComponent
  },
  data() {
    return {
      items: [],
      email: null
    };
  },
  async mounted() {
    this.email = localStorage.getItem("email");
    this.items = await getUserWishlistItems(this.email);
  },
  methods: {
    async removeItem(itemID) {
      this.items = await removeItemFromWishlist(this.email, itemID);
    },
    async handleAddItem(productID, price, quantity) {
      if (this.email) {
        await addItem(this.email, productID, price, quantity, this.$router);
      }
    },
  }
};
</script>

<style scoped>
.container {
  display: flex;
  width: 100%;
}

/* Main Content */
.main-content {
  display: flex;
  flex-direction: column;
  width: 70%;
  align-items: center;
}

.wishlist {
  width: 80%;
  display: flex;
  flex-direction: column;
}

.wishlist-item {
  display: flex;
  width: 100%;
  align-items: center;
  justify-content: space-between;
  padding: 10px 20px;
  margin: 5px;
  border-radius: 8px;
  border: 2px solid #ccc;
}

.wishlist-item:hover {
  cursor: pointer;
}

.left-section {
  align-items: center;
}

.icon {
  height: 70px;
}

.info {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.rating {
  display: flex;
  flex-direction: row;
}

.stars {
  height: 30px;
}

.buttons {
  display: flex;
  flex-direction: column;
  align-items: center;
}

button {
  border: none;
  border-radius: 8px;
  margin: 5px;
  padding: 10px;
  color: white;
  width: 100px;
}

#add_button {
  background: #0984e3;
}

#add_button:active, #add_button:hover {
  background: #0652DD;
}

#remove_button {
  background: #FF2D2D;
}

#remove_button:active, #remove_button:hover {
  background: red;
}

a {
  text-decoration: none;
  color: black;
}

</style>