<script setup>
    import axios from 'axios';
    import { onMounted, ref } from 'vue';
    
    const expenseList = ref();
    const profiles = ref([]);
</script>

<template>
    <div class="homePage-grid">
        <h1 class="text-center">Jelenlegi Profil Név</h1>

        <div class="info-container">
            <div class="profileInfo-container info-container-card">
                <h2 class="title">Jelenlegi profil</h2>
                <div class="profileInfo">                    
                    <h2 class="profileBalance">123123 HUF</h2>
                    <h2></h2>
                </div>
            </div>

            <div class="spendinginfo-container info-container-card">
                <h2 class="title">Költések</h2>
            </div>
        </div>

        <div class="prevSpending">
            <h2 class="title">Előző kiadások</h2>
        </div>
    </div>
</template>

<style scoped>

    .info-container{
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        justify-content: center;
        gap: 100px;
    }

    .title{
        margin: 5px;   
    }

    .homePage-grid{
        display: grid;
        grid-template-columns: 1fr;
        grid-template-rows: 200px, 600px, 600px;
        grid-column-gap: 0px;
        grid-row-gap: 0px;
        margin: 10px;
    }

    .info-container-card{
        background-color: var(--sec-background);
        height: 600px;
        width: 500px;        
        border-radius: var(--border-radius);
    }

    .profileInfo-container{
        
    }

    .spendinginfo-container{
        
    }

    .prevSpending{
        background-color: var(--sec-background);
        height: 600px;
        width: 800px;        
        border-radius: var(--border-radius);
        display: flex;
        justify-self: center;
        margin: 50px;
    }
</style>