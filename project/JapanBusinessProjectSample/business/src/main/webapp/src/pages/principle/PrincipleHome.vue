<template>
    <div class="principle-home-container">
        <portal-info-list @go2Create="createNewBusiness()" :infoList="principleList" style="margin-bottom: 30px;"></portal-info-list>
        <div class="other-info">
            <portal-column class="column" :infoList="reportList" title="レポート"></portal-column>
            <portal-column class="column" :infoList="toolList" title="ツール"></portal-column>
        </div>
    </div>
</template>

<script>
    import PortalInfoList from '../../components/portal/PortalInfoList'
    import PortalColumn from '../../components/portal/PortalColumn'
    import { createNamespacedHelpers } from 'vuex'
    const { mapActions: principleMapActions, mapState: principleMapState } = createNamespacedHelpers('principle')
    export default {
        name: "",
        components: {
            PortalInfoList,
            PortalColumn
        },
        computed: {
            ...principleMapState(['principleList'])
        },
        data() {
            return {
                reportList: [
                    { name: "取引先 ･ 取引担当者一覧", link: "#" },
                    { name: "パートナー一覧", link: "#" }
                ],
                toolList: [
                    { name: "取引先のインポート", link: "#" },
                    { name: "取引先エクスポート", link: "#" },
                    { name: "取引先所有権の移行", link: "#" }
                ]
            }
        },
        created() {
            this.getPrincipleList();
        },
        methods: {
            ...principleMapActions(['getPrincipleList'])
        }
    }
</script>

<style scoped>
.principle-home-container {



}
</style>