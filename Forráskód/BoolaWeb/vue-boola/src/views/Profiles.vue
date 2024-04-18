<script setup>
import axios from 'axios';
import {onMounted, ref} from 'vue';
import {v4 as uuidv4} from "uuid";
import {profileStore} from "/src/stores/ProfileStore"

const authToken = sessionStorage.getItem("authToken");
if (!authToken) window.open("/login", "_self")

axios.defaults.headers.get["Cache-Control"] = "max-age=604800"
const store = profileStore()
const hostName = "boola-backend-a71954a87e5d.herokuapp.com"
const profileCreation = ref(false);


const newProfile = ref({
    id: uuidv4(),
    name: null,
    isBusiness: false,
    languageId: null,
    expenseListId: null,
    accountEmail: store.email

})
const profileCreationToggle = () => {
  profileCreation.value = !profileCreation.value;
}

const profiles = ref();
axios.get(`http://${hostName}/api/profile`, {
    headers: {
        Authorization: `Bearer ${authToken}`,
        "Cache-Control": "no-cache"
    }


})
    .then(r => profiles.value = r.data);

/*val id:UUID, val name:String, val isBusiness:Boolean,
               val languageId:String, @Serializable(with = UUIDSerializer::class) val expenseListId:UUID?,
               val accountEmail:String)*/
               const currencies = ref([])
const selectedCurrency = ref(null)
axios.get(`https://${hostName}/api/currency`)
    .then(r => currencies.value = r.data)

const languages = ref([]);
const selectedLanguage = ref(null)
axios.get(`http://${hostName}/api/language`)
    .then(r => languages.value = r.data);

const createNewProfile = () => {
  newProfile.value.languageId = selectedLanguage.value;
  axios.post(`http://${hostName}/api/profile`, newProfile.value, {
    headers: {
      Authorization: `Bearer ${authToken}`
    }
  })
      .then(r => {
        if (r.status != 201) {
          alert("Hiba történt!")
          return;
        }
    })
        .then(r => {
            if (r.status != 201) {
                alert("Hiba történt!")
                return;
            }
            const profileAddress = r.data
            axios.get(`https://${hostName}/${profileAddress}`, {
                headers: {
                    Authorization: `Bearer ${authToken}`,
                    "Cache-Control": "no-store"
                }
            }).then(p => {
                if (p.status != 200) {
                    alert("Lekérdezési hiba történt!")
                    return;
                }
                axios.put(`https://${hostName}/api/expenselist/${p.data.expenseListId}`, {
                    id: p.data.expenseListId,
                    balance: 0,
                    currencyCode: selectedCurrency.value
                },{
                    headers:{
                        Authorization: `Bearer ${authToken}`,
                        "Cache-Control": "no-store"
                    }
                })
                store.$patch({
                    email: store.email,
                    profile: p.data
                })
            })
        });

    newProfile.value = {
        id: uuidv4(),
        name: null,
        isBusiness: null,
        languageId: null,
        expenseListId: null,
        accountEmail: store.email
    };
}

const SelectProfile = (profile) => {
    store.$patch({
        email: store.email,
        profile: profile
    })
    window.open("/", "_self")

}

const DeleteProfile = (id) => {
    if (!confirm("Biztosan törli ezt a profilt? Minden benne levő költés el fog veszni!")) return;
    axios.delete(`https://${hostName}/api/profile/${id}`,{
      headers: {
        Authorization: `Bearer ${authToken}`
      }
    }).then(r => {
        if (r.status != 204) alert("Hiba történt a törlés során!")
        else window.open("/profiles","_self")

      })
} 
</script>

<template>
    <h1 class="text-center">Profilok</h1>
    <div class="profiles-container" v-if="!profileCreation">
        <div class="profile-container" v-for="p in profiles">
            <h2>{{ p.name }}</h2>

            <div>{{ p.accountEmail }}</div>
            <button class="btn btn-rounded btn-success m-2" @click="SelectProfile(p)">Kiválasztás</button>
            <button class="btn btn-rounded btn-danger" @click="DeleteProfile(p.id)">Törlés</button>

        </div>
        <div @click="profileCreationToggle" class="new-profile profile-container"><span
                class="material-symbols-outlined size-32">add</span></div>
    </div>
    <div v-if="profileCreation" class="profileCreation-container">
        <div class="profileCreationForm">
            <h2>Név: </h2>
            <input type="text" v-model="newProfile.name">
        </div>
        <div class="profileCreationForm">
            <h2>Munkai-e: </h2>
            <input type="checkbox" v-model="newProfile.isBusiness">
        </div>
        <div class="profileCreationForm">
            <h2>Nyelv: </h2>

            <select v-model="selectedLanguage">
                <option v-for="language in languages" v-bind:value="language.code">{{ language.name }}</option>
            </select>
        </div>
        <div class="profileCreationForm">
            <h2>Pénznem: </h2>
            <select v-model="selectedCurrency">
                <option v-for="currency in currencies" v-bind:value="currency.code">{{ currency.name }}</option>

            </select>
        </div>
        <div class="profileCreationForm">
            <button @click="profileCreationToggle">Vissza</button>
            <button @click="createNewProfile">Létrehozás</button>
        </div>

    </div>

</template>

<style scoped>
.profileCreation-container {
  background-color: var(--sec-background);
  width: 400px;
  height: auto;
  border-radius: var(--border-radius);
  padding: 5px;

  position: absolute;
  top: 20%;
  right: 50%;
  transform: translateX(200px);
}

.profileCreationForm > * {
  display: inline;
  margin: 5px;
}

.new-profile {
  text-align: center;
  align-items: center;
}

.profiles-container {
    background-color: var(--sec-background);
    width: 400px;
    height: auto;
    max-height: 75vh;
    border-radius: var(--border-radius);
    overflow: auto;
    position: absolute;
    top: 20%;
    right: 50%;
    transform: translateX(200px);

}

.profile-container {
  padding: 5px;
  margin: 5px;
  border: 2px solid var(--main-background);
  border-radius: var(--border-radius);
  color: var(--sec-text-color);
  cursor: pointer;
  transition: all ease-out 0.2s;
}

.profile-container:hover {
  background-color: var(--main-background);
  color: var(--main-text-color);
  transition: all ease-out 0.2s;
}
</style>