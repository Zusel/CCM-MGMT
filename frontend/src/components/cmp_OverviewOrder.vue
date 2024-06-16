<template>
  <v-container>
    <v-container style="border: white solid;">
      <v-label>Filter:</v-label>
      <v-row>
        <v-col class="d-flex justify-center">
          <v-text-field style="width: 140vh" v-model="filterPhrase"/>
          <v-select style="margin-left: 10px" label="Status" clearable v-model="choosedState" :items="stateFilters"/>
        </v-col>
      </v-row>
    </v-container>

    <v-expansion-panels>
      <v-expansion-panel v-for="order in this.orders" v-bind:key="order.id">
        <v-expansion-panel-title>
          <template v-slot:default="">
            <v-row no-gutters>
              <v-col>
                <b>ID:</b> {{ order.id }} <b>Kunde:</b> {{ order.customer.firstName }} {{ order.customer.lastName }} <b>Kurzbeschreibung:</b>
                {{ truncat(order.description) }}
              </v-col>
            </v-row>
          </template>
        </v-expansion-panel-title>
        <v-expansion-panel-text>
          <v-container>
            <v-row>
              <v-col cols="3">
                <v-container style="border-bottom-color: black; border: solid; height: 100%">
                  <v-label><b>Gewählter Kunde:</b></v-label>
                  <br>
                  <v-label>{{ "vorname: " + order.customer.firstName }}</v-label>
                  <br>
                  <v-label>{{ "nachname: " + order.customer.lastName }}</v-label>
                  <br>
                  <v-label>{{ "postleitzahl: " + order.customer.postcode }}</v-label>
                  <br>
                  <v-label>{{ "stadt: " + order.customer.city }}</v-label>
                  <br>
                  <v-label>{{ "handynummer: " + order.customer.mobileNumber }}</v-label>
                  <br>
                  <v-label>{{ "festnetznummer: " + order.customer.landlineNumber }}</v-label>
                  <br>
                  <v-label>{{ "email: " + order.customer.email }}</v-label>
                  <br>
                  <v-label>{{ "straße: " + order.customer.street }}</v-label>
                  <br>
                  <v-label>{{ "hausnummer: " + order.customer.streetNumber }}</v-label>
                </v-container>
              </v-col>
              <v-col>
                <v-container style="border-bottom-color: black; border: solid; height: 100%">
                  <v-row>
                    <v-col>
                      <v-label>Passwörter:</v-label>
                      <v-data-table :headers="passwordHeaders" items-per-page="-1" :items="order.passwords">
                        <template v-slot:bottom></template>
                        <template v-slot:item="{ item }">
                          <tr>
                            <td style="color: white">{{ item.name }}</td>
                            <td style="color: white">{{ item.password }}</td>
                          </tr>
                        </template>
                      </v-data-table>
                    </v-col>
                  </v-row>
                </v-container>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-container @focusout="updateOrder(order)"
                             style="border: white solid; height: 100%; display: flex;flex-direction: column">
                  <v-textarea variant="outlined" rows="9" hide-details label="Beschreibung"
                              v-model="order.description"/>
                </v-container>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-container @focusout="updateOrder(order)"
                             style="border: white solid; height: 100%; display: flex;flex-direction: column">
                  <v-textarea variant="outlined" rows="9" hide-details label="Zubehör"
                              v-model="order.equipment"/>
                </v-container>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-select label="Status" @update:modelValue="updateOrder(order)" v-model="order.state" :items="this.stateFilters"/>
              </v-col>
              <v-col>
                <v-checkbox readonly label="Express?" v-model="order.express"/>
              </v-col>
            </v-row>
          </v-container>
        </v-expansion-panel-text>
      </v-expansion-panel>
    </v-expansion-panels>
  </v-container>
</template>

<style scoped>

</style>

<script>
import RESTUtils from "@/utils/RESTUtils";

export default {
  name: "OverviewOrderComponent",
  data() {
    return {
      orders: [],
      passwordHeaders: [{
        title: "name"
      },
        {
          title: "password"
        }],
      stateFilters: [],
      choosedState: null,
      filterPhrase: "",
    }
  },
  methods: {
    getOrders: function () {
      RESTUtils.sendGetRequest("/order")
        .then(response => this.orders = response.data)
    },
    getStateFilters: function () {
      RESTUtils.sendGetRequest("/order/orderStates")
        .then(response => {
          this.stateFilters = response.data
        })
    },
    getOrdersFiltered: function (value) {
      let filter = ""
      if (value != null && value !== "") {
        filter = filter + "?filter=" + value;
      }
      if (this.choosedState != null) {
        if (value === null) {
          filter = filter + "?state=" + this.choosedState;
        } else {
          filter = filter + "&state=" + this.choosedState;
        }
      }
      RESTUtils.sendGetRequest("/order" + filter)
        .then(response => this.orders = response.data)
    },
    truncat: function (string) {
      return string.length > 150 ? string.slice(0, 149) + "..." : string;
    },
    updateOrder: function (order) {
      RESTUtils.sendPostRequest("/order", order)
    }
  },
  created() {
    this.getOrders();
    this.getStateFilters();
  },
  watch: {
    filterPhrase: function (newValue) {
      this.getOrdersFiltered(newValue);
    },
    choosedState: function () {
      this.getOrdersFiltered(null);
    }
  }
}
</script>
