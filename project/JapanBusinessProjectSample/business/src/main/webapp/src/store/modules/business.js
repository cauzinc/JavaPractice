import Service from './../Service'

let service = new Service({
    prefix: "/api/business/",
    state: {},
    actions: {
        getBusinessList({ commit }, data) {
            return service.fetch('getBusinessList', 'get', data);
        },
        addToCart({ commit }, data) {
            return service.fetch('addToCart', 'post', data);
        }
    },
    mutations: {}
});

export default service