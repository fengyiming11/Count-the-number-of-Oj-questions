import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        redirect:'/login',
    },
    {
        path: '/home',
        component: () => import('@/view/index.vue'),
        redirect:'/home',
        meta:{

        },
        children:[
            {
                path: '/home',
                name: 'home',
                component: () => import('@/view/home.vue'),
                meta:{
                    needLogin: true
                }
            },
            {
                path: '/RecentlyRating',
                name: 'RecentlyRating',
                component: () => import('@/view/RecentlyRating.vue'),
                meta:{
                    needLogin: true
                }
            },
            {
                path: '/Today',
                name: 'Today',
                component: () => import('@/view/Today.vue'),
                meta:{
                    needLogin: true
                }
            },
            {
                path: '/Rating',
                name: 'Rating',
                component: () => import('@/view/CodeforcesRating.vue'),
                meta:{
                    needLogin: true,
                    keepAlive: true,
                }
            },
            {
                path: '/NowMember',
                name: 'NowMember',
                component: () => import('@/view/NowMember.vue'),
                meta:{
                    needLogin: true
                }
            },
            {
                path: '/CompletionStatistics',
                name: 'CompletionStatistics',
                component: () => import('@/view/CompletionStatistics.vue'),
                meta:{
                    needLogin: true
                }
            },
            {
                path: '/Setting',
                name: 'Setting',
                component: () => import('@/view/Setting.vue'),
                meta:{
                    needLogin: true
                }
            },
            {
                path: '/min',
                name: 'min',
                component: () => import('@/Layout/main.vue'),
                meta:{
                    needLogin: false
                }
            }
        ]
    },
    {
        path: '/login',
        component: () => import('@/view/Login.vue'),
    },
    {
        path: '/404',
        name: '404',
        component: () => import('@/view/404.vue'),
        hidden: true
    },
    {
        path: '*',
        redirect: '/404',
        hidden: true
    }
];
const router = new VueRouter({
    mode: "hash",
    routes
});

export default router;


