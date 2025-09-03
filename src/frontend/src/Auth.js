import { defineStore } from 'pinia'
export const useAuth = defineStore('user', {
    state: () => ({
        userID: null
    }),
    actions: {
        setUserId(id) {
            this.userID = id;
        },
        logout() {
            this.userID = null;
        }
    }
})
