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
  <GetOrderInformationsComponent :choosed-customer="choosedCustomer"/>
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
import GetOrderInformationsComponent from "@/components/cmp_GetOrderInformations.vue";

export default {
  name: 'AddOrderComponent',
  components: {GetOrderInformationsComponent, AddCustomerComponent},
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
