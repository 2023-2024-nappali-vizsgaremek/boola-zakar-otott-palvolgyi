<script setup>
import axios from 'axios';
import {ref} from 'vue'
import { profileStore } from '../stores/ProfileStore';
import {useToast} from "vue-toastification";
const toast=useToast()
const hostName = "boola-backend-a71954a87e5d.herokuapp.com"
const authToken = sessionStorage.getItem("authToken")
const profilStore = profileStore().profile
const currency = ref(null)
const expenselist=ref([])
const balance=ref(0.0)
import { useRouter } from 'vue-router';
const router = useRouter();

if (!authToken) {
  router.push("/login")
}
else if (profilStore==null){
  router.push("/profiles")
}

 axios.get(`https://${hostName}/api/currency`)
    .then(r => currency.value = r.data)
axios.get(`https://${hostName}/api/expenselist/${profilStore.expenseListId}`,{
headers:{
    Authorization: `Bearer ${authToken}`,
    "Cache-Control": "no-store"
}
}).then(r=>{
    expenselist.value=r.data;
    
})
const Save=()=>{
    axios.put(`https://${hostName}/api/expenselist/${profilStore.expenseListId}`, {
                    id:profilStore.expenseListId,
                    balance:expenselist.value.balance+balance.value,
                    currencyCode: expenselist.value.currencyCode
                },{
                    headers:{
                        Authorization: `Bearer ${authToken}`,
                    } 
    })
  toast.success("Sikeres egyenleg feltöltés!")
}
</script>
<template>
    <div class="container">
<div class="card mx-auto">
<h2>Töltsd fel egyenleged!</h2>
<div><input v-model="balance" type="number"> {{ expenselist.currencyCode }}</div>
<button class="btn btn-primary btn rounded" @click="Save()">Mentés</button>
</div>
    </div>
</template>
<style scoped>

.card {
    width: calc(100vw / 3);
  min-width: 300px;
    margin-top:2em;
    padding: 1em;
    background-color: #004d67;
    color: #bce9ff;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
}

.card>*{
    margin:0.5em;
}

h2 {
    text-align: center;
}

button {
    background-color: #dff4ff;
    color: #006783;
}

button:hover {
    background-color: #004d67;
    color:#bce9ff;
}
</style>
