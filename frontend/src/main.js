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
    employeeShortName: null,
    employeeId: null,
  },
  getters: {
    getEmployeeShortName(state, getters) {
      return getters.employeeShortName;
    },
    getEmployeeId(state, getters) {
      return getters.employeeId;
    },
  },
  mutations: {
    setEmployeeShortName(state, employeeShortName) {
      state.employeeShortName = employeeShortName;
    },
    setEmployeeId(state, employeeId) {
      state.employeeId = employeeId;
    }
  }
})

export default store
const app = createApp(App)

registerPlugins(app)
app.use(store)
app.mount('#app')
