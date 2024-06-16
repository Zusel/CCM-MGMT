<template>
  <v-container>
    <v-container style="border: white solid;">
      <v-label>Filter:</v-label>
      <v-row>
        <v-col class="d-flex justify-center">
          <v-text-field style="width: 140vh" v-model="filterPhrase"/>
          <v-label style="margin-right: 10px; margin-left: 10px">Status:</v-label>
          <v-select v-model="choosedState" :items="stateFilters"/>
        </v-col>
      </v-row>
    </v-container>

    <v-expansion-panels>
      <v-expansion-panel v-for="order in this.orders" v-bind:key="order.id" :title="order.customer.firstName">
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
        .then(response => this.stateFilters = response.data)
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
