<template>
  <v-app>
    <v-main>
      <router-view />
      <SnackbarComponent ref="vtoast"/>
    </v-main>
  </v-app>
</template>

<script>


import SnackbarComponent from "@/components/cmp_Snackback.vue";

export default {
  components: {SnackbarComponent},
  mounted() {
    this.$root.vtoast = this.$refs.vtoast
  }
}
</script>
