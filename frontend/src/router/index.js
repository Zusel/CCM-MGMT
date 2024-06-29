/**
 * router/index.ts
 *
 * Automatic routes for `./src/pages/*.vue`
 */

// Composables
import {createRouter, createWebHistory} from 'vue-router/auto'
import {setupLayouts} from 'virtual:generated-layouts'
import VueCookies from "vue-cookies";


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  extendRoutes: setupLayouts,
})

router.beforeEach((to, from) => {
  if (to.name === "/Login") {
    return true;
  }
  if (VueCookies.get("ccm_mgmt_shortname") === null) {
    console.log("Login benötigt!")
    return "/Login";
  }
  return true;
})

export default router
