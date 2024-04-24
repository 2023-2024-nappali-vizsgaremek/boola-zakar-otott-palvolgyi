<script setup>
    import {ref} from "vue";
    import { RouterLink, RouterView } from 'vue-router';
    import {useToast} from "vue-toastification";
    const toast=useToast();
    import { useMenuStore} from '/src/stores/MenuStore';    
    const MenuStore = useMenuStore();
    import { profileStore } from "/src/stores/ProfileStore";
    
    const Dob=()=>{
      MenuStore.hideMenus();
        
      toast.info("Viszlát!")
      sessionStorage.clear()
      profileStore().profile=null;
      profileStore().email=null;      
    }
</script>

<template>        
    <span v-if="MenuStore.isProfileMenuDisplayed" @click="MenuStore.toggleProfileMenu" class="material-symbols-outlined profile-icon-btn size-32 icon-hover-highlight">account_circle</span>
    
    <div class="profileMenu-container" :class="`${MenuStore.isProfileMenuOpened && 'profileMenu-container-open'}`">
        <div class="profileLinks-container">            
            <RouterLink class="routerLink" to="/profiles">
                <div class="routerLinkContainer" :class="`${MenuStore.isProfileMenuOpened && 'menu-text-show'}`">
                    <span class="material-symbols-outlined size-32">group</span>
                    <h2>Profilok</h2>              
                </div> 
            </RouterLink>
          <RouterLink class="routerLink" to="/login" @click="Dob()">
            <div class="routerLinkContainer routerLink" :class="`${MenuStore.isProfileMenuOpened && 'menu-text-show'}`">
                <span class="material-symbols-outlined size-32">logout</span>                
                <h2>Kijelentkezés</h2>              
            </div>
          </RouterLink>
        </div>
    </div>
</template>

<style scoped>
    .profileMenu-container{
        position: absolute;
        right: 10%;
        margin-top: 11px;
        display: block;
        z-index: 99999;
        transition: all ease-out 0.3s;
        border-bottom-left-radius: var(--border-radius);
        border-bottom-right-radius: var(--border-radius);
        overflow: hidden;
        
        width: 300px;
        height: 0px;
        background-color: var(--sec-background);
    }

    .profileMenu-container-open{
        transition: all ease-out 0.3s;
        height: 150px !important;        
    }

    .profile-icon-btn {        
        margin-right: 1.5rem;
        cursor: pointer;
        position: relative;    
    }

    .routerLinkContainer{        
        padding: 1rem;
        margin: 3px;        
        border-radius: var(--border-radius);
        text-align: center;
        transition: background-color ease-out 0.3s !important;
    }

    .routerLinkContainer > h2{
        position: relative;
        display: inline;
        height: 0px;
        width: 0px;
        opacity: 0;
        left: 10px;
        transition: all ease-out 0.1s;     
    }

    .routerLinkContainer > span{
        display: inline;              
        position: relative;        
        top: 4px;
        height: 0px;
        width: 0px;
        opacity: 0;        
        transition: all ease-out 0.1s;  
    }
    
    div.routerLinkContainer:hover{
        background-color: var(--main-background);        
        transition: all ease-out 0.3s;
    }

    .routerLink{
        text-decoration: none;
        cursor: pointer;
        color: var(--sec-text-color);        
    }

    .menu-text-show > span, .menu-text-show > h2{
        display: inline !important;
        height: auto !important;
        width: auto !important;
        opacity: 1 !important;
        transition: all ease-out 0.2s !important;
        transition-delay: 0.2s !important;        
    }   
</style>