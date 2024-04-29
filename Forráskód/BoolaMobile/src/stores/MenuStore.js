import { defineStore } from 'pinia'

export const useMenuStore = defineStore('menu', {
  state: () => ({
    isProfileMenuDisplayed: false,
    isMainMenuDisplayed: false,
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
    
    showMainMenu(){
      var root = document.querySelector(':root');
      var defMenuWidth = getComputedStyle(document.documentElement).getPropertyValue('--menu-def-width');
      root.style.setProperty('--menu-width', defMenuWidth);
      this.isMainMenuDisplayed = true;
    },

    showProfileMenu(){
      this.isProfileMenuDisplayed = true;
    },

    hideMenus(){
      var root = document.querySelector(':root');
      root.style.setProperty('--menu-width', '0px');
      this.isProfileMenuDisplayed = false;
      this.isMainMenuDisplayed = false;
      console.log(this.isProfileMenuDisplayed, this.isMainMenuDisplayed)
    },
  },
})