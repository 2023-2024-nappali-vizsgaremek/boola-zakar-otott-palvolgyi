<script setup>
import axios from "axios";
import { ref } from "vue";
import { expenseStore } from "../stores/expenseStore";
import { profileStore } from "../stores/ProfileStore";
import {useToast} from "vue-toastification";
const toast=useToast()
const hostName = "boola-backend-a71954a87e5d.herokuapp.com"
const authToken = sessionStorage.getItem("authToken")



axios.defaults.headers.get["Cache-Control"] = "max-age=604800,public"
const expense = ref([])
const store = expenseStore()
store.$reset();
const expenseListId = profileStore().profile.expenseListId
axios.get(`https://${hostName}/api/expense?listId=${expenseListId}`, {
  headers: {
    Authorization: `Bearer ${authToken}`,
    "Cache-Control":"max-age=60"
  }
}).then(r => expense.value = r.data)

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
    <div class="card p-1 m-2" v-for="expenses in expense" @click="selectExpense(expenses)"
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