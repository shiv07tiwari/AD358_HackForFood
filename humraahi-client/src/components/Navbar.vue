<template>
  <nav class="navbar navbar-expand-lg app-navbar sticky-top">
    <router-link :to="{name: 'home'}" class="navbar-brand" href="#">
      <img src="@/assets/brand-white.png" style="height: 1.6rem; margin-top:-7px;"/>
    </router-link>
    <button
      class="navbar-toggler"
      type="button"
      data-toggle="collapse"
      data-target="#navbarNavAltMarkup"
      aria-controls="navbarNavAltMarkup"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
      <div class="navbar-nav">
        <router-link tag="a" :to="{name: 'maintenance'}" class="nav-item nav-link" active-class="active">Maintenance Portal</router-link>
        <router-link tag="a" :to="{name: 'roads'}" class="nav-item nav-link" active-class="active">Road Library Portal</router-link>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: "app-navbar",
  props: {},
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">

.app-navbar {
    background-color: #276EF1;
    color: white;
    padding: .5rem 5rem;

    .nav-link {
        color: white;
        text-decoration: none;
    }

}

</style>
