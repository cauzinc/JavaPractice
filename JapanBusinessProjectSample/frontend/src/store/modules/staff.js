import Service from './../Service'

let service = new Service({
	prefix: "/api/staff/",
	state: {
		staffList: [
			{ value1: "", value2: "", value3: "" },
			{ value1: "", value2: "", value3: "" },
			{ value1: "", value2: "", value3: "" },
			{ value1: "", value2: "", value3: "" },
			{ value1: "", value2: "", value3: "" },
			{ value1: "", value2: "", value3: "" }
		]
	},
	actions: {
		getStaffList({ commit }, data) {
			service.fetch('getStaffList', 'get', data).then(res => {
				if(res.data.status === 0) {
					commit('FETCH_STAFF_LIST', res.data.data);
				}
			});
		},
		quickInsertStaff({ commit }, data) {
			return service.fetch('quickInsert', 'post', data);
		},
		updateStaff({ commit }, data) {
			return service.fetch('updateStaff', 'post', data);
		},
		insertStaff({ commit }, data) {
			return service.fetch('insertStaff', 'post', data);
		},
		getStaffDetail({ commit }, data) {
			return service.fetch('getStaffById', 'get', data);
		},
		deleteStaffById({ commit }, data) {
			return service.fetch('deleteStaffById', 'get', data);
		},
		commentStaff({ commit }, data) {
			return service.fetch('commentStaff', 'post', data);
		}
	},
	mutations: {
		FETCH_STAFF_LIST(state, data) {
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
				state.staffList[index].value1 = item.staffName;
				state.staffList[index].value2 = item.businessName;
				state.staffList[index].value3 = item.tel;
				state.staffList[index].id = item.id;
			})
		}

	}
});

export default service