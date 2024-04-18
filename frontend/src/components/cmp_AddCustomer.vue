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
          <v-text-field class="inputFields" v-model="mobileNumber"
                        label="Handynummer"/>
        </v-col>
        <v-col>
          <v-text-field class="inputFields" v-model="landlineNumber"
                        label="Festnetznummer"/>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-text-field class="inputFields" v-model="email"
                        label="Email"/>
        </v-col>
      </v-row>
    </v-container>
    <v-container>
      <v-row>
        <v-col>
          <v-text-field class="inputFields" v-model="street"
                        label="Straße"/>
        </v-col>
        <v-col>
          <v-text-field class="inputFields" v-model="streetNumber"
                        label="Hausnummer"/>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-text-field class="inputFields" v-model="postcode"
                        label="Postleitzahl"/>
        </v-col>
        <v-col>
          <v-text-field class="inputFields" v-model="city"
                        label="Stadt"/>
        </v-col>
      </v-row>
    </v-container>
    <v-container>
      <v-row justify="center">
        <v-btn>
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
export default {
  name: 'AddCustomerComponent',
  data() {
    return {
      firstName: '',
      lastName: '',
      postcode: '',
      city: '',
      mobileNumber: '',
      landlineNumber: '',
      email: '',
      street: '',
      streetNumber: ''
    }
  }
}

</script>
