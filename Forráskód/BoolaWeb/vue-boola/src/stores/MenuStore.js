import { defineStore } from 'pinia'

export const useMenuStore = defineStore('menu', {
  state: () => ({
    isMenuOpened: false
  }),  
  actions: {
    toggle() {
      this.isMenuOpened = !this.isMenuOpened;
          
      var root = document.querySelector(':root');      
      if(this.isMenuOpened)
        root.style.setProperty('--menu-width', '300px');
      else
        root.style.setProperty('--menu-width', '70px');
    },
  },
})