import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '../views/HomePage.vue'
import App from '../App.vue'
import { createApp } from 'vue'
import { createPinia } from 'pinia'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomePage
    },
    {
      path: '/newExpense',
      name: 'newExpense',
      component: () => import('../views/newExpense.vue')
    },
    {
      path: '/profiles',
      name: 'profiles',
      component: () => import('../views/Profiles.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/Login.vue')
    },
    {
      path: '/register',
      name: 'registration',
      component: () => import('../views/Registration.vue')
    },
    {
      path: '/settings',
      name: 'settings',
      component: () => import('../views/settings.vue')
    },
    {
      path:'/expenselist',
      name:'expenselist',
      component: ()=> import('../views/ExpenseList.vue')
    },
    {
    path :'/topUp',
    name: 'topUp',
    component: ()=>import('../views/topUp.vue')
    }
  ]
})

const pinia = createPinia()
const app = createApp(App)

app.use(router)
app.use(pinia)

import { useMenuStore} from '../stores/MenuStore'
const MenuStore = useMenuStore(pinia);

router.afterEach((to, from) => {  
  MenuStore.closeMenus();
});

export default router
