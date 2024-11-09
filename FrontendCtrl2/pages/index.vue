<template>
  <div>
    <HeaderNav />
    <main class="main-content">
      <h1>Bienvenido a la aplicación</h1>
    </main>
  </div>
</template>

<script>
import HeaderNav from "~/components/HeaderNav.vue";

export default {
  components: {
    HeaderNav,
  },
};
</script>

<style scoped>
.main-content {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 80vh;
  text-align: center;
}
</style>
