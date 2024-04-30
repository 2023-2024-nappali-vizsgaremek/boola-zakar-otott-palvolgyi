<script setup>
import axios from 'axios';
import {onMounted, ref} from 'vue';
import {v4 as uuidv4} from "uuid";
import {profileStore} from "/src/stores/ProfileStore"
import {useToast} from "vue-toastification";
const toast=useToast()
import { useRouter } from 'vue-router'
const router = useRouter()
import { useMenuStore} from '/src/stores/MenuStore';
const MenuStore = useMenuStore();    

const authToken = sessionStorage.getItem("authToken");
const store = profileStore();
console.log(authToken);
if (!authToken) 
{
  console.log(authToken);
  router.push("/login", "_self")
}
else if (store==null) 
{
  router.push("/profiles")
}

axios.defaults.headers.get["Cache-Control"] = "max-age=604800"

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
axios.get(`https://${hostName}/api/profile`, {
    headers: {
        Authorization: `Bearer ${authToken}`,
        "Cache-Control": "no-store"
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
axios.get(`https://${hostName}/api/language`)
    .then(r => languages.value = r.data);

const createNewProfile = () => {
  newProfile.value.languageId = selectedLanguage.value;
  axios.post(`https://${hostName}/api/profile`, newProfile.value, {
    headers: {
      Authorization: `Bearer ${authToken}`
    }
  })
      .then(r => {
        if (r.status != 201) {
          toast.error("hiba")
          return;
        }else{
          toast.success("Sikeres profil létrehozás")

          profileCreationToggle();

          axios.get(`https://${hostName}/api/profile`, {
            headers: {
              Authorization: `Bearer ${authToken}`,
              "Cache-Control": "no-store"
            }


          })
              .then(r => profiles.value = r.data);

        }
    })
        .then(r => {
            if (r.status != 201) {
              toast.error("hiba")
                return;
            }
            const profileAddress = r.data
            axios.get(`https://${hostName}/${profileAddress}`, {
                headers: {
                    Authorization: `Bearer ${authToken}`,
                    "Cache-Control": "no-store"
                }
            }).then(p => {

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
    router.push("/")
    MenuStore.showMainMenu();
}

const DeleteProfile = (id) => {
    if (!confirm("Biztosan törli ezt a profilt? Minden benne levő költés el fog veszni!")) return;
    axios.delete(`https://${hostName}/api/profile/${id}`,{
      headers: {
        Authorization: `Bearer ${authToken}`
      }

    }).then(r => {
        if (r.status != 204) toast.error("Hiba történt a törlés során!")
        else{
          axios.get(`https://${hostName}/api/profile`, {
            headers: {
              Authorization: `Bearer ${authToken}`,
              "Cache-Control": "no-store"
            }


          })
              .then(r => profiles.value = r.data);
          toast.success("Sikeres törlés!")

          router.push("/profiles")

        }

      })

}
</script>

<template>
  <div class="outer-container">
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
    </div>
    <div v-if="profileCreation" class="profileCreationForm">
      <button class="btn btn-primary btn-rounded" @click="profileCreationToggle">Vissza</button>
      <button class="btn btn-primary btn-rounded"  @click="createNewProfile">Létrehozás</button>
    </div>   
  </div>
</template>

<style scoped>
.outer-container{
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
}

.profileCreation-container {
  background-color: var(--sec-background);
  width: 30vw;
  min-width: 300px;
  height: auto;
  border-radius: var(--border-radius);
  padding: 5px;
  color: var(--sec-text-color);
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
    width: 30vw;
    min-width: 300px;
    height: auto;
    max-height: 75vh;
    border-radius: var(--border-radius);
    overflow: auto;
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
</style>