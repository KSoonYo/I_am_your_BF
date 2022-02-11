import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import { Quasar } from 'quasar'
import quasarUserOptions from './quasar-user-options'
import axios from 'axios'



  
const app = createApp(App)
app.config.globalProperties.axios = axios;
app.use(Quasar, quasarUserOptions).use(store).use(router).mount('#app')

axios.defaults.baseURL = 'http://localhost:8080'
export {App}