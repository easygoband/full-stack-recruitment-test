<script setup>
  import { RouterView } from 'vue-router'
  
</script>

<template>
<div>
    <RouterView />
    <notifications position="top center" width="90%" />
</div>
</template>

<style lang="scss"  >
.vue-notification-group{
  margin-top: 2rem;
}

</style>