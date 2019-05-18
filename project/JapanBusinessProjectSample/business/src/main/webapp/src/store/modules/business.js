import Service from './../Service'

let service = new Service({
    prefix: "/api/business/",
    state: {
        businessList: [
            { value1: "", value2: "", value3: "" },
            { value1: "", value2: "", value3: "" },
            { value1: "", value2: "", value3: "" },
            { value1: "", value2: "", value3: "" },
            { value1: "", value2: "", value3: "" },
            { value1: "", value2: "", value3: "" }
        ]
    },
    actions: {
        getBusinessList({ commit }, data) {
            // return service.fetch('getBusinessList', 'get', data);
            service.fetch('getBusinessList', 'get', data).then(res => {
                if(res.data && res.data.status === 0) {
                    commit('FETCH_BUSINESS_LIST', res.data && res.data.data);
                }
            })
        },
        quickInsertBusiness({ commit }, data) {
            return service.fetch('quickInsert', 'post', data);
        },
        getBusinessById({ commit }, data) {
            return service.fetch('getBusinessById', 'get', data);
        }
    },
    mutations: {
        FETCH_BUSINESS_LIST(state, data) {
            data.map((item, index) => {
                state.businessList[index].value1 = item.businessName;
                state.businessList[index].value2 = item.address;
                state.businessList[index].value3 = item.businessTel;
               state.businessList[index].id = item.id;
            })
        }
    }
});

export default service