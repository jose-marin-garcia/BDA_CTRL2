export default {
  
  // Desactivar renderizado del lado del servidor (SSR)
  ssr: false,

  // Objetivo de despliegue (estático en este caso)
  target: 'static',

  // Configuración de la cabecera HTML
  head: {
    title: 'FrontendCtrl2',
    htmlAttrs: {
      lang: 'en',
    },
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: '' },
      { name: 'format-detection', content: 'telephone=no' },
    ],
    link: [{ rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }],
  },

  // Ruta global de CSS
  css: [
    '@/assets/css/tailwind.css', // Ruta al archivo tailwind.css donde importas las directivas @tailwind
  ],

  // Plugins para ejecutar antes de renderizar la página
  plugins: [],

  // Importación automática de componentes
  components: true,

  // Módulos para el desarrollo y construcción (recomendado)
  buildModules: [
    // Agregar TailwindCSS como módulo de construcción
    '@nuxt/postcss8',
  ],

  // Módulos adicionales
  modules: [],

  // Configuración de construcción
  build: {
    postcss: {
      plugins: {
        tailwindcss: {},
        autoprefixer: {},
      },
    },
  },
};
