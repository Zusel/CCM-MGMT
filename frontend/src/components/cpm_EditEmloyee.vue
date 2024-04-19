<template>
  <v-dialog v-model="dialog" style="background-color: black" persistent>
    <template v-slot:activator="{ props: activatorProps }">
      <v-btn v-bind="activatorProps">Ändern</v-btn>
    </template>
    <v-container>
      <v-container>
        <v-row>
          <v-col>
            <v-text-field class="inputFields" v-model="employee.firstName"
                          label="Vorname"/>
          </v-col>
          <v-col>
            <v-text-field class="inputFields" v-model="employee.lastName"
                          label="Nachname"/>
          </v-col>
        </v-row>
      </v-container>
      <v-container>
        <v-row>
          <v-col>
            <v-text-field class="inputFields" v-model="employee.email"
                          label="Email"/>
          </v-col>
          <v-col>
            <v-text-field class="inputFields" v-model="employee.shortName"
                          label="Kürzel"/>
          </v-col>
        </v-row>
      </v-container>
      <v-container>
        <v-row justify="center">
          <v-checkbox label="Aktiv" v-model="employee.active"/>
        </v-row>
      </v-container>
    </v-container>
    <v-container>
      <v-row justify="center">
        <v-spacer/>
        <v-btn @click="updateEmployee(employee)">
          Ändern
        </v-btn>
        <v-spacer/>
        <v-btn @click="dialog = false">
          Abbruch
        </v-btn>
        <v-spacer/>
      </v-row>
    </v-container>
  </v-dialog>
</template>

<style scoped>

</style>

<script>
import RESTUtils from "@/utils/RESTUtils";

export default {
  name: 'EditEmployeeComponent',
  props: ["employee"],
  data() {
    return {
      dialog: false,
    }
  },
  methods: {
    updateEmployee: function (employee) {
      this.dialog = false;
      RESTUtils.sendPostRequest("/employee/update", employee)
    }
  }
}
</script>
