import Vue from 'vue'
import App from './App'
import routes from './route/entry'

new Vue({
    el: "#app",
    router: routes,
    render: h => h(App)
});