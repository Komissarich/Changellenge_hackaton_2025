import ErrorNotFound from "src/components/ErrorNotFound.vue";
import HomePage from "src/components/HomePage.vue";

const routes = [
  {
    path: "/",
    component: HomePage,
  },

  {
    path: "/courses",
    component: HomePage,
  },

  // {
  //   path: "/:catchAll(.*)*",
  //   component: ErrorNotFound,
  // },
];

export default routes;
