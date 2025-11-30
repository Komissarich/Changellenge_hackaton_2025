<script setup>
import axios from "axios";
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import { useRouter } from "vue-router";
import CreateMaterial from "./CreateMaterial.vue";
import Material from "./Material.vue";

import CreateTaskDialog from "./CreateTaskDialog.vue";

const router = useRouter();
onMounted(() => {
  localStorage.setItem("current_course", route.params.course_id);
  axios
    .post("http://localhost:8081/api/courses/findCourse", {
      id: route.params.course_id,
    })
    .then(function (response) {
      console.log("Course", response.data);
      course.value.title = response.data.name;
      course.value.description = response.data.description;
      course.value.duration = response.data.duration;
      course.value.level = response.data.difficulty;
      course.value.cover = response.data.cover_link;
      var today = new Date();
      var day = String(today.getDate()).padStart(2, "0");
      var month = String(today.getMonth() + 1).padStart(2, "0");
      var year = today.getFullYear();
      var currentDate = `${day}.${month}.${year}`;
      course.value.createdAt = currentDate;
    })
    .catch(function (error) {
      if (error.response) {
        console.log(error.response.data);
      }
    });
  console.log("id", route.params.course_id);
  axios
    .post("http://localhost:8081/api/courses/getMaterials", {
      course_id: route.params.course_id,
    })
    .then(function (response) {
      console.log("materials", response.data);
      materials.value = response.data;
    })
    .catch(function (error) {
      if (error.response) {
        console.log(error.response.data);
      }
    });
});

const materials = ref([]);

const showDialog = ref(false);
const showTaskDialog = ref(false);
const route = useRoute();
const courseId = route.params.id;

const course = ref({});

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
]);
const tasks = ref([
  {
    id: 1,
    title: 'Написать эссе на тему "Почему я люблю Vue 3"',
    description:
      "<p>Объём — не менее 1000 символов. Используйте <strong>жирный текст</strong> и списки.</p>",
    type: "TEXT",
  },
  {
    id: 2,
    title: "Загрузить презентацию проекта",
    description: "<p>Формат: PDF или PPTX. Не более 20 слайдов.</p>",
    type: "FILE",
  },
  {
    id: 3,
    title: "Практика: Реализовать ToDo-лист на Pinia",
    description:
      "<p>Требования: добавление, удаление, фильтры. Код прикрепить как ZIP или GitHub-ссылку.</p>",
    type: "FILE",
  },
]);

const existing = JSON.parse(localStorage.getItem("course_tasks") || "[]");
console.log("exists", existing);
for (let task of existing) {
  tasks.value.push(task);
}
const activeTab = ref("info");

function changeCreate() {
  showDialog.value = true;
}

function changeCreateTask() {
  showTaskDialog.value = true;
}

function goToTask(title, description, task_id) {
  localStorage.setItem("task_title", title);
  localStorage.setItem("task_description", description);
  router.push(`/courses/${route.params.course_id}/tasks/${task_id}`);
}
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
                          >Длительность:
                          {{ course.duration }} часов</q-item-section
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

            <q-tab-panel name="materials">
              <div class="q-pa-xl">
                <div class="text-h6 q-mb-lg">Материалы курса</div>
                <q-btn
                  color="primary"
                  icon="add"
                  label="Добавить материал"
                  unelevated
                  @click="changeCreate"
                  style="margin-bottom: 10px"
                />
                <q-list separator>
                  <Material
                    v-for="material in materials"
                    :key="material.id"
                    :material="material"
                  />
                </q-list>
                <CreateMaterial v-model="showDialog" />
              </div>
            </q-tab-panel>

            <q-tab-panel name="tasks">
              <div class="q-pa-xl">
                <div class="row items-center justify-between q-mb-lg">
                  <div class="text-h6">Задания курса</div>

                  <q-btn
                    color="primary"
                    icon="add"
                    label="Добавить задание"
                    unelevated
                    @click="changeCreateTask"
                  />
                </div>

                <q-list separator bordered class="rounded-borders">
                  <q-item
                    v-for="task in tasks"
                    :key="task.id"
                    clickable
                    v-ripple
                    @click="goToTask(task.title, task.description, task.id)"
                    class="q-py-md"
                  >
                    <q-item-section avatar>
                      <q-icon name="assignment" color="orange" size="lg" />
                    </q-item-section>

                    <q-item-section>
                      <q-item-label class="text-weight-medium">
                        {{ task.title }}
                      </q-item-label>
                    </q-item-section>

                    <q-item-section side>
                      <q-icon name="chevron_right" />
                    </q-item-section>
                  </q-item>
                </q-list>

                <CreateTaskDialog v-model="showTaskDialog" />
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
