<script setup>
import { ref, defineProps } from 'vue'

const expenseProp = defineProps(["selectedExpense"]).selectedExpense
const expense = ref(expenseProp)
</script>

<template>
    <div id="detailsRoot">
        <h1>{{ expense.name }}</h1>
        <h3>{{ expense.amount }}</h3>
        <div>{{ expense.category }}</div>
        <div v-if="expense.status" class="bg-success">Kifizetve</div>
        <div v-else class="bg-danger">Nincs fizetve</div>
        <div>{{ expense.notes }}</div>
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
    #detailsRoot{
        width: 100vw;
    }
    
}


</style>