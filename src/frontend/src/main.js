import { createApp } from 'vue'
import App from './App.vue'
import * as VueRouter from 'vue-router'

import LogInPage from "./pages/LogInPage.vue";
import ProfilePage from "./pages/ProfilePage.vue";
import WishlistPage from "./pages/WishlistPage.vue";
import SignUpPage from "./pages/SignUpPage.vue";
createApp(App)
    .use(VueRouter.createRouter({
        history: VueRouter.createWebHistory(process.env.BASE_URL),
        routes: [
            { path: "/", name: "LogIn",
                component: LogInPage },
            {
            path: '/profile',
            component: ProfilePage
        },
            {
                path: '/wishlist',
                component: WishlistPage
            },
            { path: "/signup", name: "SignUp",
                component: SignUpPage }]
    }))
    .mount('#app')
