<template>
    <div class="business-home-container">
        <portal-info-list style="margin-bottom: 30px;"></portal-info-list>
        <div class="other-info">
            <portal-column class="column" :infoList="reportList" title="レポート"></portal-column>
            <portal-column class="column" :infoList="toolList" title="ツール"></portal-column>
        </div>
    </div>
</template>


<script>
    import PortalInfoList from './../components/portal/PortalInfoList'
    import PortalColumn from './../components/portal/PortalColumn'
    export default {
        name: "BusinessHome",
        components: {
            PortalInfoList,
            PortalColumn
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
                ]

            }
        }
    }
</script>

<style lang="scss" scoped>
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