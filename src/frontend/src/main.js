import { createApp } from 'vue'
import App from './App.vue'
import * as VueRouter from 'vue-router'
import { createPinia } from 'pinia'
import { useAuth } from "@/Auth";

import ProfilePage from "./pages/ProfilePage.vue";
import WishlistPage from "./pages/WishlistPage.vue";
import SignUpPage from "./pages/SignUpPage.vue";
import LogInPage from "./pages/LogInPage.vue";
import ProductsPage from "./pages/ProductsPage.vue";
import ProductDetailsPage from "./pages/ProductDetailPage.vue";
import OrdersPage from "@/pages/OrdersPage.vue";
import OrderItems from "@/pages/OrderItems.vue";

const router = VueRouter.createRouter({
    history: VueRouter.createWebHistory(process.env.BASE_URL),
    routes: [
        {
            path: "/",
            name: "LogIn",
            component: LogInPage
        },

        {
            path: '/orderPage',
            component: OrdersPage,
            meta: { requiresAuth: true }
        },
        {
            path: '/orderItem',
            component: OrderItems
        },
        {
            path: '/profile',
            component: ProfilePage,
            meta: { requiresAuth: true }
        },
        {
            path: '/wishlist',
            component: WishlistPage,
            meta: { requiresAuth: true }
        },
        {
            path: '/ProductsPage',
            component: ProductsPage},
        {
            path: '/wishlist',
            component: WishlistPage
        },
        { 
            path: "/signup",
            name: "SignUp",
            component: SignUpPage 
        },
        {
            path: "/product",
            component: ProductDetailsPage
        }
    ]
 })

router.beforeEach((to, from, next) => {
    const user = useAuth()

    if (to.meta.requiresAuth && !user.userID) {
        // redirect to log in page
        next({ name: "LogIn" })
    } else {
        next()
    }
})

createApp(App)
    .use(createPinia())
    .use(router)
    .mount('#app')
