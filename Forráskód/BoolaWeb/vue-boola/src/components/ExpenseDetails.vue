<script setup>
import { ref } from 'vue'
import { expenseStore } from '../stores/expenseStore';
import { profileStore } from '../stores/ProfileStore';
import Axios from 'axios'

const hostName = "boola-backend-a71954a87e5d.herokuapp.com"
const authToken = sessionStorage.getItem("authToken")
const store = expenseStore()
const profilStore = profileStore().profile
const expense = ref(null)
expense.value = store.selectedExpense
const currency = ref(null)
const categoryName = ref("unknown")
Axios.get(`https://${hostName}/api/expenselist/${profilStore.expenseListId}`, {
    headers: {
        Authorization: `Bearer ${authToken}`
    }
}).then(r => {
    currency.value = r.data.currencyCode
})
store.$subscribe(() => {
    expense.value = store.selectedExpense
    Axios.get(`https://${hostName}/api/category/${expense.value.categoryId}`).then(r => {
        categoryName.value = r.data
    })
        .catch(() => categoryName.value = "unknown")
})

function closeWindow() {
    expense.value = null
}
</script>

<template>
    <div id="detailsRoot" v-if="expense != null" class="card">
        <button class="material-symbols-outlined size-32 m-1" @click="closeWindow">close</button>
        <h1>{{ expense.name }}</h1>
        <h3>{{ expense.amount }} {{ currency }}</h3>
        <div>{{ categoryName }}</div>
        <div v-if="expense.status" class="text-success">Kifizetve</div>
        <div v-else class="text-danger">Nincs fizetve</div>
        <div>{{ new Date(expense.date).toLocaleDateString() }}</div>
        <div class="d-flex flex-rowjustify-content-around">
            <div class="mx-2 align-self-center">Címkék:</div>
            <div v-for="tag in expense.tags.split(';') " class="card p-2 m-1">{{ tag }}</div>
        </div>
        <div>Megjegyzés:<br>{{ expense.note }}</div>
    </div>

</template>

<style scoped>
#detailsRoot {
    width: 25vw;
    background-color: #dff4ff;
    color: #006783;
}

@media screen and (min-width: 768px) and (max-width: 1200px) {
    #detailsRoot {
        width: 50vw;
    }
}

@media screen and (max-width:768px) {
    #detailsRoot {
        width: 100vw;
    }

}

button {
    border-radius: 5px;
    width: 2em;
    background-color: #004d67;
    color: #bce9ff;
}

h1,
h3,
div {
    margin: 0.5em;
    margin-top: 0;
}
</style>