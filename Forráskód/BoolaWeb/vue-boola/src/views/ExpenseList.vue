<script setup>
import axios, { Axios } from "axios";
import { ref } from "vue";
import ExpenseContent from "../components/ExpenseContent.vue";
import ExpenseDetails from "../components/ExpenseDetails.vue"

const authToken = sessionStorage.getItem("authToken");
if (!authToken) window.open("/login", "_self")


</script>
<template>
    <h1>Költségek</h1>
    <div class="container-fluid" id="expense-body">
        <ExpenseContent />
        <ExpenseDetails></ExpenseDetails>
    </div>
</template>

<style setup>
#expense-body {
    display: flex;
    height: 95vh;
}
</style>