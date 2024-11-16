<template>
    <div class="login-page flex justify-center items-center min-h-screen bg-gray-100">
      <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
        <h1 class="text-3xl font-bold text-center text-gray-700 mb-6">Login</h1>
        <form @submit.prevent="login" class="space-y-4">    
          <div>
            <label for="email" class="block text-sm font-medium text-gray-600">Correo Electrónico</label>
            <input
              id="email"
              v-model="email"
              type="email"
              required
              class="mt-2 p-2 w-full border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Ingresa tu correo"
            />
          </div>
          <div>
            <label for="password" class="block text-sm font-medium text-gray-600">Contraseña</label>
            <input
              id="password"
              v-model="password"
              type="password"
              required
              class="mt-2 p-2 w-full border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Ingresa tu contraseña"
            />
          </div>
          <button
            type="submit"
            class="w-full py-2 mt-4 bg-blue-500 text-white font-semibold rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500"
          >
            Login
          </button>
        </form>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  export default {
    data() {
      return {
        email: '',
        password: '',
      };
    },
    methods: {
      async login() {
        const credentials = {
          email: this.email,
          password: this.password,
        };

        try {
          const response = await axios.post('http://localhost:8090/authenticate/login', credentials);
          const { token, userId } = response.data;

          // Almacena el token y el ID del usuario
          this.$store.commit('setAuthenticated', true);
          this.$store.commit('setUserId', userId);
          localStorage.setItem('token', token); // Guarda el token si es necesario

          alert('Inicio de sesión exitoso');
          this.$router.push('/'); // Redirige al dashboard o vista principal
        } catch (error) {
          console.error(error);
          alert('Error al iniciar sesión. Por favor, verifica tus credenciales.');
        }
      },
    },
  };
  </script>
  
<style scoped>
  .login-page {
    background-color: #f7fafc;
  }
</style>