<template>
    <div :class="$style['staff-home-container']">
        <portal-info-list @go2Detail="go2Detail" @go2Create="createNewStaff()" :titleList="titleList" :infoList="staffList" style="margin-bottom: 30px;"></portal-info-list>
        <div :class="$style['other-info']">
            <portal-column :class="$style.column" :infoList="reportList" title="レポート"></portal-column>
            <portal-column :class="$style.column" :infoList="toolList" title="ツール"></portal-column>
        </div>
    </div>
</template>

<script>
	import PortalInfoList from '../../components/portal/PortalInfoList'
	import PortalColumn from '../../components/portal/PortalColumn'
    import { createNamespacedHelpers } from 'vuex'
    const { mapActions: staffMapActions, mapState: staffMapState } = createNamespacedHelpers('staff');
	export default {
		name: "StaffHome",
        components: {
			PortalColumn,
            PortalInfoList
        },
        computed: {
            ...staffMapState(['staffList'])
        },
        data() {
			return {
                titleList: ['スタッフ名', '所属先', '携帯電話'],
                reportList: [
                	{ name: 'スタッフ一覧', link: ""},
	                { name: '今月まで稼動中のスタッフ', link: ""},
	                { name: '最新推薦技術者リスト一覧', link: ""}
                ],
                toolList: [
	                { name: 'スタッフ情報のインポート･エクスポート', link: ""},
	                { name: 'スタッフ一括メール送信', link: ""}
                ]
            }
        },
        created() {
            this.getStaffList()
        },
        methods: {
            ...staffMapActions(['getStaffList']),
            go2Detail(id) {
            	if(id || id === 0) {
            		this.$router.push({name: "staffDetail", query: { staffId: id }})
                }
            },
	        createNewStaff() {
            	this.$router.push({ name: "staffCreate" })
            }
        }
	}
</script>

<style lang="scss" module>
    .staff-home-container {
        padding: 30px 10px;
        .other-info {
            @include flex-row-center;

            .column {
                width: 45%;
            }
        }
    }
</style>