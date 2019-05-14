<template>
    <div id="app">
        <div class="main-container">
            <main-header></main-header>
            <div class="main">
                <aside class="aside">
                    <business-support-menu style="margin-bottom: 10px"></business-support-menu>
                    <common-business></common-business>
                </aside>
                <div class="details">
<!--                    <router-view></router-view>-->
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import MainHeader from './components/MainHeader'
    import BusinessSupportMenu from './components/BusinessSupportMenu'
    import CommonBusiness from './components/CommonBusiness'
    export default {
        name: "App",
        components: {
            MainHeader,
            BusinessSupportMenu,
            CommonBusiness
        }
    }
</script>

<style lang="scss" scoped>
    .main-container{
        padding: 30px;
        .main {
            width: 100%;
            height: 600px;
            margin-top: 10px;
            @include flex-row;
            .aside {
                width: 300px;
                height: 100%;
            }
        }
    }

</style>