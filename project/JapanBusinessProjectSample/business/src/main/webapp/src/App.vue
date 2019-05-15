<template>
    <div id="app">
        <div class="main-container">
            <main-header></main-header>
            <div class="main-content-box">
                <aside class="aside">
                    <business-support-menu style="margin-bottom: 10px"></business-support-menu>
                    <common-business></common-business>
                </aside>
                <div class="main-content">
                    <div class="title-box">
                        <span>営業プラス</span>
                    </div>
                    <ul class="tag-box">
                        <li class="tag">ホーム</li>
                    </ul>
                    <div class="page-content">

                    </div>
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
        .main-content-box {
            width: 100%;
            margin-top: 10px;
            border: 1px solid $main-border-color;
            @include flex-row;
            .aside {
                width: 300px;
                height: 100%;
            }
            .main-content {
                height: 100%;

            }
        }
    }

</style>