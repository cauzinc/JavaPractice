<template>
    <header :class="$style.header">
        <h3 :class="$style.title">営業プラス管理システム</h3>
    </header>
</template>

<script>
    export default {
        name: "MainHeader"
    }
</script>

<style lang="scss" module>
.header {
    border: 2px solid $main-border-color;
    padding: 0 20px;
    background: $topic-color;
    .title {
        font-size: 1.8rem;
    }
}
</style>