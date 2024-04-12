<script setup>
    import axios from 'axios';
    import { onMounted, ref } from 'vue';    
    import { v4 as uuidv4 } from "uuid";

    const authToken = sessionStorage.getItem("authToken");
    if(!authToken) window.open("/login","_self")
    
    const hostName = "boola-backend-a71954a87e5d.herokuapp.com"
    const profileCreation = ref(false);
    const newProfile = ref({id: null, name: null, isBusiness: null, languageId: null, expenseListId: null, accountEmail: null})

    const profileCreationToggle = () => {
        profileCreation.value = !profileCreation.value;         
    }

    const profiles = ref();
    axios.get(`http://${hostName}/api/profile`, {
        headers: {
            Authorization: `Bearer ${authToken}`
        }
    })
    .then(r => profiles.value = r.data);

    /*val id:UUID, val name:String, val isBusiness:Boolean,
                   val languageId:String, @Serializable(with = UUIDSerializer::class) val expenseListId:UUID?,
                   val accountEmail:String)*/
    

    const languages = ref([]);
    axios.get(`http://${hostName}/api/language`)
        .then(r=>languages.value=r.data);

    const createNewProfile = () => {
        console.log(newProfile.value);

        newProfile.id = uuidv4();
        newProfile.expenseListId = uuidv4();
        //newProfile.accountEmail = 

        axios.post(`http://${hostName}/api/profile`, newProfile, {
            headers: {
                Authorization: `Bearer ${authToken}`
            }
        })
        .then(r => console.log(r));        

        newProfile.value = {id: null, name: null, isBusiness: null, languageId: null, expenseListId: null, accountEmail: null};

        profileCreationToggle();
    }
</script>

<template>
    <h1 class="text-center">Profilok</h1>
    <div class="profiles-container" v-if="!profileCreation">        
        <div class="profile-container" v-for="p in profiles">
            <h2>{{ p.name }}</h2>
            <p>{{ p.accountEmail }}</p>            
            <p v-if="p.isBusiness">Munkai</p>
        </div>
        <div @click="profileCreationToggle" class="new-profile profile-container"><span class="material-symbols-outlined size-32">add</span></div>
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
            <select>
                <option v-for="language in languages" v-bind:value="language.code">{{ language.code }}</option>
            </select>
        </div>
        <div class="profileCreationForm">
            <button @click="profileCreationToggle">Vissza</button>
            <button @click="createNewProfile">Létrehozás</button>
        </div>
    </div>
</template>

<style scoped>
    .profileCreation-container{
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

    .profileCreationForm > *{
        display: inline;
        margin: 5px;        
    }

    .new-profile{
        text-align: center;
        align-items: center;        
    }

    .profiles-container{
        background-color: var(--sec-background);
        width: 400px;
        height: auto;
        border-radius: var(--border-radius);

        position: absolute;
        top: 20%;
        right: 50%;
        transform: translateX(200px);
    }

    .profile-container{
        padding: 5px;
        margin: 5px;
        border: 2px solid var(--main-background);
        border-radius: var(--border-radius);
        color: var(--sec-text-color);
        cursor: pointer;        
        transition: all ease-out 0.2s;
    }

    .profile-container:hover{
        background-color: var(--main-background);
        color: var(--main-text-color);
        transition: all ease-out 0.2s;
    }

</style>