<script setup>
import {ref, withModifiers} from 'vue'
import Axios from 'axios'
import { profileStore } from '/src/stores/ProfileStore';

const account = ref({ email: null, pwHash: null, name: null })
const submittingEmptyFields = ref(false)
const hasLoginFailed = ref(false)
const profilStore = profileStore()
const hostName = "boola-backend-a71954a87e5d.herokuapp.com"
const submitLogin = () => {
    if (!account.value.email || !account.value.pwHash) {
        submittingEmptyFields.value = true;
        return;
    }
    submittingEmptyFields.value = false;
    hasLoginFailed.value = false;
    let accountToSubmit = null;

    if (!account.value.email.match(`^(([^<>()\\[\\]\\\\.,;:\\s@"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@"]+)*)|(".+"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$`)) {
      alert("Hibás e-mail cím")
      return;
    }
    Axios.get(`https://${hostName}/api/account/${account.value.email}`).then(r => accountToSubmit = r.data)
        .then(() => {

            accountToSubmit.pwHash = account.value.pwHash
            Axios.post(`https://${hostName}/login`, accountToSubmit).then(r => {
                if (r.status != 200) {
                    hasLoginFailed.value = true
                    return;
                }
                const tokens = r.data;
                sessionStorage.setItem("authToken", tokens.access)
                sessionStorage.setItem("refreshToken", tokens.refresh)
                profilStore.$patch({ email: account.value.email, profile: null })
                window.open("/profiles", "_self")
            })

        })
}
</script>

<template> <!--TODO: disable top-,sidebar for login,register-->
    <h1 class="text-center">Belépés</h1>
    <form class="container w-25 h-50 mx-auto text-justify d-flex flex-column justify-content-evenly">
        <label for="email_field">
            E-mail cím:
        </label>
        <input type="email" id="email_field" v-model="account.email">
        <label for="pw_field">
            Jelszó:
        </label>
        <input type="password" id="pw_field" v-model="account.pwHash">
        <div class="d-flex flex-row justify-content-around">
            <button type="button" class="btn btn-primary" @click="submitLogin">Belépés</button>
            <a href="/register" class="btn btn-primary">Regisztráció</a>
        </div>
        <p class="text-danger" v-if="submittingEmptyFields">Minden mezőt ki kell tölteni!</p>
        <p class="text-danger" v-if="loginFailed">Hiba történt a bejelentkezés során!</p>
    </form>
</template>

<style scoped></style>