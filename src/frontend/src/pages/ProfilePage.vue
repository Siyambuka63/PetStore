<template>
  <HeaderComponent />

  <div class="container">
    <sidebar-component />

    <!-- Main Content -->
    <div class="main-content">
      <div class="content">
        <!-- Tabs -->
        <div class="tabs">
          <div class="tab">General</div>
          <div class="tab">Address</div>
          <div class="tab">Card</div>
          <div class="tab">Password</div>
        </div>

        <!-- General Info -->
        <div class="details-group">
          <h2>General Information</h2>
          <form @submit.prevent="handleUpdate">
            <label for="firstName">First Name</label>
            <input type="text" id="firstName" v-model="firstName">

            <label for="middleName">Middle Name</label>
            <input type="text" id="middleName" v-model="middleName">

            <label for="surname">Surname</label>
            <input type="text" id="surname" v-model="lastName">

            <label for="email">Email Address</label>
            <input type="email" id="email" v-model="email" disabled>

            <label for="phone">Phone</label>
            <input type="text" id="phone" v-model="phone">

            <button type="submit">Save Changes</button>
          </form>
        </div>

        <!-- Shipping -->
        <div>
          <div v-if="user.shippingAddress" class="details-group">
            <h2>Shipping Address</h2>
            <form @submit.prevent="handleUpdate">
              <input type="text" v-model="shippingStreet" placeholder="Street">
              <input type="text" v-model="shippingComplex" placeholder="Complex/Flat">
              <input type="text" v-model="shippingSuburb" placeholder="Suburb">
              <input type="text" v-model="shippingCity" placeholder="City">
              <input type="text" v-model="shippingPostal" placeholder="Postal">

              <button type="submit">Save changes</button>
              <button type="button" @click="handleRemoveShippingAddress">Remove address</button>
            </form>
          </div>

          <div v-else class="details-group">
            <p>You have no shipping address</p>
            <button type="button">Add Shipping Address</button>
          </div>

          <div v-if="user.billingAddress" class="details-group">
            <h2>Billing Address</h2>
            <form @submit.prevent="handleUpdate">
              <input type="text" v-model="billingStreet" placeholder="Street">
              <input type="text" v-model="billingComplex" placeholder="Complex/Flat">
              <input type="text" v-model="billingSuburb" placeholder="Suburb">
              <input type="text" v-model="billingCity" placeholder="City">
              <input type="text" v-model="billingPostal" placeholder="Postal">

              <button type="submit">Save changes</button>
              <button type="button" @click="handleRemoveBillingAddress">Remove address</button>
            </form>
          </div>

          <div v-else class="details-group">
            <p>You have no billing address</p>
            <button type="button">Add Billing Address</button>
          </div>
        </div>

        <!-- Password Reset -->
        <div class="details-group">
          <h2>Password Reset</h2>
          <label for="passwordOld">Old Password</label>
          <input type="password" id="passwordOld" v-model="passwordOld">

          <label for="password">New Password</label>
          <input type="password" id="password" v-model="password">

          <label for="password_confirmation">Confirm New Password</label>
          <input type="password" id="password_confirmation" v-model="passwordConfirmation">
        </div>

        <!-- Card -->
        <div v-if="user.card" class="details-group" style="margin-top: 50px">
          <h2>Card Details</h2>
          <select v-model="selectedCard">
            <option v-if="cardBrand" :value="cardBrand + ' ending with ' + cardLastFour">
              {{ cardBrand }} ending with {{ cardLastFour }}
            </option>
          </select>

          <button type="button" @click="handleRemoveCard">Remove card</button>
        </div>

        <div v-else class="details-group" style="margin-top: 50px">
          <h2>Add Card</h2>

          <label for="cardBrand">Brand</label>
          <select v-model="selectedCard" id="cardBrand">
            <option value="MasterCard"> MasterCard </option>
            <option value="Visa"> Visa </option>
          </select>

          <label for="cardholder">Cardholder</label>
          <input type="text" id="cardholder" v-model="cardHolder">

          <label for="cardNumber">Card number</label>
          <input type="text" id="cardNumber" v-model="cardNumber">

          <div>
            <label for="cardExpiry">Card Expiration</label>
            <input type="text" id="cardExpiry" v-model="cardExpiry" placeholder="MM/YY">

            <label for="cvv">CVV</label>
            <input type="text" id="cvv" v-model="cardCvv">
          </div>
          <button type="button">Add card</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import HeaderComponent from "@/components/HeaderComponent.vue";
import SidebarComponent from "@/components/SidebarComponent.vue";
import { useAuth } from "@/Auth";
import { getUser, removeBillingAddress, removeCard, removeShippingAddress, updateUser } from "@/services/ProfileService";

export default {
  name: 'ProfilePage',
  components: { SidebarComponent, HeaderComponent },
  data() {
    return {
      user: {},
      // General
      firstName: '',
      middleName: '',
      lastName: '',
      email: '',
      phone: '',
      passwordOld: '',
      password: '',
      passwordConfirmation: '',
      // Card
      selectedCard: null,
      cardBrand: '',
      cardLastFour: '',
      cardHolder: '',
      cardNumber: '',
      cardExpiry: '',
      cardCvv: '',
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
      billingPostal: ''
    };
  },
  async mounted() {
    const authUser = useAuth();
    this.user = await getUser(authUser.getEmail());

    if (this.user) {
      this.firstName = this.user.firstName;
      this.middleName = this.user.middleName;
      this.lastName = this.user.lastName
      this.email = this.user.email;
      this.phone = this.user.phoneNumber;

      if (this.user.card) {
        this.cardBrand = this.user.card.brand;
        this.cardLastFour = this.user.card.lastFourDigits;
      }

      if (this.user.shippingAddress) {
        this.shippingStreet = this.user.shippingAddress.streetAddress;
        this.shippingComplex = this.user.shippingAddress.complexDetail;
        this.shippingSuburb = this.user.shippingAddress.suburb;
        this.shippingCity = this.user.shippingAddress.city;
        this.shippingPostal = this.user.shippingAddress.postalCode;
      }

      if (this.user.billingAddress) {
        this.billingStreet = this.user.billingAddress.streetAddress;
        this.billingComplex = this.user.billingAddress.complexDetail;
        this.billingSuburb = this.user.billingAddress.suburb;
        this.billingCity = this.user.billingAddress.city;
        this.billingPostal = this.user.billingAddress.postalCode;
      }
    }
  },
  methods: {
    async handleRemoveShippingAddress() {
      this.user = await removeShippingAddress(this.user);
    },
    async handleRemoveBillingAddress() {
      this.user = await removeBillingAddress(this.user);
    },
    async handleRemoveCard() {
      this.user = await removeCard(this.user);
    },
    async handleUpdate() {
      this.user = await updateUser(
          this.user,
          this.firstName,
          this.middleName,
          this.lastName,
          this.phone
      );
    }
  }
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