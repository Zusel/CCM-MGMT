<template>
  <v-dialog v-model="dialog" style="background-color: black">
    <template v-slot:activator="{ props: activatorProps }">
      <v-btn v-bind="activatorProps">History</v-btn>
    </template>
    <v-container>
      <v-expansion-panels>
        <v-virtual-scroll
          :items="this.history"
          height="600">
          <template v-slot:default="{ item }">
            <v-expansion-panel>
              <v-expansion-panel-title>
                <template v-slot:default="">
                  <v-row no-gutters>
                    <v-col>
                      <b>Type: </b> {{ item.changeType }} <b v-if="item.fieldChanged !== null">Feld: </b>
                      <v-label v-if="item.fieldChanged !== null">{{ item.fieldChanged }}</v-label>
                      <v-label style="float: right">{{ item.date }}</v-label>
                    </v-col>
                  </v-row>
                </template>
              </v-expansion-panel-title>
              <v-expansion-panel-text>
                <v-container>
                  <v-row>
                    <v-col>
                      <v-label>Alter Wert:</v-label>
                      <v-text-field readonly>{{ item.oldValue}}</v-text-field>
                    </v-col>
                    <v-col>
                      <v-label>Neuer Wert:</v-label>
                      <v-text-field readonly>{{ item.newValue}}</v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col>
                      <v-label>Feld:</v-label>
                      <v-text-field readonly>{{ item.fieldChanged}}</v-text-field>
                    </v-col>
                    <v-col>
                      <v-label>Type:</v-label>
                      <v-text-field readonly>{{ item.changeType}}</v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col>
                      <v-label>Mitarbeiter:</v-label>
                      <v-text-field readonly>{{ item.employee.firstName }} {{ item.employee.lastName }}</v-text-field>
                    </v-col>
                    <v-col>
                      <v-label>Datum:</v-label>
                      <v-text-field readonly>{{ item.date }}</v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
              </v-expansion-panel-text>
            </v-expansion-panel>
          </template>
        </v-virtual-scroll>
      </v-expansion-panels>
    </v-container>
  </v-dialog>
</template>

<style scoped>

</style>


<script>
import RESTUtils from "@/utils/RESTUtils";

export default {
  name: "ShowCustomerHistoryComponent",
  props: ["customer"],
  data() {
    return {
      dialog: false,
      history: []
    }
  },
  methods: {
    getCustomerHistory: function () {
      RESTUtils.sendGetRequest("/history/customer/" + this.customer.id)
        .then(response => {
          this.history = response.data
          console.log(this.history)
        })
    }
  },
  watch: {
    dialog: {
      handler: function () {
        if (this.dialog) {
          this.getCustomerHistory();
        }
      }
    }
  }
}
</script>
