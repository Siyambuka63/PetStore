import { defineStore } from 'pinia'
export const useAuth = defineStore('user', {
    state: () => ({
        userID: 55
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