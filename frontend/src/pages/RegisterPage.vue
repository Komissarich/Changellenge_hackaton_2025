<template>
  <div class="column items-center">
    <div class="page-title text-center">Регистрация</div>

    <q-input filled v-model="email" label="Email" class="auth-input q-mb-md" />

    <q-input filled v-model="name" label="ФИО" class="auth-input q-mb-md" />
    <q-input
      filled
      v-model="password"
      type="password"
      label="Пароль"
      class="auth-input q-mb-md"
    />

    <!-- Кнопка создать аккаунт -->
    <q-btn
      label="Создать аккаунт"
      class="auth-btn q-mb-sm"
      unelevated
      text-color="white"
      :style="{ background: '#1C3975' }"
      @click="register"
    />

    <!-- Кнопка — перейти на вход -->
    <q-btn
      flat
      label="Войти"
      class="auth-btn-link q-mt-xs"
      @click="$router.push('/login')"
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
import axios from "axios";
const router = useRouter();

const email = ref("");
const name = ref("");
const password = ref("");

function register() {
  axios
    .post("http://localhost:8081/api/teachers/register", {
      name: name.value,
      email: email.value,
      password: password.value,
    })
    .then(function (response) {
      console.log(response);
      console.log(response.data);
      localStorage.setItem("name", name.value);
      localStorage.setItem("email", email.value);
      localStorage.setItem("password", password.value);
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
