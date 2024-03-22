<script setup>
import { RouterLink, RouterView } from 'vue-router';


import Menu from './components/menu.vue';
import TopBar from './components/TopBar.vue';
</script>

<template>
  <div class="outer-grid">
    <div class="top wide">
      <TopBar></TopBar>
    </div>
    <div class="side">
      <Menu></Menu>
    </div>
    <div class="main-content">
      <RouterView></RouterView>
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
    grid-template-columns: 70px calc(100lvw - 70px);
    grid-template-rows: 70px calc(100lvh - 70px);
    grid-column-gap: 0px;
    grid-row-gap: 0px;
     
  }

  .main-content{
    padding: 1rem;
    overflow-x: hidden;
    background-color: var(--main-background);
    color:var(--main-text-color);
    transition: background ease-out 0.3s;
  }

</style>