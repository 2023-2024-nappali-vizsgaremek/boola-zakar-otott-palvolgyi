<script setup>
    import { ref } from 'vue'
    import Axios from 'axios'
    import {useRouter} from 'vue-router'

    const account = ref({email:null,pwHash:null,name:null})
    const submittingEmptyFields = ref(false)
    const hasLoginFailed = ref(false)
    const hostName = "localhost:8080" //todo: get host name from file
    const submitLogin = () => {
        if(!account.value.email || !account.value.pwHash) {
            submittingEmptyFields.value = true;
            return;
        }
        submittingEmptyFields.value = false;
        hasLoginFailed.value = false;
        let accountToSubmit = null;
        Axios.get(`http://${hostName}/account/${account.value.email}`).then(r => accountToSubmit = r.data)
        .then(() => {
            accountToSubmit.pwHash = account.value.pwHash
            Axios.post(`http://${hostName}/login`,accountToSubmit).then(r => {
                if(r.status != 200){
                    hasLoginFailed.value = true
                    return;
                }
                const tokens = r.data;
                sessionStorage.setItem("authToken",tokens.access)
                sessionStorage.setItem("refreshToken",tokens.refresh)
            })
        
        })
    }
</script>

<template>
    <h1>Belépés</h1>
    <form class="card">
        <label for="email_field">
            E-mail cím:
            <input type="email" id="email_field" v-model="account.email">
        </label>
        <label for="pw_field">
            Jelszó:
            <input type="password" id="pw_field" v-model="account.pwHash">
        </label>
        <button type="button btn btn-primary" @click="submitLogin">Belépés</button>
        <p class="text-danger" v-if="submittingEmptyFields">Minden mezőt ki kell tölteni!</p>
        <p class="text-danger" v-if="loginFailed">Hiba történt a bejelentkezés során!</p>
    </form>
</template>

<style scoped></style>