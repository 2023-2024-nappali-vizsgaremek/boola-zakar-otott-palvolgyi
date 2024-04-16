<script setup>
import axios from 'axios';
import {ref} from 'vue'
import { profileStore } from '../stores/ProfileStore';
const hostName = "boola-backend-a71954a87e5d.herokuapp.com"
const authToken = sessionStorage.getItem("authToken")
const profilStore = profileStore().profile
const currency = ref(null)
const expenselist=ref([])
const balance=ref(0.0)
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
}
</script>
<template>
    <div class="container">
<div class="card">
<h2>Töltsd fel egyenleged!</h2>
<input v-model="balance" type="number">
<button class="btn btn-primary btn rounded" @click="Save()">Mentés</button>
</div>
    </div>
</template>
<style scoped>

</style>
