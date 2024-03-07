import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '@/views/HomePage.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomePage
    },
    {
      path: '/NewExpense',
      name: 'NewExpense',      
      component: () => import('../views/NewExpense.vue')
    },
    {
      path: '/ProfileSelect',
      name: 'ProfileSelect',      
      component: () => import('../views/ProfileSelect.vue')
    },
    {
      path: '/Login',
      name: 'Login',      
      component: () => import('../views/Login.vue')
    },
    {
      path: '/Registration',
      name: 'Registration',      
      component: () => import('../views/Registration.vue')
    },
    {
      path: '/Settings',
      name: 'Settings',      
      component: () => import('../views/Settings.vue')
    },
  ]
})

export default router
