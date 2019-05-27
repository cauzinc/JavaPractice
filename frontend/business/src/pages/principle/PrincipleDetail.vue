<template>
    <div class="principle-detail-container">
        <div class="title">
            <h3 class="text">取引先担当者情報</h3>
        </div>
        <div class="btn-box" v-if="!editMode">
            <button class="btn" @click="edit()">編集</button>
            <button class="btn" @click="deleteInfo()">削除</button>
            <button class="btn">コピー</button>
        </div>
        <div class="btn-box" v-else>
            <button class="btn" @click="update()">保存</button>
            <button class="btn" @click="update()">保存して新しく作成</button>
            <button class="btn" @click="cancel()">キャンセル</button>
        </div>

        <div class="detail-box">
            <div class="wrapper basic-info">
                <div class="info-title">取引先担当者情報</div>
                <div class="info-content">
                    <div class="line">
                        <div class="item-wrapper" style="margin-bottom: 30px;">
                            <span class="label" style="width: auto">取引先担当者所有者</span>
                            <span class="item">田中 雄一</span>
                        </div>
                    </div>
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label">名前</span>
                            <span v-if="!editMode" class="item">{{principleName}}</span>
                            <input v-else v-model="principleName" type="text" />

                        </div>
                        <div class="item-wrapper">
                            <span class="label">部署</span>
                            <span v-if="!editMode" class="item">{{department}}</span>
                            <input v-else v-model="department" type="text" />
                        </div>
                    </div>
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label">取引先名</span>
                            <span v-if="!editMode" class="item">{{businessName}}</span>
                            <input v-else v-model="businessName" type="text" />
                        </div>
                        <div class="item-wrapper">
                            <span class="label">役職</span>
                            <span v-if="!editMode" class="item">{{position}}</span>
                            <input v-else v-model="position" type="text" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="wrapper detail-info">
                <div class="info-title">詳細情報</div>
                <div class="info-content">
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label">メール</span>
                            <span v-if="!editMode" class="item">{{mail}}</span>
                            <input v-else v-model="mail" type="text" />
                        </div>
                        <div class="item-wrapper">
                            <span class="label">FAX</span>
                            <span v-if="!editMode" class="item">{{fax}}</span>
                            <input v-else v-model="fax" type="text" />
                        </div>
                    </div>
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label">電話番号</span>
                            <span v-if="!editMode" class="item">{{tel}}</span>
                            <input v-else v-model="tel" type="text" />
                        </div>
                        <div class="item-wrapper">
                            <span class="label">携帯番号</span>
                            <span v-if="!editMode" class="item">{{phone}}</span>
                            <input v-else v-model="phone" type="text" />
                        </div>
                    </div>
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label">住所</span>
                            <span v-if="!editMode" class="item">{{address}}</span>
                            <input v-else v-model="address" type="text" />
                        </div>
                        <div class="item-wrapper">
                            <span class="label">郵便コード</span>
                            <span v-if="!editMode" class="item">{{postcode}}</span>
                            <input v-else v-model="postcode" type="text" />
                        </div>
                    </div>
                </div>
            </div>

            <div class="btn-box" v-if="editMode">
                <button class="btn" @click="update()">保存</button>
                <button class="btn" @click="update()">保存して新しく作成</button>
                <button class="btn" @click="cancel()">キャンセル</button>
            </div>

        </div>
    </div>
</template>

<script>
    import { createNamespacedHelpers } from 'vuex'
    const { mapActions: principleMapActions } = createNamespacedHelpers('principle')
    export default {
        name: "PrincipleDetail",
        data() {
            return {
                id: "",
                editMode: false,
                principleName: "",
                department: "",
                businessName: "",
                position: "",
                mail: "",
                fax: "",
                tel: "",
                phone: "",
                address: "",
                postcode: ""
            }
        },
        methods: {
            ...principleMapActions(['getPrincipleById', 'deletePrincipleById', 'updatePrinciple']),
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
                    this.deletePrincipleById({
                        principleId: this.$route.query.principleId
                    }).then(res => {
                        if(res.data && res.data.status === 0) {
                            this.$router.push({ name: "principleHome" })
                        } else {
                            alert(res.data.msg);
                        }
                    });
                }
            },
            update() {
                if(!this.$route.query.principleId) {
                    return alert("data error!");
                }
                this.updatePrinciple({
                    id: this.$route.query.principleId,
                    principleName: this.principleName,
                    department: this.department,
                    businessName: this.businessName,
                    position: this.position,
                    mail: this.mail,
                    fax: this.fax,
                    tel: this.tel,
                    phone: this.phone,
                    address: this.address,
                    postcode: this.postcode,
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
            this.getPrincipleById({
                principleId: this.$route.query.principleId
            }).then(res => {
                this.principleName = res.data.data.principleName;
                this.department = res.data.data.department;
                this.businessName = res.data.data.businessName;
                this.position = res.data.data.position;
                this.mail = res.data.data.mail;
                this.fax = res.data.data.fax;
                this.tel = res.data.data.tel;
                this.phone = res.data.data.phone;
                this.address = res.data.data.address;
                this.postcode = res.data.data.postcode;
            })
        }
    }
</script>

<style lang="scss" scoped>
    .principle-detail-container{
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