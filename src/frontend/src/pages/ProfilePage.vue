<template>
  <HeaderComponent/>

  <div class="container" id="container">
    <sidebar-component/>

    <div class="main-content">
      <!-- Tabs -->
      <div class="tabs">
        <div class="tab" @click="selectSection(0)" id="tab0" style="color: #0984e3">General</div>
        <div class="tab" @click="selectSection(1)" id="tab1" style="color: black">Address</div>
        <div class="tab" @click="selectSection(2)" id="tab2" style="color: black">Password</div>
        <div class="tab" @click="selectSection(3)" id="tab3" style="color: black">Card</div>
      </div>

      <!-- General Section -->
      <section class="details-group" id="general" style="display: flex">
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

          <button type="submit" id="add_button">Save Changes</button>
        </form>
      </section>

      <!-- Address Section -->
      <section class="address" id="address" style="display: none">
        <!-- Shipping Info -->
        <div v-if="shippingStreet" class="details-group">
          <h2>Shipping Address</h2>
          <form @submit.prevent="handleUpdateShippingAddress">
            <label for="shippingStreet">Street address</label>
            <input type="text" v-model="shippingStreet" id="shippingStreet" placeholder="e.g. 123 Place Street">

            <label for="shippingComplex">Complex/Flat</label>
            <input type="text" v-model="shippingComplex" id="shippingComplex" placeholder="e.g. Block 4B">

            <label for="shippingSuburb">Suburb</label>
            <input type="text" v-model="shippingSuburb" id="shippingSuburb" placeholder="e.g. Lentegeur">

            <label for="shippingCity">City</label>
            <input type="text" v-model="shippingCity" id="shippingCity" placeholder="e.g. Cape Town">

            <label for="shippingPostal">Postal</label>
            <input type="text" v-model="shippingPostal" id="shippingPostal" placeholder="e.g. 7786">

            <button type="submit" id="add_button">Save changes</button>
            <button type="button" @click="handleRemoveShippingAddress" id="remove_button">Remove address</button>
          </form>
        </div>

        <div v-else class="details-group">
          <p>You have no shipping address</p>
        </div>

        <!-- Billing Info -->
        <div v-if="billingStreet" class="details-group">
          <h2>Billing Address</h2>
          <form @submit.prevent="handleUpdateBillingAddress">
            <label for="billingStreet">Street address</label>
            <input type="text" v-model="billingStreet" id="billingStreet" placeholder="e.g. 456 Oak Avenue">

            <label for="billingComplex">Complex/Flat</label>
            <input type="text" v-model="billingComplex" id="billingComplex" placeholder="e.g. Apartment 12C">

            <label for="billingSuburb">Suburb</label>
            <input type="text" v-model="billingSuburb" id="billingSuburb" placeholder="e.g. Constantia">

            <label for="billingCity">City</label>
            <input type="text" v-model="billingCity" id="billingCity" placeholder="e.g. Cape Town">

            <label for="billingPostal">Postal</label>
            <input type="text" v-model="billingPostal" id="billingPostal" placeholder="e.g. 8001">

            <button type="submit" id="add_button">Save changes</button>
            <button type="button" @click="handleRemoveBillingAddress" id="remove_button">Remove address</button>
          </form>
        </div>

        <div v-else class="details-group">
          <p>You have no billing address</p>
        </div>

        <button type="button" @click="showAddAddress" id="add_button">Add Address</button>
      </section>

      <!-- Password Reset Section -->
      <section class="details-group" id="password" style="display: none">
        <h2>Password Reset</h2>
        <form @submit.prevent="handleResetPassword">
          <label for="passwordOld">Old Password</label>
          <input type="password" id="passwordOld" v-model="passwordOld">

          <label for="password">New Password</label>
          <input type="password" id="password" v-model="password">

          <label for="password_confirmation">Confirm New Password</label>
          <input type="password" id="password_confirmation" v-model="passwordConfirmation">
          <button type="submit" id="add_button">Reset Password</button>
        </form>
      </section>

      <!-- Card Section -->
      <section id="card" style="display: none">
        <div v-if="cardBrand" class="details-group">
          <h2>Card Details</h2>
          <select v-model="selectedCard">
            <option v-if="cardBrand" :value="cardBrand + ' ending with ' + cardLastFour">
              {{ cardBrand }} ending with {{ cardLastFour }}
            </option>
          </select>

          <button type="button" @click="handleRemoveCard" id="remove_button">Remove card</button>
        </div>

        <div v-else class="details-group">
          <p>You have no card</p>
          <button type="button" @click="showAddCard" id="add_button">Add Card</button>
        </div>
      </section>

      <!-- Add Address Form -->
      <div id="addAddress" class="formContainer">
        <div class="form-group">
          <span @click="closeAddAddress" class="close" title="Close Modal">&times;</span>
          <h2>Add Address</h2>
          <p>Fill in Address detail</p>
          <form @submit.prevent="handleAddAddress">
            <label for="street">Street address</label>
            <input type="text" v-model="street" id="street" placeholder="e.g. 123 Place Street">

            <label for="complex">Complex/Flat</label>
            <input type="text" v-model="complex" id="complex" placeholder="e.g. Block 4B">

            <label for="suburb">Suburb</label>
            <input type="text" v-model="suburb" id="suburb" placeholder="e.g. Lentegeur">

            <label for="city">City</label>
            <input type="text" v-model="city" id="city" placeholder="e.g. Cape Town">

            <label for="postal">Postal</label>
            <input type="text" v-model="postal" id="postal" placeholder="e.g. 7786">
            <div>
              <input type="checkbox" name="shipping" v-model="isShipping">
              <label for="Shipping"> Save as shipping address </label><br>
              <input type="checkbox" name="billing" v-model="isBilling">
              <label for="billing"> Save as billing address </label><br>
            </div>

            <button type="submit" id="add_button">Save changes</button>
          </form>
        </div>
      </div>

      <!-- Add Card Form -->
      <div id="addCard" class="formContainer">
        <div class="form-group">
          <span @click="closeAddCard" class="close">&times;</span>
          <h2>Add Card</h2>
          <p>Fill in Card detail</p>
          <form @submit.prevent="handleAddCard">
            <label for="cardBrand">Brand</label>
            <select v-model="selectedCard" id="cardBrand">
              <option value="MasterCard"> MasterCard</option>
              <option value="Visa"> Visa</option>
            </select>

            <label for="cardholder">Cardholder</label>
            <input type="text" id="cardholder" v-model="cardHolder" placeholder="Mr A Man">

            <label for="cardNumber">Card number</label>
            <input type="text" id="cardNumber" v-model="cardNumber" placeholder="5112345678901234">

            <label for="cardExpiry">Card Expiration</label>
            <input type="month" id="cardExpiry" v-model="cardExpiry" placeholder="e.g November 2000">

            <label for="cvv">CVV</label>
            <input type="text" id="cvv" placeholder="e.g 123" v-model="cardCvv">

            <button type="submit" id="add_button">Add card</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import HeaderComponent from "@/components/HeaderComponent.vue";
