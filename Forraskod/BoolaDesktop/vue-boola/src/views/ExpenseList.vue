<script setup>
import axios, { Axios } from "axios";
import { ref } from "vue";
import {profileStore} from "../stores/ProfileStore.js";
import ExpenseContent from "../components/ExpenseContent.vue";
import ExpenseDetails from "../components/ExpenseDetails.vue"

const authToken = sessionStorage.getItem("authToken");

if (!authToken){
router.push("/login")
}
else if (profileStore().profile==null){
router.push("/profiles")
}



</script>
<template>
    <h1>Költségek</h1>
    <div class="container-fluid" id="expense-body">

        <ExpenseContent />
        <ExpenseDetails></ExpenseDetails>
    </div>
</template>

<style scoped>
#expense-body {
    display: flex;

    height: 95vh;

}
@media only screen and (max-width: 768px) {
  #expense-body {
flex-direction: column;
  min-height: 90vh;


  }
}
</style>