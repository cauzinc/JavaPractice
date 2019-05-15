<template>
    <div class="business-home-container">

    </div>
</template>


<script>
    import BusinessSupportMenu from './../components/BusinessSupportMenu'
    import CommonBusiness from './../components/CommonBusiness'
    import PortalInfoList from './../components/portal/PortalInfoList'
    export default {
        name: "BusinessHome",
        components: {
            BusinessSupportMenu,
            CommonBusiness,
            PortalInfoList
        }
    }
</script>

<style lang="scss" scoped>
    .business-home-container {
        width: 100%;
        margin-top: 10px;
        border: 1px solid $main-border-color;
        @include flex-row;
        .aside {
            width: 300px;
            height: 100%;
        }
        .main {
            height: 100%;

        }
    }
</style>