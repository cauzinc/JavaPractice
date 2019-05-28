<template>
    <div :class="$style['business-home-container']">
        <portal-info-list @go2Detail="go2Detail" @go2Create="createNewBusiness()" :titleList="titleList" :infoList="businessList" style="margin-bottom: 30px;"></portal-info-list>
        <div :class="$style['other-info']">
            <portal-column class="column" :infoList="reportList" title="レポート"></portal-column>
            <portal-column class="column" :infoList="toolList" title="ツール"></portal-column>
        </div>
    </div>
</template>


<script>
    import PortalInfoList from '../../components/portal/PortalInfoList'
    import PortalColumn from '../../components/portal/PortalColumn'
    import { createNamespacedHelpers } from 'vuex'
    const { mapActions: businessMapActions, mapState: businessMapState } = createNamespacedHelpers('business')
    export default {
        name: "BusinessHome",
        components: {
            PortalInfoList,
            PortalColumn
        },
        computed: {
            ...businessMapState(['businessList'])
        },
        data() {
            return {
                reportList: [
                    { name: "取引先 ･ 得意先一覧", link: "#" },
                    { name: "取引先 ･ パートナー一覧", link: "#" },
                    { name: "取引先所有者一覧", link: "#" }
                ],
                toolList: [
                    { name: "取引先のインポート", link: "#" },
                    { name: "取引先エクスポート", link: "#" },
                    { name: "取引先所有権の移行", link: "#" }
                ],
                titleList: [ '取引先名', '住所', '電話' ]
            }
        },
        created() {
            this.getBusinessList();
        },
        methods: {
            ...businessMapActions(['getBusinessList']),
            createNewBusiness() {
                this.$router.push({ name: "businessCreate" })
            },
            go2Detail(id) {
                this.$router.push({ name: "businessDetail", query: { businessId: id } });
            }
        }
    }
</script>

<style lang="scss" module>
    .business-home-container {
        padding: 30px 10px;
        .other-info {
            @include flex-row-center;
            
            .column {
                width: 45%;
            }
        }
    }
</style>