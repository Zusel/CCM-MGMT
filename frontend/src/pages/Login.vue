<template>
  <v-container style="height: 100%; align-items: center; display: flex">
    <v-container style="border: white solid; width: 80vh">
      <v-select label="Mitarbeiter:" v-model="selectedEmployee" :items="employees"/>
      <v-btn @click="doLogin" style="width: 100%">Login</v-btn>
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
    doLogin: function (){
      if (this.selectedEmployee === null) {
        this.$root.$refs.vtoast.show(
          {
            message: 'Naja vielleicht das nächste mal :)',
            color: 'failed'
          }
        )
      } else {
        store.commit("setEmployeeShortName", this.selectedEmployee)
        this.$router.push("/Dashboard")
      }
    }
  },
  created() {
    this.getEmployees();
  }
}
</script>
