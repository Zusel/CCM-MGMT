<template>
  <v-dialog v-model="dialog">
    <template v-slot:activator="{ props: activatorProps }">
      <v-btn v-bind="activatorProps">Kunden erstellen</v-btn>
    </template>
    <add-customer-component v-model:customer="createdCustomer"/>
  </v-dialog>
</template>

<style scoped>

</style>


<script>
import AddCustomerComponent from "@/components/cmp_AddCustomer.vue";

export default {
  name: 'AddCustomerDialogComponent',
  components: {AddCustomerComponent},
  emits: ["update:customer"],
  props: {
    customer: Object
  },
  data() {
    return {
      dialog: false,
      createdCustomer: null,
    }
  },
  watch: {
    createdCustomer(newCustomer, oldCustomer) {
      this.dialog = false
      this.$emit("update:customer", newCustomer.data);
    }
  }
}

</script>
