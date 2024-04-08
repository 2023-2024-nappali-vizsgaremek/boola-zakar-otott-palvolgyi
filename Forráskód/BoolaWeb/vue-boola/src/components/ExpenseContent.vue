<script setup>
import axios, { Axios } from "axios";
import {ref} from "vue";
 const hostName = "boola-backend-a71954a87e5d.herokuapp.com"

const expenseList=ref([{id:null,name:null,amount:null,status:false,category:null,currency:null,date:new Date(),payeeId:null,tags:null,statException:false,note:null,listId:null}])
axios.get(`http://${hostName}/api/expense`,{
  headers:{

    "Authorization":"Bearer " + sessionStorage.getItem("authToken")
  }
}).then(r=>expenseList.value=r.data);
</script>
<template>
  <div class="container">
    <div class="card"> Demo card</div>
    <div v-for="i in expenseList" class="card">
   Név: {{i.name}}<br>
   Kedvezményezett: {{i.payee}}<br>
      Összeg: {{i.amount}} <br>
     Pénznem: {{i.currency}}<br>
      Dátum: {{i.date}}

    </div>
  </div>



</template>
<style setup>

</style>