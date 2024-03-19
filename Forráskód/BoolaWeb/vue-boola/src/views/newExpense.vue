<script setup>
import 'bootstrap/dist/css/bootstrap.css'

</script>

<template>
    <h2>Új költség felvétele:</h2>

  <div class="row mx-auto">
  <div class="col-lg-4 col-sm-12">
    <h3>Payee</h3>
    <input type="text">
  </div>
    <div class="col-lg-4 col-sm-12 ">
      <h3>Amount</h3>
    <input type="number">
    </div>
      <div class="col-lg-4 col-sm-12">
        <h3>Currency</h3>
<select name="currency" id="currencys">
  <option value="HUF">HUF</option>
  <option value="USD">USD</option>
  <option value="EUR">EUR</option>
</select>
  </div>
  </div>
</template>

<style scoped>
h2{
  padding: 1em;
  font-size: 40px;
}
div{
  margin-left:1em;
  padding: 0;
}

</style>