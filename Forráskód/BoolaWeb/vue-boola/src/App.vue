<script setup>
import { RouterLink, RouterView } from 'vue-router';


import Menu from './components/menu.vue';
import TopBar from './components/TopBar.vue';
</script>

<template>

    <header>
        <TopBar></TopBar>
        <Menu></Menu>
    </header>
  <main>

    <RouterView></RouterView>

  </main>


</template>


<style>
    @import "../public/mainStyles.css";
    @import url("https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined");

    main{
      float: right;
        height: 92vh;
      width: calc(100vw - 65px);
        background-color: var(--main-background);
      color:var(--main-text-color);
        transition: background 0.5s;

    }

</style>