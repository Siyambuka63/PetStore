import { createApp } from 'vue'
import App from './App.vue'
import * as VueRouter from 'vue-router'

import ProfilePage from "./pages/ProfilePage.vue";
import WishlistPage from "./pages/WishlistPage.vue";
import SignUpPage from "./pages/SignUpPage.vue";
import ProductsPage from "./pages/ProductsPage.vue";
import LogInPage from "./pages/LogInPage.vue";
createApp(App)
    .use(VueRouter.createRouter({
        history: VueRouter.createWebHistory(process.env.BASE_URL),
        routes: [
            {
            path: '/profile',
            component: ProfilePage
        },
            {
                path: '/ProductsPage',
                component: ProductsPage},
            {
                path: '/wishlist',
                component: WishlistPage
            },
            { path: "/signup", name: "SignUp",
                component: SignUpPage },

            { path: "/", name: "LogIn",
                component: LogInPage },]
    }))
    .mount('#app')
