<script setup>
    import { ref, onMounted } from 'vue'
    import { RouterLink, RouterView } from 'vue-router'

    const isMenuOpened = ref(false);

    const toogleMenu = () => {
        isMenuOpened.value = !isMenuOpened.value;        
    }
</script>

<template>
    <div class="menu-container" :class="`${isMenuOpened && 'menu-opened'}`">        
        <span @click="toogleMenu()" class="material-symbols-outlined size-32 btn-menu-toggle">menu</span>        
        <div class="menu-link-container">        
            <RouterLink class="routerLink" to="/">
                <div class="routerLinkContainer">
                    <span class="material-symbols-outlined size-32">home</span>                
                    <h2 :class="`${!isMenuOpened && 'menu-text-hide'}`">Főoldal</h2>              
                </div>                       
            </RouterLink>
            <RouterLink class="routerLink" to="/newExpense">
                <div class="routerLinkContainer">
                    <span class="material-symbols-outlined size-32">add_circle</span>
                    <h2 :class="`${!isMenuOpened && 'menu-text-hide'}`">Új kiadás</h2>
                </div>                  
            </RouterLink>
            <RouterLink class="routerLink" to="/settings">
                <div class="routerLinkContainer">
                    <span class="material-symbols-outlined size-32">settings</span>
                    <h2 :class="`${!isMenuOpened && 'menu-text-hide'}`">Beállítások</h2>
                </div>                  
            </RouterLink>        
        </div>
    </div>
</template>

<style scoped>
    .routerLinkContainer{
        margin-bottom: 2rem;
    }

    span.size-32{
        font-size: 32px;
        font-variation-settings: 'OPSZ' 32;
        color: var(--sec-text-color);        
    }

    .menu-container{
        background-color: var(--sec-background);
        transition: background 0.5s;       
        height: 100lvh;
        width: 70px!important;
        overflow: hidden;
        text-align: center;
        position: fixed;

        transition: 0.2s ease-out;
    }

    .routerLink{
        text-decoration: none;
        color: var(--sec-text-color);
        margin-bottom: 3rem;        
    }

    .menu-opened{
        width: 15lvw !important;
        transition: 0.2s ease-out;
    }

    .btn-menu-toggle{
        position: relative;
        text-align: center;
        cursor: pointer;
    }

    .menu-text-hide{
        display: none;        
    }

    .menu-link-container{
        margin-top: 2rem;
    }

</style>