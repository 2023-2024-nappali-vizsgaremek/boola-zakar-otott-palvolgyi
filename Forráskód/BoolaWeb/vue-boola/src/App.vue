<script setup>
import { RouterLink, RouterView } from 'vue-router';

import Menu from './components/Menu.vue';
import TopBar from './components/TopBar.vue';
</script>

<template>
    <header>
        <TopBar></TopBar>
        <Menu></Menu>
    </header>    
    <main>
        
    </main>
</template>

<style>
    @import "../public/MainStyles.css";
    @import url("https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined");

    main{
        height: 100lvh;
        background-color: var(--main-background);
        transition: background 0.5s;
    }
</style>