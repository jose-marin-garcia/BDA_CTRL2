<template>
    <nav class="header-nav">
      <div class="logo">
        <h2>ChoreFlow</h2>
      </div>
      <div class="auth-buttons">
        <button v-if="!isAuthenticated" @click="navigateTo('/register')">Registrarse</button>
        <button v-if="!isAuthenticated" @click="navigateTo('/login')">Login</button>
        <button v-if="isAuthenticated" @click="navigateTo('/tareas')">Mis Tareas</button>
      </div>
    </nav>
  </template>
  
  <script>
  export default {
    computed: {
      // Comprobar si el usuario está autenticado desde el store 
      isAuthenticated() {
        return this.$store.state.isAuthenticated;
      }
    },
    methods: {
      navigateTo(path) {
        this.$router.push(path);
      }
    },
  };
  </script>
  
  <style scoped>
  .header-nav {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 1rem 2rem;
    background-color: #6d9751;
    color: #fff065;
  }
  
  .auth-buttons button {
    margin-left: 1rem;
    background-color: #003f83;
    color: #fff;
    border: none;
    padding: 1rem 2rem;
    font-size: 1.1rem;
    border-radius: 6px;
    cursor: pointer;
  }
  
  .auth-buttons button:hover {
    background-color: #0056b3;
  }
  </style>
  