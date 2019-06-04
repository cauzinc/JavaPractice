import Vue from 'vue'
import Router from 'vue-router'
import BusinessHome from '../pages/business/BusinessHome'
import BusinessDetail from '../pages/business/BusinessDetail'
import BusinessCreate from '../pages/business/BusinessCreate'
import PrincipleHome from '../pages/principle/PrincipleHome'
import PrincipleDetail from '../pages/principle/PrincipleDetail'
import PrincipleCreate from '../pages/principle/PrincipleCreate'
import StaffHome from '../pages/staff/StaffHome'
import StaffDetail from '../pages/staff/StaffDetail'
import StaffCreate from '../pages/staff/StaffCreate'
import StaffComment from '../pages/staff/StaffComment'

Vue.use(Router);

export default new Router({
    routes: [
        { path: '', name: "home" },
        { path: '/business-portal', name: 'businessHome', component: BusinessHome },
        { path: '/business-detail', name: "businessDetail", component: BusinessDetail },
        { path: '/business-create', name: "businessCreate", component: BusinessCreate },
        { path: '/principle-portal', name: "principleHome", component: PrincipleHome },
        { path: '/principle-detail', name: "principleDetail", component: PrincipleDetail },
        { path: '/principle-create', name: "principleCreate", component: PrincipleCreate },
	    { path: '/staff-portal', name: "staffHome", component: StaffHome },
	    { path: '/staff-detail', name: "staffDetail", component: StaffDetail },
	    { path: '/staff-create', name: "staffCreate", component: StaffCreate },
	    { path: '/staff-comment', name: "staffComment", component: StaffComment }
    ]
})