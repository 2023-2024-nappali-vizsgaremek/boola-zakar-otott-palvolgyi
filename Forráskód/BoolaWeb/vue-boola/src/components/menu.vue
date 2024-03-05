<script setup>
    import ref from 'vue';
    import { RouterLink, RouterView } from 'vue-router';

    const menuOpened = ref(false);

    const toggleMenu = () => {
        menuOpened = !menuOpened;
    }
</script>

<template>
    <div class="menu-container" :class="`${menuOpened && 'menu-opened'}`">
        <button @click="toggleMenu()" class="btn-menu-toggle">Menu</button>
        <RouterLink class="routerLink" to="/"><h2>Főoldal</h2></RouterLink>
        <RouterLink class="routerLink" to="/newExpense"><h2>Új kiadás</h2></RouterLink>
        <RouterLink class="routerLink" to="/settings"><h2>Beállítások</h2></RouterLink>
    </div>
</template>

<style scoped>
    .menu-container{
        background-color: var(--sec-light-background);        
        height: 100lvh;
        width: 50px;
        overflow: hidden;
        text-align: center;
        position: fixed;
        padding: 1rem;
    }

    .routerLink{
        text-decoration: none;
        color: var(--sec-light-text-color);
        margin-bottom: 1rem;     
    }

    .menu-opened{
        width: 15lvw;
    }

    .btn-menu-toggle{
        position: relative;
        float: right;
    }

</style>