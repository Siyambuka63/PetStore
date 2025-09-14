import { defineStore } from 'pinia'
export const useAuth = defineStore('user', {
    state: () => ({
        email: "test@gmail.com"
    }),
    actions: {
        getEmail(){
          if (this.email) {
              return this.email;
          } else {
              this.$router.push("/login");
          }
        },
        setUserEmail(email) {
            this.email = email;
        },
        logout() {
            this.email = null;
        }
    }
})
