import { defineStore } from "pinia";
import { ref } from "vue";
import axios from "axios";

export const useUserStore = defineStore(
  "user",
  () => {
    const username = ref("");
    const email = ref("");
    const isLogged = ref(false);
    const avatar = ref("");
    const token = ref("");

    function register(name, email, password) {
      axios
        .post("/api/auth/register", {
          username: name,
          password: password,
        })
        .then(function (response) {
          username.value = name;
          email.value = email;
          token.value = response.data.token;
          console.log(response.data);
          if (token.value === null) {
            throw new Error("error");
          }
        })
        .catch(function (error) {
          if (error.response) {
            console.log(error.response.data); // => the response payload
          }
          throw error;
        });
    }

    function login(name, password) {
      axios
        .post("/api/auth/login", {
          username: name,
          password: password,
        })
        .then(function (response) {
          isLogged.value = true;
          username.value = name;
          token.value = response.data.token;
          console.log(response.data);
          if (token.value === null) {
            throw new Error("error");
          }
        })
        .catch(function (error) {
          if (error.response) {
            console.log(error.response.data); // => the response payload
          }

          throw error;
        });
    }
    function logout() {
      isLogged.value = false;
      token.value = null;
      username.value = null;
    }

    function checkJWT() {
      return true;
      // const decoded = jwtDecode(token);
      // const isExpired = decoded.exp < Date.now() / 1000;

      // if (isExpired) {
      // console.log("token has expired", isExpired)
      // const auth = authState
      // auth.logout()
      // return next({ path: "/auth" })
      //}
    }

    return {
      username,
      email,
      isLogged,
      avatar,
      token,
      register,
      login,
      logout,
      checkJWT,
    };
  },
  {
    persist: {
      storage: localStorage,
    },
  }
);
