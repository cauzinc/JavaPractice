<template>
    <div :class="$style['staff-comment-container']">
        <div :class="$style.itemBox">
            <span>業務知識経験</span>
            <span><input name="businessExperience" type="radio" v-model="businessExperience" value="0" /> ある &emsp;</span>
            <span><input name="businessExperience" type="radio" v-model="businessExperience" value="1" /> 無い</span>
        </div>
        <div :class="$style.itemBox">
            <span>リーダ知識経験</span>
            <span><input name="leaderExperience" type="radio" v-model="leaderExperience" value="0" /> ある &emsp;</span>
            <span><input name="leaderExperience" type="radio" v-model="leaderExperience" value="1" /> 無い</span>
        </div>
        <div :class="$style.itemBox">
            <span>生産性</span>
            <span><input name="efficiency" type="radio" v-model="efficiency" value="0" /> 高い &emsp;</span>
            <span><input name="efficiency" type="radio" v-model="efficiency" value="1" /> 中 &emsp;</span>
            <span><input name="efficiency" type="radio" v-model="efficiency" value="2" /> 低い &emsp;</span>
        </div>

        <div :class="$style.btnBox">
            <button @click="submit()">確認</button>
            <button @click="cancel()">キャンセル</button>

        </div>

    </div>
</template>

<script>
    import { createNamespacedHelpers } from 'vuex'
    const { mapActions: staffMapActions } = createNamespacedHelpers('staff')
	export default {
		name: "StaffComment",
        data() {
			return {
				businessExperience: 0,
				leaderExperience: 0,
				efficiency: 0
            }
        },
        methods: {
            ...staffMapActions(['submitComment']),
			cancel() {
                this.$router.go(-1);
            },
            submit() {
				let comments = [];
				comments.push(this.businessExperience ? "業務知識経験ある" : "業務知識経験無い");
				comments.push(this.leaderExperience ? "リーダ知識経験ある" : "リーダ知識経験ない");
	            comments.push(this.leaderExperience === 0 ? "生産性高い" :
		            this.leaderExperience === 1 ? "生産性中" : "生産性低い");
	            let staffComment = comments.join(",");
	            this.submitComment(staffComment);

                // this.$router.push({ name: this.$router.from, query: { from: "staffComment" } });
            }
        }
	}
</script>

<style lang="scss" module>
.staff-comment-container {
    width: 60%;
    margin: 0 auto;
    padding-top: 30px;
    .itemBox {
        @include flex-row-between;
        margin: 30px 0;
    }
}
</style>