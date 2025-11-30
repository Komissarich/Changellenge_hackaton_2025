<template>
  <q-dialog v-model="localValue" persistent>
    <q-card style="width: 600px; max-width: 90vw">
      <q-card-section class="row items-center q-pb-none">
        <div class="text-h6">Добавить материал</div>
        <q-space />
        <q-btn icon="close" flat round dense v-close-popup @click="close" />
      </q-card-section>

      <q-card-section class="q-pt-lg text-center">
        <div class="text-h5 q-mb-xl">Какой тип материала хотите добавить?</div>
        <q-btn
          size="xl"
          color="primary"
          icon="attach_file"
          label="Файл или видео"
          class="full-width q-mb-md"
          unelevated
          @click="openFileMode"
        />
        <q-btn
          size="xl"
          color="purple"
          icon="edit_note"
          label="Создать статью"
          class="full-width"
          unelevated
          @click="goToArticleEditor"
        />

        <div v-if="mode === 'file'">
          <q-input
            v-model="fileTitle"
            label="Название материала *"
            filled
            class="q-mb-md"
            style="margin-top: 10px"
          />
          <q-file
            v-model="file"
            accept=".pdf,.mp4,.mov,.pptx,.docx,.zip"
            label="Выберите файл"
            outlined
            style="margin-top: 10px; margin-bottom: 10px"
          ></q-file>

          <div class="row justify-end q-gutter-md">
            <q-btn
              color="positive"
              label="Добавить"
              :disable="!file"
              @click="uploadFileMaterial"
            />
          </div>
        </div>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script setup>
import { ref, watch } from "vue";
import { useRouter, useRoute } from "vue-router";
import { yandexCloud } from "src/service/yandexCloudUpload";
import axios from "axios";

const router = useRouter();
const route = useRoute();
const file = ref({});
const fileTitle = ref("");
const props = defineProps({ modelValue: Boolean });
const emit = defineEmits(["update:modelValue", "created"]);
const mode = ref("");
const localValue = ref(props.modelValue);
watch(
  () => props.modelValue,
  (val) => (localValue.value = val)
);
watch(localValue, (val) => emit("update:modelValue", val));

const close = () => emit("update:modelValue", false);

const goToArticleEditor = () => {
  const courseId = route.params.id;
  router.push(`/create_article`);
  close();
};

const openFileMode = () => {
  mode.value = "file";
};
async function uploadFileMaterial() {
  console.log(file.value);
  let contentUrl = await yandexCloud.upload(file.value, "materials");
  console.log(fileTitle.value, contentUrl);

  axios
    .post("http://localhost:8081/api/courses/createMaterial", {
      course_id: route.params.course_id,
      file_link: contentUrl,
      title: fileTitle.value,
      type: "MEDIA",
    })
    .then(function (response) {
      console.log(response);
      console.log(response.data);
      close();
    })
    .catch(function (error) {
      if (error.response) {
        console.log(error.response.data);
      }
    });
}
</script>
