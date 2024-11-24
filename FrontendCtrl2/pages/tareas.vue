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
    <!-- Barra de búsqueda -->
    <div class="mb-6">
      <input type="text" v-model="searchQuery" @input="searchTareas"
        placeholder="Buscar tarea por título o descripcion..."
        class="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500" />
    </div>
    <!-- Botones para filtrar tareas por estado -->
    <div class="mb-6">
      <button @click="filterTareas('pendiente')" class="px-6 py-2 bg-yellow-500 text-white rounded hover:bg-yellow-600">
        Pendientes
      </button>
      <button @click="filterTareas('completada')" class="px-6 py-2 bg-green-500 text-white rounded hover:bg-green-600">
        Completadas
      </button>
      <button @click="clearFilter" class="px-6 py-2 bg-gray-500 text-white rounded hover:bg-gray-600">
        Limpiar Filtro
      </button>
    </div>

    <table class="table-auto w-full border-collapse border border-gray-300">
      <thead>
        <tr class="bg-gray-200">
          <th class="py-2 px-4 border-b text-center">Título</th>
          <th class="py-2 px-4 border-b text-center">Descripción</th>
          <th class="py-2 px-4 border-b text-center">Fecha de Vencimiento</th>
          <th class="py-2 px-4 border-b text-center">Estado</th>
          <th class="py-2 px-4 border-b text-center">Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="tarea in tareas" :key="tarea.id">
          <td class="py-2 px-4 border-b text-center">{{ tarea.titulo }}</td>
          <td class="py-2 px-4 border-b text-center">{{ tarea.descripcion }}</td>
          <td class="py-2 px-4 border-b text-center">{{ formatDate(tarea.fechaVencimiento) }}</td>
          <td class="py-2 px-4 border-b text-center">{{ tarea.estado }}</td>
          <td class="py-2 px-4 border-b text-center">
            <div>
              <button @click="editarTarea(tarea.id)" class="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600">
                Editar
              </button>
              <button @click="eliminarTarea(tarea.id)" class="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600">
                Eliminar
              </button>
              <button @click="completarTarea(tarea.id)"
                class="px-4 py-2 bg-green-500 text-white rounded hover:bg-green-600">
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
      searchQuery: "",
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
    async searchTareas() {
      if (!this.searchQuery.trim()) {
        // Si no hay texto en el campo de búsqueda, carga todas las tareas
        await this.fetchTareas();
        return;
      }

      this.loading = true;
      try {
        const response = await axios.get(`http://localhost:8090/api/tarea/search/${this.searchQuery}/${this.usuarioId}`);
        this.tareas = response.data;
      } catch (error) {
        this.error = "Hubo un error al buscar las tareas.";
        console.error(error);
      } finally {
        this.loading = false;
      }
    },
    filterTareas(estado) {
      this.filteredTareas = this.tareas.filter(tarea => tarea.estado === estado);
      this.tareas = this.filteredTareas;
    },
    clearFilter() {
      this.fetchTareas();
    },
    formatDate(dateString) {
      const date = new Date(dateString);
      return `${date.getDate()}/${date.getMonth() + 1
        }/${date.getFullYear()} ${date.getHours()}:${date.getMinutes()}`;
    },
    editarTarea(id) {
      this.$router.push(`/EditarTarea/${id}`);
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
