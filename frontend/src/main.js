import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import { Quasar } from 'quasar'
import quasarUserOptions from './quasar-user-options'
import axios from 'axios'

// Flicking library
import Flicking from "@egjs/vue3-flicking/dist/flicking.esm";
import "@egjs/vue3-flicking/dist/flicking.css";
// Or, if you have to support IE9
import "@egjs/vue3-flicking/dist/flicking-inline.css";



const app = createApp(App)
app.config.globalProperties.axios = axios
app.component("Flicking", Flicking)
app.use(Quasar, quasarUserOptions).use(store).use(router).mount('#app')

axios.defaults.baseURL = 'http://i6b107.p.ssafy.io/'
export {App}