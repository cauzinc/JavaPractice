<template>
    <div class="principle-home-container">
        <portal-info-list @go2Detail="go2Detail" @go2Create="createNewPrinciple()" :titleList="['担当者名', '取引先名', '電話']" :infoList="principleList" style="margin-bottom: 30px;"></portal-info-list>
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
            ...principleMapActions(['getPrincipleList']),
            createNewPrinciple() {
                this.$router.push({ name: 'principleDetail' })
            },
            go2Detail(id) {
                this.$router.push({ name: 'principle', query: { principleI: id } })
            }
        }
    }
</script>

<style lang="scss" scoped>
.principle-home-container {
    padding: 30px 10px;
    .other-info {
        @include flex-row-center;

        .column {
            width: 45%;
        }
    }
}
</style>