import Service from './../Service'

let service = new Service({
    prefix: "/api/principle/",
    state: {
        principleList: [
            { value1: "", value2: "", value3: "" },
            { value1: "", value2: "", value3: "" },
            { value1: "", value2: "", value3: "" },
            { value1: "", value2: "", value3: "" },
            { value1: "", value2: "", value3: "" },
            { value1: "", value2: "", value3: "" }
        ]
    },
    actions: {
        getPrincipleList({ commit }, data) {
            service.fetch('getPrinciplePortalList', 'get', data).then(res => {
                if(res.data.status === 0) {
                    commit('FETCH_PRINCIPLE_LIST', res.data.data);
                }
            });
        },
        quickInsertPrinciple({ commit }, data) {
            return service.fetch('insertPrinciple', 'post', data);
        },
        updatePrinciple({ commit }, data) {
            return service.fetch('updatePrinciple', 'post', data);
        },
        insertPrinciple({ commit }, data) {
            return service.fetch('insertPrinciple', 'post', data);
        },
        getPrincipleById({ commit }, data) {
            return service.fetch('getPrincipleDetail', 'get', data);
        },
        deletePrincipleById({ commit }, data) {
            return service.fetch('deletePrincipleById', 'get', data);
        }
    },
    mutations: {
        FETCH_PRINCIPLE_LIST(state, data) {
            // 先进行初始化
            state.principleList = [
                { value1: "", value2: "", value3: "" },
                { value1: "", value2: "", value3: "" },
                { value1: "", value2: "", value3: "" },
                { value1: "", value2: "", value3: "" },
                { value1: "", value2: "", value3: "" },
                { value1: "", value2: "", value3: "" }
            ];
            data.map((item, index) => {
                state.principleList[index].value1 = item.principleName;
                state.principleList[index].value2 = item.businessName;
                state.principleList[index].value3 = item.tel;
                state.principleList[index].id = item.id;
            })
        }

    }
});

export default service