<template>
  <v-dialog v-model="dialog" style="background-color: black" persistent>
    <template v-slot:activator="{ props: activatorProps }">
      <v-btn v-bind="activatorProps">Ändern</v-btn>
    </template>
    <v-container>
      <v-container>
        <v-row>
          <v-col>
            <v-text-field class="inputFields" v-model="cp_customer.firstName"
                          label="Vorname"/>
          </v-col>
          <v-col>
            <v-text-field class="inputFields" v-model="cp_customer.lastName"
                          label="Nachname"/>
          </v-col>
        </v-row>
      </v-container>
      <v-container>
        <v-row>
          <v-col>
            <v-text-field class="inputFields" v-model="cp_customer.mobileNumber"
                          label="Handynummer"/>
          </v-col>
          <v-col>
            <v-text-field class="inputFields" v-model="cp_customer.landlineNumber"
                          label="Festnetznummer"/>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-text-field class="inputFields" v-model="cp_customer.email"
                          label="Email"/>
          </v-col>
        </v-row>
      </v-container>
      <v-container>
        <v-row>
          <v-col>
            <v-text-field class="inputFields" v-model="cp_customer.street"
                          label="Straße"/>
          </v-col>
          <v-col>
            <v-text-field class="inputFields" v-model="cp_customer.streetNumber"
                          label="Hausnummer"/>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-text-field class="inputFields" v-model="cp_customer.postcode"
                          label="Postleitzahl"/>
          </v-col>
          <v-col>
            <v-text-field class="inputFields" v-model="cp_customer.city"
                          label="Stadt"/>
          </v-col>
        </v-row>
      </v-container>
      <v-container>
        <v-row justify="center">
          <v-spacer/>
          <v-btn @click="updateCustomer(cp_customer)">
            Ändern
          </v-btn>
          <v-spacer/>
          <v-btn @click="dialog = false">
            Abbruch
          </v-btn>
          <v-spacer/>
        </v-row>

      </v-container>
    </v-container>
  </v-dialog>
</template>

<script>
import RESTUtils from "@/utils/RESTUtils";

export default {
  name: 'EditCustomerComponent',
  props: ["customer"],
  data() {
    return {
      dialog: false,
      cp_customer: {...this.customer}
    }
  },
  methods: {
    updateCustomer: function (customer) {
      this.dialog = false;
      RESTUtils.sendPostRequest("/customer/update", customer);
      this.$emit("customerChanged", customer)
    }
  }

}
</script>
