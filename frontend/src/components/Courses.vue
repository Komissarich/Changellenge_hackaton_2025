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
            :src="course.cover_link"
            height="200px"
            width="300px"
            class="relative-position img_inner"
          >
            <div class="absolute-full bg-gradient" />
            <div
              class="absolute-bottom text-h6 text-white q-pl-md q-pb-md text-shadow"
            >
              {{ course.name }}
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
import axios from "axios";
import { useUserStore } from "src/stores/user";
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const loaded = ref(false);
const userStore = useUserStore();
const courses = ref([]);
onMounted(() => {
  console.log(courses);
  loaded.value = true;
  axios
    .post("http://localhost:8081/api/courses/get", {
      teacher_id: localStorage.getItem("id"),
    })
    .then(function (response) {
      console.log(response);
      console.log(response.data);
      courses.value = response.data;
    })
    .catch(function (error) {
      if (error.response) {
        console.log(error.response.data);
      }
    });
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
