import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'bootstrap/dist/css/bootstrap.min.css'
import { createPinia } from 'pinia'
import piniaPluginPersistedState from "pinia-plugin-persistedstate"

const pinia = createPinia()
const app = createApp(App)

pinia.use(piniaPluginPersistedState)
app.use(pinia)
app.use(router)

app.mount('#app')
