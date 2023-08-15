import {createRouter, createWebHistory} from 'vue-router';
import Home from '../views/Home.vue';
import HomeBody from "@/views/HomeBody.vue";
import ChatMessageOld from "@/views/ChatMessageOld.vue";


const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home,
            children: [
                {
                    path: '/',
                    component: HomeBody
                },
                {
                    path: '/generate',
                    component: ChatMessageOld
                },
            ]
        }
    ]
});

export default router;
