<template>
  <q-page class="q-pa-xl bg-white">

    <!-- Заголовок -->
    <div class="text-h4 text-dark q-mb-xl">
      Расписание
    </div>

    <!-- Блок кнопок дней -->
    <div class="q-mb-xl">
      <div class="row q-gutter-md">
        <q-btn
          v-for="(day, index) in days"
          :key="index"
          :label="day.label"
          :color="selectedDay === index ? 'primary' : 'white'"
          :text-color="selectedDay === index ? 'white' : 'black'"
          unelevated
          class="q-pa-sm"
          style="border-radius: 12px; border: 1px solid #e0e0e0;"
          @click="selectedDay = index"
        />
      </div>
    </div>

    <!-- Уроки -->
    <div v-if="selectedDay !== null">

      <!-- Заголовок дня -->
      <div class="text-h6 text-primary q-mb-md">
        {{ days[selectedDay].label }} — {{ days[selectedDay].date }}
      </div>

      <!-- Карточки занятий -->
      <div class="column">
        <q-card
          v-for="(lesson, idx) in days[selectedDay].lessons"
          :key="idx"
          class="q-pa-md q-mb-md"
          flat
          bordered
          style="border-radius: 16px; box-shadow: 0 4px 12px rgba(0,0,0,0.06);"
        >
          <div class="text-body1">
            <b>{{ lesson.time }}</b> — {{ lesson.title }}
          </div>
        </q-card>
      </div>

    </div>

  </q-page>
</template>

<script>
export default {
  name: "SchedulePage",

  data() {
    return {
      selectedDay: null,

      days: [
        {
          label: "Понедельник",
          date: "27 января",
          lessons: [
            { time: "10:00 — 11:30", title: "Основы программирования" },
            { time: "12:00 — 13:30", title: "Алгоритмы и структуры данных" }
          ]
        },
        {
          label: "Вторник",
          date: "28 января",
          lessons: [
            { time: "09:00 — 10:30", title: "Python: базовый уровень" },
            { time: "11:00 — 12:30", title: "Git и GitHub" }
          ]
        },
        {
          label: "Среда",
          date: "29 января",
          lessons: [
            { time: "08:30 — 10:00", title: "Frontend: HTML/CSS" },
            { time: "10:15 — 11:45", title: "JavaScript основы" }
          ]
        },
        {
          label: "Четверг",
          date: "30 января",
          lessons: [
            { time: "10:00 — 11:30", title: "Backend: Node.js" },
            { time: "12:00 — 13:30", title: "Базы данных" }
          ]
        },
        {
          label: "Пятница",
          date: "31 января",
          lessons: [
            { time: "09:00 — 10:30", title: "Создание Telegram-ботов" },
            { time: "11:00 — 12:30", title: "API и HTTP" }
          ]
        },
        {
          label: "Суббота",
          date: "1 февраля",
          lessons: [
            { time: "10:00 — 12:00", title: "Игры на Python (pygame)" }
          ]
        },
        {
          label: "Воскресенье",
          date: "2 февраля",
          lessons: [
            { time: "11:00 — 13:00", title: "Практикум: мини-проекты" }
          ]
        }
      ]
    };
  }
};
</script>
