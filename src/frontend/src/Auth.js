import { defineStore } from 'pinia'
export const useAuth = defineStore('user', {
    state: () => ({
        userID: null,
        userEmail: null,
    }),
    actions: {
        setUser(user) {
            this.userID = user.id;
            this.userEmail = user.email;
        },
        logout() {
            this.userID = null;
            this.userEmail = null;
        }
    }
})