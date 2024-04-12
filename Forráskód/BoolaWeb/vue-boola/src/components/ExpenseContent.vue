<script setup>
import axios, { Axios } from "axios";
import { ref } from "vue";
import { expenseStore } from "../stores/expenseStore";
const hostName = "boola-backend-a71954a87e5d.herokuapp.com"
const authToken = sessionStorage.getItem("authToken")


const expense = ref([])
const expenseList = ref([])
const store = expenseStore()
axios.get(`https://${hostName}/api/expenselist/5e5f526b-acdb-49e9-9252-471385c202ea`, {
  headers: {
    Authorization: `Bearer ${authToken}`
  }
}).then(r => {
  expenseList.value = r.data
  axios.get(`https://${hostName}/api/expense?listId=${expenseList.value.id}`, {
    headers: {
      Authorization: `Bearer ${authToken}`
    }
  }).then(r => expense.value = r.data)
})

function Delete(id) {
  if (!confirm("Biztos törölni szeretné ezt a kiadást?")) return;
  axios.delete(`https://${hostName}/api/expense/${id}`, {
    headers: {
      Authorization: `Bearer ${authToken}`
    }
  }).then(r => {
    if (r.status != 204) {
      alert("Hiba történt a törlés során!")
      return;
    }
    axios.get(`https://${hostName}/api/expense?listId=${expenseList.value.id}`, {
      headers: {
        Authorization: `Bearer ${authToken}`
      }
    }).then(r => expense.value = r.data)
  })
}

function selectExpense(expense) {
  console.log("hello from expense")
  store.$patch({ selectedExpense: expense })
}

</script>
<template>
  <div class="container-fluid">
    <div class="card p-1 m-2" v-for="expenses in expense" @click="selectExpense(expenses)" @focus="selectExpense(expenses)"
      v-bind:tabindex="expense.indexOf(expenses)">
      <div>Név: {{ expenses.name }}</div>
      <div v-if="!expenses.status">Státusz: Fizetendő </div>
      <div v-else>Státusz: Kifizetve</div>
      <div>Dátum: {{ new Date(expenses.date).toDateString() }}</div>
      <button class="btn btn-primary btn-rounded w-25" @click="Delete(expenses.id)">Törlés</button>
    </div>
  </div>



</template>
<style scoped>
.container-fluid {
  height: 90vh;
}

.card {
  z-index: 1;
  background-color: #dff4ff;
  color:#006783;
}

button{
  background-color: #004d67;
  color:#bce9ff;
  align-self: flex-end;
}

button:hover{
  background-color: #dff4ff;
  color:#006783;
}
</style>