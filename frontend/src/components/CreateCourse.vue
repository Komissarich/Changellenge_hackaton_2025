<!-- src/components/course/CourseCreate.vue -->
<template>
  <q-page padding>
    <div class="q-mx-auto" style="max-width: 1000px">
      <div class="text-h4 q-mb-lg">Создание нового курса</div>

      <q-card flat bordered class="q-mb-xl">
        <q-card-section>
          <div class="text-h6 q-mb-md">Обложка курса</div>

          <q-file
            v-model="form.coverFile"
            accept="image/*"
            label="Выберите обложку"
            outlined
            @update:model-value="onFileSelected"
          >
            <template v-slot:prepend>
              <q-icon name="attach_file" />
            </template>
          </q-file>

          <div v-if="coverPreview" class="q-mt-md">
            <q-img
              :src="coverPreview"
              style="height: 300px; max-width: 100%; object-fit: cover"
              class="rounded-borders"
            >
              <div class="absolute-top-right q-pa-sm">
                <q-btn
                  round
                  color="negative"
                  icon="close"
                  size="sm"
                  flat
                  @click="clearCover"
                />
              </div>
            </q-img>
          </div>

          <div v-else class="q-mt-md text-center text-grey-6">
            <q-icon name="image" size="100px" />
            <div class="q-mt-md">Обложка</div>
          </div>
        </q-card-section>
      </q-card>

      <q-card flat bordered>
        <q-card-section>
          <div class="row q-col-gutter-lg">
            <div class="col-12 col-md-7">
              <div class="text-h6 q-mb-md">Основная информация</div>

              <q-input
                v-model="form.title"
                label="Название курса *"
                outlined
                class="q-mb-md"
                :rules="[(val) => !!val || 'Обязательное поле']"
              />

              <q-input
                v-model="form.description"
                label="Описание курса *"
                type="textarea"
                outlined
                rows="6"
                class="q-mb-md"
                :rules="[(val) => !!val || 'Обязательное поле']"
              />

              <div class="row q-col-gutter-md">
                <div class="col">
                  <q-input
                    v-model.number="form.durationHours"
                    type="number"
                    label="Длительность (часов)"
                    outlined
                  />
                </div>
                <div class="col">
                  <q-select
                    v-model="form.level"
                    :options="levels"
                    label="Сложность"
                    outlined
                    map-options
                    emit-value
                  />
                </div>
              </div>
            </div>

            <!-- ПРАВАЯ КОЛОНКА — ДНИ НЕДЕЛИ -->
            <div class="col-12 col-md-5">
              <div class="text-h6 q-mb-md">Дни проведения</div>

              <q-option-group
                v-model="form.weekDays"
                :options="weekDaysOptions"
                color="primary"
                type="toggle"
                inline
                class="week-days-group"
              />

              <div
                v-if="form.weekDays.length === 0"
                class="text-negative q-mt-sm"
              >
                Выберите хотя бы один день
              </div>
            </div>
          </div>

          <!-- КНОПКИ -->
          <div class="q-mt-xl text-right">
            <q-btn
              label="Отмена"
              flat
              color="grey-7"
              to="/courses"
              class="q-mr-md"
            />
            <q-btn
              label="Создать курс"
              color="primary"
              size="lg"
              :loading="loading"
              :disable="!isValid"
              @click="createCourse"
            />
          </div>
        </q-card-section>
      </q-card>
    </div>
  </q-page>
</template>

<script setup>
import { ref, computed } from "vue";
import { useRouter } from "vue-router";
import { useQuasar } from "quasar";
import axios from "axios";

import { yandexCloud } from "src/service/yandexCloudUpload";

const router = useRouter();

const form = ref({
  title: "",
  description: "",
  durationHours: null,
  level: "Начальный",
  weekDays: [],
  coverFile: null,
});

const coverPreview = ref(null);
const loading = ref(false);
const uploadProgress = ref(0);

const levels = [
  { label: "Начальный", value: "BEGINNER" },
  { label: "Средний", value: "INTERMEDIATE" },
  { label: "Продвинутый", value: "ADVANCED" },
  { label: "Эксперт", value: "EXPERT" },
];
const weekDaysOptions = [
  { label: "Пн", value: "monday" },
  { label: "Вт", value: "tuesday" },
  { label: "Ср", value: "wednesday" },
  { label: "Чт", value: "thursday" },
  { label: "Пт", value: "friday" },
  { label: "Сб", value: "saturday" },
  { label: "Вс", value: "sunday" },
];

const isValid = computed(() => {
  return (
    form.value.title && form.value.description && form.value.weekDays.length > 0
  );
});

function onFileSelected(file) {
  if (file) {
    form.value.coverFile = file;
    coverPreview.value = URL.createObjectURL(file);
  }
}

function clearCover() {
  form.value.coverFile = null;
  coverPreview.value = null;
}

async function createCourse() {
  if (!isValid.value) return;

  loading.value = true;
  uploadProgress.value = 0;

  try {
    let coverUrl = null;

    // 1. Загружаем обложку в Яндекс.Облако
    if (form.value.coverFile) {
      console.log("Загружаем обложку...");

      coverUrl = await yandexCloud.upload(
        form.value.coverFile,
        "covers",
        (percent) => {
          uploadProgress.value = percent;
        }
      );
    }
    console.log(coverUrl);
    const payload = {
      name: form.value.title,
      description: form.value.description,
      duration: form.value.durationHours.toString(),
      difficulty: form.value.level,
      schedule: form.value.weekDays.join(","),
      coverUrl: coverUrl,
      author_id: localStorage.getItem("id"),
    };
    console.log(payload);

    await axios.post("http://localhost:8081/api/courses/create", payload);

    console.log("Курс создан");

    router.push("/courses");
  } catch (err) {
    console.error("Ошибка создания курса:", err);
    console.log("Не удалось создать курс");
  } finally {
    loading.value = false;
    uploadProgress.value = 0;
  }
}
</script>

<style scoped>
.week-days-group .q-toggle {
  margin: 6px;
  min-width: 60px;
}
</style>
