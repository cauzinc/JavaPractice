<template>
    <div :class="$style['business-detail-container']">
        <div :class="$style.title">
            <h3 :class="$style.text">取引先情報</h3>
        </div>
        <div :class="$style['btn-box']" v-if="!editMode">
            <button :class="$style.btn" @click="edit()">編集</button>
            <button :class="$style.btn" @click="deleteInfo()">削除</button>
            <button :class="$style.btn">コピー</button>
        </div>
        <div :class="$style['btn-box']" v-else>
            <button :class="$style.btn" @click="update()">保存</button>
            <button :class="$style.btn" @click="update()">保存して新しく作成</button>
            <button :class="$style.btn" @click="cancel()">キャンセル</button>
        </div>

        <div :class="$style['detail-box']">
            <div :class="[$style.wrapper, $style['basic-info']]">
                <div :class="$style['info-title']">取引先情報</div>
                <div :class="$style['info-content']">
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']" style="margin-bottom: 30px;">
                            <span :class="$style.label">取引先所有者</span>
                            <span :class="$style.item">田中 雄一</span>
                        </div>
                    </div>
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label" style="color: red">* 取引名</span>
                            <span v-if="!editMode" :class="$style.item">{{businessName}}</span>
                            <input v-else v-model="businessName" type="text" />

                        </div>
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">FAX</span>
                            <span v-if="!editMode" :class="$style.item">{{businessFax}}</span>
                            <input v-else v-model="businessFax" type="text" />
                        </div>
                    </div>
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">電話番号</span>
                            <span v-if="!editMode" :class="$style.item">{{businessTel}}</span>
                            <input v-else v-model="businessTel" type="text" />
                        </div>
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">Webサイト</span>
                            <span v-if="!editMode" :class="$style.item">{{website}}</span>
                            <input v-else v-model="website" type="text" />
                        </div>
                    </div>
                </div>
            </div>
            <div :class="[$style.wrapper, $style['detail-info']]">
                <div :class="$style['info-title']">詳細情報</div>
                <div :class="$style['info-content']">
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">住所</span>
                            <span v-if="!editMode" :class="$style.item">{{address}}</span>
                            <input v-else v-model="address" type="text" />
                        </div>
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">郵便コード</span>
                            <span v-if="!editMode" :class="$style.item">{{postcode}}</span>
                            <input v-else v-model="postcode" type="text" />
                        </div>
                    </div>
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">得意分野</span>
                            <span v-if="!editMode" :class="$style.item">{{advantageField}}</span>
                            <input v-else v-model="advantageField" type="text" />
                        </div>
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">従業員数</span>
                            <span v-if="!editMode" :class="$style.item">{{stuffNum}}</span>
                            <input v-else v-model="stuffNum" type="text" />
                        </div>
                    </div>
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">取引先の種別</span>
                            <span v-if="!editMode" :class="$style.item">{{accountKind}}</span>
                            <input v-else v-model="accountKind" type="text" />
                        </div>
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">年間売上</span>
                            <span v-if="!editMode" :class="$style.item">{{annualSales}}</span>
                            <input v-else v-model="annualSales" type="text" />
                        </div>
                    </div>
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">銀行名</span>
                            <span v-if="!editMode" :class="$style.item">{{bankName}}</span>
                            <input v-else v-model="bankName" type="text" />
                        </div>
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">支店名</span>
                            <span v-if="!editMode" :class="$style.item">{{bankBranchName}}</span>
                            <input v-else v-model="bankBranchName" type="text" />
                        </div>
                    </div>
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">口座番号</span>
                            <span v-if="!editMode" :class="$style.item">{{bankNumber}}</span>
                            <input v-else v-model="bankNumber" type="text" />
                        </div>
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">支払サイト</span>
                            <span v-if="!editMode" :class="$style.item">{{paymentSight}}</span>
                            <input v-else v-model="paymentSight" type="text" />
                        </div>
                    </div>
                </div>
            </div>

            <div :class="[$style.wrapper, $style['system-info']]">
                <div :class="$style['info-title']">システム情報</div>
                <div :class="$style['info-content']">
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">作成者</span>
                            <span :class="$style.item">{{createUser}}</span>
                        </div>
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">最終更新者</span>
                            <span :class="$style.item">{{updateUser}}</span>
                        </div>
                    </div>
                </div>
            </div>

            <div :class="$style['btn-box']" v-if="editMode">
                <button :class="$style['btn']" @click="update()">保存</button>
                <button :class="$style['btn']" @click="update()">保存して新しく作成</button>
                <button :class="$style['btn']" @click="cancel()">キャンセル</button>
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
                id: "",
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
                updateUser: "",
                editMode: false
            }
        },
        methods: {
            ...businessMapActions(['getBusinessById', 'deleteBusinessById', 'updateBusiness']),
            edit() {
                this.editMode = true;
            },
            cancel() {
                this.editMode = false;
            },
            update() {

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
            },
            update() {
                if(!this.$route.query.businessId) {
                    return alert("data error!");
                }
                this.updateBusiness({
                    id: this.$route.query.businessId,
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
                    updateUser: "admin"
                }).then(res => {
                    if(res.data && res.data.status === 0) {
                        this.editMode = false;
                    } else {
                        alert(res.data.msg);
                    }
                });
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

<style lang="scss" module>
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