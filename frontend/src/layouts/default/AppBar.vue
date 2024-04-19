<template>
  <v-app-bar>
    <v-spacer/>
    <v-list id="headerList">
      <v-list-item>
        <v-btn>
          Kundenverwaltung
          <v-menu activator="parent">
            <v-list>
              <v-list-item title="Hinzufügen" @click="navigateAddCustomer"/>
              <v-list-item title="Übersicht" @click="navigateOverviewCustomer"/>
            </v-list>
          </v-menu>
        </v-btn>
      </v-list-item>
      <v-list-item>
        <v-btn>
          Administration
          <v-menu activator="parent">
            <v-list>
              <v-list-item title="Mitarbeiter Hinzufügen" @click="navigateAddEmployees"/>
              <v-list-item title="Übersicht Mitarbeiter" @click="navigateOverviewEmployees"/>
            </v-list>
          </v-menu>
        </v-btn>
      </v-list-item>
    </v-list>
    <v-spacer/>
  </v-app-bar>
</template>

<script>

export default {
  name: 'DefaultAppBar',
  methods: {
    navigateAddCustomer: function () {
      this.$router.push('/AddCustomer');
    },
    navigateOverviewCustomer: function () {
      this.$router.push('/OverviewCustomer');
    },
    navigateOverviewEmployees: function () {
      this.$router.push('/OverviewEmployee');
    },
    navigateAddEmployees: function (){
      this.$router.push('/AddEmployee');
    }
  }
}
</script>

<style scoped>
#headerList {
  background-color: black;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
