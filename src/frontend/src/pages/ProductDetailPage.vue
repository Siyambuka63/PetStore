<template>
  <div class="product-details">

    <header class="header">
      <h1 class="title">Pet Store - Product Details</h1>
    </header>


    <div class="product-selector">
      <button
          v-for="item in products"
          :key="item.id"
          class="selector-button"
          @click="selectProduct(item)"
      >
        {{ item.title }}
      </button>
    </div>


    <section class="product-card" v-if="selectedProduct">

      <img
          :src="selectedProduct.image"
          :alt="selectedProduct.title"
          class="product-image"
      />


      <div class="product-info">
        <h2 class="product-title">{{ selectedProduct.title }}</h2>
        <p class="product-description">{{ selectedProduct.description }}</p>


        <ul class="product-extra">
          <li><b>Price:</b> {{ selectedProduct.price }}</li>
          <li><b>Quality:</b> {{ selectedProduct.quality }}</li>
          <li><b>Return Policy:</b> {{ selectedProduct.returnPolicy }}</li>
        </ul>


        <div class="reviews">
          <h3>Customer Reviews</h3>
          <ul>
            <li v-for="(review, index) in selectedProduct.reviews" :key="index">
              "{{ review }}"
            </li>
          </ul>
        </div>


        <button class="cta-button" @click="addToCart">
          🛒 Add to Cart
        </button>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: "ProductDetailsPage",
  data() {
    return {

      products: [
        {
          id: 1,
          title: "Dog Food - Premium Beef Mix",
          description: "Nutritious dog food with protein and vitamins for health and energy.",
          price: "R250",
          quality: "Vet Approved - High Quality",
          returnPolicy: "Can return within 30 days if unopened.",
          reviews: [
            "My dog enjoys it every day!",
            "Good for shiny fur.",
            "Worth the price."
          ],
          image: "https://tse1.mm.bing.net/th/id/OIP.8sUh6mHZbNk3Mwq6JVOouwHaIj?rs=1&pid=ImgDetMain&o=7&rm=3"
        },
        {
          id: 2,
          title: "Cat Food - Salmon & Chicken Blend",
          description: "Tasty salmon and chicken recipe that supports strong bones and coat.",
          price: "R180",
          quality: "Premium Blend",
          returnPolicy: "Return in 15 days if sealed.",
          reviews: [
            "My cat loves this.",
            "Good for sensitive tummies.",
            "Fresh smell and good quality."
          ],
          image: "https://tse3.mm.bing.net/th/id/OIP.v_tG4yQ7CaMal71h67L_ngHaHa?rs=1&pid=ImgDetMain&o=7&rm=3"
        },
        {
          id: 3,
          title: "Fish Food - Tropical Flakes",
          description: "Balanced flakes for tropical fish. Keeps water clear and fish healthy.",
          price: "R120",
          quality: "Aquarium Safe - Trusted Brand",
          returnPolicy: "7 day return if unopened.",
          reviews: [
            "Fish are active and colorful.",
            "Water stays clean.",
            "My fish like the taste."
          ],
          image: "https://tse2.mm.bing.net/th/id/OIP.zYgn3Gye6CfPFsYZ1kKdxQHaHa?rs=1&pid=ImgDetMain&o=7&rm=3"
        }
      ],

      selectedProduct: null
    };
  },
  created() {
    this.selectedProduct = this.products[0];
  },
  methods: {

    selectProduct(product) {
      this.selectedProduct = product;
    },

    addToCart() {
      alert(this.selectedProduct.title + " added to cart!");
    }
  }
};
</script>

<style scoped>

.product-details {
  max-width: 900px;
  margin: auto;
  padding: 20px;
}

.header {
  text-align: center;
  margin-bottom: 20px;
}

.title {
  font-size: 26px;
  font-weight: bold;
  color: #333;
}


.product-selector {
  text-align: center;
  margin-bottom: 20px;
}

.selector-button {
  margin: 5px;
  padding: 8px 15px;
  background: #f0f0f0;
  border: 1px solid #ddd;
  border-radius: 6px;
  cursor: pointer;
}

.selector-button:hover {
  background: #ddd;
}


.product-card {
  display: flex;
  background: #fff;
  padding: 20px;
  border-radius: 12px;
  box-shadow: 0px 4px 8px rgba(0,0,0,0.1);
}


.product-image {
  width: 250px;
  height: auto;
  border-radius: 8px;
  margin-right: 20px;
}


.product-info {
  flex: 1;
}

.product-title {
  font-size: 22px;
  margin-bottom: 10px;
  color: #222;
}

.product-description {
  margin-bottom: 15px;
  color: #555;
}


.product-extra {
  list-style: none;
  padding: 0;
  margin-bottom: 20px;
}

.product-extra li {
  margin-bottom: 5px;
}


.reviews {
  margin-bottom: 20px;
}

.reviews h3 {
  font-size: 18px;
  margin-bottom: 8px;
}

.reviews li {
  margin-bottom: 5px;
  font-style: italic;
}


.cta-button {
  padding: 10px 20px;
  background: #4CAF50;
  color: white;
  font-size: 16px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
}

.cta-button:hover {
  background: #45a049;
}
</style>
