<template>
  <div>

    <!-- Заголовок -->
   <div class="page-title text-center">
  Вход
</div>


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
  font-size: 26px;      /* одинаковый размер */
  font-weight: 700;     /* жирный */
  color: #1C3975;       /* фирменный синий */
  margin-bottom: 20px;  /* аккуратное расстояние вниз */
}


</style>
<script>
export default {
  data() {
    return {
      email: '',
      password: ''
    }
  },

  methods: {
    login() {
      // здесь твоя логика авторизации
      // успешный вход → переходим на главную

      this.$router.push('/app/home');
    }
  }
}
</script>
