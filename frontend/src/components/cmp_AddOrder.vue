<template>
  <v-container style="background-color: white;margin-top: 5vh">
    <v-row>
      <choose-customer-component v-model:choosed="choosed"/>
    </v-row>
  </v-container>
</template>

<style scoped>

</style>

<script>
import ChooseCustomerComponent from "@/components/cpm_chooseCustomer.vue";

export default {
  name: 'AddOrderComponent',
  components: {ChooseCustomerComponent},
  methods: {
    test: function () {
      console.log(this.choosed)
    }
  },
  data() {
    return {
      choosed: null,
    }
  },

}
</script>
