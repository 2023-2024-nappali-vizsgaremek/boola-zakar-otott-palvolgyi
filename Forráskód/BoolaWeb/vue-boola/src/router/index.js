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
      path: '/newExpense',
      name: 'newExpense',
      component: () => import('../views/newExpense.vue')
    },
    {
      path: '/profileSelect',
      name: 'profileSelect',
      component: () => import('../views/ProfileSelect.vue')
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
    }
  ]
})

export default router
