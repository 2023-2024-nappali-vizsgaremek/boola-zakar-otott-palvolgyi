<script setup>
import axios from "axios";
import { ref } from "vue";
import { expenseStore } from "../stores/expenseStore";
import { profileStore } from "../stores/ProfileStore";
import {useToast} from "vue-toastification";
const toast=useToast()
const hostName = "boola-backend-a71954a87e5d.herokuapp.com"
const authToken = sessionStorage.getItem("authToken")
const filtered=ref([])
const beker=ref(null);

axios.defaults.headers.get["Cache-Control"] = "max-age=604800,public"
const expense = ref([])
const store = expenseStore()
store.$reset();
const expenseListId = profileStore().profile.expenseListId
axios.get(`https://${hostName}/api/expense?listId=${expenseListId}`, {
  headers: {
    Authorization: `Bearer ${authToken}`,
    "Cache-Control":"no-cache"
  }
}).then(r => expense.value = r.data)
function filter() {
  filtered.value = expense.value.filter(r => r.name.toLowerCase().startsWith(beker.value.toLowerCase()))
    console.log(filtered.value.length)
  
}

function Delete(id) {
  if (!confirm("Biztos törölni szeretné ezt a kiadást?")) return;
  axios.delete(`https://${hostName}/api/expense/${id}`, {
    headers: {
      Authorization: `Bearer ${authToken}`
    }
  }).then(r => {
    if (r.status != 204) {
      toast.error("Hiba történt a törlés során!")
      return;
    }else{
      toast.success("Sikeres törlés")
    }
    axios.get(`https://${hostName}/api/expense?listId=${expenseListId}`, {
      headers: {
        Authorization: `Bearer ${authToken}`,
        "Cache-Control":"no-store"
      }
    }).then(r => expense.value = r.data)
    store.$reset()
  })
  store.$reset()


}

function selectExpense(expense) {
  store.$patch({ selectedExpense: expense })
}

</script>
<template>
  <div class="container-fluid">
    <input type="text" v-model="beker" @input="filter()" placeholder="🔍 Keresés" style="width: 50%; height: 30px;margin-left: 25vw; padding-bottom: 1em; ">
  
    <div v-if="beker!=null" class="card p-1 m-2" v-for="expenses in filtered" @click="selectExpense(expenses)"
         @focus="selectExpense(expenses)" v-bind:tabindex="filtered.indexOf(expenses)">
      <div>Név: {{ expenses.name }}</div>
      <div v-if="!expenses.status">Státusz: Fizetendő </div>
      <div v-else>Státusz: Kifizetve</div>
      <div>Dátum: {{ new Date(expenses.date).toDateString() }}</div>
      <button class="btn btn-primary btn-rounded w-25" @click="Delete(expenses.id)">Törlés</button>
    </div>
    <div v-else class="card p-1 m-2" v-for="expenses in expense" @click="selectExpense(expenses)"
      @focus="selectExpense(expenses)" v-bind:tabindex="expense.indexOf(expenses)">
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
  color: #006783;
}

button {
  background-color: #004d67;
  color: #bce9ff;
  align-self: flex-end;
}

button:hover {
  background-color: #dff4ff;
  color: #006783;
}
</style>