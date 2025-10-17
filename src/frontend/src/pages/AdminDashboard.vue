<template>
  <div>
    <!-- Header -->
    <HeaderComponent />


    <div class="admin-header">
      <h1>Admin Dashboard</h1>
      <button class="add-product-btn" @click="openAddModal">+ Add Product</button>
    </div>

    <h2 class="section-title">Products</h2>


    <p v-if="loading" style="padding-left:30px">Loading products...</p>
    <p v-else-if="error" style="padding-left:30px">{{ error }}</p>

    <!-- Product Grid -->
    <div v-else class="products">
      <div v-for="product in products" :key="product.id" class="product-card">
        <img
            :src="product.imageAddress
            ? '/productImages/' + product.imageAddress
            : '/productImages/placeholder.png'"
            :alt="product.productName"
        />

        <p class="product-link"><strong>{{ product.productName }}</strong></p>


        <p class="price">
          <span v-if="product.salePercentage && product.salePercentage > 0">
            Was: <s>R{{ product.price.toFixed(2) }}</s><br />
            Now: R{{ (product.price * (1 - product.salePercentage / 100)).toFixed(2) }}
            ({{ product.salePercentage }}% off)
          </span>
          <span v-else>
            R{{ product.price.toFixed(2) }}
          </span>
        </p>

        <p class="description">{{ product.description }}</p>
        <p><strong>Stock:</strong> {{ product.stock }}</p>


        <button class="edit" @click="editProduct(product)">Edit</button>
        <button class="delete" @click="deleteProduct(product.id)">Delete</button>
      </div>
    </div>


    <div v-if="showModal" class="modal-overlay">
      <div class="modal">
        <h2>{{ editMode ? 'Edit Product' : 'Add Product' }}</h2>

        <form @submit.prevent="saveProduct">
          <label>Product Name</label>
          <input v-model="form.productName" required />

          <label>Price</label>
          <input v-model.number="form.price" type="number" required />

          <label>Sale Percentage (0 if none)</label>
          <input v-model.number="form.salePercentage" type="number" min="0" max="100" />

          <label>Description</label>
          <textarea v-model="form.description" required></textarea>

          <label>Stock</label>
          <input v-model.number="form.stock" type="number" min="0" max="5000"  />

          <label>Image Filename (from assets)</label>
          <input v-model="form.imageAddress" placeholder="example.png" />

          <div class="modal-buttons">
            <button type="submit" class="save">Save</button>
            <button type="button" class="cancel" @click="closeModal">Cancel</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import ProductService from "@/services/ProductService";
import HeaderComponent from "@/components/HeaderComponent.vue";

export default {
  name: "AdminDashboard",
  components: { HeaderComponent },
  data() {
    return {
      products: [],
      loading: true,
      error: null,
      showModal: false,
      editMode: false,
      form: {
        id: null,
        productName: "",
        price: 0,
        salePercentage: 0,
        description: "",
        stock: 0,
        imageAddress: ""
      },
    };
  },
  methods: {
    async loadProducts() {
      this.loading = true;
      try {
        const response = await ProductService.getProduct();
        this.products = response.data;
        this.error = null;
      } catch (err) {
        console.error(err);
        this.error = "Failed to load products.";
      } finally {
        this.loading = false;
      }
    },
    openAddModal() {
      this.editMode = false;
      this.form = {
        id: null,
        productName: "",
        price: 0,
        salePercentage: 0,
        description: "",
        stock: 0,
        imageAddress: ""
      };
      this.showModal = true;
    },
    editProduct(product) {
      this.editMode = true;
      this.form = { ...product };
      this.showModal = true;
    },
    closeModal() {
      this.showModal = false;
    },
    async saveProduct() {
      try {
        if (this.editMode) {
          await ProductService.updateProduct(this.form);
        } else {
          await ProductService.addProduct(this.form);
        }
        await this.loadProducts();
        this.closeModal();
      } catch (err) {
        console.error(err);
        alert("Error saving product. Please try again.");
      }
    },
    async deleteProduct(id) {
      if (confirm("Are you sure you want to delete this product?")) {
        try {
          await ProductService.deleteProduct(id);
          await this.loadProducts();
        } catch (err) {
          console.error(err);
          alert("Failed to delete product.");
        }
      }
    },
  },
  mounted() {
    this.loadProducts();
  }
};
</script>

<style scoped>
.products {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
  gap: 30px;
  padding: 0 30px;
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
  height: 150px;
  object-fit: contain;
  margin-bottom: 10px;
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

.product-card .edit,
.product-card .delete {
  margin: 8px 5px 0 5px;
  border-radius: 5px;
  padding: 6px 10px;
  font-weight: bold;
  cursor: pointer;
  width: 100%;
  box-sizing: border-box;
}

.product-card .edit {
  border: none;
  background: #0984e3;
  color: white;
}

.product-card .delete {
  border: 2px solid #0984e3;
  background: white;
  color: #0984e3;
}

.product-card .edit:hover {
  background: #0652DD;
}

.product-card .delete:hover {
  background: #f1f1f1;
}

.modal-overlay {
  position: fixed;
  inset: 0;
  background: rgba(0,0,0,0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 10;
}

.modal {
  background: white;
  border-radius: 10px;
  padding: 25px;
  width: 400px;
  box-shadow: 0 10px 25px rgba(0,0,0,0.2);
}

.modal h2 {
  text-align: center;
  color: #0984e3;
  margin-bottom: 15px;
}

.modal form {
  display: flex;
  flex-direction: column;
}

.modal label {
  margin: 5px 0;
  color: #2d3436;
}

.modal input,
.modal textarea {
  margin-bottom: 10px;
  padding: 8px;
  border-radius: 5px;
  border: 1px solid #ccc;
}

.modal-buttons {
  display: flex;
  justify-content: space-between;
}
.add-product-btn {
  background: #0984e3;
  color: white;
  border: none;
  border-radius: 6px;
  padding: 10px 20px;
  font-weight: bold;
  cursor: pointer;
}

.add-product-btn:hover {
  background: #0652DD;
}

.admin-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px 30px;
}
</style>
