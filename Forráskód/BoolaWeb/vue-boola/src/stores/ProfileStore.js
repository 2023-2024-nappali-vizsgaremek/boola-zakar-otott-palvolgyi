import { defineStore } from "pinia";

export const profileStore = defineStore("profile", {
    state: () => {
        return {
            email: null,
            profile: null
        }
    },
    persist: {
        storage: sessionStorage
    }
})