import HomePage from "src/components/HomePage.vue";

const routes = [
  {
    path: "/",
    component: HomePage,
  },

  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/ErrorNotFound.vue"),
  },
];

export default routes;
