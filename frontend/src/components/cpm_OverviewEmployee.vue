<template>
  <v-container style="background-color: white;margin-top: 5vh">
    <v-text-field class="centered-input text--darken-3 mt-3" v-model="searchTerm" label="suchen"
                  style="color: black; justify-self: center"/>
    <v-data-table
      :headers="dataTableHeaders"
      :items="employees"
      :search="searchTerm"
      item-key="id"
      style="max-height: 70vh; overflow-y: scroll;">
      <template v-slot:item.actions="{item}">
        <edit-employee-component :employee="item"/>
        <v-btn @click="deleteEmployee(item)">
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
import EditEmployeeComponent from "@/components/cpm_EditEmloyee.vue";

export default {
  name: 'OverviewEmployeeComponent',
  components: {EditEmployeeComponent},
  created() {
    this.getEmployees()
  },
  methods: {
    getEmployees: function () {
      RESTUtils.sendGetRequest("/employee")
        .then(result => this.employees = result.data)
        .catch(error => console.log(error));
    },
    deleteEmployee: function (employee) {
      RESTUtils.sendDeleteRequest("/employee", employee)
      this.employees.splice(this.employees.indexOf(employee), 1);
    }
  },
  data() {
    return {
      employees: [],
      searchTerm: null,
      openEditDialog: false,
      dataTableHeaders: [
        {key: "shortName", title: "Kürzel"},
        {key: "firstName", title: "Vorname"},
        {key: "lastName", title: "Nachname"},
        {key: "email", title: "Email"},
        {key: "active", title: "Aktiv"},
        {key: "actions", title: "Aktionen", align: "center", sortable: false},
      ]
    }
  }
}
</script>
