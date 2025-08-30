import { defineStore } from 'pinia'
export const useAuth = defineStore('user', {
    state: () => ({
        userID: null
    }),
    actions: {
        setUserId(id) {
            this.userId = id;
        },
        logout() {
            this.userId = null;
        }
    }
})