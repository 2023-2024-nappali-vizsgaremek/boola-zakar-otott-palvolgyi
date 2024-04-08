<script setup>
import 'bootstrap/dist/css/bootstrap.css'
import Axios from "axios";
import {ref} from "vue";
import {v4 as uuidv4} from "uuid";

const NewExpense=ref({id:null,name:null,amount:null,status:false,category:null,currency:null,date:new Date(),payeeId:null,tags:null,statException:false,note:null,listId:null});
const hostName = "boola-backend-a71954a87e5d.herokuapp.com"
const currency=ref([]);
const category=ref([]);
let partner=ref("");
const partners=ref([]);
Axios.get(`https://${hostName}/api/partner`).then(r=>partners.value=r.data)
Axios.get(`https://${hostName}/api/category`).then(r=>category.value=r.data)
Axios.get(`https://${hostName}/api/currency`).then(r=>currency.value=r.data)
function Send(){
  const length=partners.value.length
 if(!NewExpense.value.payeeId){

   Axios.post(`https://${hostName}/api/partner`,{
     "id":length,
     "name":partner.value
   }).then(r=>{
     if (r.status!=201){
       alert("Partner Hiba!");
       return;
     }
     NewExpense.value.payeeId=length;

   })

 }
Axios.post(`https://${hostName}/api/expense`,NewExpense).then(r=>{
  if (r.status!=201){
    alert("Hiba!");
  }
  NewExpense.value.id=uuidv4()


}
  )
}
function getPartner() {
  let t = partners.value.find(r => r.name == partner.value)
if(t){
  NewExpense.value.payeeId=t.id;
}
}

</script>

<template>
    <h2 style="text-align: center">Új költség felvétele:</h2>
  <div>
    <h2>Költség neve</h2>
    <input v-model="NewExpense.name" type="text" style="width: 10vw; height: 3vh">
  </div>
  <div class="container-fluid mx-0">
  <div class="row mx-auto">
    
  <div class="col-lg-4 col-sm-12">
    <h3>Kedvezményezett</h3>
    <input v-model="partner" @blur="getPartner" id="text" type="text">
  </div>
    
    <div class="col-lg-4 col-sm-12 ">
      <h3>Összeg</h3>
    <input v-model="NewExpense.amount" type="number">
    </div>
    
      <div class="col-lg-4 col-sm-12">
        <h3>Pénznem</h3>
<select name="currency" id="currencys" >
  <option v-for="i in currency" v-bind:value=i>{{i.code}}</option>

</select>
  </div>



    <div class="row mx-auto">
      <div class="col-lg-4 col-sm-12">
        <h3>Kategória</h3>
        <select name="category" id="categorys">
          <option v-for="categorys in category" v-bind:value=categorys>{{categorys.name}}</option>
        </select>
      </div>

      <div class="col-lg-4 col-sm-12">
        <h3>Dátum</h3>
        <input v-model="NewExpense.date" id="date" type="date">
      </div>
      <div class="col-lg-4 col-sm-12">
        <h3>Címke</h3>

       <input v-model="NewExpense.tags" type="text" style="width: 10vw; height: 3vh">

      </div>

    </div>
  </div>
  <div class="mx-auto text-center" style="padding-top: 2em">
    <h3>Statisztikában megjelenjen-e?</h3>

    <input v-model="NewExpense.statException"  type="radio" name="stat" value="true" ><label>Igen</label>
    <input  v-model="NewExpense.statException"  type="radio" name="stat" value="false"><label>Nem</label>

  </div>
  <div class="mx-auto" style="text-align: center; padding-top: 2em;padding-bottom:2em;">
    <h3>Fizetett-e?</h3>

    <input v-model="NewExpense.status"  type="radio" name="future" value="true" ><label>Már igen</label>
    <input v-model="NewExpense.status" type="radio" name="future" value="false"><label>Még nem</label>

  </div>
  <div style="text-align: center" class="mx-auto">
    Megjegyzés<br>
    <input v-model="NewExpense.note" type="text" style="width: 25vw; height: 15vh">
  </div>
  <button class="btn btn-primary rounded" @click="Send()">Küldés</button>
  </div>




<button class="btn btn-primary rounded" @click="Send()">Küldés</button>

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
margin-top:7%;
  margin-left: 45%!important;
}
button:hover{
  background: #bce9ff;
  color: #006783;

}
</style>