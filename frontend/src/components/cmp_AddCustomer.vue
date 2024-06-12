<template>
  <v-container style="background-color: white; margin-top: 10vh">
    <v-form v-model="form" @submit.prevent="addCustomer">
      <v-container>
        <v-row>
          <v-col>
            <v-text-field class="inputFields" v-model="firstName"
                          label="Vorname"
                          :rules="[rules.notNull, rules.length3]"/>
          </v-col>
          <v-col>
            <v-text-field class="inputFields" v-model="lastName"
                          :rules="[rules.notNull, rules.length3]"
                          label="Nachname"/>
          </v-col>
        </v-row>
      </v-container>
      <v-container>
        <v-row>
          <v-col>
            <v-text-field class="inputFields" v-model="mobileNumber"
                          :rules="[rules.notNull,rules.phonenumber]"
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
                          :rules="[rules.email]"
                          label="Email"/>
          </v-col>
        </v-row>
      </v-container>
      <v-container>
        <v-row>
          <v-col>
            <v-text-field class="inputFields" v-model="street"
                          :rules="[rules.notNull]"
                          label="Straße"/>
          </v-col>
          <v-col>
            <v-text-field class="inputFields" v-model="streetNumber"
                          :rules="[rules.notNull]"
                          label="Hausnummer"/>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-text-field class="inputFields" v-model="postcode"
                          :rules="[rules.notNull]"
                          label="Postleitzahl"/>
          </v-col>
          <v-col>
            <v-text-field class="inputFields" v-model="city"
                          :rules="[rules.notNull]"
                          label="Stadt"/>
          </v-col>
        </v-row>
      </v-container>
      <v-container>
        <v-row justify="center">
          <v-btn :disabled="!form" type="submit">
            Erstellen
          </v-btn>
        </v-row>
      </v-container>
    </v-form>
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
      streetNumber: '',
      rules: {
        notNull: value => !!value || 'Das Feld ist leer! Bitte überprüfe die Eingabe!',
        length3: value => value.length > 2 || 'Der Feld-Inhalt ist zu kurz! Mindestens 3 Buchstaben/Zahlen notwendig!',
        phonenumber: value => value.match("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$") !== null || 'Handynummer ist ungültig!',
        email: value => {
          if (value !== "") {
            if (value.match("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") === null) {
              return 'Email ist ungültig!'
            }
          }
        }
      },
      form: null
    }
  },
  methods: {
    addCustomer: function () {
      if (this.form) {
        const customer = {
          "firstName": this.firstName,
          "lastName": this.lastName,
          "postcode": this.postcode,
          "city": this.city,
          "mobileNumber": this.mobileNumber,
          "landlineNumber": this.landlineNumber,
          "email": this.email,
          "street": this.street,
          "streetNumber": this.streetNumber
        }
        RESTUtils.sendPostRequest("/customer", customer)
          .then(
            this.$root.$refs.vtoast.show({message: 'Customer erstellt'})
          )
          .catch(error => {
            console.log(error)
            this.$root.$refs.vtoast.show({
              message: 'Fehler: ' + error,
              color: 'failed'
            })
          })
      }
    }
  },
  watch:{
    customer: {
      handler: function (){
        this.$emit('customerChanged', this.customer)
      }
    }
  },
  computed: {
    customer: function (){
      const customer = {
        "firstName": this.firstName,
        "lastName": this.lastName,
        "postcode": this.postcode,
        "city": this.city,
        "mobileNumber": this.mobileNumber,
        "landlineNumber": this.landlineNumber,
        "email": this.email,
        "street": this.street,
        "streetNumber": this.streetNumber
      };
      return customer
    }
  }
}
</script>
