<script setup>
const authToken = sessionStorage.getItem("authToken");
if (!authToken) window.open("/login", "_self")
</script>

<template>
    <h2>Főoldal</h2>
    <div class="asd"></div>
</template>

<style scoped>
.asd {
    height: 100vh;

}
</style>