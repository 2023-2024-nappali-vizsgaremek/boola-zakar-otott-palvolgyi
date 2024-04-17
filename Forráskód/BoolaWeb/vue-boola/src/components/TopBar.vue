<script setup>
    import {ref, onMounted} from 'vue' 
    import profileMenu from './profileMenu.vue';
    
    import { useMenuStore} from '/src/stores/MenuStore';
    const MenuStore = useMenuStore();
    
    const root = document.querySelector(':root');
    
    var isDarkTheme = localStorage.getItem("isPreferedThemeDark");
    isDarkTheme = (isDarkTheme === "true");

    onMounted (() => {        
        toggleTheme();
    })

    const toggleThemeValue = () => {
        isDarkTheme = !isDarkTheme;
        localStorage.setItem("isPreferedThemeDark", isDarkTheme);
        toggleTheme();
    }
    
    const toggleTheme = () => {        
        if(isDarkTheme === true)
        {         
            root.style.setProperty("--main-background", "#001f2a");
            root.style.setProperty("--sec-background", "#0080aa");
            root.style.setProperty("--main-text-color", "#bce9ff");
            root.style.setProperty("--sec-text-color", "#bce9ff");
        }
        else
        {            
            root.style.setProperty("--main-background", "#dff4ff");
            root.style.setProperty("--sec-background", "#004d67");
            root.style.setProperty("--main-text-color", "#006783");
            root.style.setProperty("--sec-text-color", "#bce9ff");
        }
    }
            
    

</script>

<template>
    <div class="TopBar-container unselectable">
        <span @click="MenuStore.toggleMainMenu" class="material-symbols-outlined size-32 btn-menu-toggle icon-hover-highlight" :class="`${MenuStore.isMainMenuOpened && 'btn-menu-move'}`">menu</span>
        <h1 class="appName">Boola</h1>
        <div class="profile-theme-container">                    
            <span @click="toggleThemeValue()" class="material-symbols-outlined theme-toggle-btn size-32 icon-hover-highlight">contrast</span>            
            <profileMenu/>                    
        </div>

    </div>
</template>

<style scoped>
    .TopBar-container{
        width: 100lvw;        
        height: 70px;
        
        position: fixed;
        z-index: 999999;
        
        background-color: var(--sec-background);
        transition: background ease-out 0.3s;     
    }

    .appName{
        margin: auto;
        font-size: 30pt;
        color: var(--sec-text-color);
        
        position: relative;
        top: 20%;        
        text-align: center;
    }

    .theme-toggle-btn{        
        margin-right: 1.5rem;
        cursor: pointer;        
    }

    .profile-theme-container{
        position: absolute;        
        right: 1%;
        top: 30%;        
    }

    .btn-menu-toggle{
        position: absolute;
        top: 30%;
        margin-left: 19px;
        
        cursor: pointer;
        z-index: 9999999;        
        transition: all ease-out 0.3s !important;
    }       

    @media screen and (min-width: 1200px) {
        .btn-menu-move{
            transform: translateX(230px);
            transition: all ease-out 0.3s !important;    
        }
    }

</style>