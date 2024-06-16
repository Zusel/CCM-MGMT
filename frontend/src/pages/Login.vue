<template>
  <v-container style="height: 100%; align-items: center; display: flex">
    <v-container style="border: white solid; width: 80vh">
      <v-select label="Mitarbeiter:" v-model="selectedEmployee" :items="employees"/>
      <v-btn @click="setEmployee" style="width: 100%">Login</v-btn>
    </v-container>
  </v-container>

</template>

<script>
import RESTUtils from "@/utils/RESTUtils";
import store from "@/main";

export default {
  name: "Login",
  data() {
    return {
      employees: [],
      selectedEmployee: null,
    }
  },
  methods: {
    getEmployees: function () {
      RESTUtils.sendGetRequest("/employee/shortnames")
        .then(response => {
          this.employees = response.data
        })
    },
    setEmployee: function () {
      if (this.selectedEmployee === null) {
        this.$root.$refs.vtoast.show(
          {
            message: 'Naja vielleicht das nächste mal :)',
            color: 'failed'
          }
        )
      } else {
        RESTUtils.sendGetRequest("/employee/getIdForShortName?shortName=" + this.selectedEmployee)
          .then(response => {
            store.commit("setEmployeeShortName", this.selectedEmployee)
            store.commit("setEmployeeId", response.data)
          })
      }
    }
  },
  created() {
    this.getEmployees();
  }
}
</script>
