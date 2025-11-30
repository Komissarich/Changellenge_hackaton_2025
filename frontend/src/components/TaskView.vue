<!-- src/pages/course/TaskViewPage.vue -->
<template>
  <q-page class="q-pa-xl">
    <q-btn
      icon="arrow_back"
      flat
      round
      @click="router.back()"
      class="q-mb-lg"
    />

    <div class="text-h4 q-mb-md">{{ task.title }}</div>

    <!-- Условие задания — чистый HTML из TipTap -->
    <div class="task-description q-mb-xl">
      <div v-html="task.description"></div>
    </div>

    <div class="text-h6 q-mb-md">Ответы студентов ({{ answers.length }})</div>

    <!-- Пока хардкодим ответы — потом будет с бэка -->
    <q-list separator>
      <q-item v-for="answer in answers" :key="answer.id" class="q-py-md">
        <q-item-section avatar>
          <q-avatar color="primary" text-color="white">
            {{ answer.student[0] }}
          </q-avatar>
        </q-item-section>

        <q-item-section>
          <q-item-label class="text-weight-medium">
            {{ answer.student }}
          </q-item-label>
          <q-item-label caption>
            сдал {{ formatDate(answer.submitted_at) }}
          </q-item-label>

          <!-- Если ответ — файл -->
          <q-item-label v-if="answer.file_url" class="q-mt-sm">
            <q-btn
              color="blue"
              icon="attach_file"
              :href="answer.file_url"
              target="_blank"
              label="Скачать ответ"
              unelevated
            />
          </q-item-label>

          <!-- Если ответ — текст -->
          <q-item-label v-else class="q-mt-sm text-body1">
            {{ answer.text }}
          </q-item-label>

          <!-- Комментарий преподавателя -->
          <div class="q-mt-md">
            <q-input
              v-model="answer.comment"
              filled
              type="textarea"
              label="Комментарий преподавателя"
              placeholder="Отличная работа!"
            />
            <q-btn
              color="positive"
              label="Отправить"
              class="q-mt-sm"
              @click="sendComment(answer)"
            />
          </div>
        </q-item-section>
      </q-item>
    </q-list>
  </q-page>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";

const route = useRoute();
const router = useRouter();
const taskId = route.params.taskId;
let title = localStorage.getItem("task_title");
let description = localStorage.getItem("task_description");
// Хардкодим задание и ответы — потом будет API
const task = ref({
  id: taskId,
  title: title,
  description: description,
  created_at: new Date(),
});

const answers = ref([
  {
    id: 1,
    student: "Иван Иванов",
    text: "Vue 3 — это будущее фронтенда! Composition API — огонь!",
    file_url: null,
    submitted_at: new Date(),
    comment: "",
  },
  {
    id: 2,
    student: "Мария Петрова",
    text: null,
    file_url:
      "https://storage.yandexcloud.net/testpublicbucket/answers/essay-maria.pdf",
    submitted_at: new Date(),
    comment: "Отличная структура!",
  },
]);

const formatDate = (date) => {
  return new Date(date).toLocaleString("ru-RU", {
    day: "numeric",
    month: "short",
    hour: "2-digit",
    minute: "2-digit",
  });
};

const sendComment = (answer) => {
  alert(`Комментарий отправлен студенту: "${answer.comment}"`);
};
</script>

<style scoped>
.task-description :deep(img) {
  max-width: 100%;
  border-radius: 12px;
  margin: 16px 0;
}
</style>
