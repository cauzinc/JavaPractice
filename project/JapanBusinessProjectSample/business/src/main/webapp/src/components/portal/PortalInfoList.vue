<template>
    <div :class="$style.container">
        <div :class="$style.titleBox">
            <strong>最近の取引情報</strong>
        </div>
        <div :class="$style.btnBox">
            <button :class="$style.btn">新規</button>
        </div>
        <div :class="$style.listBox">
            <ul>
                <li :class="$style.header">
                    <span :class="$style.tableItem">取引先名</span>
                    <span :class="$style.tableItem">住所</span>
                    <span :class="$style.tableItem">電話</span>
                </li>
                <li :class="$style.items" v-for="(item, index) in infoList">
                    <span :class="$style.tableItem">{{item.value1}}</span>
                    <span :class="$style.tableItem">{{item.value2}}</span>
                    <span :class="$style.tableItem">{{item.value3}}</span>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
    export default {
        name: "PortalInfoList",
        props: {
            infoList: {
                type: Array,
                default: [
                    { value1: "", value2: "", value3: "" },
                    { value1: "", value2: "", value3: "" },
                    { value1: "", value2: "", value3: "" },
                    { value1: "", value2: "", value3: "" },
                    { value1: "", value2: "", value3: "" },
                    { value1: "", value2: "", value3: "" }
                ]
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
        border: 1px solid $main-border-color;
        .titleBox {
            background: $topic-color;
            border-bottom: 1px solid $main-border-color;
            height: 20px;
            width: 100%;
            strong {
                display: inline-block;
                line-height: 20px;
                padding-left: 20px;
            }
        }
        .btnBox {
            width: 100%;
            height: 50px;
            position: relative;
            .btn {
                display: block;
                width: 50px;
                text-align: center;
                padding: 3px 5px;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background: #ddd;
                border-radius: 3px;
                box-shadow: 1px 1px #ccc;
            }
        }
        .listBox {
            .header {
                @include title-box;
            }
            .items {
                border-bottom: $thin-border;
                &:last-child {
                    border-bottom: none;
                }
            }
            .header, .items  {
                font-size: 0;
                .tableItem {
                    display: inline-block;
                    width: calc(100%/3);
                    font-size: 1.2rem;
                    height: 20px;
                    line-height: 20px;
                    text-indent: 3px;

                }
            }

        }
    }
</style>