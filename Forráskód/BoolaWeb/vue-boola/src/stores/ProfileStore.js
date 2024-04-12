import { defineStore } from "pinia";

export const profileStore = defineStore("profile", {
    state: () => {
        return {
            email: "otottkovi@hotmail.com",
            profile: null
        }
    }
})