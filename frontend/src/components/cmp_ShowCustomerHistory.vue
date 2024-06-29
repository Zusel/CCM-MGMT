<template>
  <v-dialog v-model="dialog" style="background-color: black">
    <template v-slot:activator="{ props: activatorProps }">
      <v-btn v-bind="activatorProps">History</v-btn>
    </template>
    <v-container>
      <v-label style="color: white">asdasdasd</v-label>
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
          console.log(response.data)
        })
    }
  },
  watch: {
    dialog:{
      handler: function (){
        if(this.dialog){
          this.getCustomerHistory();
        }
      }
    }
  }
}
</script>
