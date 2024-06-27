/**
 * main.js
 *
 * Bootstraps Vuetify and other plugins then mounts the App`
 */

// Plugins
import {registerPlugins} from '@/plugins'

// Components
import App from './App.vue'

// Composables
import {createApp} from 'vue'
import {createStore} from "vuex";

const store = createStore({
  state: {
    employeeShortName: null
  },
  getters: {
    getEmployeeShortName(state, getters) {
      return getters.employeeShortName;
    },
  },
  mutations: {
    setEmployeeShortName(state, employeeShortName) {
      state.employeeShortName = employeeShortName;
    }
  }
})

export default store
const app = createApp(App)

registerPlugins(app)
app.use(store)
app.mount('#app')
