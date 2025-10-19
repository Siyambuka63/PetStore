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
import { useRouter } from "vue-router";
import {LogIn} from "@/services/UserService";

const email = ref("");
const password = ref("");
const router = useRouter();

async function handleLogin() {
  await LogIn(router, email.value, password.value);
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
  color: #0984e3;
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
  width: calc(100% - 20px);
  padding: 12px 10px;
  margin-bottom: 15px;
  border-radius: 6px;
  border: 2px solid #ccc;
  font-size: 16px;
}

.login-box input:focus {
  border-color: #0984e3;
  outline: none;
}

.login-box button {
  width: 100%;
  padding: 12px;
  border: none;
  border-radius: 6px;
  background-color: #0984e3;
  color: white;
  font-size: 16px;
  cursor: pointer;
  transition: background 0.2s;
}

.login-box button:hover {
  background-color: #0652DD;
}

.links {
  margin-top: 15px;
  text-align: center;
}

.links a {
  color: #0984e3;
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
