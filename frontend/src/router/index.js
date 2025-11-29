import { defineRouter } from "#q-app/wrappers";
import {
  createRouter,
  createMemoryHistory,
  createWebHistory,
  createWebHashHistory,
} from "vue-router";
import routes from "./routes";

export default defineRouter(function (/* { store, ssrContext } */) {
  const createHistory = process.env.SERVER
    ? createMemoryHistory
    : createWebHistory;

  const Router = createRouter({
    scrollBehavior: () => ({ left: 0, top: 0 }),
    routes,
    history: createHistory(process.env.VUE_ROUTER_BASE),
  });

  Router.beforeEach((to, from, next) => {
    const isAuth = localStorage.getItem("isAuth");
    console.log("here", isAuth, isAuth == "false");

    const publicRoutes = ["/login", "/register"];

    if (publicRoutes.includes(to.path)) {
      return next();
    }
    if (isAuth === null || isAuth == "false") {
      console.log("fuck");
      return next({ path: "/login" });
    }

    next();
  });
  return Router;
});
