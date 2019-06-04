import Vue from 'vue'
import App from './App'
import routes from './route/entry'
import store from './store/index'
import axios from 'axios'

import Modal from 'vue-modal-caller'
import StaffCommentModal from './components/modal/StaffCommentModal'

Vue.prototype.$axios = axios;
Vue.use(Modal, {
	modules: [ StaffCommentModal ]
});
Vue.prototype.$modal = Vue.prototype.$getModalHandler();

new Vue({
    el: "#app",
    router: routes,
    store,
    render: h => h(App)
});