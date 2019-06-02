<template>
    <div :class="$style['common-business-container']">
        <div :class="$style.title">
            <h4>通常業務</h4>
        </div>
        <div :class="$style.main">

            <div :class="$style['business-box']">
                <div :class="$style['menu-box']" v-for="menu in businessList">
                    <p>{{menu.title}}</p>
                    <ul :class="$style.list">
                        <li v-for="item in menu.items" :class="$style['list-item']">
                            <a href="#">{{item}}</a>
                        </li>
                    </ul>
                </div>
            </div>

            <div :class="$style['quick-insert']" v-if="insertBusinessPage.indexOf($route.name) >= 0">
                <p :class="$style['item-content']">簡易作成</p>
                <div :class="$style['form-box']">
                    <div :class="$style.form">
                        <label for="name-business">*取引名</label> <br />
                        <input id="name-business" type="text" v-model="businessName" /> <br />

                        <label for="tel-business">電話</label> <br />
                        <input id="tel-business" type="text" v-model="businessTel" /> <br />

                        <label for="website-business">Web サイト</label> <br />
                        <input id="website-business" type="text" v-model="website" /> <br />

                        <button @click="insertBusiness()">保存</button>
                    </div>
                </div>
            </div>

            <div :class="$style['quick-insert']" v-if="insertStaffPage.indexOf($route.name) >= 0">
                <p :class="$style['item-content']">簡易作成</p>
                <div :class="$style['form-box']">
                    <div :class="$style.form">
                        <label for="first-name-staff">*姓</label> <br />
                        <input id="first-name-staff" type="text" v-model="firstName" /> <br />

                        <label for="last-name-staff">名</label> <br />
                        <input id="last-name-staff" type="text" v-model="lastName" /> <br />

                        <label for="business-staff">取引先</label> <br />
                        <input id="business-staff" type="text" v-model="businessName" /> <br />

                        <label for="tel-staff">電話</label> <br />
                        <input id="tel-staff" type="text" v-model="tel" /> <br />

                        <label for="mail-staff">メール</label> <br />
                        <input id="mail-staff" type="text" v-model="mail" /> <br />

                        <button @click="insertStaff()">保存</button>
                    </div>
                </div>
            </div>


        </div>

    </div>
</template>

<script>
    import { createNamespacedHelpers } from 'vuex'
    const { mapActions: businessMapActions } = createNamespacedHelpers('business')
    const { mapActions: staffMapActions } = createNamespacedHelpers('staff')
    export default {
        name: "BusinessSupportMenu",
        data() {
            return {
                businessList: [{
                    title: "基本情報",
                    items: ["取引先", "取引先担当者", "スタッフ"]
                }, {
                    title: "営業活動",
                    items: ["相談", "案件", "契約"]
                }, {
                    title: "請求関連",
                    items: ["請求書作成", "注文書作成"]
                }],
                // pages where quick insert display
                insertBusinessPage: ['businessHome'],
                insertStaffPage: ['staffHome'],
                businessName: "",
                businessTel: "",
                website: "",
                tel: "",
                mail: "",
                firstName: "",
                lastName: ""
            }
        },
        methods: {
            ...businessMapActions(['quickInsertBusiness', 'getBusinessList']),
            ...staffMapActions(['quickInsertStaff', 'getStaffList']),
            insertBusiness() {
                if(!this.businessName) {
                    return alert("取引名を入力してください");
                }
                this.quickInsertBusiness({
                    businessName: this.businessName,
                    businessTel: this.businessTel,
                    website: this.website
                }).then(res => {
                    if(res.data.status === 1) {
                        alert(res.data.msg);
                    } else {
                        this.getBusinessList();
                        alert("保存しました");
                        this.businessName = "";
                        this.businessTel = "";
                        this.website = "";
                    }
                });
            },
	        insertStaff() {
            	if(!this.firstName) {
                    return alert("姓を入力してください");
                }
                let staffName = this.firstName + this.lastName;
                this.quickInsertStaff({
                    staffName,
	                businessName: this.businessName,
	                tel: this.tel,
	                mail: this.mail
                }).then(res => {
                	if(res.data.status === 1) {
                		alert(res.data.msg);
                    } else {
                		this.getStaffList();
                		alert("保存しました");
                		this.businessName = "";
                		this.lastName = "";
                		this.firstName = "";
                		this.tel = "";
                		this.mail = "";
                    }
                })

            }
        }
    }
</script>

<style lang="scss" module>
    .common-business-container {
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
            .business-box {
                .menu-box {
                    margin-bottom: 12px;
                    ul {
                        padding-inline-start: 25px;
                    }
                    li {
                        list-style-type: disc;
                        margin: 5px 0;
                    }
                }
            }
            .quick-insert {
                border: 1px solid $main-border-color;
                width: 100%;
                height: auto;
                .item-content {
                    border-bottom: 1px solid $main-border-color;
                    height: 20px;
                    line-height: 20px;
                    text-align: center;
                }

                .form-box {
                    background: $topic-color;
                    height: calc(100% - 21px);
                    padding: 10px 0;
                    .form {
                        width: 80%;
                        margin: 0 auto;
                        label {
                            display: inline-block;
                        }
                        input {
                            width: 100%;
                            margin-bottom: 10px;
                        }
                    }
                }
            }
        }
    }
</style>