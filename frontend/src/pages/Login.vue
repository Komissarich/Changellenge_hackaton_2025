<template>
  <div>

    <!-- Заголовок -->
    <div class="text-h5 text-center q-mb-md">
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

    <!-- Кнопка входа -->
    <q-btn
      label="Войти"
      class="full-width q-mb-md"
      color="dark"
      unelevated
      rounded
      @click="login"
    />

    <!-- Кнопка — переход на регистрацию -->
    <q-btn
      label="Регистрация"
      flat
      class="full-width text-white"
      @click="$router.push('/register')"
    />

  </div>
</template>

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
