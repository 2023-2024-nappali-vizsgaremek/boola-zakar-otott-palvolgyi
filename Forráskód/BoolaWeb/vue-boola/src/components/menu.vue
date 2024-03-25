<script setup>
    import { ref, onMounted } from 'vue'
    import { RouterLink, RouterView } from 'vue-router'
    
    import { useMenuStore} from '@/stores/MenuStore';
    const MenuStore = useMenuStore();
</script>

<template>
    <div class="menu-container unselectable" :class="`${MenuStore.isMenuOpened && 'menu-opened'}`">                
        <div class="menu-link-container">        
            <RouterLink class="routerLink" to="/">
                <div class="routerLinkContainer">
                    <span class="material-symbols-outlined size-32">home</span>                
                    <h2 :class="`${!MenuStore.isMenuOpened && 'menu-text-hide'}`">Főoldal</h2>              
                </div>                       
            </RouterLink>
            <RouterLink class="routerLink" to="/newExpense">
                <div class="routerLinkContainer">
                    <span class="material-symbols-outlined size-32">add_circle</span>
                    <h2 :class="`${!MenuStore.isMenuOpened && 'menu-text-hide'}`">Új kiadás</h2>
                </div>                  
            </RouterLink>
            <RouterLink class="routerLink" to="/settings">
                <div class="routerLinkContainer">
                    <span class="material-symbols-outlined size-32">settings</span>
                    <h2 :class="`${!MenuStore.isMenuOpened && 'menu-text-hide'}`">Beállítások</h2>
                </div>                  
            </RouterLink>
            <RouterLink class="routerLink" to='/expenselist'>
                <div class="routerLinkContainer">
                    <span class="material-symbols-outlined size-32" >list_alt</span>
                    <h2 :class="`${!MenuStore.isMenuOpened && 'menu-text-hide'}`">Költség Lista</h2>
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
        left: 50px;
        transition: all ease-out 0.2s;        
    }

    .routerLinkContainer > span{                
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

        transition: all ease-out 0.3s;
    }

    .routerLink{
        text-decoration: none;
        color: var(--sec-text-color);         
    }    

    .menu-opened{
        width: 300px;        
    }

    .menu-text-hide{
        display: none !important;                
    }


</style>