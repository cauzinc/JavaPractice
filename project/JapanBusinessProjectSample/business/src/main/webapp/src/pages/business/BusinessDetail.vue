<template>
    <div class="business-detail-container">
        <div class="title">
            <h3 class="text">取引先情報</h3>
        </div>
        <div class="btn-box">
            <button class="btn" @click="edit()">編集</button>
            <button class="btn" @click="deleteInfo()">削除</button>
            <button class="btn">コピー</button>
        </div>
        <div class="detail-box">
            <div class="wrapper basic-info">
                <div class="info-title">取引先情報</div>
                <div class="info-content">
                    <div class="line">
                        <div class="item-wrapper" style="margin-bottom: 30px;">
                            <span class="label">取引先所有者</span>
                            <span class="item">田中 雄一</span>
                        </div>
                    </div>
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label" style="color: red">* 取引名</span>
                            <span class="item">{{businessName}}</span>
                        </div>
                        <div class="item-wrapper">
                            <span class="label">FAX</span>
                            <span class="item">{{businessFax}}</span>
                        </div>
                    </div>
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label">電話番号</span>
                            <span class="item">{{businessTel}}</span>
                        </div>
                        <div class="item-wrapper">
                            <span class="label">Webサイト</span>
                            <span class="item">{{website}}</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="wrapper detail-info">
                <div class="info-title">詳細情報</div>
                <div class="info-content">
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label">住所</span>
                            <span class="item">{{address}}</span>
                        </div>
                        <div class="item-wrapper">
                            <span class="label">郵便コード</span>
                            <span class="item">{{postcode}}</span>
                        </div>
                    </div>
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label">得意分野</span>
                            <span class="item">{{advantageField}}</span>
                        </div>
                        <div class="item-wrapper">
                            <span class="label">従業員数</span>
                            <span class="item">{{stuffNum}}</span>
                        </div>
                    </div>
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label">取引先の種別</span>
                            <span class="item">{{accountKind}}</span>
                        </div>
                        <div class="item-wrapper">
                            <span class="label">年間売上</span>
                            <span class="item">{{annualSales}}</span>
                        </div>
                    </div>
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label">銀行名</span>
                            <span class="item">{{bankName}}</span>
                        </div>
                        <div class="item-wrapper">
                            <span class="label">支店名</span>
                            <span class="item">{{bankBranchName}}</span>
                        </div>
                    </div>
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label">口座番号</span>
                            <span class="item">{{bankNumber}}</span>
                        </div>
                        <div class="item-wrapper">
                            <span class="label">支払サイト</span>
                            <span class="item">{{paymentSight}}</span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="wrapper system-info">
                <div class="info-title">システム情報</div>
                <div class="info-content">
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label">作成者</span>
                            <span class="item">{{createUser}}</span>
                        </div>
                        <div class="item-wrapper">
                            <span class="label">最終更新者</span>
                            <span class="item">{{updateUser}}</span>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</template>

<script>
    import { createNamespacedHelpers } from 'vuex'
    const { mapActions: businessMapActions } = createNamespacedHelpers('business')
    export default {
        name: "BusinessDetail",
        data() {
            return {
                businessName: "",
                businessFax: "",
                businessTel: "",
                website: "",
                address: "",
                postcode: "",
                advantageField: "",
                stuffNum: "",
                accountKind: "",
                annualSales: "",
                bankName: "",
                bankBranchName: "",
                bankNumber: "",
                paymentSight: "",
                createUser: "",
                updateUser: ""
            }
        },
        methods: {
            ...businessMapActions(['getBusinessById', 'deleteBusinessById']),
            edit() {

            },
            deleteInfo() {
                if(confirm("このデータを削除しますか")) {
                    this.deleteBusinessById({
                        businessId: this.$route.query.businessId
                    }).then(res => {
                        if(res.data && res.data.status === 0) {
                            this.$router.push({ name: "businessHome" })
                        } else {
                            alert(res.data.msg);
                        }
                    });
                }
            }
        },
        created() {
            this.getBusinessById({
                businessId: this.$route.query.businessId
            }).then(res => {
                this.businessName = res.data.data.businessName;
                this.businessFax = res.data.data.businessFax;
                this.businessTel = res.data.data.businessTel;
                this.website = res.data.data.website;
                this.address = res.data.data.address;
                this.postcode = res.data.data.postcode;
                this.advantageField = res.data.data.advantageField;
                this.stuffNum = res.data.data.stuffNum;
                this.accountKind = res.data.data.accountKind;
                this.annualSales = res.data.data.annualSales;
                this.bankName = res.data.data.bankName;
                this.bankBranchName = res.data.data.bankBranchName;
                this.bankNumber = res.data.data.bankNumber;
                this.paymentSight = res.data.data.paymentSight;
                this.createUser = res.data.data.createUser;
                this.updateUser = res.data.data.updateUser;
            })
        }
    }
</script>

<style lang="scss" scoped>
.business-detail-container{
    .title {
        width: 100%;
        height: 40px;
        padding-left: 15px;
        .text {
            font-weight: bold;
            font-size: 2rem;
            line-height: 40px;
        }
    }
    .btn-box {
        height: 50px;
        @include flex-row;
        align-items: center;
        justify-content: center;
        .btn {
            display: inline-block;
            margin: 0 5px;
        }
    }
    .detail-box {
        .wrapper {
            width: 90%;
            margin: 0 auto;
            .info-title {
                @include title-box;
                border: $thin-border;
                text-indent: 10px;
                width: calc(100% - 2px);
            }
            .info-content {
                background: #d5d5d5;
                padding: 30px 50px;
                .line {
                    width: 100%;
                    @include flex-row;
                    align-items: center;
                    margin: 0 10px;
                    font-size: 0;
                    .item-wrapper {
                        display: inline-block;
                        width: 50%;
                        font-size: 12px;
                        margin-bottom: 10px;
                        .label {
                            display: inline-block;
                            width: 100px;
                            text-align: center;
                            color: cornflowerblue;
                        }
                    }
                }
            }
        }
    }
}
</style>