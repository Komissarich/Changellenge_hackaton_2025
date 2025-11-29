<template>
  <div>
    <!-- Заголовок -->
    <div class="page-title text-center">Вход</div>

    <!-- Поля -->
    <q-input
      v-model="email"
      filled
      type="email"
      label="Email"
      class="q-mb-md"
    />

    <q-input
      v-model="password"
      filled
      type="password"
      label="Пароль"
      class="q-mb-lg"
    />

    <q-btn
      label="Войти"
      class="auth-btn q-mt-sm q-mb-sm"
      unelevated
      text-color="white"
      :style="{ background: '#1C3975' }"
      @click="$router.push('/login')"
    />

    <!-- Кнопка — переход на регистрацию -->
    <q-btn
      label="Регистрация"
      flat
      class="auth-btn-link q-mt-xs"
      @click="$router.push('/register')"
    />
  </div>
</template>
<style scoped>
.auth-input {
  width: 350px;
  max-width: 100%;
}

.auth-btn {
  width: 240px;
  height: 48px;
  border-radius: 16px;
}

.auth-btn-link {
  color: white;
  font-size: 15px;
}

.page-title {
  font-size: 26px; /* одинаковый размер */
  font-weight: 700; /* жирный */
  color: #1c3975; /* фирменный синий */
  margin-bottom: 20px; /* аккуратное расстояние вниз */
}
</style>
<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
const email = ref("");

const password = ref("");

const router = useRouter();
function login() {
  axios
    .post("/api/teachers/login", {
      email: email.value,
      password: password.value,
    })
    .then(function (response) {
      // token.value = response.data.token;
      console.log(response.data);
      localStorage.setItem("email", email.value);
      localStorage.setItem("password", password.value);
      localStorage.setItem("isAuth", true);
    })
    .catch(function (error) {
      if (error.response) {
        console.log(error.response.data); // => the response payload
      }
      throw error;
    });
  router.push("/");
}
</script>
