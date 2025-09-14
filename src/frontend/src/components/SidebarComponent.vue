<template>
  <!-- Sidebar -->
  <div class="sidebar">
    <div class="my_account">
      <div class="header">
        <img src="@/assets/user.png" alt="user"/>
        <h2>My Account</h2>
      </div>
      <div class="list">
        <ul>
          <li><router-link to="/profile">My Profile</router-link></li>
          <li><router-link to="/wishlist">My Wishlist</router-link></li>
          <li><router-link to="/orders">My Orders</router-link></li>
          <li><router-link to="/logout">Logout</router-link></li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SidebarComponent"
};
</script>

<style scoped>
/* Sidebar */
.sidebar {
  width: 20%;
  margin: 10px;
  display: flex;
  flex-direction: column;
}

.my_account {
  border-radius: 8px;
  border: 2px solid #ccc;
}

.my_account .header {
  border-top-left-radius: 8px;
  border-top-right-radius: 8px;
  border-bottom: 2px solid #ccc;
  padding-left: 20px;
  display: flex;
  gap: 10%;
  align-items: center;
  background: #0984e3;
}

.my_account .header h2 {
  font-size: 16px;
}

.my_account .header img {
  height: 20px;
}

.sidebar ul {
  list-style: none;
  padding: 5px;
  margin: 0;
}

.sidebar ul li a {
  text-decoration: none;
  color: black;
  font-size: 15px;
  display: block;
  padding: 8px 12px;
  border-radius: 8px;
  transition: background 0.2s;
}

.sidebar ul li a:hover {
  text-decoration: underline;
  font-weight: bold;
}

.sidebar ul li a.router-link-exact-active {
  color: #0984e3;
}
</style>