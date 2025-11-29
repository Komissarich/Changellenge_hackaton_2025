import CourseInfo from "src/components/CourseInfo.vue";
import Courses from "src/components/Courses.vue";
import ErrorNotFound from "src/components/ErrorNotFound.vue";
import HomePage from "src/components/HomePage.vue";
import Login from "src/components/Login.vue";
import Register from "src/components/Register.vue";

const routes = [
  {
    path: "/",
    component: HomePage,
  },

  {
    path: "/courses",
    component: Courses,
  },

  {
    path: "/courses/:course_id",
    component: CourseInfo,
  },

  // {
  //   path: "/login",
  //   component: Login,
  // },

  // {
  //   path: "/register",
  //   component: Register,
  // },
];

export default routes;
