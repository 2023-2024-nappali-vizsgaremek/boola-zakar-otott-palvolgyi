<script setup>
    import axios from 'axios';
    import { onMounted, ref } from 'vue';

    const authToken = sessionStorage.getItem("authToken");
    if(!authToken) window.open("/login","_self")
    
    const hostName = "localhost:8080"
    const profileCreation = ref(false);
    const newProfile = ref({id: null, name: null, isBusiness: null, languageId: null, expenseListId: null, accountEmail: null})

    const profileCreationToggle = () => {
        profileCreation.value = !profileCreation.value;         
    }
    /*val id:UUID, val name:String, val isBusiness:Boolean,
                   val languageId:String, @Serializable(with = UUIDSerializer::class) val expenseListId:UUID?,
                   val accountEmail:String)*/
    

    const languages = ref([]);
    axios.get(`http://${hostName}/api/language`)
        .then(r=>languages.value=r.data);

    const createNewProfile = () => {
        axios.post(`http://${hostName}/api/profile`, newProfile)
            .then(r => console.log(r));

        console.log(newProfile.value);

        newProfile.value = {id: null, name: null, isBusiness: null, languageId: null, expenseListId: null, accountEmail: null};
    }
</script>

<template>
    <h1 class="text-center">Profilok</h1>
    <div class="profiles-container" v-if="!profileCreation">        
        <div class="profile-asd"><h2>asd</h2>asd</div>
        <div class="profile-asd"><h2>asd</h2>asd</div>
        <div class="profile-asd"><h2>asd</h2>asd</div>
        <div class="profile-asd"><h2>asd</h2>asd</div>
        <div @click="profileCreationToggle" class="new-profile profile-asd"><span class="material-symbols-outlined size-32">add</span></div>
    </div>
    <div v-if="profileCreation" class="profileCreationAsd">
        <div class="asdasd">
            <h2>Név: </h2>
            <input type="text" v-model="newProfile.name">
        </div>
        <div class="asdasd">
            <h2>Munkai-e: </h2>
            <input type="checkbox" v-model="newProfile.isBusiness">
        </div>
        <div class="asdasd">
            <h2>Nyelv: </h2>
            <select>
                <option v-for="language in languages" v-bind:vlaue="language.code">{{ language.code }}</option>
            </select>
        </div>
        <div class="asdasd">
            <button @click="profileCreationToggle">Vissza</button>
            <button @click="createNewProfile">Létrehozás</button>
        </div>
    </div>
</template>

<style scoped>
    .profileCreationAsd{
        background-color: var(--sec-background);
        width: 400px;
        height: auto;
        border-radius: var(--border-radius);

        position: absolute;
        top: 20%;
        right: 50%;
        transform: translateX(200px);
    }

    .asdasd{
        display: inline;
        
        
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

    .profile-asd{
        padding: 5px;
        margin: 5px;
        border: 2px solid var(--main-background);
        border-radius: var(--border-radius);
        color: var(--sec-text-color);
        cursor: pointer;        
        transition: all ease-out 0.2s;
    }

    .profile-asd:hover{
        background-color: var(--main-background);
        color: var(--main-text-color);
        transition: all ease-out 0.2s;
    }

</style>