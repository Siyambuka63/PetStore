<template>
  <HeaderComponent/>

  <div class="container">
    <sidebar-component/>
    <div class="main-content">
      <h1>My Wishlist</h1>
      <div class="wishlist">
        <div v-if="!items || items.length === 0"> You have no items wishlisted </div>
        <div v-for="(item, index) in items" :key="index" class="wishlist-item">
          <div class="left-section">
            <img class="icon" v-if="item.image_address" :src="require(`@/assets/${item.image_address}`)" v-bind:alt="item.productName">
            <img class="icon" v-else src="@/assets/logo.png" v-bind:alt="item.productName">
          </div>

          <div class="info">
            <h3 v-text="item.productName"></h3>
            <p v-if="item.onSale">
              Was: <s>R{{ item.price.toFixed(2) }}</s>
              Now: R{{ item.salePrice.toFixed(2) }}
            </p>
            <p v-else v-text="'R' + item.price.toFixed(2)"></p>
          </div>

          <div class="rating">
            <img v-for="i in Math.ceil(item.rating)" :key="'filled-' + i" class = "stars" src="@/assets/star_filled.png" alt="Filled Star">

            <img v-for="i in Math.ceil(5- item.rating)" :key="'empty-' + i" class = "stars" src="@/assets/star_empty.png" alt="Empty Star">
          </div>

          <div class = "buttons">
            <button v-if="item.stock > 0 && item.on_Sale" id="add_button" @click="handleAddItem(userID, item.id, item.salePrice, 1)">Add to Cart</button>
            <button v-else-if="item.stock > 0" id="add_button" @click="handleAddItem(userID, item.id, item.price, 1)">Add to Cart</button>
            <p v-else>SOLD OUT</p>
            <button id="remove_button" @click="removeItem(this.userID, item.id)" >Remove</button>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import HeaderComponent from "@/components/HeaderComponent.vue";
import SidebarComponent from "@/components/SidebarComponent.vue";
import {useAuth} from "@/Auth";
import {getUserWishlistItems, removeItemFromWishlist, removeWishlistUser} from "@/services/WishlistService";

export default {
  name: "WishlistPage",
  components: {
    SidebarComponent,
    HeaderComponent
  },
  data() {
    return {
      items: [],
      userID: null
    };
  },
  async mounted() {
    const user = useAuth();

    this.userID = user.userID;
    this.items = await getUserWishlistItems(this.userID);
  },
  methods: {
    async removeItem(userId, itemID) {
      await removeWishlistUser(userId, itemID);
      const updatedUser = await removeItemFromWishlist(userId, itemID);
      this.items = await updatedUser.wishlistItems;
    }
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
  border: 2px solid #dfe6e9;
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

</style>