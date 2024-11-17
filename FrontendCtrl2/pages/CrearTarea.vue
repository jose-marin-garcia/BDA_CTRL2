<template>
  <div class="crear-tarea p-6">
    <h1 class="text-2xl font-semibold mb-4">Crear Nueva Tarea</h1>

    <form @submit.prevent="crearTarea">
      <div class="mb-4">
        <label for="titulo" class="block text-gray-700">Título</label>
        <input v-model="titulo" type="text" id="titulo" class="w-full p-2 border rounded" required />
      </div>

      <div class="mb-4">
        <label for="descripcion" class="block text-gray-700">Descripción</label>
        <textarea v-model="descripcion" id="descripcion" class="w-full p-2 border rounded" required></textarea>
      </div>

      <div class="mb-4">
        <label for="fechaVencimiento" class="block text-gray-700">Fecha de Vencimiento</label>
        <input v-model="fechaVencimiento" type="datetime-local" id="fechaVencimiento" class="w-full p-2 border rounded" required />
      </div>

      <div class="mb-4">
        <button type="submit" class="px-6 py-3 bg-green-500 text-white rounded hover:bg-green-600">
          Crear Tarea
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      titulo: "",
      descripcion: "",
      fechaVencimiento: "",
    };
  },
  methods: {
    async crearTarea() {
      try {
        const tarea = {
          titulo: this.titulo,
          descripcion: this.descripcion,
          fechaVencimiento: this.fechaVencimiento,
          estado: "pendiente",
          fechaCreacion: new Date().toISOString(),
          id_usuario: this.$store.state.userId,
        };

        const response = await axios.post("http://localhost:8090/api/tarea", tarea);
        this.$router.push("/tareas");
      } catch (error) {
        console.error("Error al crear tarea:", error);
      }
    },
  },
  
};
</script>

  