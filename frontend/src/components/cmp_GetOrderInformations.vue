<template>
  <v-container class="container" v-if="choosedCustomer != null">
    <v-row>
      <v-col cols="3">
        <v-container style="border-bottom-color: black; border: solid;">
          <v-label><b>Gewählter Kunde:</b></v-label>
          <br>
          <v-label>{{ "vorname: " + choosedCustomer.firstName }}</v-label>
          <br>
          <v-label>{{ "nachname: " + choosedCustomer.lastName }}</v-label>
          <br>
          <v-label>{{ "postleitzahl: " + choosedCustomer.postcode }}</v-label>
          <br>
          <v-label>{{ "stadt: " + choosedCustomer.city }}</v-label>
          <br>
          <v-label>{{ "handynummer: " + choosedCustomer.mobileNumber }}</v-label>
          <br>
          <v-label>{{ "festnetznummer: " + choosedCustomer.landlineNumber }}</v-label>
          <br>
          <v-label>{{ "email: " + choosedCustomer.email }}</v-label>
          <br>
          <v-label>{{ "straße: " + choosedCustomer.street }}</v-label>
          <br>
          <v-label>{{ "hausnummer: " + choosedCustomer.streetNumber }}</v-label>
          <br>
          <v-row align="center" justify="center" style="margin-top: 1vh">
            <v-btn style="color: white; margin-bottom: 1vh" @click="resetCustomer">neuen Kunden wählen</v-btn>
          </v-row>
        </v-container>
      </v-col>
      <v-col>
        <v-container style="border: black solid; height: 100%; display: flex;flex-direction: column">
          <v-textarea variant="outlined" rows="10" hide-details label="Beschreibung" v-model="description"/>
        </v-container>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-container style="border-bottom-color: black; border: solid;">
          <v-row>
            <v-col>
              <v-label>Passwörter:</v-label>
              <v-data-table :headers="passwordHeaders" items-per-page="-1" :items="passwords"
                            style="height: 20vh">
                <template v-slot:bottom></template>
                <template v-slot:item="{ item }">
                  <tr>
                    <td style="color: white">{{ item.name }}</td>
                    <td style="color: white">{{ item.password }}</td>
                    <td style="width: 10px">
                      <v-avatar>
                        <v-img @click="item => removePassword(item)" src="@/assets/trashbin.png"/>
                      </v-avatar>
                    </td>
                  </tr>
                </template>
              </v-data-table>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-text-field v-model="passwordName" label="Password-Name"/>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-text-field v-model="passwordValue" label="Passwort"/>
            </v-col>
          </v-row>
          <v-row justify="center">
            <v-btn @click="addPassword" style="color: white; margin-bottom: 1vh">
              Passwort Hinzufügen
            </v-btn>
          </v-row>
        </v-container>
      </v-col>
      <v-col>
        <v-container style="border: black solid; height: 100%">
          <v-textarea label="Zubehör:" variant="outlined" rows="18" hide-details v-model="equipment"/>
        </v-container>
      </v-col>
    </v-row>
    <v-row align="center" justify="center">
      <v-checkbox label="Ist eine Express bearbeitung gewünscht?" v-model="express"/>
    </v-row>
    <v-row justify="center">
      <v-btn style="color: white" @click="createOrder">
        Auftrag erstellen
      </v-btn>
    </v-row>
  </v-container>
</template>


<script>
import RESTUtils from "@/utils/RESTUtils";

export default {
  name: 'GetOrderInformationsComponent',
  props: ["prom_choosedCustomer"],
  setup(prom_choosedCustomer){
    this.data().choosedCustomer = prom_choosedCustomer;
  },
  data() {
    return {
      choosedCustomer: null
    }
  },
  methods: {
    createOrder: function () {
      const order = {
        "customerId": this.choosedCustomer.id,
        "description": this.description,
        "passwords": this.passwords,
        "equipment": this.equipment,
        "express": this.express,
      };
      RESTUtils.sendPostRequest("/order", order);
    },
    addPassword: function () {
      const newItem = {name: this.passwordName, password: this.passwordValue}
      this.passwords.push(newItem);
      this.passwordName = "";
      this.passwordValue = "";
    },
    removePassword: function (item) {
      this.passwords.splice(this.passwords.indexOf(item), 1)
    },
    resetCustomer: function () {
      this.choosedCustomer = null;
    },
  }
}
</script>
