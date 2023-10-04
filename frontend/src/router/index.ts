import {createRouter, createWebHashHistory} from "vue-router";

export enum PAGES {
    PAGE_HOME = '/'
}

const router = createRouter({
    history: createWebHashHistory(import.meta.env.BASE_URL),
    routes: [

    ]
})

export default router