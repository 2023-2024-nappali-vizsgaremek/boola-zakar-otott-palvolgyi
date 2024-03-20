<script setup>

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
        <input class="text" type="text">
      </div>
    </div>
    <div class="row">
      <div class="col-lg-6">
<h3>Jelszó</h3>
      </div>
      <div class="col-lg-6">
<input class="text" type="text">
      </div>
    </div>
    <div class="row">
      <div class="col-lg-6">
<h3>Nyelv: </h3>
      </div>
      <div class="col-lg-6">
<select name="nyelvek">
  <option vlaue="Magyar">Magyar</option>
  <option value="Angol">Angol</option>
  <option value="Német">Német</option>
</select>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-6">
        <h3>Személyre szabott e-mailek:</h3>
      </div>
      <div class="col-lg-6">
<input type="radio" value="nem"><label class="radio">Nem kérek</label>
        <input   type="radio" value="igen"><label class="radio">Igen kérek</label>
      </div>
    </div>
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
  margin-top:20vh;

}
</style>