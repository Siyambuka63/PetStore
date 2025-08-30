<template>
  <HeaderComponent/>

  <div class="container">
    <sidebar-component/>
    <!-- Main Content -->
    <main class="main-content">
      <h1>Profile Information</h1>

      <form>
        <div class="content">
          <div class="details-group">
            <h2>General Information</h2>
            <label for="firstName">First Name</label>
            <input type="text" id="firstName" value="John">

            <label for="middleName">Middle Name</label>
            <input type="text" id="middleName">

            <label for="Surname">Surname</label>
            <input type="text" id="surname" value="Doe">

            <label for="email">Email Address</label>
            <input type="email" id="email" value="johndoe@gmail.com">

            <label for="phone">Phone</label>
            <input type="text" id="phone" value="0987654321">
          </div>

          <div style="width: 100%">
            <div class="details-group">
              <h2>Password Reset</h2>
              <label for="password">New Password</label>
              <input type="password" id="password">

              <label for="password_confirmation">Confirm Password</label>
              <input type="password" id="password_confirmation">
            </div>

            <div class="details-group" style="margin-top: 50px">
              <h2>Card Details</h2>
              <select>
                <option>MASTER CARD ending with 8907</option>
              </select>
              <button>Remove card</button>
            </div>
          </div>

          <div class="details-group">
            <h2>Shipping Address</h2>
            <label for="shipping_street">Street</label>
            <input type="text" id="shipping_street" value="123 Sam Street">

            <label for="shipping_complex">Complex/Flat/Apartment Detail</label>
            <input type="text" id="shipping_complex" value="Floor 3A">

            <label for="shipping_suburb">Suburb</label>
            <input type="text" id="shipping_suburb" value="Place">

            <label for="shipping_city">City</label>
            <input type="text" id="shipping_city" value="Placers">

            <label for="shipping_postal">Postal</label>
            <input type="text" id="shipping_postal" value="4567">
          </div>

          <div class="details-group">
            <h2>Billing Address Details</h2>
            <label for="billing_street">Street</label>
            <input type="text" id="billing_street" value="123 Sam Street">

            <label for="billing_complex">Complex/Flat/Apartment Detail</label>
            <input type="text" id="billing_complex" value="Floor 3A">

            <label for="billing_suburb">Suburb</label>
            <input type="text" id="billing_suburb" value="Place">

            <label for="billing_city">City</label>
            <input type="text" id="billing_city" value="Placers">

            <label for="postal">Postal</label>
            <input type="text" id="postal" value="4567">
          </div>
        </div>

        <button type="submit" style="margin-top: 10px">Save Changes</button>
      </form>
    </main>
  </div>
</template>

<script>
import HeaderComponent from "@/components/HeaderComponent.vue";
import SidebarComponent from "@/components/SidebarComponent.vue";

export default {
  name: 'ProfilePage',
  components: {
    SidebarComponent,
    HeaderComponent
  },
  props: {
    msg: String
  }
}
</script>

<style scoped>
.container {
  display: flex;
  width: 100%;
}

/* Main Content */
.main-content {
  padding: 10px 30px;
  display: flex;
  flex-direction: column;
  width: 70%;
}

.content {
  gap: 50px;
  display: grid;
  grid-template-columns: 1fr 1fr;
}

input {
  width: calc(100% - 20px);
  padding: 6px 10px;
  border-radius: 8px;
  border: 2px solid #dfe6e9;
}

input:focus {
  border-color: #0984e3;
  outline: none;
}

select, option {
  width: 100%;
  padding: 6px 10px;
  border-radius: 8px;
  border: 2px solid #dfe6e9;
}

select:focus {
  border-color: #0984e3;
  outline: none;
}

.details-group {
  gap: 10px;
  width: 100%;
  display: flex;
  flex-direction: column;
  padding: 10px;
  border-radius: 8px;
  border: 2px solid #dfe6e9;
}

button{
  border: none;
  border-radius: 8px;
  padding: 10px;
  background: #0984e3;
  color: white;
}

button:hover {
  background: #0652DD;
}

h1, h2 {
  margin: 10px 0
}
</style>