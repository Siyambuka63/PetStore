<template>
  <div class="signup-page">
    <h1 class="title">PetShop</h1>

    <div class="signup-box">
      <h2 class="titles">Create an Account</h2>
      <form @submit.prevent="handleSignUp">
        <input v-model="firstName" placeholder="First Name" required />
        <input v-model="middleName" placeholder="Middle Name" />
        <input v-model="lastName" placeholder="Surname" required />
        <input v-model="email" placeholder="Email" required />
        <input v-model="phone" placeholder="Phone Number" required />
        <input type="password" v-model="password" placeholder="Password" required />
        <input type="password" v-model="confirmPassword" placeholder="Confirm Password" required />

        <button type="submit">Sign Up</button>
      </form>

      <div class="links">
        <p>Already have an account? <router-link to="/">Login</router-link></p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useAuth } from "@/Auth.js";

const auth = useAuth();

const users = ref([]);
const firstName = ref("");
const middleName = ref("");
const lastName = ref("");
const email = ref("");
const phone = ref("");
const password = ref("");
const confirmPassword = ref("");

function handleSignUp() {
  if (password.value !== confirmPassword.value) {
    alert("Passwords do not match!");
    return;
  }

  const newUser = {
    id: users.value.length + 1,
    firstName: firstName.value,
    middleName: middleName.value,
    lastName: lastName.value,
    email: email.value,
    phone: phone.value,
    password: password.value,
  };

  users.value.push(newUser);
  auth.setUser(newUser);
  alert(`Sign up successful! User ID: ${newUser.id}`);


  firstName.value = "";
  middleName.value = "";
  lastName.value = "";
  email.value = "";
  phone.value = "";
  password.value = "";
  confirmPassword.value = "";
}
</script>




<style scoped>
.signup-page {
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
  color: #000;
  margin-bottom: 30px;
}

.signup-box {
  background-color: #fff;
  padding: 40px 30px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
  width: 360px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.signup-box input {
  width: 100%;
  padding: 12px 10px;
  margin-bottom: 15px;
  border-radius: 6px;
  border: 1px solid #ccc;
  font-size: 16px;
}

.signup-box button {
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

.signup-box button:hover {
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
