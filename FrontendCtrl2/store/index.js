
// Con persistencia
export const state = () => ({
    isAuthenticated: localStorage.getItem('isAuthenticated') === 'true', // Recupera el estado del almacenamiento
    user: JSON.parse(localStorage.getItem('user')), // Por si queremos mantener datos del usuario
  });
  
  export const mutations = {
    setAuthenticated(state, status) {
      state.isAuthenticated = status;
      localStorage.setItem('isAuthenticated', status); // Guarda en localStorage
    },
    setUser(state, userData) {
      state.user = userData;
      localStorage.setItem('user', JSON.stringify(userData)); // Guarda los datos del usuario en localStorage
    },
  };
  
  export const actions = {
    login({ commit }, userData) {
      // Aqui hacemos uso de nuestra api JWT
      commit('setAuthenticated', true);
      commit('setUser', userData);
    },
    logout({ commit }) {
      commit('setAuthenticated', false);
      commit('setUser', null);
      localStorage.removeItem('isAuthenticated'); // Limpia el almacenamiento
      localStorage.removeItem('user');
    },
  };
  
  