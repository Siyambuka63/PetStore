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
import AdminDashboard from "@/pages/AdminDashboard.vue";

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
            get component() {
                if (localStorage.getItem("roles").includes("ADMIN")) {
                    return AdminDashboard;
                } else {
                    return ProductsPage;
                }
            }
        },
        {
            path: '/orders',
            component: OrdersPage,
            meta: { requiresAuth: true }
        },
        {
            path: '/orders/:id',
            component: OrderItems,
            meta: { requiresAuth: true }
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
            component: ProductsPage
        },
        { 
            path: "/signup",
            name: "SignUp",
            component: SignUpPage 
        },
        {
            path: "/products/:id",
            name: "ProductDetails",
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
        },
        // {
        //     path: "/admin",
        //     name: "admin",
        //     component: AdminDashboard,
        //     meta: { requiresAdmin: true }
        // }
    ]
 })

router.beforeEach((to, from, next) => {
    const user = useAuth()

    if (to.name === "logout") {
        localStorage.removeItem("email");
        localStorage.removeItem("roles");
        localStorage.removeItem("token");
        return next("/")
    }

    if (to.meta.requiresAuth && !localStorage.getItem("token")) {
        return next({ name: "LogIn" })
    }

    next()
})

createApp(App)
    .use(router)
    .mount('#app')
