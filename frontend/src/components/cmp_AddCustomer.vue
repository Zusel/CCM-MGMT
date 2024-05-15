<template>
  <v-container style="background-color: white; margin-top: 10vh">
    <v-container>
      <v-row>
        <v-col>
          <v-text-field class="inputFields" v-model="firstName"
                        label="Vorname"/>
        </v-col>
        <v-col>
          <v-text-field class="inputFields" v-model="lastName"
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
    <v-container>
      <v-row justify="center">
        <v-btn @click="addCustomer()">
          Erstellen
        </v-btn>
      </v-row>
    </v-container>
  </v-container>
</template>

<style scoped>
.inputFields {
  color: black;
}
</style>

<script>
import RESTUtils from "@/utils/RESTUtils";

export default {
  name: 'AddCustomerComponent',
  emits: ["update:customer"],
  props: {
    customer: Object
  },
  data() {
    return {
      firstName: '',
      lastName: '',
      postcode: '',
      city: '',
      mobileNumber: '',
      landlineNumber: '',
      email: '',
      street: '',
      streetNumber: ''
    }
  },
  methods: {
    addCustomer: function () {
      const customer = {
        "firstName": this.firstName,
        "lastName": this.lastName,
        "postcode": this.postcode,
        "city": this.city,
        "mobileNumber": this.mobileNumber,
        "landlineNumber": this.landlineNumber,
        "email": this.email,
        "street": this.street,
        "streetNumber": this.streetNumber
      }
      RESTUtils.sendPostRequest("/customer", customer)
        .then(console.log("customer erstellt"))
        .catch(error => console.log(error))
    }
  }
}

</script>
