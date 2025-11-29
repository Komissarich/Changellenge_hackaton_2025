<template>
  <q-layout class="auth-layout">
    <q-page-container>
      <q-page
        class="row items-center justify-center q-pa-xl"
        style="min-height: 100vh"
      >
        <div class="auth-card q-pa-xl">
          <!-- Лого + название — ТОЛЬКО ЗДЕСЬ -->
          <div class="row items-center justify-center q-mb-lg">
            <img src="~assets/icon.png" class="auth-logo" alt="logo" />
            <div class="app-title">ПСБ Learn</div>
          </div>

          <!-- сюда рендерятся Login.vue или Register.vue -->
          <router-view />
        </div>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script>
export default {
  name: "AuthLayout",
};
</script>

<style scoped>
.auth-layout {
  background: #eef3ff;
}

.title-main,
.title-main * {
  color: #7b90b9 !important;
}

.app-title {
  font-size: 28px; /* увеличили */
  font-weight: 700;
  color: #1c3975;
}

/* Сам синий блок */
.auth-card {
  width: 360px;
  max-width: calc(100% - 48px);
  background: #81b6ff;
  border-radius: 28px;
  box-shadow: 0 20px 40px rgba(20, 40, 80, 0.18);
  color: #fff;
  text-align: center;
  padding-top: 28px;
  padding-bottom: 32px;
}

/* логотип слева от названия */
.auth-logo {
  width: 44px;
  height: 44px;
}

/* название рядом с логотипом */
.auth-title {
  font-weight: 600;
  color: #fff;
}

/* мелкие утилиты ниже для форм */
.auth-input {
  background: rgba(255, 255, 255, 0.9);
  border-radius: 8px;
  padding: 12px;
  color: #0b2550;
  box-shadow: 8px 8px 0 rgba(0, 0, 0, 0.06) inset;
}

/* кнопки */
.auth-btn {
  width: 60%;
  margin: 12px auto 0;
  border-radius: 12px;
}
.auth-btn {
  width: 160px; /* кнопка как на макете */
  height: 44px;
  border-radius: 10px;
  font-weight: 600;
  font-size: 16px;
}

.auth-link {
  color: #ffffff;
  font-weight: 500;
  text-decoration: none;
  letter-spacing: 1px;
}

.auth-input {
  width: 360px;
  border-radius: 10px;
}

.auth-btn {
  width: 200px;
  height: 44px;
  border-radius: 10px;
  font-weight: 600;
  font-size: 16px;
}

.auth-btn-link {
  color: white !important;
  font-size: 16px;
  font-weight: 500;
}

.auth-btn {
  width: 230px;
  height: 48px;
  border-radius: 10px;
  background: #0d2b7d; /* Тёмно-синий */
  color: white;
  font-weight: 600;
  font-size: 15px;
  letter-spacing: 0.5px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0 auto;
  margin-top: 25px;
  border: none;
  cursor: pointer;
  transition: 0.2s;
}

.auth-btn:hover {
  opacity: 0.85;
}

.auth-link-btn {
  margin-top: 20px;
  text-align: center;
  font-size: 15px;
  font-weight: 500;
  color: white;
  cursor: pointer;
}

.auth-link-btn:hover {
  text-decoration: underline;
}
</style>
