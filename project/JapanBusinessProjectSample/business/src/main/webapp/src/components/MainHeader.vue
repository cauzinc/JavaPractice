<template>
    <header class="header">
        <h3 class="title">営業プラス管理システム</h3>
    </header>
</template>

<script>
    export default {
        name: "MainHeader"
    }
</script>

<style lang="scss" scoped>
.header {
    border: 2px solid $main-border-color;
    padding: 0 20px;
    background: $topic-color;
    .title {
        font-size: 1.8rem;
    }
}
</style>