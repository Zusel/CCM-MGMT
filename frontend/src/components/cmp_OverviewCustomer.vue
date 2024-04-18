<template>
  <v-container style="background-color: white;margin-top: 10vh">
    <v-text-field class="centered-input text--darken-3 mt-3" v-model="searchTerm" label="suchen"
                  style="color: black; justify-self: center"/>
    <v-table>
      <thead>
      <tr>
        <th>Vorname</th>
        <th>Nachname</th>
        <th>Handynummer</th>
        <th>Festnetznummer</th>
        <th>Email</th>
        <th>Straße</th>
        <th>Hausnummer</th>
        <th>Postleitzahl</th>
        <th>Stadt</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="item in filteredCustomers" :key="item.id">
        <td>{{ item.firstName }}</td>
        <td>{{ item.lastName }}</td>
        <td>{{ item.mobileNumber }}</td>
        <td>{{ item.landlineNumber }}</td>
        <td>{{ item.email }}</td>
        <td>{{ item.street }}</td>
        <td>{{ item.streetNumber }}</td>
        <td>{{ item.postcode }}</td>
        <td>{{ item.city }}</td>
      </tr>
      </tbody>
    </v-table>
  </v-container>

</template>

<style scoped>

</style>

<script>
import RESTUtils from "@/utils/RESTUtils";

export default {
  name: 'OverviewCustomerComponent',
  created() {
    this.getCustomer();
  },
  computed: {
    filteredCustomers: function () {
      if (this.searchTerm != null) {
        let returnArray = [];
        for (const customer of this.customers) {
          for (const field of Object.keys(customer)) {
            if (typeof customer[field] == "string" && customer[field].indexOf(this.searchTerm) > -1) {
              returnArray.push(customer);
            }
          }
        }
        return returnArray;
      }
      return this.customers;
    }
  },
  data() {
    return {
      customers: null,
      searchTerm: null,
    }
  }
  ,
  methods: {
    getCustomer: function () {
      RESTUtils.sendGetRequest("/customer")
        .then(response => {
          this.customers = response.data;
        })
        .catch(error => console.log(error));
    }
  }
}
</script>
