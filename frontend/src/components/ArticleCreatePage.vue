<template>
  <q-page class="bg-grey-1">
    <div class="q-pa-xl">
      <div class="row items-center q-mb-md">
        <q-btn icon="arrow_back" flat round @click="router.back()" />
        <div class="text-h5 q-ml-md">Новая статья</div>
      </div>

      <q-input
        v-model="title"
        label="Заголовок статьи *"
        filled
        class="q-mb-lg max-w-800"
        style="max-width: 800px; margin: 0 auto"
      />

      <ArticleEditor
        :initial-content="content"
        @update:content="content = $event"
        class="q-mb-xl"
      />

      <div class="row justify-center q-mt-xl q-gutter-md">
        <q-btn label="Отмена" flat @click="router.back()" />
        <q-btn
          color="positive"
          label="Опубликовать"
          :loading="saving"
          :disable="!title.trim()"
          @click="publish"
        />
      </div>
    </div>
  </q-page>
</template>

<script setup>
import { ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import ArticleEditor from "src/components/ArticleEditor.vue";
import { useQuasar } from "quasar";
import axios from "axios";
import { yandexCloud } from "src/service/yandexCloudUpload";

const $q = useQuasar();
const router = useRouter();
const route = useRoute();

const title = ref("");
const content = ref("");
const saving = ref(false);

const publish = async () => {
  if (!title.value.trim()) return;
  console.log(title.value, content.value);
  saving.value = true;
  const fullHtml = `<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>${title.value}</title>
  <style>
    body { font-family: system-ui, sans-serif; line-height: 1.7; max-width: 900px; margin: 40px auto; padding: 20px; background: #fff; color: #1a1a1a; }
    h1, h2, h3 { color: #1976d2; margin: 2em 0 1em; }
    img { max-width: 100%; border-radius: 12px; margin: 20px 0; box-shadow: 0 4px 12px rgba(0,0,0,0.1); }
    ul, ol { padding-left: 30px; margin: 1em 0; }
    code { background: #f5f5f5; padding: 2px 6px; border-radius: 4px; }
    pre { background: #f5f5f5; padding: 16px; border-radius: 8px; overflow-x: auto; }
    hr { border: none; border-top: 2px solid #1976d2; margin: 3em 0; }
  </style>
</head>
<body>
  <h1>${title.value}</h1>
  <hr>
  ${content.value}
</body>
</html>`;

  const safeName = title.value;

  const fileName = `articles/${Date.now()}-${safeName || "lesson"}.html`;

  const htmlUrl = await yandexCloud.uploadHtmlString(fullHtml, fileName);
  console.log(htmlUrl);
  try {
    await axios.post("http://localhost:8081/api/courses/createMaterial", {
      course_id: localStorage.getItem("current_course"),
      title: title.value,
      type: "ARTICLE",
      file_link: htmlUrl,
    });
    console.log("Статья опубликована");
    router.back();
  } catch (err) {
    console.log(err);
  } finally {
    saving.value = false;
  }
};
</script>
