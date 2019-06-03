<template>
    <div :class="$style['staff-create-container']">
        <div :class="$style.title">
            <h3 :class="$style.text">スタッフ</h3>
        </div>
        <div :class="$style['btn-box']">
            <button :class="$style.btn" @click="save()">保存</button>
            <button :class="$style.btn" @click="save()">保存して新しく作成</button>
            <button :class="$style.btn" @click="goBack()">キャンセル</button>
        </div>
        <div :class="$style['detail-box']">
            <div :class="[$style.wrapper, $style['basic-info']]">
                <div :class="$style['info-title']">スタッフ情報</div>
                <div :class="$style['info-content']">
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']" style="margin-bottom: 30px;">
                            <span :class="$style.label">取引先所有者</span>
                            <span :class="$style.item">田中 雄一</span>
                        </div>
                    </div>
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label" style="color: red">* スタッフ氏名</span>
                            <input :class="$style.item" type="text" v-model="staffName" />
                        </div>
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">性別</span>
                            <input type="radio" v-model="staffGender" value="1"/> 男&emsp;
                            <input type="radio" v-model="staffGender" value="0"/> 女
                        </div>
                    </div>
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label" style="color: red">* 所属会社</span>
                            <input :class="$style.item" type="text" v-model="businessName" />
                        </div>
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">ポジション</span>
                            <input :class="$style.item" type="text" v-model="position" />
                        </div>
                    </div>
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">得意分野</span>
                            <input :class="$style.item" type="text" v-model="preferedField" />
                        </div>
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">得意言語</span>
                            <input :class="$style.item" type="text" v-model="preferedLanguage" />
                        </div>
                    </div>
                </div>
            </div>
            <div :class="[$style.wrapper, $style['detail-info']]">
                <div :class="$style['info-title']">詳細情報</div>
                <div :class="$style['info-content']">
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">携帯電話</span>
                            <input :class="$style.item" type="text" v-model="tel" />
                        </div>
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">メール</span>
                            <input :class="$style.item" type="text" v-model="mail" />
                        </div>
                    </div>
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">生年月日</span>
                            <input :class="$style.item" type="text" v-model="birthday" />
                        </div>
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">緊急連絡先</span>
                            <input :class="$style.item" type="text" v-model="emergencyAddress" />
                        </div>
                    </div>
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">郵便番号</span>
                            <input :class="$style.item" type="text" v-model="postcode" />
                        </div>
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">現住所</span>
                            <input :class="$style.item" type="text" v-model="address" />
                        </div>
                    </div>
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">最寄駅</span>
                            <input :class="$style.item" type="text" v-model="cloestStation" />
                        </div>
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">評価</span>
                            <input :class="$style.item" type="text" v-model="note" />
                        </div>
                    </div>
                </div>
            </div>

            <div :class="[$style.wrapper, $style['system-info']]">
                <div :class="$style['info-title']">所有者会社追加情報</div>
                <div :class="$style['info-content']">
                    <div :class="$style['line-comment']">
                        <div :class="$style.label">備考</div>
                        <textarea :class="$style['comment-area']" name="comment" v-model="comment"></textarea>
                    </div>
                    <div :class="$style['btn-box']">
                        <button :class="$style.btn" @click="save()">保存</button>
                        <button :class="$style.btn" @click="save()">保存して新しく作成</button>
                        <button :class="$style.btn" @click="goBack()">キャンセル</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import { createNamespacedHelpers } from 'vuex'
    const { mapActions: staffMapActions } = createNamespacedHelpers('staff')
	export default {
		name: "StaffCreate",
        data() {
			return {
				staffName: "",
				staffGender: "",
				businessName: "",
				position: "",
				preferedField: "",
				preferedLanguage: "",
				tel: "",
				mail: "",
				birthday: "",
				emergencyAddress: "",
				postcode: "",
				address: "",
				cloestStation: "",
				note: "",
				comment: ""
            }
        },
        methods: {
            ...staffMapActions(['insertStaff']),
            goBack() {
            	this.$router.push({ name: "staffHome" })
            },
            save() {
            	if(!this.staffName.trim() || !this.businessName.trim()) {
                    return alert("スタッフ氏名と取引先名は必要です");
                }
            	this.insertStaff({
		            staffName: this.staffName,
		            staffGender: this.staffGender,
	                businessName: this.businessName,
		            position: this.position,
                    preferedField: this.preferedField,
                    preferedLanguage: this.preferedLanguage,
                    tel: this.tel,
                    mail: this.mail,
                    birthday: this.birthday,
                    emergencyAddress: this.emergencyAddress,
                    postcode: this.postcode,
                    address: this.address,
                    cloestStation: this.cloestStation,
                    note: this.note,
                    comment: this.comment
                }).then(res => {
                	if(res.data.status !== 0) {
                		alert(res.data.msg);
                    } else {
                		alert("保存しました");
                		this.$router.push({ name: "staffHome" });
                    }
                })
            }
        }
	}
</script>

<style lang="scss" module>
    .staff-create-container {
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