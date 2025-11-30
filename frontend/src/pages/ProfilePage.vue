<template>
  <q-page class="q-pa-xl bg-white">

    <div class="row q-col-gutter-xl items-start">

      <!-- ЛЕВАЯ КАРТОЧКА: Фото + ФИО + должность -->
      <div class="col-12 col-md-3">
        <q-card flat bordered class="q-pa-lg"
          style="border-radius: 16px; box-shadow: 0 4px 12px rgba(0,0,0,0.06);">

          <div class="column items-center">

            <!-- Прямоугольное фото -->
            <q-img
              src="https://i.pravatar.cc/400"
              style="width: 100%; height: 220px; border-radius: 12px; object-fit: cover;"
            />

            <!-- ФИО -->
            <div class="text-h6 q-mt-md text-center">
              {{ user.name }}
            </div>

            <!-- Должность -->
            <div class="text-grey-7 text-center">
              {{ user.role }}
            </div>

          </div>

        </q-card>
      </div>

      <!-- ЦЕНТР: Информация + редактирование -->
      <div class="col-12 col-md-6">
        <q-card flat bordered class="q-pa-xl"
          style="border-radius: 16px; box-shadow: 0 4px 12px rgba(0,0,0,0.06);">

          <div class="text-h5 q-mb-xl text-dark text-center">
            Личная информация
          </div>

          <!-- Email -->
          <div class="q-mb-lg">
            <div class="text-grey-7 q-mb-xs">Email</div>
            <q-input v-model="user.email" outlined dense bg-color="white" />
          </div>

          <q-separator class="q-my-md" />

          <!-- Телефон -->
          <div class="q-mb-lg">
            <div class="text-grey-7 q-mb-xs">Телефон</div>
            <q-input v-model="user.phone" outlined dense />
          </div>

          <q-separator class="q-my-md" />

          <!-- Город -->
          <div>
            <div class="text-grey-7 q-mb-xs">Город</div>
            <q-input v-model="user.city" outlined dense />
          </div>

        </q-card>
      </div>

      <!-- ПРАВАЯ КОЛОНКА: кнопка выхода -->
      <div class="col-12 col-md-3">
        <q-card flat bordered class="q-pa-lg"
          style="border-radius: 16px; box-shadow: 0 4px 12px rgba(0,0,0,0.06);">

          <div class="column items-center">

            <q-btn
              label="Выйти"
              class="full-width"
              size="md"
              unelevated
              style="background:#1C3975; color: white;"
              @click="logout"
            />

          </div>

        </q-card>
      </div>

    </div>

  </q-page>
</template>




<script>
export default {
  name: "ProfilePage",

  data() {
    return {
      user: {
        name: "Смирнова Полина Сергеевна",
        role: "Преподаватель программирования",
        email: "Smirnova@example.com",
        phone: "+7 (900) 123-45-67",
        city: "Москва"
      }
    };
  },

  methods: {
    logout() {
      this.$router.push("/login");
    }
  }
};
</script>
