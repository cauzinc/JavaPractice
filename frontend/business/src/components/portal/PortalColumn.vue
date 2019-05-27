<template>
    <div :class="$style.container">
        <div :class="$style.titleBox">
            {{title}}
        </div>
        <div :class="$style.infoListBox">
            <a :class="$style.line" v-for="(item, index) in infoList" :href="item.link">{{item.name}}</a>
        </div>

    </div>
</template>
<script>
    export default {
        name: "PortalColumn",
        props: {
            infoList: {
                type: Array,
                default: []
            },
            title: {
                type: String,
                default: ""
            }
        },
        data() {
            return {

            }
        }
    }
</script>

<style lang="scss" module>
.container {
    width: 100%;
    text-indent: 3px;
    border: $thin-border;
    .titleBox {
        @include title-box;
    }
    .infoListBox {
        min-height: 150px;
        .line {
            text-decoration: underline;
            display: block;
            height: 15px;
            margin-top: 15px;
        }
    }

}
</style>