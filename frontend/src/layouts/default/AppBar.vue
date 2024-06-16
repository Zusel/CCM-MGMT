<template>
  <v-app-bar>
    <v-app-bar-nav-icon variant="text"
                        @click.stop="drawer = !drawer"/>
    <v-toolbar-title>Menü</v-toolbar-title>
  </v-app-bar>
  <v-navigation-drawer
    v-model="drawer"
    temporary>
    <v-list>
      <v-list-item @click="navigateDashboard"><h3><b>Dashboard</b></h3></v-list-item>
      <v-list-item><h3><b>Auftragsverwaltung</b></h3></v-list-item>
      <v-list-item>
        <v-list>
          <v-list-item title="Hinzufügen" @click="navigateAddOrder"/>
          <v-list-item title="Übersicht" @click="navigateOverviewOrder"/>
        </v-list>
      </v-list-item>
      <v-list-item><h3><b>Kundenverwaltung</b></h3></v-list-item>
      <v-list-item>
        <v-list>
          <v-list-item title="Hinzufügen" @click="navigateAddCustomer"/>
          <v-list-item title="Übersicht" @click="navigateOverviewCustomer"/>
        </v-list>
      </v-list-item>
      <v-list-item><h3><b>Administration</b></h3></v-list-item>
      <v-list-item>
        <v-list>
          <v-list-item title="Mitarbeiter Hinzufügen" @click="navigateAddEmployees"/>
          <v-list-item title="Übersicht Mitarbeiter" @click="navigateOverviewEmployees"/>
        </v-list>
      </v-list-item>
    </v-list>

  </v-navigation-drawer>
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
    navigateAddEmployees: function () {
      this.$router.push('/AddEmployee');
    },
    navigateDashboard: function () {
      this.$router.push('/Dashboard');
    },
    navigateAddOrder: function () {
      this.$router.push('/AddOrder');
    },
    navigateOverviewOrder: function () {
      this.$router.push("/OverviewOrder")
    }
  },
  data: () => ({
    drawer: false,
    group: null
  })
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
