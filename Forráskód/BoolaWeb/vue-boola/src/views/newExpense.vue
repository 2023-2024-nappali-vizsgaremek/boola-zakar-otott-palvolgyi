<script setup>
import 'bootstrap/dist/css/bootstrap.css'

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
<select name="currency" id="currencys">
  <option value="HUF">HUF</option>
  <option value="USD">USD</option>
  <option value="EUR">EUR</option>
</select>
  </div>
  </div>
</div>
  <div class="container-fluid mx-auto ">
    <div class="row mx-auto">
      <div class="col-lg-4 col-sm-12">
        <h3>Dátum</h3>
        <input id="date" type="date">
      </div>
      <div class="col-lg-4 col-sm-12">
        <h3>Fizetett-e?</h3>

        <input  type="radio" name="true" value="true" ><label>Már igen</label>
        <input  type="radio" name="false" value="false"><label>Még nem</label>

      </div>
      <div class="col-lg-4 col-sm-12">
        <h3>Hozzászólás</h3>
        <input type="text" style="height: 10vh">
      </div>
    </div>
  </div>

<button class="btn btn-primary rounded">Küldés</button>

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

  background: #0080aa;
  border: #191c1e;
margin-top:15%;
  margin-left: 45%!important;
}
button:hover{
  background: #bce9ff;
  color: #006783;

}
</style>