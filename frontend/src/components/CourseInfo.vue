<script setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";

// имитация данных (заменишь на API)
const route = useRoute();
const courseId = route.params.id;

const course = ref({
  id: courseId,
  title: "Программирование на Java с нуля до Senior",
  description:
    "Полный курс от переменных до Spring Boot, микросервисов и рефакторинга legacy-кода. 120+ часов практики, 15 проектов в портфолио.",
  cover: "https://cdn.quasar.dev/img/mountains.jpg",
  duration: "120 часов",
  level: "Middle",
  students: 156,
  rating: 4.92,
  createdAt: "15 марта 2025",
});

const teachers = ref([
  {
    id: 1,
    name: "Алексей Воробьёв",
    role: "Lead Java Developer",
    avatar: "https://cdn.quasar.dev/img/avatar5.jpg",
  },
  {
    id: 2,
    name: "Мария Соколова",
    role: "Tech Lead",
    avatar: "https://cdn.quasar.dev/img/avatar3.jpg",
  },
  {
    id: 1,
    name: "Алексей Воробьёв",
    role: "Lead Java Developer",
    avatar: "https://cdn.quasar.dev/img/avatar5.jpg",
  },
  {
    id: 2,
    name: "Мария Соколова",
    role: "Tech Lead",
    avatar: "https://cdn.quasar.dev/img/avatar3.jpg",
  },
]);

const students = ref([
  { id: 1, name: "Иван Петров", progress: 87 },
  { id: 2, name: "Анна Смирнова", progress: 65 },
  { id: 3, name: "Дмитрий Кузнецов", progress: 100 },
  { id: 1, name: "Иван Петров", progress: 87 },
  { id: 2, name: "Анна Смирнова", progress: 65 },
  { id: 3, name: "Дмитрий Кузнецов", progress: 100 },
  { id: 1, name: "Иван Петров", progress: 87 },
  { id: 2, name: "Анна Смирнова", progress: 65 },
  { id: 3, name: "Дмитрий Кузнецов", progress: 100 },
  { id: 3, name: "Дмитрий Кузнецов", progress: 100 },
  { id: 1, name: "Иван Петров", progress: 87 },
  { id: 2, name: "Анна Смирнова", progress: 65 },
  { id: 3, name: "Дмитрий Кузнецов", progress: 100 },
  // ... и ещё 150+
]);

const activeTab = ref("info");
</script>

