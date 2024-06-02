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
            //子路由
            children: [
                {
                    //子路由不需要带/
                    path: 'user',
                    component: () => import('../view/UserView.vue')
                },
                {
                    //id为动态变量，是不确定的，这个叫动态路由
                    path: 'user/:id',
                    component: () => import('../view/UserDetailView.vue')
                }

            ]
        }
    ]
})

export default router