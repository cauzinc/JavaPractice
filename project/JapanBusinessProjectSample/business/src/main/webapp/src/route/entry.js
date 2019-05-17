import Vue from 'vue'
import Router from 'vue-router'
import BusinessHome from '../pages/business/BusinessHome'

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            name: 'businessHome',
            component: BusinessHome,
            children: [

            ]
        }
    ]
})