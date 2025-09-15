import { defineStore } from 'pinia'
export const useAuth = defineStore('user', {
    state: () => ({
        email: "test@gmail.com"
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
