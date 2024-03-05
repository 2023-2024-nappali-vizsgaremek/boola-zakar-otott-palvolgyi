<script setup>
import { RouterLink, RouterView } from 'vue-router';

import Menu from './components/menu.vue';
import aHeader from './components/header.vue';
</script>

<template>
    <header>
        <aHeader></aHeader>
    </header>    
    <main>
        <Menu></Menu>
    </main>
</template>

<style>
    @import "../public/mainStyles.css";

    main{
        height: 100lvh;
        background-color: var(--main-light-background);        

    }
</style>