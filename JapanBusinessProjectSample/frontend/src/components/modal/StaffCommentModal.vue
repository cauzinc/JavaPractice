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
	export default {
		name: "StaffCommentModal",
		data() {
			return {
				businessExperience: 0,
				leaderExperience: 0,
				efficiency: 0
			}
		},
		methods: {
			cancel() {
				this.$close();
			},
			submit() {
				let comments = [];
				comments.push(this.businessExperience ? "業務知識経験無い" : "業務知識経験ある");
				comments.push(this.leaderExperience ?  "リーダ知識経験ない" : "リーダ知識経験ある");
				comments.push(this.leaderExperience === 0 ? "生産性高い" :
					this.leaderExperience === 1 ? "生産性中" : "生産性低い");
				let staffComment = comments.join(",");
				this.$emit('submitComment', staffComment);
			}
		}
	}
</script>

<style lang="scss" module>
    .staff-comment-container {
        width: 60%;
        margin: 0 auto;
        background: #fff;
        border-radius: 10px;
        padding: 10px 20px;
        .itemBox {
            @include flex-row-between;
            width: 50%;
            margin: 30px 0;
        }
    }
</style>