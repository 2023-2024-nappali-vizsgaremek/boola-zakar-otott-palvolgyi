import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'bootstrap/dist/css/bootstrap.min.css'
import { createPinia } from 'pinia'
import piniaPluginPersistedState from "pinia-plugin-persistedstate"
import Toast from "vue-toastification"
import 'vue-toastification/dist/index.css'

const app = createApp(App)
const pinia = createPinia();

const option={
    timeout: 2000,
    maxToasts: 20,
    transition:'Vue-Toastification__bounce',
    newestOnTop:true,
    position: "bottom-center"
}

pinia.use(piniaPluginPersistedState)
app.use(pinia)
app.use(router)
app.use(Toast,option)
app.mount('#app')
