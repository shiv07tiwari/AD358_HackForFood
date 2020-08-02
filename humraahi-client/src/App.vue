<template>
  <div id="app">
    <div id="nav">
      <Navbar />
    </div>
    <transition name="fade" mode="out-in">
      <router-view />
    </transition>
  </div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";

export default {
  components: {
    Navbar,
  },
};
</script>

<style lang="scss">
#app {
  font-family: "Roboto", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.fade-enter-active,
.fade-leave-active {
  transition-duration: 0.2s;
  transition-property: opacity;
  transition-timing-function: ease;
}

.fade-enter,
.fade-leave-active {
  opacity: 0;
}

.header-fixed {
  width: 100%;
}

.header-fixed > thead,
.header-fixed > tbody,
.header-fixed > thead > tr,
.header-fixed > tbody > tr,
.header-fixed > thead > tr > th,
.header-fixed > tbody > tr > td {
  display: block;
}

.header-fixed > tbody > tr:after,
.header-fixed > thead > tr:after {
  content: " ";
  display: block;
  visibility: hidden;
  clear: both;
}

.header-fixed > tbody {
  overflow-y: auto;
  max-height: 300px;
}

.roads-table {
  .header-fixed > thead > tr > th {
    width: 14.2857142857%;
    float: left;
  }

  .header-fixed > tbody > tr > td {
    width: 14.27%;
    float: left;
  }
}
</style>
