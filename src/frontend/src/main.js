import { createApp } from 'vue'
import App from './App.vue'
import * as VueRouter from 'vue-router'

import ProfilePage from "./pages/ProfilePage.vue";
import WishlistPage from "./pages/WishlistPage.vue";

createApp(App)
    .use(VueRouter.createRouter({
        history: VueRouter.createWebHistory(process.env.BASE_URL),
        routes: [{
            path: '/profile',
            component: ProfilePage
        },
            {
                path: '/wishlist',
                component: WishlistPage
            }]
    }))
    .mount('#app')
