<template>
  <q-dialog v-model="localValue" persistent>
    <q-card style="width: 900px; max-width: 90vw">
      <q-card-section class="row items-center q-pb-none">
        <div class="text-h6">Новое задание</div>
        <q-space />
        <q-btn icon="close" flat round dense v-close-popup @click="close" />
      </q-card-section>

      <q-card-section>
        <q-input
          v-model="title"
          label="Название задания *"
          filled
          class="q-mb-md"
        />

        <div class="text-subtitle1 q-mb-sm">Условие задания</div>
        <ArticleEditor
          :initial-content="description"
          @update:content="description = $event"
        />

        <q-select
          v-model="type"
          :options="typeOptions"
          label="Тип ответа"
          filled
          class="q-mt-md"
        />
      </q-card-section>

      <q-card-actions align="right" class="q-pa-md">
        <q-btn flat label="Отмена" @click="close" />
        <q-btn
          color="primary"
          label="Создать задание"
          :loading="saving"
          :disable="!title || !description"
          @click="createTask"
        />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script setup>
import { ref, watch } from "vue";
import { useQuasar } from "quasar";
import ArticleEditor from "src/components/ArticleEditor.vue";

const $q = useQuasar();
const emit = defineEmits(["update:modelValue", "created"]);

const props = defineProps({ modelValue: Boolean });
const localValue = ref(props.modelValue);

const title = ref("");
const description = ref("");
const type = ref("TEXT");
const saving = ref(false);

const typeOptions = [
  { label: "Текстовый ответ", value: "TEXT" },
  { label: "Прикрепить файл", value: "FILE" },
];

watch(
  () => props.modelValue,
  (v) => (localValue.value = v)
);
watch(localValue, (v) => emit("update:modelValue", v));

const close = () => {
  emit("update:modelValue", false);
  title.value = "";
  description.value = "";
};

const createTask = async () => {
  saving.value = true;
  console.log(description.value);
  const existing = JSON.parse(localStorage.getItem("course_tasks") || "[]");

  const newTask = {
    title: title.value,
    description: description.value || "<p>Условие задания...</p>",
    type: type.value,
  };
  existing.push(newTask);
  localStorage.setItem("course_tasks", JSON.stringify(existing));
  try {
    // await axios.post("/api/tasks", {
    //   courseId: route.params.id,
    //   title: title.value,
    //   description: description.value,
    //   type: type.value,
    // });
    // localStorage.setItem("tasks", {
    //   title: title.value,
    //   description: description.value,
    //   type: type.value,
    // });
    emit("created");
    close();
  } catch (err) {
  } finally {
    saving.value = false;
  }
};
</script>
