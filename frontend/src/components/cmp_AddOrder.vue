<template>
  <v-container class="container" v-if="choosedCustomer === null" >
      <v-row>
        <v-col>
          <v-list style="color: white; height: 15vh">
            <v-list-item style="color: white;" @click="getSelectedUser(customer.id)" :key="customer.id" v-for="customer in filteredCustomers">
            <v-list-item-title>
              {{"id:" + customer.id + " | " + "vorname: " +customer.firstName + " | " + "nachname: " +customer.lastName + " | " + "postleitzahl: " +customer.postcode + " | " + "stadt: " +customer.city + " | " + "handynummer: " +customer.mobileNumber + " | " + "festnetznummer: " +customer.landlineNumber + " | " + "email: " +customer.email + " | " + "straße: " +customer.street + " | " + "hausnummer: " +customer.streetNumber}}
            </v-list-item-title>
            </v-list-item>
          </v-list>
        </v-col>
      </v-row>
    <v-row>
      <v-col>
        <v-text-field class="inputFields" v-model="firstName"
                      @update:modelValue="filterByValue" label="Vorname"/>
      </v-col>
      <v-col>
        <v-text-field class="inputFields" v-model="lastName"
                      label="Nachname"/>
      </v-col>
    </v-row>
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
    <v-row>
      <v-spacer/>
      <v-btn @click="createCustomer">
        Kunden erstellen
      </v-btn>
      <v-spacer/>
    </v-row>
    </v-container>
  <v-container class="container" v-if="choosedCustomer != null" :hidden="choosedCustomer === null">
    <v-row>
      <v-col>
        <v-label style="color: black;"><b>Gewählter Kunde:</b></v-label><br>
        <v-label class="inputFields">{{"vorname: " + choosedCustomer.firstName}}</v-label><br>
        <v-label class="inputFields">{{"nachname: " + choosedCustomer.lastName}}</v-label><br>
        <v-label class="inputFields">{{"postleitzahl: " + choosedCustomer.postcode}}</v-label><br>
        <v-label class="inputFields">{{"stadt: " + choosedCustomer.city}}</v-label><br>
        <v-label class="inputFields">{{"handynummer: " + choosedCustomer.mobileNumber}}</v-label><br>
        <v-label class="inputFields">{{"festnetznummer: " + choosedCustomer.landlineNumber}}</v-label><br>
        <v-label class="inputFields">{{"email: " + choosedCustomer.email}}</v-label><br>
        <v-label class="inputFields">{{"straße: " + choosedCustomer.street}}</v-label><br>
        <v-label class="inputFields">{{"hausnummer: " + choosedCustomer.streetNumber}}</v-label><br>
      </v-col>
      <v-col>
        <v-spacer/>
        <v-row>
          <v-btn @click="resetCustomer" style="margin-bottom: 5vh; margin-top: 5vh">
            neuen Kunden wählen
          </v-btn>
        </v-row>
        <v-row>
          <v-btn @click="resetAndDeleteCustomer">
            neuen Kunden wählen (aktueller wird gelöscht)
          </v-btn>
        </v-row>
        <v-spacer/>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>

.inputFields {
  color: black;
}

.container {
  background-color: white;
  margin-top: 5vh;
}

</style>

<script>
import RESTUtils from "@/utils/RESTUtils";

export default {
  name: 'AddOrderComponent',
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
    filterByValue: function (){
      if(this.firstName.length>3||this.lastName.length>3||this.postcode.length>3||this.city.length>3||this.mobileNumber.length>3||this.landlineNumber.length>3||this.email.length>3||this.street.length>3||this.streetNumber.length>3){
        const payload = {
          "firstName":this.firstName,
          "lastName":this.lastName,
          "postcode":this.postcode,
          "city":this.city,
          "mobileNumber":this.mobileNumber,
          "landlineNumber":this.landlineNumber,
          "email":this.email,
          "street":this.street,
          "streetNumber":this.streetNumber
        }
        RESTUtils.sendPostRequest("/customer/filter", payload)
          .then(response => {
            this.filteredCustomers = response.data;
          })
          .catch(error => console.log(error));
      }
    },
    getSelectedUser: function (customerId){
      RESTUtils.sendGetRequest("/customer/" + customerId)
        .then(response =>{
          this.choosedCustomer = response.data;
        })
        .catch(error => {
          console.log("user not found. UserId: " + customerId + ".",error)
        })
    },
    createCustomer: function (){
      const payload = {
        "firstName":this.firstName,
        "lastName":this.lastName,
        "postcode":this.postcode,
        "city":this.city,
        "mobileNumber":this.mobileNumber,
        "landlineNumber":this.landlineNumber,
        "email":this.email,
        "street":this.street,
        "streetNumber":this.streetNumber,
      }
      RESTUtils.sendPostRequest("/customer", payload)
        .then( response => {
          this.choosedCustomer = response.data;
        })
        .catch(error => console.log(error))
    },
    resetCustomer:function (){
      this.choosedCustomer = null;
      this.firstName='';
      this.lastName='';
      this.postcode='';
      this.city='';
      this.mobileNumber='';
      this.landlineNumber='';
      this.email='';
      this.street='';
      this.streetNumber='';
      this.filteredCustomers=[];
    },
    resetAndDeleteCustomer: function (){
      RESTUtils.sendDeleteRequest("/customer", this.choosedCustomer)
        .then(this.resetCustomer())
        .catch(error => {console.log(error)})
    }
    },
  data(){
    return{
      filteredCustomers: [],
      firstName: '',
      lastName: '',
      postcode: '',
      city: '',
      mobileNumber: '',
      landlineNumber: '',
      email: '',
      street: '',
      streetNumber: '',
      choosedCustomer: null,
    }
  }

}
</script>
