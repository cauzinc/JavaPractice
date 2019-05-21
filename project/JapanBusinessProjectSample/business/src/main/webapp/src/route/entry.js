import Vue from 'vue'
import Router from 'vue-router'
import BusinessHome from '../pages/business/BusinessHome'
import BusinessDetail from '../pages/business/BusinessDetail'
import BusinessCreate from '../pages/business/BusinessCreate'

Vue.use(Router);

export default new Router({
    routes: [
        { path: '', name: "home" },
        { path: '/business-portal', name: 'businessHome', component: BusinessHome },
        { path: '/business-detail', name: "businessDetail", component: BusinessDetail },
        { path: '/business-create', name: "businessCreate", component: BusinessCreate }
    ]
})