import AuthLayout from "layouts/AuthLayout.vue";
import MainLayout from "layouts/MainLayout.vue";

import Login from "pages/Login.vue";
import RegisterPage from "pages/RegisterPage.vue";
import HomePage from "pages/HomePage.vue";

import CourseInfo from "src/components/CourseInfo.vue";
import Courses from "src/components/Courses.vue";
import CreateCourse from "src/components/CreateCourse.vue";
// import Login from "@/pages/Login.vue";

const routes = [
  {
    path: "/",
    component: HomePage,
  },

  {
    component: AuthLayout,
    children: [
      { path: "/login", component: Login },
      { path: "/register", component: RegisterPage },
    ],
  },

  { path: "/courses", component: Courses },
  { path: "/courses/:course_id", component: CourseInfo },
  {
    path: "/courses/create",
    component: CreateCourse,
  },
];

export default routes;
