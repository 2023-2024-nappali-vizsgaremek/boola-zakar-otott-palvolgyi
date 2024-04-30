<script setup>
import { ref } from 'vue'
import Axios from 'axios'
import {useToast} from "vue-toastification";
const toast=useToast()
import {useRouter} from 'vue-router';
const router = useRouter();

const account = ref({ email: null, pwHash: null, name: null })
const submittingEmptyFields = ref(false)
const hasRegistrationFailed = ref(false)
const hostName = "boola-backend-a71954a87e5d.herokuapp.com" //TODO: get host name from file
const submitRegistration = () => {
    if (!account.value.email || !account.value.pwHash || !account.value.name) {
        submittingEmptyFields.value = true;
        return;
    }
    submittingEmptyFields.value = false;
    hasRegistrationFailed.value = false;
    let accountToSubmit = account;
  if (!account.value.email.match(`^(([^<>()\\[\\]\\\\.,;:\\s@"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@"]+)*)|(".+"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$`)) {

    toast.error("Hibás e-mail cím")

    return;
  }
    Axios.post(`https://${hostName}/register`, accountToSubmit.value).then(r => {
        if (r.status != 201) {
            hasRegistrationFailed.value = true
            return;
        }
        toast.success("Sikeres Regisztráció")
       setTimeout(()=> router.push("/login"),2000)
    }).catch(_ => {
        if(hasRegistrationFailed.value == true){
          toast.error("Hiba történt")
        }

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
        <div class="d-flex flex-row justify-content-around m-3">
            <button type="button" class="btn btn-primary" @click="submitRegistration">Regisztráció</button>
            <a href="/login" class="btn btn-primary">Bejelenkezés</a>
        </div>
        <p class="text-danger" v-if="submittingEmptyFields">Minden mezőt ki kell tölteni!</p>
        <p class="text-danger" v-if="hasRegistrationFailed">Hiba történt a Regisztráció során!</p>
    </form>
</template>

<style scoped></style>