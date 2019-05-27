import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

// require.context() returns a function
let contextHandler = require.context('./modules', true, /(.js)$/);
let modules = {};

// keys() returns each fileName of file
contextHandler.keys().forEach(path => {
    let key = path.replace(/\.\/(.*)\.js/, (match, $1) => {
        return $1;
    });
    // with contextHandler(filename) we get context of every module javascript
    let module = contextHandler(path).default;
    modules[key] = module;
});

export default new Vuex.Store({
    modules
})



