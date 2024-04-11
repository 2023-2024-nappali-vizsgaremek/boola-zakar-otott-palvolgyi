<script setup>
import { ref } from 'vue'
import { expenseStore } from '../stores/expenseStore';
import Axios from 'axios'

const store = expenseStore()
const expense = ref(null)
expense.value = store.selectedExpense
store.$subscribe((m, s) => {
    expense.value = store.selectedExpense
})

function closeWindow() {
    expense.value = null
}
</script>

<template>
    <div id="detailsRoot" v-if="expense != null" class="card">
        <button class="material-symbols-outlined size-32 w-25 m-1 bg-light" @click="closeWindow">close</button>
        <h1>{{ expense.name }}</h1>
        <h3>{{ expense.amount }}</h3>
        <div>{{ expense.categoryId }} //TODO:ide majd a kategória nevét kell írni</div>
        <div v-if="expense.status" class="bg-success text-light">Kifizetve</div>
        <div v-else class="bg-danger text-light">Nincs fizetve</div>
        <div>{{ new Date(expense.date).toLocaleDateString() }}</div>
        <div class="d-flex flex-row w-25 justify-content-around">
            <div class="mx-2">Címkék:</div>
            <div v-for="tag in expense.tags.split(';') " class="card p-2">{{ tag }}</div>
        </div>
        <div>Megjegyzés:<br>{{ expense.note }}</div>
    </div>

</template>

<style scoped>
#detailsRoot {
    width: 25vw;
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
</style>