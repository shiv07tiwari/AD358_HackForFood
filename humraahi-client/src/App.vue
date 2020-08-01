<template>
  <div id="app">
    <div id="nav">
      <Navbar />
    </div>
    <router-view/>
  </div>
</template>

<script>

import Navbar from '@/components/Navbar.vue';

export default {
  components: {
    Navbar
  }
}
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
</style>
