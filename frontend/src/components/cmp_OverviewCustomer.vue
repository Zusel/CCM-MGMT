<template>
  <v-container style="background-color: white;margin-top: 5vh">
    <v-text-field class="centered-input text--darken-3 mt-3" v-model="searchTerm" label="suchen"
                  style="color: black; justify-self: center"/>
    <v-data-table
      :headers="dataTableHeaders"
      :items="customers"
      :search="searchTerm"
      item-key="id"
      style="max-height: 70vh; overflow-y: scroll;">
      <template v-slot:[`item.actions`]="{item}">
        <show-customer-history-component :customer="item"/>
        <edit-customer-component
          @customerChanged="customer => customers[customers.findIndex(obj => obj.id = customer.id)] = customer"
          :customer="item"/>
        <v-btn @click="deleteCustomer(item)">
          Löschen
        </v-btn>
      </template>
    </v-data-table>
  </v-container>
</template>

<style scoped>

</style>

<script>
import RESTUtils from "@/utils/RESTUtils";
import EditCustomerComponent from "@/components/com_EditCustomer.vue";
import ShowCustomerHistoryComponent from "@/components/cmp_ShowCustomerHistory.vue";

export default {
  name: 'OverviewCustomerComponent',
  components: {ShowCustomerHistoryComponent, EditCustomerComponent},
  created() {
    this.getCustomer();
  },
  data() {
    return {
      customers: [],
      searchTerm: null,
      dataTableHeaders: [
        {key: "firstName", title: "Vorname"},
        {key: "lastName", title: "Nachname"},
        {key: "mobileNumber", title: "Handynummer"},
        {key: "landlineNumber", title: "Festnetznummer"},
        {key: "email", title: "Email"},
        {key: "street", title: "Straße"},
        {key: "streetNumber", title: "Hausnummer"},
        {key: "postcode", title: "Postleitzahl"},
        {key: "city", title: "Stadt"},
        {key: "actions", title: "Aktionen", align: "center", sortable: false},
      ]
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
    },
    deleteCustomer: function (customer) {
      RESTUtils.sendDeleteRequest("/customer", customer);
      this.customers.splice(this.customers.indexOf(customer), 1);
    }
  }
}
</script>
