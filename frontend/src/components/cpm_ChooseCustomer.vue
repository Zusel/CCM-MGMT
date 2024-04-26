<template>
  <v-dialog v-model="dialog" style="background-color: black">
    <template v-slot:activator="{ props: activatorProps }">
      <v-btn v-bind="activatorProps">Kunden auswählen</v-btn>
    </template>
    <v-container style="background-color: white;margin-top: 5vh">
      <v-text-field class="centered-input text--darken-3 mt-3" v-model="searchTerm" label="suchen"
                    style="color: black; justify-self: center"/>
      <v-data-table
        :headers="dataTableHeaders"
        :items="customers"
        :search="searchTerm"
        item-key="id"
        style="max-height: 70vh; overflow-y: scroll;">
        <template v-slot:item.actions="{item}">
          <v-btn @click="chooseCustomer(item)">Auswählen</v-btn>
        </template>
      </v-data-table>
    </v-container>
  </v-dialog>
</template>

<style scoped>

</style>


<script>

import RESTUtils from "@/utils/RESTUtils";

export default {
  name: 'ChooseCustomerComponent',
  props: {
    choosed: Object,
  },
  emits: ["update:choosed"],
  created() {
    this.getCustomer();
  },
  methods: {
    getCustomer: function () {
      RESTUtils.sendGetRequest("/customer")
        .then(response => {
          this.customers = response.data;
        })
        .catch(error => console.log(error));
    },
    chooseCustomer: function (item) {
      this.$emit("update:choosed", item);
      this.dialog = false;
    }
  },
  data() {
    return {
      searchTerm: "",
      customers: [],
      dialog: false,
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
  },

}
</script>
