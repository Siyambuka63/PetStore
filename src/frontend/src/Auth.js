import { defineStore } from 'pinia'
export const useAuth = defineStore('user', {
    state: () => ({
        email: null
    }),
    actions: {
        getEmail(){
            return this.email;
        },
        setUserEmail(email) {
            this.email = email;
        },
        logout() {
            this.email = null;
        }
    }
})
