<script setup>
    import axios from 'axios';
    import { onMounted, ref } from 'vue';
    import {profileStore} from "/src/stores/ProfileStore"
    import { useRouter } from 'vue-router';
    const router = useRouter();

    const expenseList = ref([]);
    const expenseList2=ref([]);
    const profiles = profileStore().profile
    const authToken = sessionStorage.getItem("authToken");
    const expenses=ref([])
    const language=ref([])
    const currency=ref([])

    if (!authToken){
    window.open("/login","_self")
    }
    else if (profiles==null) 
    {
    router.push("/profiles")
}
    const hostName = "boola-backend-a71954a87e5d.herokuapp.com"
    axios.get(`https://${hostName}/api/expenselist/${profiles.expenseListId}`,{
      headers:{
        Authorization: `Bearer ${authToken}`,
        "Cache-Control": "no-cache"
      }
    }).then(r=>{
      expenseList.value = r.data
      axios.get(`https://${hostName}/api/currency/${expenseList.value.currencyCode}`,{
        headers:{
          "Cache-Control":"max-age=604800,public"
        }
      }).then(r=>currency.value=r.data)
    })

    axios.get(`https://${hostName}/api/expense?listId=${profileStore().profile.expenseListId}`, {
      headers: {
        Authorization: `Bearer ${authToken}`,
        "Cache-Control":"no-cache"
      }
    }).then(r => {
      expenseList2.value = r.data
expenses.value=expenseList2.value.slice(-2).reverse()

    })
    axios.get(`https://${hostName}/api/language/${profiles.languageId}`,{
    headers: {

      "Cache-Control":"max-age=604800,public"
    }}).then(r=>language.value=r.data)


</script>

<template>
    <div class="homePage-grid">
      <h1 class="text-center" style="margin-top: 0.5em">Jelenleg a <i><strong>{{profiles.name}}</strong></i> nevű profilban vagy.</h1>
      <h1 class="text-center" style="margin-top: 0.5em;margin-bottom:1em; ">Egyenleged: <i><strong>{{expenseList.balance}} {{expenseList.currencyCode}}</strong></i></h1>
        <div class="info-container">
            <div class="activeProfileInfo">
              <h2 class="text-center" style="font-size: xxx-large ">Legutóbbi költségek</h2>

              <div class="card" v-for="pay in expenses">
                <div>Név: {{ pay.name }}</div>
                <div v-if="!pay.status">Státusz: Fizetendő </div>
                <div v-else>Státusz: Kifizetve</div>
                <div>Dátum: {{ new Date(pay.date).toDateString() }}</div>
              </div>
            </div>


        </div>
      <div class="info-container" style="text-align: center">
        <div class="prevSpending" style="text-align: center;">
          <div style="margin-top:5em">
            <h2 class="text-center" style="font-size: xxx-large">Profil adatok</h2>
            <div class="text-center meret" >Név: <br>{{profileStore().profile.name}}</div><br>
          <div class="text-center meret">Pénznem:<br> {{currency}}</div><br>
          <div class="text-center meret">Nyelv:<br> {{language.name}}</div><br>
          </div>
        </div>
      </div>
    </div>>
</template>

<style scoped>

    .info-container{
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        justify-content: center;
        gap: 100px;
      width: 100%;

    }

    .title{
        margin: 5px;   
    }

    .homePage-grid{
        display: grid;
        grid-template-columns: 1fr;
        grid-template-rows: 200px, 600px, 600px;
        grid-column-gap: 0px;
        grid-row-gap: 0px;
        margin: 10px;
    }

    .info-container-card{
        background-color: var(--sec-background);
        height: 600px;
        width: 500px;        
        border-radius: var(--border-radius);
      padding: 3em 1em 1em;
    }

    .profileInfo-container{
        
    }

    .spendinginfo-container{
        
    }

    .prevSpending{
        background-color: var(--sec-background);

        border-radius: var(--border-radius);
        display: flex;
        justify-self: center;
        margin: 50px;


    }
   .meret {
     font-size: xxx-large;

   }

    .card {
      z-index: 1;
      background-color: #dff4ff;
      color: #006783;
      margin-top: 2em;
      padding: 1em;
    }
</style>