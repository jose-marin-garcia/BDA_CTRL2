
// Con persistencia
export const state = () => ({
  isAuthenticated: localStorage.getItem('isAuthenticated') === 'true', // Recupera el estado de autenticación
  userId: localStorage.getItem('userId'), // Recupera solo la ID del usuario
});

export const mutations = {
  setAuthenticated(state, status) {
    state.isAuthenticated = status;
    localStorage.setItem('isAuthenticated', status); // Guarda en localStorage
  },
  setUserId(state, userId) {
    state.userId = userId;
    if (userId) {
      localStorage.setItem('userId', userId); // Guarda la ID del usuario en localStorage
    } else {
      localStorage.removeItem('userId'); // Limpia si no hay ID
    }
  },
};

export const actions = {
  login({ commit }, userData) {
    // Aquí recibimos los datos del usuario tras la autenticación
    commit('setAuthenticated', true);
    commit('setUserId', userData.id); // Guardamos solo la ID del usuario
  },
  logout({ commit }) {
    commit('setAuthenticated', false);
    commit('setUserId', null); // Limpiamos la ID del usuario
    localStorage.removeItem('isAuthenticated'); // Limpia el almacenamiento
  },
};

  
  