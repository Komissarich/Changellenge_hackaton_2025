<template>
  <div class="column items-center">

    <div class="page-title text-center">Регистрация</div>


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
  font-size: 26px;      /* одинаковый размер */
  font-weight: 700;     /* жирный */
  color: #1C3975;       /* фирменный синий */
  margin-bottom: 20px;  /* аккуратное расстояние вниз */
}


</style>


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