import SidebarComponent from "@/components/SidebarComponent.vue";
import {useAuth} from "@/Auth";
import {
  addAddress,
  addCard,
  getUser,
  removeBillingAddress,
  removeCard,
  removeShippingAddress,
  resetPassword,
  updateBillingAddress,
  updateShippingAddress,
  updateUser
} from "@/services/ProfileService";
import {useRouter} from "vue-router";

export default {
  name: 'ProfilePage',
  components: {SidebarComponent, HeaderComponent},
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
      selectedCard: '',
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
      billingPostal: '',
      // Add Address
      street: '',
      complex: '',
      suburb: '',
      city: '',
      postal: '',
      isShipping: false,
      isBilling: false,

      router: useRouter()
    };
  },
  async mounted() {
    const authUser = useAuth();
    this.user = await getUser(localStorage.getItem("email"));

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
      this.user = await removeShippingAddress(this.user, this.router);
    },
    async handleRemoveBillingAddress() {
      this.user = await removeBillingAddress(this.user, this.router);
    },
    async handleUpdateShippingAddress() {
      this.user = await updateShippingAddress(
          this.user,
          this.shippingStreet,
          this.shippingSuburb,
          this.shippingCity,
          this.shippingPostal,
          this.shippingComplex,
          this.router
      );
    },
    async handleUpdateBillingAddress() {
      this.user = await updateBillingAddress(
          this.user,
          this.billingStreet,
          this.billingSuburb,
          this.billingCity,
          this.billingPostal,
          this.billingComplex,
          this.router
      );
    },
    async handleRemoveCard() {
      this.user = await removeCard(this.user, this.router);
    },
    async handleUpdate() {
      this.user = await updateUser(
          this.user,
          this.firstName,
          this.middleName,
          this.lastName,
          this.phone,
          this.router
      );
    },
    async handleAddAddress() {
      this.user = await addAddress(
          this.user,
          this.street,
          this.suburb,
          this.city,
          this.postal,
          this.complex,
          this.isShipping,
          this.isBilling,
          this.router
      )
    },
    async handleAddCard() {
      this.user = await addCard(
          this.user,
          this.selectedCard,
          this.cardHolder,
          this.cardNumber,
          this.cardExpiry,
          this.cardCvv,
          this.router
      )
    },
    async handleResetPassword() {
      this.user = await resetPassword(
          this.user,
          this.passwordOld,
          this.password,
          this.passwordConfirmation,
          this.router
      )
    },
    showAddAddress() {
      document.getElementById('addAddress').style.display = 'block';
      document.body.style.overflowY = 'hidden';
    },
    closeAddAddress() {
      document.getElementById('addAddress').style.display = 'none';
      document.body.style.overflowY = 'auto';
    },
    showAddCard() {
      document.getElementById('addCard').style.display = 'block';
      document.body.style.overflowY = 'hidden';
    },
    closeAddCard() {
      document.getElementById('addCard').style.display = 'none';
      document.body.style.overflowY = 'auto';
    },
    selectSection(section) {
      const general = document.getElementById('general');
      const address = document.getElementById('address');
      const password = document.getElementById('password');
      const card = document.getElementById('card');

      general.style.display = 'none';
      address.style.display = 'none';
      password.style.display = 'none';
      card.style.display = 'none';

      const tabs = []
      for (let i = 0; i <= 3; i++) {
        tabs.push(document.getElementById('tab' + i));
        tabs[i].style.color = 'black';
      }

      switch (section) {
        case 0:
          general.style.display = 'flex';
          tabs[0].style.color = '#0984e3';
          break;
        case 1:
          address.style.display = 'grid';
          tabs[1].style.color = '#0984e3';
          break;
        case 2:
          password.style.display = 'flex';
          tabs[2].style.color = '#0984e3';
          break;
        case 3:
          card.style.display = 'flex';
          tabs[3].style.color = '#0984e3';
          break;
      }
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

.formContainer {
  display: none;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4);
  align-content: center;
}

