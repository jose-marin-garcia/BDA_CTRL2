<template>
    <div class="editar-tarea p-6">
      <h1 class="text-2xl font-semibold mb-4">Editar Tarea</h1>
  
      <form @submit.prevent="editarTarea">
        <div class="mb-4">
          <label for="titulo" class="block text-gray-700">Título</label>
          <input v-model="tarea.titulo" type="text" id="titulo" class="w-full p-2 border rounded" required />
        </div>
        
        <div class="mb-4">
          <label for="descripcion" class="block text-gray-700">Descripción</label>
          <textarea v-model="tarea.descripcion" id="descripcion" class="w-full p-2 border rounded" required></textarea>
        </div>
        
        <div class="mb-4">
          <label for="fechaVencimiento" class="block text-gray-700">Fecha de Vencimiento</label>
          <input v-model="tarea.fechaVencimiento" type="datetime-local" id="fechaVencimiento" class="w-full p-2 border rounded" required />
        </div>
        
        <div class="mb-4">
          <button type="submit" class="px-6 py-3 bg-blue-500 text-white rounded hover:bg-blue-600">
            Guardar Cambios
          </button>
        </div>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        tarea: {
          id: null,
          titulo: '',
          descripcion: '',
          fechaVencimiento: '',
        },
      };
    },
    created() {
      this.fetchTarea();
    },
    methods: {
      async fetchTarea() {
        const tareaId = this.$route.params.id;
        try {
          const response = await axios.get(`http://localhost:8090/api/tarea/${tareaId}`);
          this.tarea = response.data;
        } catch (error) {
          console.error('Error al obtener tarea:', error);
        }
      },
      async editarTarea() {
        try {
          const tareaId = this.$route.params.id;
          await axios.put(`http://localhost:8090/api/tarea/${tareaId}`, this.tarea);
          this.$router.push('/tareas'); // Redirige a la página de tareas después de editar
        } catch (error) {
          console.error('Error al editar tarea:', error);
        }
      },
    },
  };
  </script>
  