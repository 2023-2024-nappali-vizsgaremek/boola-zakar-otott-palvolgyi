<script setup>
import { RouterLink, RouterView } from 'vue-router';


import Menu from './components/menu.vue';
import TopBar from './components/TopBar.vue';
import { useMenuStore} from '/src/stores/MenuStore';
  const MenuStore = useMenuStore();
</script>

<template>
  <div class="outer-grid">
    <div class="top wide">
      <TopBar></TopBar>
    </div>
    <div class="side">
      <Menu></Menu>
    </div>
    <div class="main-content" :class="`${MenuStore.isMenuOpened && 'menu-open-blur'}`">
      <div :class="`${(MenuStore.isMainMenuOpened) && 'main-content-blur'} 
        ${(MenuStore.isProfileMenuOpened) && 'main-content-blur-profileMenu'}`" 
        @click="MenuStore.closeMenus()"></div>      
    </div>
  </div>
</template>

<style>
  @import "../public/mainStyles.css";
  @import url("https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined");
  
  .wide {
    grid-column: span 2;
  }   

  .outer-grid {    
    display: grid;
    
    grid-column-gap: 0px;
    grid-row-gap: 0px;
    transition: 0.3s ease-out;
  }

  .main-content{    
    overflow-x: hidden;
    background-color: var(--main-background);
    color:var(--main-text-color);
    transition: background ease-out 0.3s;
  }

  @media (max-width: 576px) {
    .outer-grid {
      grid-template-columns: 0px calc(100lvw - 0px);
      grid-template-rows: 70px calc(100lvh - 70px);
    }
    
    .main-content-inner{
      position: absolute;
      right: 0;
      height: calc(100lvh - 70px);
      width: calc(100lvw - var(--menu-width));
      padding: 0px;
      margin: 0px;
      backdrop-filter: blur(1px);
      transition: ease-out 0.3s;      
    }

  }

  @media (min-width: 577px) and (max-width: 1199px){
    .outer-grid {
      grid-template-columns: 70px calc(100lvw - 70px);
      grid-template-rows: 70px calc(100lvh - 70px);
    }
      
    .main-content-inner{
      position: absolute;
      right: 0;
      height: calc(100lvh - 70px);
      width: calc(100lvw - var(--menu-width));
      padding: 0px;
      margin: 0px;
      backdrop-filter: blur(1px);
      transition: ease-out 0.3s;      
    }

  }

  @media (min-width: 1200px) {
    .outer-grid {
      grid-template-columns: var(--menu-width) calc(100lvw - var(--menu-width));
      grid-template-rows: 70px calc(100lvh - 70px);
    }
  }
</style>