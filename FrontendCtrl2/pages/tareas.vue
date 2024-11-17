<template>
  <div class="tareas-page p-6">
    <h1 class="text-2xl font-semibold mb-4">Tareas</h1>

    <!-- Botón para volver al inicio alineado a la derecha -->
    <div class="flex justify-between mb-6">
      <router-link to="/CrearTarea" class="px-6 py-3 bg-green-500 text-white rounded hover:bg-green-600">
        Crear Nueva Tarea
      </router-link>
      <router-link to="/" class="px-6 py-3 bg-blue-500 text-white rounded hover:bg-blue-600">
        Volver al inicio
      </router-link>
    </div>

    <table class="min-w-full table-auto border-collapse border border-gray-300">
      <thead>
        <tr class="bg-gray-200">
          <th class="py-2 px-4 border-b">Título</th>
          <th class="py-2 px-4 border-b">Descripción</th>
          <th class="py-2 px-4 border-b">Fecha de Vencimiento</th>
          <th class="py-2 px-4 border-b">Estado</th>
          <th class="py-2 px-4 border-b">Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="tarea in tareas" :key="tarea.id">
          <td class="py-2 px-4 border-b">{{ tarea.titulo }}</td>
          <td class="py-2 px-4 border-b">{{ tarea.descripcion }}</td>
          <td class="py-2 px-4 border-b">{{ formatDate(tarea.fechaVencimiento) }}</td>
          <td class="py-2 px-4 border-b">{{ tarea.estado }}</td>
          <td class="py-2 px-4 border-b">
            <div>
              <router-link :to="`/EditarTarea/${tarea.id}`" class="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600">
                Editar
              </router-link>
              <button @click="eliminarTarea(tarea.id)" class="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600">
                Eliminar
              </button>
              <button @click="completarTarea(tarea.id)" class="px-4 py-2 bg-green-500 text-white rounded hover:bg-green-600">
                Completar
              </button>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      tareas: [], // Lista de tareas
      loading: false, // Estado de carga
      error: null, // Error de la solicitud
    };
  },
  computed: {
    usuarioId() {
      return this.$store.state.userId;
    },
  },
  methods: {
    async fetchTareas() {
      if (!this.usuarioId) {
        this.error = "No se ha encontrado el ID del usuario.";
        return;
      }

      this.loading = true;
      try {
        const response = await axios.get(`http://localhost:8090/api/tarea/getAllUser/${this.usuarioId}`);
        this.tareas = response.data;
      } catch (error) {
        this.error = "Hubo un error al cargar las tareas.";
        console.error(error);
      } finally {
        this.loading = false;
      }
    },
    formatDate(dateString) {
      const date = new Date(dateString);
      return `${date.getDate()}/${
        date.getMonth() + 1
      }/${date.getFullYear()} ${date.getHours()}:${date.getMinutes()}`;
    },
    async eliminarTarea(tareaId) {
      if (!confirm("¿Estás seguro de que deseas eliminar esta tarea?")) {
        return;
      }

      try {
        await axios.delete(`http://localhost:8090/api/tarea/${tareaId}`);
        this.fetchTareas();
      } catch (error) {
        console.error("Error al eliminar tarea:", error);
      }
    },
    async completarTarea(tareaId) {
      try {
        await axios.put(`http://localhost:8090/api/tarea/complete/${tareaId}`);
        this.fetchTareas();
      } catch (error) {
        console.error("Error al completar tarea:", error);
      }
    },
  },
  mounted() {
    this.fetchTareas();
  },
};
</script>

<style scoped>
.error {
  color: red;
}
</style>
