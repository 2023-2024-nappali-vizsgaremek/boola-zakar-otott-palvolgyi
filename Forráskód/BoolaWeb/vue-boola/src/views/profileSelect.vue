<script setup>
const authToken = sessionStorage.getItem("authToken");
if(!authToken) window.open("/login","_self")
</script>

<template>

</template>

<style scoped>

</style>