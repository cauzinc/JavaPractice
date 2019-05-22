<template>
    <div class="principle-create-container">
        <div class="title">
            <h3 class="text">取引先担当者</h3>
        </div>
        <div class="btn-box">
            <button class="btn" @click="save()">保存</button>
            <button class="btn" @click="save()">保存して新しく作成</button>
            <button class="btn" @click="goBack()">キャンセル</button>
        </div>
        <div class="detail-box">
            <div class="wrapper basic-info">
                <div class="info-title">取引先担当者情報</div>
                <div class="info-content">
                    <div class="line">
                        <div class="item-wrapper" style="margin-bottom: 30px;">
                            <span class="label" style="width: auto;">取引先担当者所有者</span>
                            <span class="item">田中 雄一</span>
                        </div>
                    </div>
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label" style="color: red">* 担当者氏名</span>
                            <input class="item" type="text" v-model="principleName" />
                        </div>
                        <div class="item-wrapper">
                            <span class="label">部署</span>
                            <input class="item" type="text" v-model="department" />
                        </div>
                    </div>
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label" style="color: red">取引先名</span>
                            <input class="item" type="text" v-model="businessName" />
                        </div>
                        <div class="item-wrapper">
                            <span class="label">役職</span>
                            <input class="item" type="text" v-model="position" />
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
                            <input class="item" type="text" v-model="mail" />
                        </div>
                        <div class="item-wrapper">
                            <span class="label">携帯電話</span>
                            <input class="item" type="text" v-model="tel" />
                        </div>
                    </div>
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label">電話</span>
                            <input class="item" type="text" v-model="phone" />
                        </div>
                        <div class="item-wrapper">
                            <span class="label">FAX</span>
                            <input class="item" type="text" v-model="fax" />
                        </div>
                    </div>
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label">郵便番後</span>
                            <input class="item" type="text" v-model="postcode" />
                        </div>
                        <div class="item-wrapper">
                            <span class="label">住所</span>
                            <input class="item" type="text" v-model="address" />
                        </div>
                    </div>
                    <div class="line">
                        <div class="item-wrapper">
                            <span class="label">趣味</span>
                            <input class="item" type="text" v-model="favorite" />
                        </div>
                    </div>
                </div>
            </div>

            <div class="wrapper system-info">
                <div class="info-title">担当者追加情報</div>
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
    const { mapActions: principleMapActions } = createNamespacedHelpers('principle')
    export default {
        name: "PrincipleCreate",
        data() {
            return {
                principleName: "",
                department: "",
                businessName: "",
                position: "",
                mail: "",
                tel: "",
                phone: "",
                fax: "",
                postcode: "",
                address: "",
                favorite: "",
                comment: ""
            }
        },
        methods: {
            ...principleMapActions(['insertPrinciple']),
            goBack() {
                this.$router.push({ name: "principleHome" })
            },
            save() {
                if(!this.principleName.trim() || !this.businessName.trim()) {
                    return alert("担当者指名と取引先名が必要");
                }
                this.insertPrinciple({
                    principleName: this.principleName,
                    department: this.department,
                    businessName: this.businessName,
                    position: this.position,
                    mail: this.mail,
                    tel: this.tel,
                    phone: this.phone,
                    fax: this.fax,
                    postcode: this.postcode,
                    address: this.address,
                    favorite: this.favorite,
                    comment: this.comment
                }).then(res => {
                    if(res.data.status === 0) {
                        alert('success')
                        // this.goBack()
                    } else {
                        alert(res.data.msg)
                    }
                })
            }
        }
    }
</script>

<style lang="scss" scoped>
    .principle-create-container {
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