<template>
  <div class="register-page flex justify-center items-center min-h-screen bg-gray-100">
    <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
      <h1 class="text-3xl font-bold text-center text-gray-700 mb-6">Registro</h1>
      <form @submit.prevent="register" class="space-y-4">
        <div>
          <label for="name" class="block text-sm font-medium text-gray-600">Nombre</label>
          <input
            id="name"
            v-model="name"
            type="text"
            required
            class="mt-2 p-2 w-full border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="Ingresa tu nombre"
          />
        </div>
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
          Registrarse
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios'; // Importar Axios

export default {
  data() {
    return {
      name: '',
      email: '',
      password: '',
    };
  },
  methods: {
    async register() {
      const user = {
        name: this.name,
        email: this.email,
        password: this.password,
      };

      try {
        const response = await axios.post('http://localhost:8090/api/usuario/register', user); // URL del backend
        alert(response.data); // Mostrar mensaje del servidor
        this.name = '';
        this.email = '';
        this.password = '';
      } catch (error) {
        console.error(error);
        alert('Error al registrarse. Por favor, inténtalo de nuevo.');
      }
    },
  },
};
</script>

<style scoped>
.register-page {
  background-color: #f7fafc;
}
</style>


