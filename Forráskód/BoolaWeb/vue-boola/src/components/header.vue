<script setup>

</script>

<template>
    <div class="header-container">
        <h1 class="appName">Boola</h1>
    </div>
</template>

<style scoped>
    .header-container{
        width: 100%;
        height: 8lvh;
        min-height: 70px;
        overflow: hidden;
        position: fixed;
        
        background-color: var(--sec-light-background);
    }

    .appName{
        margin: auto;
        font-size: 30pt;
        color: var(--sec-light-text-color);

        position: relative;
        top: 20%;
        left: 50%;
        transform: translateX(-55px);
    }

</style>