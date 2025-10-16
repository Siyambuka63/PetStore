<template>
  <div class="admin-dashboard">
    <h1>Admin Dashboard</h1>

    <div class="actions">
      <button @click="showAddProductForm = true">Add Product</button>
    </div>

    <table v-if="products.length">
      <thead>
      <tr>
        <th>Name</th>
        <th>Category</th>
        <th>Price</th>
        <th>Stock</th>
        <th>Description</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="product in products" :key="product.productId">
        <td>{{ product.productName }}</td>
        <td>{{ product.category }}</td>
        <td>R{{ product.price }}</td>
        <td>{{ product.stock }}</td>
        <td>{{ product.description }}</td>
        <td>
          <button @click="editProduct(product)">Edit</button>
          <button @click="deleteProduct(product.productId)">Delete</button>
        </td>
      </tr>
      </tbody>
    </table>

    <div v-if="showAddProductForm" class="form-container">
      <h2>{{ isEditing ? "Edit Product" : "Add New Product" }}</h2>
      <form @submit.prevent="saveProduct">
        <input v-model="form.productName" placeholder="Product Name" required />
        <input v-model="form.category" placeholder="Category" required />
        <input type="number" v-model="form.price" placeholder="Price" required />
        <input type="number" v-model="form.salePrice" placeholder="Sale Price" />
        <input type="number" v-model="form.stock" placeholder="Stock" required />
        <textarea v-model="form.description" placeholder="Product Description"></textarea>

        <button type="submit">{{ isEditing ? "Update" : "Save" }}</button>
        <button type="button" @click="cancel">Cancel</button>
      </form>
    </div>
  </div>
</template>

<script>
import ProductService from "@/services/ProductService";

export default {
  name: "AdminDashboard",
  data() {
    return {
      products: [],
      form: {
        productId: null,
        productName: "",
        category: "",
        price: "",
        salePrice: "",
        stock: "",
        description: "",
      },
      showAddProductForm: false,
      isEditing: false,
    };
  },
  methods: {
    loadProducts() {
      ProductService.getProducts().then((response) => {
        this.products = response.data;
      });
    },

    async saveProduct() {
      if (this.isEditing) {
        await ProductService.updateProduct(this.form);
      } else {
        await ProductService.addProduct(this.form);
      }
      this.loadProducts();
      this.cancel();
    },

    editProduct(product) {
      this.form = { ...product };
      this.isEditing = true;
      this.showAddProductForm = true;
    },

    deleteProduct(id) {
      if (confirm("Are you sure you want to delete this product?")) {
        ProductService.deleteProduct(id).then(() => this.loadProducts());
      }
    },

    cancel() {
      this.showAddProductForm = false;
      this.isEditing = false;
      this.form = {
        productId: null,
        productName: "",
        category: "",
        price: "",
        salePrice: "",
        stock: "",
        description: "",
      };
    },
  },
  mounted() {
    this.loadProducts();
  },
};
</script>

<style scoped>
.admin-dashboard {
  padding: 2rem;
}
table {
  width: 100%;
  border-collapse: collapse;
}
th,
td {
  border: 1px solid #ddd;
  padding: 8px;
}
.form-container {
  margin-top: 1rem;
  padding: 1rem;
  border: 1px solid #ccc;
}
textarea {
  width: 100%;
  min-height: 60px;
}
</style>
