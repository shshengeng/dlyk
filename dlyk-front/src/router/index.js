import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({

    history: createWebHistory(),

    routes: [
        {
            path: '/',
            component: () => import('../view/LoginView.vue'),
        },
        {
            path: '/dashboard',
            component: () => import('../view/DashboardView.vue'),
        }
    ]
})

export default router