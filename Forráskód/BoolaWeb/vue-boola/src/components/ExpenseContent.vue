<script setup>
import axios, { Axios } from "axios";
import {ref} from "vue";
 const hostName = "boola-backend-a71954a87e5d.herokuapp.com"
 const authToken = sessionStorage.getItem("authToken")


const expense=ref([])
const expenseList=ref([])
axios.get(`https://${hostName}/api/expenselist/87e02966-af04-4232-9606-9d30ce9f7d2f`,{
  headers:{
    Authorization: `Bearer ${authToken}`
  }
}).then(r => {
  expenseList.value = r.data
  axios.get(`https://${hostName}/api/expense?listId=${expenseList.value.id}`,{
    headers:{
      Authorization: `Bearer ${authToken}`
    }
  }).then(r=>expense.value=r.data)
})

</script>
<template>
<div class="container-fluid">
  <div class="card" v-for="expenses in expense" >
    <div>Név: {{expenses.name}}</div>
    <div v-if="!expenses.status">Státusz: Fizetendő </div>
    <div v-else>Státusz: Kifizetve</div>
    <div>Dátum: {{new Date(expenses.date).toDateString()}}</div>
  </div>
</div>




</template>
<style setup>

</style>