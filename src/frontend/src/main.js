import { createApp } from 'vue'
import App from './App.vue'
import * as VueRouter from 'vue-router'
import { createPinia } from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import { useAuth } from "@/Auth";

import ProfilePage from "./pages/ProfilePage.vue";
import WishlistPage from "./pages/WishlistPage.vue";
import SignUpPage from "./pages/SignUpPage.vue";
import LogInPage from "./pages/LogInPage.vue";
import ProductsPage from "./pages/ProductsPage.vue";
import ProductDetailsPage from "./pages/ProductDetailPage.vue";
import OrdersPage from "@/pages/OrdersPage.vue";
import OrderItems from "@/pages/OrderItems.vue";
import CartPage from "@/pages/CartPage.vue";

const router = VueRouter.createRouter({
    history: VueRouter.createWebHistory(process.env.BASE_URL),
    routes: [
        {
            path: "/login",
            name: "LogIn",
            component: LogInPage
        },
        {
            path: '/',
            component: ProductsPage
        },
        {
            path: '/orders',
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
            path: '/products',
            redirect: "/"
        },
        { 
            path: "/signup",
            name: "SignUp",
            component: SignUpPage 
        },
        {
            path: "/product",
            component: ProductDetailsPage
        },
        {
            path: "/cart",
            component: CartPage,
            meta: { requiresAuth: true }
        },
        {
            path: "/logout",
            name: "logout"
        }
    ]
 })

router.beforeEach((to, from, next) => {
    const user = useAuth()

    if (to.name === "logout") {
        user.logout()
        console.log(user.getEmail())
        return next("/")
    }

    if (to.meta.requiresAuth && !user.getEmail()) {
        return next({ name: "LogIn" })
    }

    next()
})

const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)

createApp(App)
    .use(pinia)
    .use(router)
    .mount('#app')
