<script setup>
    import {ref, onMounted} from 'vue'
    
    const root = document.querySelector(':root');
    var isDarkTheme = localStorage.getItem("isPreferedThemeDark");

    onMounted (() => {        
        toggleTheme();
    })

    const toggleThemeValue = () => {
        isDarkTheme = !isDarkTheme;
        localStorage.setItem("isPreferedThemeDark", isDarkTheme);
        toggleTheme();
    }
    
    const toggleTheme = () => {        
        if(isDarkTheme)
        {        
            console.log("dark", isDarkTheme);
            root.style.setProperty("--main-background", "#001f2a");
            root.style.setProperty("--sec-background", "#0080aa");
            root.style.setProperty("--main-text-color", "#bce9ff");
            root.style.setProperty("--sec-text-color", "#bce9ff");
        }
        else
        {
            console.log("light", isDarkTheme);
            root.style.setProperty("--main-background", "#dff4ff");
            root.style.setProperty("--sec-background", "#004d67");
            root.style.setProperty("--main-text-color", "#006783");
            root.style.setProperty("--sec-text-color", "#bce9ff");
        }
    }
</script>

<template>
    <div class="header-container">
        <h1 class="appName">Boola</h1>
        <div class="profile-theme-container">                    
            <span @click="toggleThemeValue()" class="material-symbols-outlined theme-toggle-btn size-32">contrast</span>
            <span class="material-symbols-outlined profile-icon-btn size-32">account_circle</span>
        </div>
    </div>
</template>

<style scoped>
    .header-container{
        width: 100lvw;
        height: 8lvh;
        min-height: 70px;
        overflow: hidden;
        position: sticky;
        
        background-color: var(--sec-background);
        transition: background 0.5s;        
    }

    .appName{
        margin: auto;
        font-size: 30pt;
        color: var(--sec-text-color);
        
        position: relative;
        top: 20%;        
        text-align: center;
    }

    .profile-icon-btn{        
        margin-right: 1.5rem;
        cursor: pointer;
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

    span.size-32{
        font-size: 32px;
        font-variation-settings: 'OPSZ' 32;
        color: var(--sec-text-color);        
    }
</style>