<template>
  <q-page padding>
    <div class="q-mx-auto content" style="max-width: 1400px">
      <div
        class="relative-position overflow-hidden rounded-borders q-mb-lg shadow-10"
        style="height: 300px"
      >
        <q-img
          :src="course.cover"
          class="full-width full-height"
          fit="cover"
          loading="lazy"
        />

        <div class="absolute-bottom q-pa-xl">
          <div class="text-h3 text-white text-weight-bold text-shadow q-mb-md">
            {{ course.title }}
          </div>
          <div class="row items-center text-white q-gutter-md text-subtitle1">
            <span><q-icon name="schedule" /> {{ course.duration }}</span>
            <span><q-icon name="trending_up" /> {{ course.level }}</span>
            <span
              ><q-icon name="people" /> {{ course.students }} студентов</span
            >
          </div>
        </div>
      </div>

      <div class="row q-col-gutter-xl">
        <div class="col-12 col-lg-8">
          <q-tabs
            v-model="activeTab"
            dense
            class="bg-grey-1 text-grey-8"
            active-color="primary"
            indicator-color="primary"
            align="justify"
            narrow-indicator
          >
            <q-tab name="info" icon="info" label="Информация" />
            <q-tab name="materials" icon="folder_open" label="Материалы" />
            <q-tab name="tasks" icon="assignment" label="Задания" />
          </q-tabs>

          <q-separator />

          <q-tab-panels v-model="activeTab" animated>
            <q-tab-panel name="info" class="q-pa-none">
              <div class="q-pa-xl">
                <div class="text-h6 q-mb-md">О курсе</div>
                <p class="text-body1 text-grey-8 q-mb-lg">
                  {{ course.description }}
                </p>

                <div class="row q-col-gutter-lg">
                  <div class="col-6">
                    <q-list>
                      <q-item>
                        <q-item-section avatar
                          ><q-icon name="today" color="primary"
                        /></q-item-section>
                        <q-item-section
                          >Начало: {{ course.createdAt }}</q-item-section
                        >
                      </q-item>
                      <q-item>
                        <q-item-section avatar
                          ><q-icon name="access_time" color="primary"
                        /></q-item-section>
                        <q-item-section
                          >Длительность: {{ course.duration }}</q-item-section
                        >
                      </q-item>
                    </q-list>
                  </div>
                  <div class="col-6">
                    <q-list>
                      <q-item>
                        <q-item-section avatar
                          ><q-icon name="school" color="primary"
                        /></q-item-section>
                        <q-item-section
                          >Уровень: {{ course.level }}</q-item-section
                        >
                      </q-item>
                      <q-item>
                        <q-item-section avatar
                          ><q-icon name="emoji_events" color="amber"
                        /></q-item-section>
                        <q-item-section>Сертификат по окончании</q-item-section>
                      </q-item>
                    </q-list>
                  </div>
                </div>
              </div>
            </q-tab-panel>

            <!-- Вкладка: Материалы -->
            <q-tab-panel name="materials">
              <div class="q-pa-xl">
                <div class="text-h6 q-mb-lg">Материалы курса (42)</div>
                <q-list separator>
                  <q-item clickable v-ripple>
                    <q-item-section avatar
                      ><q-icon name="play_circle color='red'"
                    /></q-item-section>
                    <q-item-section
                      >Введение в Java. Установка JDK</q-item-section
                    >
                    <q-item-section side
                      ><q-icon name="download"
                    /></q-item-section>
                  </q-item>
                  <q-item clickable v-ripple>
                    <q-item-section avatar
                      ><q-icon name="article" color="blue"
                    /></q-item-section>
                    <q-item-section>Конспект: OOP в Java</q-item-section>
                    <q-item-section side
                      ><q-badge color="green">PDF</q-badge></q-item-section
                    >
                  </q-item>
                  <!-- ещё 40 пунктов -->
                </q-list>
              </div>
            </q-tab-panel>

            <!-- Вкладка: Задания -->
            <q-tab-panel name="tasks">
              <div class="q-pa-xl">
                <div class="text-h6 q-mb-lg">Задания (18)</div>
                <q-markup-table flat bordered>
                  <thead>
                    <tr>
                      <th>Задание</th>
                      <th>Срок</th>
                      <th>Статус</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>Калькулятор на Swing</td>
                      <td>25 ноября</td>
                      <td><q-badge color="green">Сдано</q-badge></td>
                    </tr>
                    <tr>
                      <td>REST API на Spring Boot</td>
                      <td>30 ноября</td>
                      <td><q-badge color="orange">На проверке</q-badge></td>
                    </tr>
                  </tbody>
                </q-markup-table>
              </div>
            </q-tab-panel>
          </q-tab-panels>
        </div>
        <div class="col-12 col-lg-4">
          <q-card flat bordered class="q-mb-lg">
            <q-card-section>
              <div class="text-h6 q-mb-md">Преподаватели</div>
              <div class="scrollable-list">
                <q-list separator>
                  <q-item v-for="t in teachers" :key="t.id" clickable v-ripple>
                    <q-item-section avatar>
                      <q-avatar>
                        <img :src="t.avatar" />
                      </q-avatar>
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>{{ t.name }}</q-item-label>
                    </q-item-section>
                  </q-item>
                </q-list>
              </div>
            </q-card-section>
          </q-card>

          <q-card flat bordered>
            <q-card-section>
              <div class="text-h6 q-mb-md">
                Студенты ({{ course.students }})
              </div>
              <div class="scrollable-list">
                <q-list separator>
                  <q-item v-for="s in students" :key="s.id">
                    <q-item-section avatar>
                      <q-avatar size="36px">
                        <img src="https://cdn.quasar.dev/img/avatar.png" />
                      </q-avatar>
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>{{ s.name }}</q-item-label>
                      <q-item-label caption class="q-mt-xs">
                        <q-linear-progress
                          :value="s.progress / 100"
                          size="6px"
                          rounded
                        />
                        <span class="text-caption text-grey-6"
                          >{{ s.progress }}%</span
                        >
                      </q-item-label>
                    </q-item-section>
                  </q-item>
                </q-list>
              </div>
            </q-card-section>
          </q-card>
        </div>
      </div>
    </div>
  </q-page>
</template>

<style scoped>
.bg-gradient-to-bottom {
  background: linear-gradient(
    to bottom,
    transparent 0%,
    rgba(0, 0, 0, 0.7) 100%
  );
}
.scrollable-list {
  max-height: 400px;
  overflow-y: auto;
  padding-right: 8px;
}
.text-shadow {
  text-shadow: 0 4px 12px rgba(0, 0, 0, 0.8);
}
.my-card:hover {
  transform: translateY(-8px);
  transition: transform 0.3s ease;
}
.content {
  border: 1px solid rgb(99, 99, 99);
  border-radius: 15px;
  padding: 10px;
}
</style>
