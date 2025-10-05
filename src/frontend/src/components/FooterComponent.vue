<template>
  <footer class="footer">
    <div class="footer-container">
      <!-- Left Section -->
      <div class="footer-section">
        <h3 class="footer-title">Quick Links</h3>
        <router-link to="/" class="footer-link">Home</router-link>
        <router-link to="/products" class="footer-link">Products</router-link>
        <router-link to="/wishlist" class="footer-link">Wishlist</router-link>
        <router-link to="/profile" class="footer-link">Account</router-link>
      </div>

      <!-- Middle Section -->
      <div class="footer-section">
        <h3 class="footer-title">Information</h3>
        <router-link to="/about" class="footer-link">About Us</router-link>
        <router-link to="/contact" class="footer-link">Contact Us</router-link>
        <router-link to="/policy" class="footer-link">Privacy Policy</router-link>
        <router-link to="/terms" class="footer-link">Terms & Conditions</router-link>
      </div>

      <!-- Newsletter Section -->
      <div class="footer-section newsletter">
        <h3 class="footer-title">Subscribe to our Newsletter</h3>
        <p>Get the latest pet care tips, product updates, and exclusive offers.</p>
        <form class="newsletter-form" @submit.prevent="subscribe">
          <input
              v-model="name"
              type="text"
              placeholder="Enter your name"
              class="newsletter-input"
              required
          />
          <input
              v-model="email"
              type="email"
              placeholder="Enter your email"
              class="newsletter-input"
              required
          />
          <button type="submit" class="subscribe-btn">Subscribe</button>
        </form>
      </div>
    </div>

    <!-- Bottom Bar -->
    <div class="footer-bottom">
      <p>© {{ new Date().getFullYear() }} PetShop. All rights reserved.</p>
      <div class="social-icons">
        <i class="fa fa-facebook"></i>
        <i class="fa fa-instagram"></i>
        <i class="fa fa-twitter"></i>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name: "FooterComponent",
  data() {
    return {
      name: "",
      email: ""
    };
  },
  methods: {
    subscribe() {
      alert(`Thank you, ${this.name}! You’re subscribed with ${this.email}.`);
      this.name = "";
      this.email = "";
    }
  }
};
</script>

<style scoped>
.footer {
  background-color: #0984e3;
  color: white;
  padding: 40px 60px 20px;
  margin-top: 40px;
}

.footer-container {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  gap: 40px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.3);
  padding-bottom: 30px;
}

.footer-section {
  flex: 1;
  min-width: 220px;
}

.footer-title {
  font-size: 16px;
  margin-bottom: 15px;
  text-transform: uppercase;
  font-weight: bold;
  border-left: 3px solid white;
  padding-left: 8px;
}

.footer-link {
  display: block;
  color: white;
  text-decoration: none;
  margin: 6px 0;
  font-size: 14px;
}

.footer-link:hover {
  color: #0652DD;
}

.newsletter-form {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.newsletter-input {
  padding: 8px;
  border: none;
  border-radius: 6px;
  width: 100%;
}

.subscribe-btn {
  background-color: white;
  color: #0984e3;
  border: none;
  border-radius: 6px;
  padding: 8px;
  cursor: pointer;
  font-weight: bold;
}

.subscribe-btn:hover {
  background-color: #0652DD;
  color: white;
}

.footer-bottom {
  margin-top: 20px;
  text-align: center;
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
}

.footer-bottom p {
  font-size: 13px;
}

.social-icons {
  display: flex;
  gap: 15px;
}

.social-icons i {
  font-size: 18px;
  cursor: pointer;
}

.social-icons i:hover {
  color: #0652DD;
}
</style>