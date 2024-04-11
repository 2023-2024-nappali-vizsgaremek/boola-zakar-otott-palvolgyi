<script setup>
import axios, { Axios } from "axios";
import {ref} from "vue";
 const hostName = "boola-backend-a71954a87e5d.herokuapp.com"
 const authToken = sessionStorage.getItem("authToken")


const expense=ref([])
const expenseList=ref([])
axios.get(`https://${hostName}/api/expenseList/87e02966-af04-4232-9606-9d30ce9f7d2f`,{
  headers:{
    Authorization: `Bearer ${authToken}`
  }
})
axios.get(`https://${hostName}/api/expense`,expenseList.value,{
  headers:{
    Authorization: `Bearer ${authToken}`

    
  }
}).then(r=>expense.value=r.data);
</script>
<template>
  <div class="container">
    <div class="card"> Demo card</div>
    <div v-for="i in expense" class="card">
   Név: {{i.name}}<br>
   Kedvezményezett: {{i.payee}}<br>
      Összeg: {{i.amount}} <br>
      Dátum: {{i.date}}

    </div>
  </div>



</template>
<style setup>

</style>