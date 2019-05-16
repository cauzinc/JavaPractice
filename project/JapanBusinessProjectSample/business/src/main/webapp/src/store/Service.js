import axios from 'axios'

/**
 * Service 是基于vuex结构，用于封装前后端服务的类
 * 根据不同的前后端服务，我们封装不同的module，作为namespace来存放变量和方法
 * 我们将axios请求统一封装到actions中，返回一个promise对象，作为前后端交互的方法
 * 如果请求到的数据要全局缓存，那么就在actions中调用mutation，并把全局变量储存到state里
 * */

export default class Service {
    constructor(params) {
        let { namespaced = true, prefix, state = {}, mutations ={}, actions = {}, getters = {} } = params;
        this.namespaced = namespaced;
        this.prefix = prefix;
        this.state = state;
        this.mutations = mutations;
        this.actions = actions;
        this.getters = getters;
    }

    fetch(api, method, data) {
        let mtd = method.toLowerCase();
        let url = this.prefix + api;
        return mtd === 'get' ? axios.get(url, { params: data }) : axios(url, data);
    }

}