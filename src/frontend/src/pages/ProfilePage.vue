<template>
  <HeaderComponent/>

  <div class="container">
    <sidebar-component/>
    <!-- Main Content -->
    <main class="main-content">
      <h1>Profile Information</h1>

      <form @submit.prevent="handleUpdate">
        <div class="content">
          <div class="details-group">
            <h2>General Information</h2>
            <label for="firstName">First Name</label>
            <input type="text" id="firstName" v-model="firstName">

            <label for="middleName">Middle Name</label>
            <input type="text" id="middleName" v-model="middleName">

            <label for="surname">Surname</label>
            <input type="text" id="surname" v-model="lastName">

            <label for="email">Email Address</label>
            <input class="readonly" type="email" id="email" v-model="email" disabled>

            <label for="phone">Phone</label>
            <input class="readonly" type="text" id="phone" v-model="phone" disabled>
          </div>

          <div style="width: 100%">
            <div class="details-group">
              <h2>Password Reset</h2>
              <label for="password">New Password</label>
              <input type="password" id="password" v-model="password">

              <label for="password_confirmation">Confirm Password</label>
              <input type="password" id="password_confirmation" v-model="passwordConfirmation">
            </div>

            <div class="details-group" style="margin-top: 50px">
              <h2>Card Details</h2>
              <select v-model="selectedCard">
                <option v-if="cardBrand" :value="cardBrand + ' ending with ' + cardLastFour">
                  {{ cardBrand }} ending with {{ cardLastFour }}
                </option>
              </select>

              <button @click="this.user = handleRemoveCard()">Remove card</button>
            </div>
          </div>

          <div class="details-group">
            <h2>Shipping Address</h2>
            <input type="text" v-model="shippingStreet" placeholder="Street">
            <input type="text" v-model="shippingComplex" placeholder="Complex/Flat">
            <input type="text" v-model="shippingSuburb" placeholder="Suburb">
            <input type="text" v-model="shippingCity" placeholder="City">
            <input type="text" v-model="shippingPostal" placeholder="Postal">
            <button @click="this.user = handleRemoveShippingAddress()">Remove address</button>
          </div>

          <div class="details-group">
            <h2>Billing Address</h2>
            <input type="text" v-model="billingStreet" placeholder="Street">
            <input type="text" v-model="billingComplex" placeholder="Complex/Flat">
            <input type="text" v-model="billingSuburb" placeholder="Suburb">
            <input type="text" v-model="billingCity" placeholder="City">
            <input type="text" v-model="billingPostal" placeholder="Postal">
            <button @click="this.user = handleRemoveBillingAddress()">Remove address</button>
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
import {useAuth} from "@/Auth";
import {getUser, removeBillingAddress, removeCard, removeShippingAddress, updateUser} from "@/services/ProfileService";
import { useRouter } from "vue-router";

export default {
  name: 'ProfilePage',
  components: {
    SidebarComponent,
    HeaderComponent
  },
  data() {
    return {
      user: null,
      // General
      firstName: '',
      middleName: '',
      lastName: '',
      email: '',
      phone: '',
      password: '',
      passwordConfirmation: '',
      // Card
      selectedCard: null,
      cardBrand: '',
      cardLastFour: '',
      // Shipping
      shippingStreet: '',
      shippingComplex: '',
      shippingSuburb: '',
      shippingCity: '',
      shippingPostal: '',
      // Billing
      billingStreet: '',
      billingComplex: '',
      billingSuburb: '',
      billingCity: '',
      billingPostal: '',
      router: useRouter()
    };
  },
  async mounted() {
    const authUser = useAuth();
    this.user = await getUser(authUser.userID);

    if(this.user){
      // Populate form fields with current user values
      this.firstName = this.user.firstName;
      this.middleName = this.user.middleName;
      this.lastName = this.user.lastName;

      this.email = this.user.contact.email;
      this.phone = this.user.contact.phone;

      if(this.user.card){
        this.cardBrand = this.user.card.brand;
        this.cardLastFour = this.user.card.lastFourDigits;
      }

      if (this.user.shippingAddress){
        this.shippingStreet = this.user.shippingAddress.streetAddress;
        this.shippingComplex = this.user.shippingAddress.complexDetail;
        this.shippingSuburb = this.user.shippingAddress.suburb;
        this.shippingCity = this.user.shippingAddress.city;
        this.shippingPostal = this.user.shippingAddress.postalCode;
      }

      if (this.user.billingAddress){
        this.billingStreet = this.user.billingAddress.streetAddress;
        this.billingComplex = this.user.billingAddress.complexDetail;
        this.billingSuburb = this.user.billingAddress.suburb;
        this.billingCity = this.user.billingAddress.city;
        this.billingPostal = this.user.billingAddress.postalCode;
      }
    }
  },
  methods: {
    async handleRemoveShippingAddress(){
      this.user = await removeShippingAddress(this.user, this.router);
    },
    async handleRemoveBillingAddress(){
      this.user = await removeBillingAddress(this.user, this.router);
    },
    async handleRemoveCard(){
      this.user = await removeCard(this.user, this.router);
    },
    async handleUpdate() {
      this.user = await updateUser(
          this.user,
          this.firstName,
          this.middleName,
          this.lastName,
          this.password,
          this.passwordConfirmation,
          this.shippingStreet,
          this.shippingComplex,
          this.shippingSuburb,
          this.shippingCity,
          this.shippingPostal,
          this.billingStreet,
          this.billingComplex,
          this.billingSuburb,
          this.billingCity,
          this.billingPostal,
          this.router
      );
    }
  },
};
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

button {
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