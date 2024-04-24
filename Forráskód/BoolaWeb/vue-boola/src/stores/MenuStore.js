import { defineStore } from 'pinia'

export const useMenuStore = defineStore('menu', {
  state: () => ({
    isMainMenuOpened: false,
    isProfileMenuOpened: false,
  }),  
  actions: {
    toggleMainMenu() {
      this.isMainMenuOpened = !this.isMainMenuOpened;
      
      var root = document.querySelector(':root');      
      if(this.isMainMenuOpened)
        root.style.setProperty('--menu-width', '300px');
      else
        root.style.setProperty('--menu-width', '70px');
    },

    toggleProfileMenu(){
      this.isProfileMenuOpened = !this.isProfileMenuOpened;
    },

    closeMenus(){      
      this.isProfileMenuOpened = false;
      var root = document.querySelector(':root');
      var style = getComputedStyle(document.documentElement);      

      if(!(style.getPropertyValue('--viewSize') == `"desktop"`))
      {
        this.isMainMenuOpened = false;      
        root.style.setProperty('--menu-width', '70px');
      }      
    },
  },
})