<template>
  <div class="column items-center">

    <div class="text-h4 text-white q-mb-lg">Регистрация</div>

    <q-input
      filled
      v-model="email"
      label="Email"
      class="auth-input q-mb-md"
    />

    <q-input
      filled
      v-model="password"
      type="password"
      label="Пароль"
      class="auth-input q-mb-xl"
    />

    <!-- ТЁМНО-СИНЯЯ КНОПКА СОЗДАТЬ АККАУНТ -->
    <q-btn
      color="#0d2c6b"
      text-color="white"
      label="Создать аккаунт"
      class="auth-btn"
      @click="register"
    />

    <q-btn
      flat
      label="Войти"
      to="/login"
      class="auth-btn-link q-mt-md"
    />

  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const email = ref('')
const password = ref('')

function register() {
  // логика создания профиля
  router.push('/login') // или на главную — как тебе надо
}
</script>
