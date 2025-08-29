<template>
  <HeaderComponent/>

  <div class="container">
    <h1>My Wishlist</h1>
    <div class="wishlist">
      <div v-if="items.length === 0">You have no items wishlisted</div>
      <div v-for="(item, index) in items" :key="index" class="wishlist-item">
        <div class="left-section">
          <img class="icon" src="@/assets/logo.png" v-bind:alt="item.name">
        </div>

        <div class="info">
          <h3 v-text="item.name"></h3>
          <p v-if="item.on_sale">
            Was: <s>R{{ item.price.toFixed(2) }}</s>
            Now: R{{ item.sale_price.toFixed(2) }}
          </p>
          <p v-else v-text="'R' + item.price.toFixed(2)"></p>
        </div>

        <div class="rating">
          <img v-for="i in Math.ceil(item.rating)" :key="'filled-' + i" class = "stars" src="@/assets/star_filled.png" alt="Filled Star">

          <img v-for="i in Math.ceil(5- item.rating)" :key="'empty-' + i" class = "stars" src="@/assets/star_empty.png" alt="Empty Star">
        </div>

        <div class = "buttons">
          <button v-if = "item.stock >= 1" id="add_button">Add to Cart</button>
          <p v-else>SOLD OUT</p>
          <button id="remove_button">Remove</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import HeaderComponent from "@/components/HeaderComponent.vue";

export default {
  name: "WishlistPage",
  components: {HeaderComponent},
  data() {
    return {
      items: [
        {
          image: "",
          name: "Wireless Headphones",
          on_sale: false,
          price: 200.0,
          sale_price: 180.0,
          rating: 3.5,
          stock: 3
        },
        {
          image: "",
          name: "Wired Headphones",
          on_sale: true,
          price: 200.0,
          sale_price: 180.0,
          rating: 2.5,
          stock: 3
        },
        {
          image: "",
          name: "Headphones",
          on_sale: true,
          price: 200.0,
          sale_price: 150.0,
          rating: 4.5,
          stock: 3
        }
      ]
    };
  }
};
</script>

<style scoped>
.container {
  display: flex;
  width: 100%;
  flex-direction: column;
  align-items: center;
}

.wishlist {
  width: 80%;
  display: flex;
  flex-direction: column;
}

.wishlist-item {
  display: flex;
  gap: 10%;
  align-items: center;
  justify-content: center;
  padding: 10px;
  margin: 5px;
  border-radius: 8px;
  border: 2px solid #dfe6e9;
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