<template>
    <div id="app">
        <div :class="$style['main-container']">
            <main-header></main-header>
            <div :class="$style['main-content-box']">
                <aside :class="$style.aside">
                    <business-support-menu style="margin-bottom: 10px"></business-support-menu>
                    <common-business></common-business>
                </aside>
                <div :class="$style['main-content']">
                    <div :class="$style['title-box']">
                        <span>営業プラス</span>
                    </div>
                    <div :class="$style['tag-box']">
                        <div @click="select(index)" :class="[$style.tag, {[$style.active]: index === currentPageIndex}]" v-for="(tag, index) in menu">
                            {{tag.name}}
                        </div>
                    </div>
                    <div :class="$style['page-content']">
                        <router-view />
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
        },
        computed: {
            currentPageIndex() {
                return this.menu.findIndex(item => {
                    return item.link === this.$route.name;
                })
            }
        },
        data() {
            return {
                menu: [
                    { name: "ホーム", link: "home" },
                    { name: "取引先", link: "businessHome" },
                    { name: "取引先担当者", link: "principleHome" },
                    { name: "スタッフ", link: "staffHome" },
                    { name: "相談", link: "" },
                    { name: "案件", link: "" },
                    { name: "契約", link: "" },
                    { name: "リード", link: "" },
                    { name: "レポート", link: "" },
                    { name: "+", link: "" }
                ]
            }
        },
        methods: {
            select(index) {
                if(this.menu[index] && this.menu[index].link) {
                    this.$router.push({ name: this.menu[index].link })
                } else {
                    alert("この部分は開発中");
                }
            }
        }
    }
</script>

<style lang="scss" module>
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
                width: 100%;
                .title-box {
                    width: 100%;
                    height: 19px;
                    background: $topic-color;
                    border-bottom: 1px solid $main-border-color;
                    span {
                        display: inline-block;
                        padding-left: 20px;
                        line-height: 20px;
                    }
                }
                .tag-box {
                    width: 100%;
                    height: 30px;
                    border-bottom: 1px solid $main-border-color;
                    overflow: hidden;
                    .tag {
                        display: inline-block;
                        height: 30px;
                        min-width: 80px;
                        padding: 0 5px;
                        text-align: center;
                        line-height: 30px;
                        border-right: 1px solid $main-border-color;
                    }
                    .active {
                        background: $topic-color;
                    }
                }
            }
        }
    }

</style>