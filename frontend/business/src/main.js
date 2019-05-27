import Vue from 'vue'
import App from './App'
import routes from './route/entry'
import store from './store/index'
import axios from 'axios'

Vue.prototype.$axios = axios;

new Vue({
    el: "#app",
    router: routes,
    store,
    render: h => h(App)
});