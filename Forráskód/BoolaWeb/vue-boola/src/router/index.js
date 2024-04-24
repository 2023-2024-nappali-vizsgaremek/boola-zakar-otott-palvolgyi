import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '../views/HomePage.vue'
import {ref} from 'vue'

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

import { useMenuStore} from '../stores/MenuStore'


router.afterEach(() => {
  const MenuStore = useMenuStore();
  MenuStore.closeMenus();
});

const authToken = ref(sessionStorage.getItem("authToken"));
import {profileStore} from "/src/stores/ProfileStore"

/*
router.beforeEach((to, from) => {
  const profiles = profileStore().profile
  //console.log(profiles, authToken);
  if(!authToken)
    router.push("/login");
  if(profiles==null && authToken)
    return false;
})*/

export default router
