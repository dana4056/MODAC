import { defineStore } from "pinia";
import { ref } from "vue";

export const useAuthStore = defineStore("auth", () => {
  const isLoggedIn = ref(true);
  const login = () => {
    isLoggedIn.value = true;
  };
  const logout = () => {
    isLoggedIn.value = false;
  };

  return { isLoggedIn, login, logout };
});
