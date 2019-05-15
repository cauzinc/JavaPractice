<template>
    <div class="business-support-menu-container">
        <div class="title">
            <h4>業務支援メニュー</h4>
        </div>
        <div class="main">
            <div class="create">
                <a href="#" class="item-content">新規作成</a>
            </div>
            <div class="recent-date">
                <p class="item-content">最近使ったデータ</p>
                <ul class="data-menu">
                    <li v-for="(item, index) in recentData">
                        <a href="#">{{item.name}}</a>
                    </li>
                </ul>
            </div>
        </div>

    </div>
</template>

<script>
    export default {
        name: "BusinessSupportMenu",
        data() {
            return {
                recentData: [
                    { name: "情報技術開発後" },
                    { name: "テスト" },
                    { name: "新規テスト2" },
                    { name: "携帯システム開発" }
                ]
            }
        }
    }
</script>

<style lang="scss" scoped>
.business-support-menu-container {
    border: 1px solid $main-border-color;
    .title {
        width: 100%;
        height: 30px;
        background: $topic-color;
        h4 {
            line-height: 30px;
            text-align: center;
            border-bottom: 1px solid $main-border-color;
            margin: 0;
        }
    }
    .main {
        padding: 5px 10px;
        .create {
            text-align: center;
            margin: 10px 0;
        }
        .recent-date {
            border: 1px solid $main-border-color;
            width: 100%;
            height: auto;
            .item-content {
                border-bottom: 1px solid $main-border-color;
                height: 20px;
                line-height: 20px;
                text-align: center;
            }

            .data-menu {
                background: $topic-color;
                height: calc(100% - 21px);
                padding: 10px 0;
                li {
                    margin: 5px 0;
                    padding: 0 10px;
                    a {
                        text-decoration: underline;
                    }
                }
            }
        }
    }
}
</style>