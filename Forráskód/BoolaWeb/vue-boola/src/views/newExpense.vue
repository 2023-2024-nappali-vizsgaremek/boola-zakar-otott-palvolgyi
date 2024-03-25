<script setup>
import 'bootstrap/dist/css/bootstrap.css'
import Axios from "axios";
import {ref} from "vue";

const NewExpense=ref({payee:null,amount:null,IsPayed:false});
const hostName = "localhost:8080"
const currency=ref([]);
const category=ref([])
Axios.get(`http://${hostName}/api/category`).then(r=>category.value=r.data)
Axios.get(`http://${hostName}/api/currency`).then(r=>currency.value=r.data)


const send=()=>{
  Axios.post(`http://${hostName}/api/expenselist`,NewExpense).then(r=>{
if (r.status!=201){
Alert("Hiba történt! Kérlük próbáld újra egy kicsit később!");
}
})
}

</script>

<template>
    <h2>Új költség felvétele:</h2>
<div class="container-fluid mx-0">
  <div class="row mx-auto">
    
  <div class="col-lg-4 col-sm-12">
    <h3>Kedvezményezett</h3>
    <input id="text" type="text">
  </div>
    
    <div class="col-lg-4 col-sm-12 ">
      <h3>Összeg</h3>
    <input type="number">
    </div>
    
      <div class="col-lg-4 col-sm-12">
        <h3>Pénznem</h3>
<select name="currency" id="currencys" >
  <option v-for="i in currency" v-bind:value=i>{{i.code}}</option>

</select>
  </div>

</div>
  <div class="container-fluid mx-auto ">
    <div class="row mx-auto">
      <div class="col-lg-4 col-sm-12">
        <h3>Kategória</h3>
        <select name="category" id="categorys">
          <option v-for="categorys in category" v-bind:value=categorys>{{categorys.name}}</option>
        </select>
      </div>

      <div class="col-lg-4 col-sm-12">
        <h3>Dátum</h3>
        <input id="date" type="date">
      </div>
      <div class="col-lg-4 col-sm-12">
        <h3>Fizetett-e?</h3>

        <input  type="radio" name="future" value="true" ><label>Már igen</label>
        <input  type="radio" name="future" value="false"><label>Még nem</label>

      </div>

    </div>
  </div>
</div>

<button class="btn btn-primary rounded" onclick="send()">Küldés</button>

</template>

<style scoped>
h2{
  padding: 1em;
  font-size: 40px;
}
#text{
  width:15vw;
}
.container-fluid{
  padding: 1em;
}
label{
  padding:0.5em;
}
#date{
  width:12vw;
}
button{
  width: 8vw;

  background:var(--sec-background);
  border: #191c1e;
margin-top:15%;
  margin-left: 45%!important;
}
button:hover{
  background: #bce9ff;
  color: #006783;

}
</style>