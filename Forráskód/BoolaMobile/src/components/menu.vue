<script setup>
    import { ref, onMounted } from 'vue'
    import { RouterLink, RouterView } from 'vue-router'

    import { useMenuStore} from '/src/stores/MenuStore';
    const MenuStore = useMenuStore();
</script>

<template>
    <div v-if="MenuStore.isMainMenuDisplayed" class="menu-container unselectable" :class="`${MenuStore.isMainMenuOpened && 'menu-opened'}`">                
        <div class="menu-link-container">        
            <RouterLink class="routerLink" to="/">
                <div class="routerLinkContainer">
                    <span class="material-symbols-outlined size-32">home</span>                
                    <h2 :class="`${MenuStore.isMainMenuOpened && 'menu-text-show'}`">Főoldal</h2>              
                </div>                       
            </RouterLink>
            <RouterLink class="routerLink" to="/newExpense">
                <div class="routerLinkContainer">
                    <span class="material-symbols-outlined size-32">add_circle</span>
                    <h2 :class="`${MenuStore.isMainMenuOpened && 'menu-text-show'}`">Új&nbspkiadás</h2>
                </div>                  
            </RouterLink>
            <RouterLink class="routerLink" to='/expenselist'>
                <div class="routerLinkContainer">
                    <span class="material-symbols-outlined size-32" >list_alt</span>
                    <h2 :class="`${MenuStore.isMainMenuOpened && 'menu-text-show'}`">Költség&nbspLista</h2>
                </div>
            </RouterLink>
          <RouterLink class="routerLink" to='/topUp'>
            <div class="routerLinkContainer">
              <span class="material-symbols-outlined size-32" >savings</span>
              <h2 :class="`${MenuStore.isMainMenuOpened && 'menu-text-show'}`">Feltöltés</h2>
            </div>
          </RouterLink>
          <RouterLink class="routerLink" to="/settings">
            <div class="routerLinkContainer">
              <span class="material-symbols-outlined size-32">settings</span>
              <h2 :class="`${MenuStore.isMainMenuOpened && 'menu-text-show'}`">Beállítások</h2>
            </div>
          </RouterLink>
        </div>
    </div>
</template>

<style scoped>
    .routerLinkContainer{        
        padding: 1rem;
        margin: 3px;        
        border-radius: var(--border-radius);
    }

    .routerLinkContainer > h2{
        position: relative;
        display: inline;
        height: 0px;
        width: 0px;
        opacity: 0;
        left: 50px;
        transition: all ease-out 0.2s;     
    }

    .routerLinkContainer > span{
        display: inline;              
        position: relative;
        top: 4px;
    }
    
    div.routerLinkContainer:hover{
        background-color: var(--main-background);        
        transition: all ease-out 0.3s;
    }

    .menu-container{
        background-color: var(--sec-background);
        transition: background ease-out 0.3s;
        height: 100lvh;
        width: var(--menu-def-width);
        overflow: hidden;
        text-align: left;
        position: fixed;
        z-index: 9999999;

        transition: all ease-out 0.3s;
    }

    .routerLink{
        text-decoration: none;
        color: var(--sec-text-color);         
    }    

    .menu-opened{
        width: 300px;
        z-index: 9999999;
    }

    .menu-text-show{
        display: inline !important;
        height: auto !important;
        width: auto !important;
        opacity: 1 !important;
        transition: all ease-out 0.2s;
        transition-delay: 0.2s !important;        
    }


</style>