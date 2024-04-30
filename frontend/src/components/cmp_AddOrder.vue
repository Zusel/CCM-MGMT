<template>
  <v-container style="background-color: white;margin-top: 5vh">
    <v-container>
      <v-row>
        <v-col>
          <v-combobox @input="getFilteredFirstName" id="firstName" class="inputFields"
                      :items="filteredCustomerFirstName"
                      v-model="firstName"
                      menu-props=""
                      label="Vorname">
            <template v-slot:item="{ item }">
              <v-label :text="item.firstName"/>
            </template>
          </v-combobox>
        </v-col>
        <v-col>
          <v-combobox @input="getCustomerFiltered" id="lastName" class="inputFields" :items="filteredCustomer"
                      label="Nachname"/>
        </v-col>
      </v-row>
    </v-container>
    <v-container>
      <v-row>
        <v-col>
          <v-text-field class="inputFields" v-model="mobileNumber"
                        label="Handynummer"/>
        </v-col>
        <v-col>
          <v-text-field class="inputFields" v-model="landlineNumber"
                        label="Festnetznummer"/>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-text-field class="inputFields" v-model="email"
                        label="Email"/>
        </v-col>
      </v-row>
    </v-container>
    <v-container>
      <v-row>
        <v-col>
          <v-text-field class="inputFields" v-model="street"
                        label="Straße"/>
        </v-col>
        <v-col>
          <v-text-field class="inputFields" v-model="streetNumber"
                        label="Hausnummer"/>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-text-field class="inputFields" v-model="postcode"
                        label="Postleitzahl"/>
        </v-col>
        <v-col>
          <v-text-field class="inputFields" v-model="city"
                        label="Stadt"/>
        </v-col>
      </v-row>
    </v-container>
    <v-row style="margin-top: 5vh">
      <v-col>
        <v-textarea style="color: black" label="Fehlerbeschreibung" v-model="description"/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-textarea style="color: black" label="Passwörter" v-model="passwords"/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-textarea style="color: black" label="Zubehör" v-model="equipment"/>
      </v-col>
    </v-row>
    <v-row justify="center">
      <v-checkbox label="Express gewollt?" style="color: black" v-model="express"/>
    </v-row>
    <!--    <v-row justify="center" style="margin-bottom: 3vh;">-->
    <!--      <v-btn :disabled="!choosed" @click="createOrder">Erstellen</v-btn>-->
    <!--    </v-row>-->
  </v-container>
</template>

<style scoped>

.inputFields {
  color: black;
}

</style>

<script>
import AddCustomerComponent from "@/components/cmp_AddCustomer.vue";
import AddCustomerDialogComponent from "@/components/cpm_AddCustomerDialog.vue";
import RESTUtils from "@/utils/RESTUtils";

export default {
  name: 'AddOrderComponent',
  components: {AddCustomerDialogComponent, AddCustomerComponent},
  methods: {
    createOrder: function () {
      const order = {
        "customerId": this.customer.id,
        "description": this.description,
        "passwords": this.passwords,
        "equipment": this.equipment,
        "express": this.express,
      };
      RESTUtils.sendPostRequest("/order", order);
    },
    clearFieldItems() {
      this.firstName = [],
        this.lastName = [],
        this.mobileNumber = [],
        this.landlineNumber = [],
        this.email = [],
        this.street = [],
        this.streetNumber = [],
        this.postcode = [],
        this.city = []
    },
    getFilteredFirstName() {
      //TODO dynamic
      if (this.firstName.length > 3) {
        const payload = {
          "firstName": this.firstName
        }
        RESTUtils.sendPostRequest("/customer/filter", payload)
          .then(response => {
            this.filteredCustomerFirstName = response.data;
          })
          .catch(error => console.log(error));
      }
    },
    getFilteredKastName() {
//TODO dynamic
    },
    getFilteredMobileNumber() {
//TODO dynamic
    },
    getFilteredLandlineNumber() {
//TODO dynamic
    },
    getFilteredEmail() {
//TODO dynamic
    },
    getFilteredStreet() {
//TODO dynamic
    },
    getFilteredStreetNumber() {
//TODO dynamic
    },
    getFilteredPostcode() {
//TODO dynamic
    },
    getFilteredCity() {
//TODO dynamic
    },
    getCustomerFiltered(filter) {
      //TODO dynamic
      this.clearFieldItems();


    }
  },
  data() {
    return {
      filteredCustomerFirstName: [],
      filteredCustomerLastName: [],
      filteredCustomerMobileNumber: [],
      filteredCustomerLandlineNumber: [],
      filteredCustomerEmail: [],
      filteredCustomerStreet: [],
      filteredCustomerStreetNumber: [],
      filteredCustomerPostcode: [],
      filteredCustomerCity: [],
      firstName: null,
      lastName: null,
      mobileNumber: null,
      landlineNumber: null,
      email: null,
      street: null,
      streetNumber: null,
      postcode: null,
      city: null,
      description: null,
      passwords: null,
      equipment: null,
      express: null,
    }
  }
}
</script>
