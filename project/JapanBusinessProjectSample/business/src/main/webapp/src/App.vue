<template>
    <div id="app">
        <div class="main-container">
            <main-header></main-header>
            <router-view />
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

<style lang="scss">
    .main-container{
        padding: 30px;
    }

</style>