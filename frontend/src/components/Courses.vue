<template>
  <q-page class="q-pa-md" v-if="loaded">
    <div class="q-mx-auto" style="max-width: 1090px">
      <div class="row items-center justify-between ju">
        <div class="text-h4 text-weight-bold" style="margin-bottom: 30px">
          Мои курсы
        </div>
        <q-btn
          unelevated
          color="secondary"
          icon="add"
          label="Создать курс"
          size="lg"
          style="margin-bottom: 30px"
          class="q-px-lg"
          @click="router.push('/courses/create')"
        />
      </div>
      <div class="row q-col-gutter-lg q-gutter-md content q-pa-md">
        <q-card
          v-for="course in courses"
          :key="course.id"
          class="my-card cursor-pointer"
          @click="router.push(`/courses/${course.id}`)"
        >
          <q-img
            src="https://cdn.quasar.dev/img/mountains.jpg"
            height="200px"
            width="300px"
            class="relative-position img_inner"
          >
            <div class="absolute-full bg-gradient" />
            <div
              class="absolute-bottom text-h6 text-white q-pl-md q-pb-md text-shadow"
            >
              {{ course.title }}
            </div>
          </q-img>

          <q-card-section class="q-pt-md">
            <div class="text-caption text-grey-6">24 урока • 156 студентов</div>
          </q-card-section>
        </q-card>
      </div>
    </div>
  </q-page>
</template>

<script setup>
import { useUserStore } from "src/stores/user";
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const loaded = ref(false);
const userStore = useUserStore();
const courses = [];
onMounted(() => {
  courses.push({ title: "Программирование на Python", id: "aksdka1" });
  courses.push({ title: "Программирование на Java", id: "dddd1" });
  courses.push({ title: "Программирование на Python", id: "aksdka1" });
  courses.push({ title: "Программирование на Java", id: "dddd1" });

  console.log(courses);
  loaded.value = true;
  //   axios({
  //     method: "get",
  //     url: "/api/courses",
  //     data: {
  //       name: userStore.username,
  //     },
  //   })
  //     .then(function (response) {
  //       console.log(response.data);
  //       //   question.value.text = response.data.question;
  //       //   question.value.category = response.data.category;
  //       //   question.value.difficulty = response.data.difficulty;
  //       //   question.value.answers = response.data.incorrectAnswers;
  //       //   const index = Math.floor(Math.random() * 3);
  //       //   question.value.answers.splice(index, 0, response.data.correctAnswer);
  //       //   question.value.correct = response.data.correctAnswer;
  //       //   loaded.value = true;
  //       //   console.log(question.value);
  //     })
  //     .catch(function (error) {
  //       console.log(error);
  //     });
});
</script>

<style>
.my-card {
  cursor: pointer;
  border-radius: 16px;
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  overflow: hidden;
  padding: auto;
  padding: 20px;
}
.content {
  border: 1px solid rgb(99, 99, 99);
  border-radius: 15px;

  max-width: 1520px;
}
.my-card {
  transition: transform 0.3s ease-in-out box-shadow 0.5s;
}

.my-card:hover {
  transform: translateY(-12px);
  box-shadow: 0 20px 70px rgba(0, 0, 0, 0.15) !important;
}
</style>
