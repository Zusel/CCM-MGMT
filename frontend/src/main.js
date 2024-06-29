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
import VueCookies from 'vue-cookies'


const app = createApp(App)
export default app
registerPlugins(app)

app.use(VueCookies, {expires: '1d'})
app.mount('#app')
