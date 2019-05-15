<template>
    <div class="business-home-container">
        <aside class="aside">
            <business-support-menu style="margin-bottom: 10px"></business-support-menu>
            <common-business></common-business>
        </aside>
        <div class="main"></div>
    </div>
</template>


<script>
    import BusinessSupportMenu from './../components/BusinessSupportMenu'
    import CommonBusiness from './../components/CommonBusiness'
    export default {
        name: "BusinessHome",
        components: {
            BusinessSupportMenu,
            CommonBusiness
        }
    }
</script>

<style lang="scss">
    .business-home-container {
        width: 100%;
        height: 600px;
        margin-top: 10px;
        @include flex-row;
        .aside {
            width: 300px;
            height: 100%;
        }
    }
</style>