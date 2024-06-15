<template>
  <v-container class="container" v-if="choosedCustomer === null">
    <v-row>
      <v-col>
        <v-list style="color: white; height: 15vh">
          <v-list-item style="color: white;" @click="getSelectedCustomer(customer.id)" :key="customer.id"
                       v-for="customer in filteredCustomers">
            <v-list-item-title style="color: white">
              {{
                "id:" + customer.id + " | " + "vorname: " + customer.firstName + " | " + "nachname: " + customer.lastName + " | " + "postleitzahl: " + customer.postcode + " | " + "stadt: " + customer.city + " | " + "handynummer: " + customer.mobileNumber + " | " + "festnetznummer: " + customer.landlineNumber + " | " + "email: " + customer.email + " | " + "straße: " + customer.street + " | " + "hausnummer: " + customer.streetNumber
              }}
            </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-col>
    </v-row>
    <add-customer-component @customerChanged="customer => filterByValue(customer)"
                            @customerCreated="customer => this.getSelectedCustomer(customer.id)" v-model="addCustomer"/>
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
              <v-data-table :headers="passwordHeaders" items-per-page="-1" :items="passwords"
                            style="height: 20vh">
                <template v-slot:bottom></template>
                <template v-slot:item="{ item }">
                  <tr>
                    <td style="color: white">{{ item.name }}</td>
                    <td style="color: white">{{ item.password }}</td>
                    <td style="width: 10px">
                      <v-avatar>
                        <v-img @click="item => removePassword(item)" src="@/assets/trashbin.png"/>
                      </v-avatar>
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
import AddCustomerComponent from "@/components/cmp_AddCustomer.vue";

export default {
  name: 'AddOrderComponent',
  components: {AddCustomerComponent},
  methods: {
    filterByValue: function (customer) {
      if (customer.firstName.length > 3 || customer.lastName.length > 3 || customer.postcode.length > 3 || customer.city.length > 3 || customer.mobileNumber.length > 3 || customer.landlineNumber.length > 3 || customer.email.length > 3 || customer.street.length > 3 || customer.streetNumber.length > 3) {
        const payload = {
          "firstName": customer.firstName,
          "lastName": customer.lastName,
          "postcode": customer.postcode,
          "city": customer.city,
          "mobileNumber": customer.mobileNumber,
          "landlineNumber": customer.landlineNumber,
          "email": customer.email,
          "street": customer.street,
          "streetNumber": customer.streetNumber
        }
        RESTUtils.sendPostRequest("/customer/filter", payload)
          .then(response => {
            this.filteredCustomers = response.data;
          })
          .catch(error => console.log(error));
      }
    },
    getSelectedCustomer: function (customerId) {
      RESTUtils.sendGetRequest("/customer/" + customerId)
        .then(response => {
          this.choosedCustomer = response.data;
        })
        .catch(error => {
          console.log("user not found. UserId: " + customerId + ".", error)
        })
    },
    createOrder: function () {
      const order = {
        "customerId": this.choosedCustomer.id,
        "description": this.description,
        "passwords": this.passwords,
        "equipment": this.equipment,
        "express": this.express,
      };
      RESTUtils.sendPostRequest("/order", order);
    },
    addPassword: function () {
      const newItem = {name: this.passwordName, password: this.passwordValue}
      this.passwords.push(newItem);
      this.passwordName = "";
      this.passwordValue = "";
    },
    removePassword: function (item) {
      this.passwords.splice(this.passwords.indexOf(item), 1)
    },
    resetCustomer: function () {
      this.choosedCustomer = null;
    },
  },
  data() {
    return {
      passwordHeaders: [
        {
          title: "Name"
        },
        {
          title: "Passwort"
        }
      ],
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
      passwordValue: '',
      addCustomer: '',
      rules: {
        notNull: value => !!value || 'Das Feld ist leer! Bitte überprüfe die Eingabe!',
        length3: value => value.length > 2 || 'Der Feld-Inhalt ist zu kurz! Mindestens 3 Buchstaben/Zahlen notwendig!',
        phonenumber: value => value.match("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$") !== null || 'Handynummer ist ungültig!',
        email: value => {
          if (value !== "") {
            if (value.match("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") === null) {
              return 'Email ist ungültig!'
            }
          }
        }
      },
    }
  }
}
</script>
