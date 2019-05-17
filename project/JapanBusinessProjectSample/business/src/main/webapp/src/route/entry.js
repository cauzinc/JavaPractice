import Vue from 'vue'
import Router from 'vue-router'
import BusinessHome from '../pages/business/BusinessHome'
import BusinessDetail from '../pages/business/BusinessDetail'
import App from '../App'

Vue.use(Router);

export default new Router({
    routes: [
        { path: '/', name: 'businessHome', component: BusinessHome },
        { path: '/business-detail', name: "businessDetail", component: BusinessDetail }
    ]
})