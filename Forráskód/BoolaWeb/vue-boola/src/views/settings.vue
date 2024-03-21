<script setup>
import axios from 'axios';
import {ref} from "vue";

const settings=ref({email:null,name:null,password:null})
const nyelv=ref([])
const hostName = "localhost:8080"
let settingsToSubmit=null;
axios.get(`http://${hostName}/api/settings/${settings.value.email}`).then(r=>settingsToSubmit=r.data)
    .then(()=>{
      settingsToSubmit.password=settings.value.password;
    })
axios.post(`http://${hostName}/api/settings`).then(r=>{
  const tokens=r.data
  sessionStorage.setItem("authToken",tokens.accesss)
  sessionStorage.setItem("refreshToken",tokens.refresh())
})
axios.get(`http://${hostName}/api/language`).then(r=>nyelv.value=r.data)

</script>

<template>
    <h1>Beállítások</h1>
  <div class="container-fluid mx-auto">
    <div class="row">
      <div class="col-lg-6">
      <h3>Név:</h3>
      </div>
      <div class="col-lg-6">
        <input class="text" type="text">
    </div>
    </div>
    <div class="row">
      <div class="col-lg-6">
        <h3>E-mail:</h3>
      </div>
      <div class="col-lg-6">
        <input id="email" class="text" type="text">
      </div>
    </div>
    <div class="row">
      <div class="col-lg-6">
<h3>Jelszó</h3>
      </div>
      <div class="col-lg-6">
<input id="password" class="text" type="text">
      </div>
    </div>
    <div class="row">
      <div class="col-lg-6">
<h3>Nyelv: </h3>
      </div>
      <div class="col-lg-6">
<select id="nyelvek" name="nyelvek">
  <option vlaue="Magyar">Magyar</option>
  <option value="Angol">Angol</option>
  <option value="Német">Német</option>
</select>
      </div>
    </div>
    <button id="btn" class="btn btn-primary">Mentés</button>
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
button:hover{
  background: #bce9ff;
  color: #006783;

}
</style>