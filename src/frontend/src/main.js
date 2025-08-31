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
import ProductDetailsPage from "./pages/ProductDetailsPage.vue";  

const router = VueRouter.createRouter({
    history: VueRouter.createWebHistory(process.env.BASE_URL),
    routes: [
        {
            path: "/",
            name: "LogIn",
            component: LogInPage
        },
        {
            path: "/profile",
            component: ProfilePage,
            meta: { requiresAuth: true }
        },
        {
            path: "/wishlist",
            component: WishlistPage,
            meta: { requiresAuth: true }
        },
        {
            path: "/products",
            name: "Products",
            component: ProductsPage
        },
        {
            path: "/product-details",    
            name: "ProductDetails",
            component: ProductDetailsPage
        },
        { 
            path: "/signup",
            name: "SignUp",
            component: SignUpPage 
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
