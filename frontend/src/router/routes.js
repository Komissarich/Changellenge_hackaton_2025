import AuthLayout from "layouts/AuthLayout.vue";
import MainLayout from "layouts/MainLayout.vue";

import Login from "pages/Login.vue";
import RegisterPage from "pages/RegisterPage.vue";
import HomePage from "pages/HomePage.vue";

import CourseInfo from "src/components/CourseInfo.vue";
import Courses from "src/components/Courses.vue";
import CreateCourse from "src/components/CreateCourse.vue";
import CreateMaterial from "src/components/CreateMaterial.vue";
import ArticleCreatePage from "src/components/ArticleCreatePage.vue";
import TaskView from "src/components/TaskView.vue";

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
  { path: "/create_material", component: CreateMaterial },
  { path: "/create_article", component: ArticleCreatePage },
  {
    path: "/courses/:courseId/tasks/:taskId",
    name: "task-view",
    component: TaskView,
  },
];

export default routes;
