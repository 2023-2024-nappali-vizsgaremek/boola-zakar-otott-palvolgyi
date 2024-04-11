<script setup>
import axios, { Axios } from 'axios';
import {ref,defineProps} from "vue";

const settings=ref({email:null,pwHash:null,name:null})
const nyelv=ref([])
const profile=ref([])
const authToken = sessionStorage.getItem("authToken")
if(!authToken) window.open("/login","_self")
const hostName = "boola-backend-a71954a87e5d.herokuapp.com"
let settingsToSubmit=null;
axios.get(`https://${hostName}/api/profile/1a8b8ad4-89eb-4ff8-883a-854bca6bfb19`,{
  headers : {
    Authorization: `Bearer ${authToken}`
  }
}).then(r=>{
  profile.value=r.data
  axios.get(`https://${hostName}/api/account/${profile.value.accountEmail}`,{
  headers : {
    Authorization: `Bearer ${authToken}`
  }
}).then(r=>settings.value=r.data)
})
function Save(){
axios.put(`https://${hostName}/api/account/${profile.value.accountEmail}`,settings.value,{
  headers : {
    Authorization: `Bearer ${authToken}`
  }
}).then(r=>{
if(r.status!=200){
  alert("Hiba történt!")
}
})
}
axios.get(`https://${hostName}/api/language`,{
  headers : {
    Authorization: `Bearer ${authToken}`
  }
}).then(r=>nyelv.value=r.data)

</script>

<template>
    <h1>Beállítások</h1>
  <div class="container-fluid mx-auto">
    <div class="row">
      <div class="col-lg-6">
      <h3>Név:</h3>
      </div>
      <div class="col-lg-6">
        <input v-model="settings.name" class="text" type="text">
    </div>
    </div>
    <div class="row">
      <div class="col-lg-6">
        <h3>E-mail:</h3>
      </div>
      <div class="col-lg-6">
        <input v-model="settings.email" id="email" class="text" type="text">
      </div>
    </div>
    <div class="row">
      <div class="col-lg-6">
<h3>Jelszó</h3>
      </div>
      <div class="col-lg-6">
<input v-model="settings.pwHash" id="password" class="text" type="password">
      </div>
    </div>
    <div class="row">
      <div class="col-lg-6">
<h3>Nyelv: </h3>
      </div>
      <div class="col-lg-6">
<select id="nyelvek" name="nyelvek">
  <option v-for="nyelvs in nyelv" v-bind:vlaue="profile.languagecode">{{ nyelvs.name }}</option>

</select>
      </div>
    </div>
    <button id="btn" @click="Save()" class="btn btn-primary">Mentés</button>
    <div class="card">
      Ha bármilyen kérdése vagy észrevétele van, ne habozzon kapcsolatba lépni velünk! <br>A Boola Pénzügyi Alkalmazás ügyfélszolgálata mindig készen áll, hogy segítsen.<br><br>E-mail: info@boolaapp.com<br><br>Telefonszám: +36 1 234 5678<br><br>Köszönjük, hogy a Boola alkalmazást választotta pénzügyi szükségletei kielégítésére. <br>Tartsa velünk az úton a gazdagság és a pénzügyi függetlenség felé!
    </div>
  </div>
  
</template>

<style scoped>
h1{
  padding-bottom: 1em;
}
.text{
  width:11vw
}
.radio{
padding: 0.5em;
}
.card{
  text-align: center;
  background: #bce9ff;
margin-left: 20vw ;
  margin-right: 20vW ;
  padding: 1em;
  margin-top:10vh;

}
button{
  width: 8vw;

  background: #0080aa;
  border: #191c1e;
  margin-top:5%;
  margin-left: 45%!important;

}
button:hover,button:active,button:visited{
  background: #bce9ff;
  color: #006783;

}
</style>