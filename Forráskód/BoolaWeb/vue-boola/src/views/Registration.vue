<script setup>
import { ref } from 'vue'
import Axios from 'axios'

const account = ref({ email: null, pwHash: null, name: null })
const submittingEmptyFields = ref(false)
const hasRegistrationFailed = ref(false)
const hostName = "https://boola-backend-a71954a87e5d.herokuapp.com/" //TODO: get host name from file
const submitRegistration = () => {
    if (!account.value.email || !account.value.pwHash || !account.value.name) {
        submittingEmptyFields.value = true;
        return;
    }
    submittingEmptyFields.value = false;
    hasRegistrationFailed.value = false;
    let accountToSubmit = account;
    Axios.post(`http://${hostName}/register`, accountToSubmit,{
      headers:{
        "Access-Control-Allow-Origin": "localhost",
}
    }).then(r => {
        if (r.status != 201) {
            hasRegistrationFailed.value = true
            //return;
        }
        //TODO: send user to login page
        //window.open("/login","_self")
    }).catch(_ => {
        hasRegistrationFailed.value = true;
        //window.open("/login","_self") //only here for testing purposes, remove for production
    })
}
</script>

<template>
    <h1 class="text-center">Regisztráció</h1>
    <form class="container w-25 h-50 mx-auto text-justify d-flex flex-column justify-content-evenly">
        <label for="email_field">
            E-mail cím:
        </label>
        <input type="email" id="email_field" v-model="account.email">
        <label for="pw_field">
            Jelszó:
        </label>
        <input type="password" id="pw_field" v-model="account.pwHash">
        <label for="name_field">
            Név:
        </label>
        <input type="text" id="name_field" v-model="account.name">
        <div class="d-flex flex-row justify-content-around">
            <button type="button" class="btn btn-primary" @click="submitRegistration">Regisztráció</button>
            <a href="/login" class="btn btn-primary">Bejelenkezés</a>
        </div>
        <p class="text-danger" v-if="submittingEmptyFields">Minden mezőt ki kell tölteni!</p>
        <p class="text-danger" v-if="hasRegistrationFailed">Hiba történt a Regisztráció során!</p>
    </form>
</template>

<style scoped></style>