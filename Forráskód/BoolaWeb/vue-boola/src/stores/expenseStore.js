import { defineStore } from "pinia";

export const expenseStore = defineStore("expense", {
    state: () => {
        return { selectedExpense: null }
    }
})