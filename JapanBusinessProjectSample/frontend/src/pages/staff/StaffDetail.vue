<template>
    <div :class="$style['staff-detail-container']">
        <div :class="$style.title">
            <h3 :class="$style.text">取引先担当者情報</h3>
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
                <div :class="$style['info-title']">スタッフ情報</div>
                <div :class="$style['info-content']">
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']" style="margin-bottom: 30px;">
                            <span :class="$style.label" style="width: auto">スタッフ所有者</span>
                            <span :class="$style.item">田中 雄一</span>
                        </div>
                    </div>
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">スタッフ氏名</span>
                            <span v-if="!editMode" :class="$style.item">{{staffName}}</span>
                            <input v-else v-model="staffName" type="text" />

                        </div>
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">性別</span>
                            <span v-if="!editMode" :class="$style.item">{{staffGender === 1 ? "男" : "女"}}</span>
                            <template v-else>
                                <input type="radio" v-model="staffGender" value="1"/> 男&emsp;
                                <input type="radio" v-model="staffGender" value="0"/> 女
                            </template>
                        </div>
                    </div>
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">所属会社</span>
                            <span v-if="!editMode" :class="$style.item">{{businessName}}</span>
                            <input v-else v-model="businessName" type="text" />
                        </div>
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">生年月日</span>
                            <span v-if="!editMode" :class="$style.item">{{birthday}}</span>
                            <input v-else v-model="birthday" type="text" />
                        </div>
                    </div>
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">得意分野</span>
                            <span v-if="!editMode" :class="$style.item">{{preferedField}}</span>
                            <input v-else v-model="preferedField" type="text" />
                        </div>
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">得意言語</span>
                            <span v-if="!editMode" :class="$style.item">{{preferedLanguage}}</span>
                            <input v-else v-model="preferedLanguage" type="text" />
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
                            <span v-if="!editMode" :class="$style.item">{{tel}}</span>
                            <input v-else v-model="tel" type="text" />
                        </div>
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">メール</span>
                            <span v-if="!editMode" :class="$style.item">{{mail}}</span>
                            <input v-else v-model="mail" type="text" />
                        </div>
                    </div>
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">緊急連絡先</span>
                            <span v-if="!editMode" :class="$style.item">{{emergencyAddress}}</span>
                            <input v-else v-model="emergencyAddress" type="text" />
                        </div>
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">現住所</span>
                            <span v-if="!editMode" :class="$style.item">{{address}}</span>
                            <input v-else v-model="address" type="text" />
                        </div>
                    </div>
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">役割</span>
                            <span v-if="!editMode" :class="$style.item">{{position}}</span>
                            <input v-else v-model="position" type="text" />
                        </div>
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">郵便番号</span>
                            <span v-if="!editMode" :class="$style.item">{{postcode}}</span>
                            <input v-else v-model="postcode" type="text" />
                        </div>
                    </div>
                    <div :class="$style.line">
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">評価</span>
                            <span v-if="!editMode" :class="$style.item">{{note}}</span>
                            <input @click="go2Comment" v-else v-model="note" type="text" />
                        </div>
                        <div :class="$style['item-wrapper']">
                            <span :class="$style.label">最寄駅</span>
                            <span v-if="!editMode" :class="$style.item">{{cloestStation}}</span>
                            <input v-else v-model="cloestStation" type="text" />
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
                <button :class="$style.btn" @click="update()">保存</button>
                <button :class="$style.btn" @click="update()">保存して新しく作成</button>
                <button :class="$style.btn" @click="cancel()">キャンセル</button>
            </div>

        </div>
    </div>
</template>

<script>
    import { createNamespacedHelpers } from 'vuex'
    const { mapState: staffMapState, mapActions: staffMapActions } = createNamespacedHelpers('staff')
	export default {
		name: "StaffDetail",
        computed: {
            ...staffMapState(['staffComment'])
        },
        data() {
			return {
				editMode: false,
				staffName: "",
				staffGender: "",
				businessName: "",
				birthday: "",
				preferedField: "",
				preferedLanguage: "",
				tel: "",
				mail: "",
				emergencyAddress: "",
				address: "",
				position: "",
				postcode: "",
				note: "",
				cloestStation: "",
				createUser: "",
                updateUser: ""

            }
        },
        created() {
			this.getStaffDetail({
                staffId: this.$route.query.staffId
            }).then(res => {
            	if(res.data.status !== 0) {
            		alert(res.data.msg);
                } else {
		            this.staffName = res.data.data.staffName;
		            this.staffGender = res.data.data.staffGender;
		            this.businessName = res.data.data.businessName;
		            this.birthday = res.data.data.birthday;
		            this.preferedField = res.data.data.preferedField;
		            this.preferedLanguage = res.data.data.preferedLanguage;
		            this.tel = res.data.data.tel;
		            this.mail = res.data.data.mail;
		            this.emergencyAddress = res.data.data.emergencyAddress;
		            this.address = res.data.data.address;
		            this.position = res.data.data.position;
		            this.postcode = res.data.data.postcode;
		            this.note = res.data.data.note;
		            this.cloestStation = res.data.data.cloestStation;
		            this.createUser = res.data.data.createUser;
                    this.updateUser = res.data.data.updateUser;
                }
            })
        },
        methods: {
            ...staffMapActions(['getStaffDetail', 'deleteStaffById', 'updateStaff']),
	        go2Comment() {
		        let self = this;
		        this.$modal.show('StaffCommentModal', {
			        invokers: {
				        submitComment(comment) {
					        this.$close().then(() => {
						        self.note = comment;
					        })

				        }
			        }
		        })
            },
            update() {
	            if(!this.staffName.trim() || !this.businessName.trim()) {
                    return alert("スタッフ氏名と取引先名を入力してください");
	            }
	            this.updateStaff({
                    staffId: this.$route.query.staffId,
                    staffName: this.staffName,
                    staffGender: this.staffGender,
                    businessName: this.businessName,
                    birthday: this.birthday,
                    preferedField: this.preferedField,
                    preferedLanguage: this.preferedLanguage,
                    tel: this.tel,
                    mail: this.mail,
                    emergencyAddress: this.emergencyAddress,
                    address: this.address,
                    position: this.position,
                    postcode: this.postcode,
                    note: this.note,
                    cloestStation: this.cloestStation,
                    createUser: this.createUser,
                    updateUser: this.updateUser
                }).then(res => {
                	if(res.data.status !== 0) {
                        alert(res.data.msg);
                    } else {
		                this.editMode = false;
                    }

                })
            },
            cancel() {
                this.editMode = false;
            },
            edit() {
	            this.editMode = true;
            },
            deleteInfo() {
            	if(confirm("データを削除しますか？")) {

                    this.deleteStaffById({
                        staffId: this.$route.query.staffId
                    }).then(res => {
                        if(res.data.status !== 0) {
                            alert(res.data.msg);
                        } else {
                            alert("データを削除しました");
                            this.$router.push({ name: "staffHome" });
                        }
                    })
                }
            }
        }
	}
</script>

<style module lang="scss">
    .staff-detail-container{
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