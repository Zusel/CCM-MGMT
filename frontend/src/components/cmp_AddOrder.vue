<template>
  <v-container class="container" v-if="choosedCustomer === null">
    <v-row>
      <v-col>
        <v-list style="color: white; height: 15vh">
          <v-list-item style="color: white;" @click="getSelectedUser(customer.id)" :key="customer.id"
                       v-for="customer in filteredCustomers">
            <v-list-item-title>
              {{
                "id:" + customer.id + " | " + "vorname: " + customer.firstName + " | " + "nachname: " + customer.lastName + " | " + "postleitzahl: " + customer.postcode + " | " + "stadt: " + customer.city + " | " + "handynummer: " + customer.mobileNumber + " | " + "festnetznummer: " + customer.landlineNumber + " | " + "email: " + customer.email + " | " + "straße: " + customer.street + " | " + "hausnummer: " + customer.streetNumber
              }}
            </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-text-field v-model="firstName"
                      @update:modelValue="filterByValue" label="Vorname"/>
      </v-col>
      <v-col>
        <v-text-field v-model="lastName"
                      label="Nachname"/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-text-field v-model="mobileNumber"
                      label="Handynummer"/>
      </v-col>
      <v-col>
        <v-text-field v-model="landlineNumber"
                      label="Festnetznummer"/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-text-field v-model="email"
                      label="Email"/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-text-field v-model="street"
                      label="Straße"/>
      </v-col>
      <v-col>
        <v-text-field v-model="streetNumber"
                      label="Hausnummer"/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-text-field v-model="postcode"
                      label="Postleitzahl"/>
      </v-col>
      <v-col>
        <v-text-field v-model="city"
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
  <v-container class="container" v-if="choosedCustomer != null">
    <v-row>
      <v-col cols="3">
        <v-container style="border-bottom-color: black; border: solid;">
          <v-label><b>Gewählter Kunde:</b></v-label>
          <br>
          <v-label>{{ "vorname: " + choosedCustomer.firstName }}</v-label>
          <br>
          <v-label>{{ "nachname: " + choosedCustomer.lastName }}</v-label>
          <br>
          <v-label>{{ "postleitzahl: " + choosedCustomer.postcode }}</v-label>
          <br>
          <v-label>{{ "stadt: " + choosedCustomer.city }}</v-label>
          <br>
          <v-label>{{ "handynummer: " + choosedCustomer.mobileNumber }}</v-label>
          <br>
          <v-label>{{ "festnetznummer: " + choosedCustomer.landlineNumber }}</v-label>
          <br>
          <v-label>{{ "email: " + choosedCustomer.email }}</v-label>
          <br>
          <v-label>{{ "straße: " + choosedCustomer.street }}</v-label>
          <br>
          <v-label>{{ "hausnummer: " + choosedCustomer.streetNumber }}</v-label>
          <br>
          <v-row align="center" justify="center" style="margin-top: 1vh">
            <v-btn style="color: white; margin-bottom: 1vh" @click="resetCustomer">neuen Kunden wählen</v-btn>
          </v-row>
        </v-container>
      </v-col>
      <v-col>
        <v-container style="border: black solid; height: 100%; display: flex;flex-direction: column">
          <v-textarea variant="outlined" rows="10" hide-details label="Beschreibung" v-model="description"/>
        </v-container>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-container style="border-bottom-color: black; border: solid;">
          <v-row>
            <v-col>
              <v-label>Passwörter:</v-label>
              <v-data-table items-per-page="-1" :items="passwords" style="height: 20vh">
                <template v-slot:bottom></template>
                <template v-slot:item="{ item }">
                  <tr>
                    <td style="color: white">{{ item.name }}</td>
                    <td style="color: white">{{ item.password }}</td>
                    <td>
                      <v-icon @click="item => removePassword(item)" icon="@/assets/tashbin.png"/>
                    </td>
                  </tr>
                </template>
              </v-data-table>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-text-field v-model="passwordName" label="Password-Name"/>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-text-field v-model="passwordValue" label="Passwort"/>
            </v-col>
          </v-row>
          <v-row justify="center">
            <v-btn @click="addPassword" style="color: white; margin-bottom: 1vh">
              Passwort Hinzufügen
            </v-btn>
          </v-row>
        </v-container>
      </v-col>
      <v-col>
        <v-container style="border: black solid; height: 100%">
          <v-textarea label="Zubehör:" variant="outlined" rows="18" hide-details v-model="equipment"/>
        </v-container>
      </v-col>
    </v-row>
    <v-row align="center" justify="center">
        <v-checkbox label="Ist eine Express bearbeitung gewünscht?" v-model="express"/>
    </v-row>
    <v-row justify="center">
      <v-btn style="color: white" @click="createOrder">
        Auftrag erstellen
      </v-btn>
    </v-row>
  </v-container>
</template>

<style scoped>
.v-list-item-title {
  color: white;
}

* {
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
        "customerId": this.choosedCustomer,
        "description": this.description,
        "passwords": this.passwords,
        "equipment": this.equipment,
        "express": this.express,
      };
      RESTUtils.sendPostRequest("/order", order);
    },
    filterByValue: function () {
      if (this.firstName.length > 3 || this.lastName.length > 3 || this.postcode.length > 3 || this.city.length > 3 || this.mobileNumber.length > 3 || this.landlineNumber.length > 3 || this.email.length > 3 || this.street.length > 3 || this.streetNumber.length > 3) {
        const payload = {
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
        RESTUtils.sendPostRequest("/customer/filter", payload)
          .then(response => {
            this.filteredCustomers = response.data;
          })
          .catch(error => console.log(error));
      }
    },
    getSelectedUser: function (customerId) {
      RESTUtils.sendGetRequest("/customer/" + customerId)
        .then(response => {
          this.choosedCustomer = response.data;
        })
        .catch(error => {
          console.log("user not found. UserId: " + customerId + ".", error)
        })
    },
    createCustomer: function () {
      const payload = {
        "firstName": this.firstName,
        "lastName": this.lastName,
        "postcode": this.postcode,
        "city": this.city,
        "mobileNumber": this.mobileNumber,
        "landlineNumber": this.landlineNumber,
        "email": this.email,
        "street": this.street,
        "streetNumber": this.streetNumber,
      }
      RESTUtils.sendPostRequest("/customer", payload)
        .then(response => {
          this.choosedCustomer = response.data;
        })
        .catch(error => console.log(error))
    },
    resetCustomer: function () {
      this.choosedCustomer = null;
      this.firstName = '';
      this.lastName = '';
      this.postcode = '';
      this.city = '';
      this.mobileNumber = '';
      this.landlineNumber = '';
      this.email = '';
      this.street = '';
      this.streetNumber = '';
      this.filteredCustomers = [];
    },
    resetAndDeleteCustomer: function () {
      RESTUtils.sendDeleteRequest("/customer", this.choosedCustomer)
        .then(this.resetCustomer())
        .catch(error => {
          console.log(error)
        })
    },
    addPassword: function () {
      const newItem = {name: this.passwordName, password: this.passwordValue}
      this.passwords.push(newItem);
    },
    removePassword: function (item) {
      this.passwords.splice(this.passwords.indexOf(item), 1)
    }
  },
  data() {
    return {
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
      passwords: [],
      description: '',
      equipment: '',
      express: false,
      passwordName: '',
      passwordValue: ''
    }
  }

}
</script>
