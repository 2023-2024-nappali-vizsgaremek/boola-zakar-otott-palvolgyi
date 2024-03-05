import { createRouter, createWebHistory } from 'vue-router'
import basePage from "@/views/basePage.vue"


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: basePage
    },
    {
      path: '/newExpense',
      name: 'newExpense',      
      component: () => import('../views/newExpense.vue')
    },
    {
      path: '/profileSelect',
      name: 'profileSelect',      
      component: () => import('../views/profileSelect.vue')
    },
    {
      path: '/login',
      name: 'login',      
      component: () => import('../views/login.vue')
    },
    {
      path: '/register',
      name: 'register',      
      component: () => import('../views/register.vue')
    },
    {
      path: '/settings',
      name: 'settings',      
      component: () => import('../views/settings.vue')
    },
  ]
})

export default router