.tabs {
  border-radius: 8px;
  border: 2px solid #ccc;
  display: flex;
  gap: 30px;
  width: 100%;
  align-items: center;
  margin-bottom: 10px;
  padding: 10px;
}

.tab {
  transition: background 0.2s;
}

.tab:hover {
  text-decoration: underline;
  font-weight: bold;
  cursor: pointer;
}

input[type="text"], input[type="email"], input[type="password"], input[type="month"] {
  width: calc(100% - 20px);
  padding: 6px 10px;
  border-radius: 8px;
  border: 2px solid #ccc;
  margin-bottom: 10px;
}

input[type="text"]:focus, input[type="email"]:focus, input[type="password"]:focus, input[type="month"]:focus {
  border-color: #0984e3;
  outline: none;
}

select, option {
  width: 100%;
  padding: 6px 10px;
  border-radius: 8px;
  border: 2px solid #ccc;
}

select:focus {
  border-color: #0984e3;
  outline: none;
}

.address {
  gap: 10px 50px;
  display: grid;
  grid-template-columns: 1fr 1fr;
}

.details-group {
  width: 100%;
  flex-direction: column;
  padding: 10px;
  border-radius: 8px;
  border: 2px solid #ccc;
  background-color: white;
}

.form-group {
  padding: 10px;
  width: 70%;
  border-radius: 8px;
  border: 2px solid #ccc;
  background-color: white;
  justify-self: center;
}

.close {
  font-size: 30px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  cursor: pointer;
}

button {
  border: none;
  border-radius: 8px;
  margin: 5px;
  padding: 10px;
  color: white;
  width: 100px;
}

#add_button {
  background: #0984e3;
}

#add_button:active, #add_button:hover {
  background: #0652DD;
}

#remove_button {
  background: #FF2D2D;
}

#remove_button:active, #remove_button:hover {
  background: red;
}

h1, h2 {
  margin: 10px 0
}
</style>