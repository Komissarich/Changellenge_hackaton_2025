<template>
  <div class="auth-container">
    <div class="auth-card">
      <h2 class="title">
        {{ mode === 'login' ? 'Вход' : 'Регистрация' }}
      </h2>

      <form @submit.prevent="handleSubmit">
        <transition name="fade">
          <div v-if="mode === 'register'">
            <label>Имя</label>
            <input v-model="name" type="text" placeholder="Ваше имя" required />
          </div>
        </transition>

        <label>Email</label>
        <input v-model="email" type="email" placeholder="you@example.com" required />

        <label>Пароль</label>
        <input v-model="password" type="password" placeholder="••••••••" required />

        <button class="main-btn">
          {{ mode === 'login' ? 'Войти' : 'Зарегистрироваться' }}
        </button>
      </form>

      <p class="switch">
        <span v-if="mode === 'login'">Нет аккаунта?</span>
        <span v-else>Уже есть аккаунт?</span>

        <button class="link-btn" @click="toggleMode">
          {{ mode === 'login' ? 'Создать аккаунт' : 'Войти' }}
        </button>
      </p>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const mode = ref('login')

const name = ref('')
const email = ref('')
const password = ref('')

const toggleMode = () => {
  mode.value = mode.value === 'login' ? 'register' : 'login'
}

const handleSubmit = () => {
  const payload = {
    email: email.value,
    password: password.value,
    ...(mode.value === 'register' && { name: name.value }),
  }

  console.log('Отправка формы:', mode.value, payload)
  // здесь ты подключишь запросы на backend
}
</script>

<style scoped>
.auth-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(135deg, #c7d8ff, #f0f4ff);
}

.auth-card {
  width: 380px;
  padding: 32px;
  border-radius: 24px;
  background: white;
  box-shadow: 0 20px 50px rgba(0,0,0,0.12);
  display: flex;
  flex-direction: column;
  animation: pop 0.4s ease;
}

@keyframes pop {
  from { transform: scale(0.94); opacity: 0; }
  to { transform: scale(1); opacity: 1; }
}

.title {
  margin-bottom: 20px;
  font-size: 26px;
  font-weight: 700;
  color: #2b2b2b;
}

label {
  font-size: 14px;
  color: #444;
  margin-top: 12px;
  margin-bottom: 6px;
  display: block;
}

input {
  width: 100%;
  padding: 12px;
  border-radius: 12px;
  border: 1px solid #ccd6ee;
  outline: none;
  font-size: 15px;
  transition: 0.2s;
}

input:focus {
  border-color: #7b9dff;
  box-shadow: 0 0 6px rgba(123,157,255,0.4);
}

.main-btn {
  margin-top: 22px;
  padding: 12px;
  width: 100%;
  border: none;
  background: #7b9dff;
  color: white;
  font-weight: 600;
  font-size: 16px;
  border-radius: 14px;
  cursor: pointer;
  transition: 0.2s;
}

.main-btn:hover {
  background: #5e7cf3;
}

.switch {
  margin-top: 18px;
  font-size: 14px;
  color: #555;
}

.link-btn {
  background: none;
  border: none;
  font-size: 14px;
  color: #5e7cf3;
  cursor: pointer;
  text-decoration: underline;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.25s;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
