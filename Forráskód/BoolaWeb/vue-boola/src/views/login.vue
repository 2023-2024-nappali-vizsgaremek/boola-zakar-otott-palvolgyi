<script setup>
    import { ref } from 'vue'

    const account = ref({email:null,pwHash:null,name:null})
    const submitLogin = () => {

    }
</script>

<template>
    <h1>Belépés</h1>
    <form class="card">
        <label for="email_field">
            E-mail cím:
            <input type="email" id="email_field">
        </label>
        <label for="pw_field">
            Jelszó:
            <input type="password" id="pw_field">
        </label>
        <button type="button">Belépés</button>
    </form>
</template>

<style scoped></style>