<script setup>
import axios, { Axios } from "axios";
import {ref} from "vue";
import expense from "@/components/ExpenseContent.vue"

const authToken = sessionStorage.getItem("authToken");
if(!authToken) window.open("/login","_self")


</script>
<template>
<h1>Költségek</h1>
<ExpenseContent></ExpenseContent>
</template>
<style setup>
</style>