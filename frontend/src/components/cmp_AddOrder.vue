<template>
  <v-container style="background-color: white;margin-top: 5vh">
    <v-row justify="center" style="margin-top: 2vh">
      <v-spacer/>
      <choose-customer-component v-if="!customerChoosed" v-model:choosed="choosed"/>
      <v-spacer v-if="!customerChoosed"/>
      <h3 v-if="customerChoosed" style="color: black">Kunde: </h3>
      <v-spacer v-if="customerChoosed" style="max-width: 1vh"/>
      <v-label :text="choosedCustomerText" style="color: black" readonly
               v-if="customerChoosed"/>
      <add-customer-dialog-component v-model:customer="choosed" v-if="!customerChoosed"/>
      <v-spacer/>
    </v-row>

  </v-container>
</template>

<style scoped>

</style>

<script>
import ChooseCustomerComponent from "@/components/cpm_ChooseCustomer.vue";
import AddCustomerComponent from "@/components/cmp_AddCustomer.vue";
import AddCustomerDialogComponent from "@/components/cpm_AddCustomerDialog.vue";

export default {
  name: 'AddOrderComponent',
  components: {AddCustomerDialogComponent, AddCustomerComponent, ChooseCustomerComponent},
  methods: {},
  computed: {
    customerChoosed() {
      return this.choosed !== null;
    },
    choosedCustomerText() {
      if (this.choosed !== null) {
        return this.choosed.firstName + ' | ' + this.choosed.lastName;
      }
      return null;
    }
  },
  data() {
    return {
      choosed: null,
    }
  }
  ,
  watch: {
    choosed(newCustomer, oldCustomer) {
      console.log(newCustomer);
    }
  }

}
</script>
