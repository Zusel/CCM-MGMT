<template>
  <v-container style="background-color: white; margin-top: 10vh">
    <v-container>
      <v-row>
        <v-col>
          <v-text-field class="inputFields" v-model="firstName"
                        label="Vorname"/>
        </v-col>
        <v-col>
          <v-text-field class="inputFields" v-model="lastName"
                        label="Nachname"/>
        </v-col>
      </v-row>
    </v-container>
    <v-container>
      <v-row>
        <v-col>
          <v-text-field class="inputFields" v-model="email"
                        label="Email"/>
        </v-col>
        <v-col>
          <v-text-field class="inputFields" v-model="shortName"
                        label="Kürzel"/>
        </v-col>
      </v-row>
    </v-container>
    <v-container>
      <v-row>
        <v-col>
          <v-text-field class="inputFields" type="password" label="Password" v-model="password"/>
        </v-col>
      </v-row>
    </v-container>
    <v-container>
      <v-row justify="center">
        <v-checkbox class="inputFields" label="Aktiv" v-model="active"/>
      </v-row>
    </v-container>
    <v-container>
      <v-row justify="center">
        <v-btn @click="addEmployee()">
          Erstellen
        </v-btn>
      </v-row>
    </v-container>
  </v-container>
</template>

<style scoped>
.inputFields {
  color: black;
}

</style>

<script>
import RESTUtils from "@/utils/RESTUtils";

export default {
  name: 'AddEmployeeComponent',
  data() {
    return {
      firstName: '',
      lastName: '',
      shortName: '',
      active: '',
      email: '',
      password: '',
    }
  },
  methods: {
    addEmployee: function () {
      const employee = {
        "firstName": this.firstName,
        "lastName": this.lastName,
        "shortName": this.shortName,
        "active": this.active,
        "email": this.email,
        "password": this.password
      }
      RESTUtils.sendPostRequest("/employee", employee)
    }
  }
}
</script>
