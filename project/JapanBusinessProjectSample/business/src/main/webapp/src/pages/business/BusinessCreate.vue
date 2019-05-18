<template>
    <div class="business-create-container">
        <div class="title">
            <h3 class="text">取引先</h3>
        </div>
        <div class="btn-box">
            <button class="btn" @click="save()">保存</button>
            <button class="btn" @click="save()">保存して新しく作成</button>
            <button class="btn" @click="goBack()">キャンセル</button>
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
                            <input class="item" type="text" v-model="businessName" />
                        </div>
                        <div class="item-wrapper">
                            <span class="label">FAX</span>
                            <input class="item" type="text" v-model="businessFax" />
                        </div>
                    </div>
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label">電話番号</span>
                            <input class="item" type="text" v-model="businessTel" />
                        </div>
                        <div class="item-wrapper">
                            <span class="label">Webサイト</span>
                            <input class="item" type="text" v-model="website" />
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
                            <input class="item" type="text" v-model="address" />
                        </div>
                        <div class="item-wrapper">
                            <span class="label">郵便コード</span>
                            <input class="item" type="text" v-model="postcode" />
                        </div>
                    </div>
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label">得意分野</span>
                            <input class="item" type="text" v-model="advantageField" />
                        </div>
                        <div class="item-wrapper">
                            <span class="label">従業員数</span>
                            <input class="item" type="text" v-model="stuffNum" />
                        </div>
                    </div>
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label">取引先の種別</span>
                            <input class="item" type="text" v-model="accountKind" />
                        </div>
                        <div class="item-wrapper">
                            <span class="label">年間売上</span>
                            <input class="item" type="text" v-model="annualSales" />
                        </div>
                    </div>
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label">銀行名</span>
                            <input class="item" type="text" v-model="bankName" />
                        </div>
                        <div class="item-wrapper">
                            <span class="label">支店名</span>
                            <input class="item" type="text" v-model="bankBranchName" />
                        </div>
                    </div>
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label">口座番号</span>
                            <input class="item" type="text" v-model="bankNumber" />
                        </div>
                        <div class="item-wrapper">
                            <span class="label">支払サイト</span>
                            <input class="item" type="text" v-model="paymentSight" />
                        </div>
                    </div>
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label">名義人</span>
                            <input class="item" type="text" v-model="bankRepresent" />
                        </div>
                    </div>
                </div>
            </div>

            <div class="wrapper system-info">
                <div class="info-title">所有者会社追加情報</div>
                <div class="info-content">
                    <div class="line-comment">
                        <div class="label">備考</div>
                        <textarea class="comment-area" name="comment" v-model="comment"></textarea>
                    </div>
                    <div class="btn-box">
                        <button class="btn" @click="save()">保存</button>
                        <button class="btn" @click="save()">保存して新しく作成</button>
                        <button class="btn" @click="goBack()">キャンセル</button>
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
        name: "BusinessCreate",
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
                bankRepresent: "",
                comment: ""
            }
        },
        methods: {
            ...businessMapActions(['insertBusiness']),
            goBack() {
                this.$router.push({ name: "BusinessHome" });
            },
            save() {
                if(!this.businessName.trim()) {
                    return alert("取引先名は必要です");
                }
                this.insertBusiness({
                    businessName: this.businessName,
                    businessFax: this.businessFax,
                    businessTel: this.businessTel,
                    website: this.website,
                    address: this.address,
                    postcode: this.postcode,
                    advantageField: this.advantageField,
                    stuffNum: this.stuffNum,
                    accountKind: this.accountKind,
                    annualSales: this.annualSales,
                    bankName: this.bankName,
                    bankBranchName: this.bankBranchName,
                    bankNumber: this.bankNumber,
                    paymentSight: this.paymentSight,
                    bankRepresent: this.bankRepresent,
                    comment: this.comment
                }).then(res => {
                    if(res.data && res.data.status === 0) {
                        alert("取引先データ保存しました");
                        this.$router.push({ name: "businessHome" });
                    } else {
                        alert(res.data.msg);
                    }
                });
            }
        }
    }
</script>

<style lang="scss" scoped>
    .business-create-container {
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
            padding-bottom: 30px;
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

                    .line-comment {
                        @include flex-row;
                        align-items: center;
                        margin: 0 10px 20px;
                        .label {
                            display: inline-block;
                            width: 50px;
                            text-align: center;
                            color: cornflowerblue;
                            flex-shrink: 0;
                        }
                        .comment-area {
                            width: 100%;
                            height: 50px;
                        }
                    }
                }
            }
        }


    }
</style>