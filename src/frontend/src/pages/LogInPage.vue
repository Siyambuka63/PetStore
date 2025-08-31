<template>
  <div class="login-page">
    <h1 class="title">PetShop</h1>

    <div class="login-box">
      <h2 class="titles">Log in to PetShop</h2>
      <form @submit.prevent="handleLogin">
        <input type="email" v-model="email" placeholder="Email" required />
        <input type="password" v-model="password" placeholder="Password" required />
        <button type="submit">Login</button>
      </form>

      <div class="links">
        <p>Don't have an account? <router-link to="/signup">Sign up</router-link></p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import axiosInstance from "@/api/axiosInstance";
import { useAuth } from "@/Auth.js";
import { useRouter } from "vue-router";

const email = ref("");
const password = ref("");
const auth = useAuth();
const router = useRouter();

async function handleLogin() {
  try {
    const response = await axiosInstance.post("/auth/login", {
      email: email.value,
      password: password.value
    });

    const user = response.data;
    auth.setUserId(user.id);

    alert(`Login successful! User ID: ${auth.userID}`);
    router.push("/products");
  } catch (err) {
    console.error(err);
    alert(err.response?.data?.message || "Login failed.");
  }
}
</script>



<style scoped>
.login-page {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f0f2f5;
}

.title {
  font-size: 48px;
  font-weight: bold;
  color: #1877f2;
  margin-bottom: 40px;
}

.titles {
  font-size: 18px;
  font-weight: bold;
  color: #000000;
  margin-bottom: 40px;
}

.login-box {
  background-color: #fff;
  padding: 40px 30px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
  width: 360px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.login-box input {
  width: 100%;
  padding: 12px 10px;
  margin-bottom: 15px;
  border-radius: 6px;
  border: 1px solid #ccc;
  font-size: 16px;
}

.login-box button {
  width: 100%;
  padding: 12px;
  border: none;
  border-radius: 6px;
  background-color: #1877f2;
  color: white;
  font-size: 16px;
  cursor: pointer;
  transition: background 0.2s;
}

.login-box button:hover {
  background-color: #165fc1;
}

.links {
  margin-top: 15px;
  text-align: center;
}

.links a {
  color: #1877f2;
  text-decoration: none;
  font-size: 14px;
}

.links a:hover {
  text-decoration: underline;
}

.links p {
  margin-top: 10px;
  font-size: 14px;
  color: #555;
}
</style>
