import { RouteLocationRaw, RouteLocationNormalizedLoaded } from "vue-router";
import { HasSsrParam } from "./ssr";
import { HasStoreParam } from "./store";

interface PreFetchOptions extends HasSsrParam, HasStoreParam {
  currentRoute: RouteLocationNormalizedLoaded;
  previousRoute: RouteLocationNormalizedLoaded;
  redirect: (url: RouteLocationRaw, statusCode?: number) => void;
  urlPath: string;
  publicPath: string;
}

// https://github.com/quasarframework/quasar/issues/6576#issuecomment-603787603
// Promise<{}> allow nearly any type of Promise to be used
export type PrefetchCallback = (
  options: PreFetchOptions
) => void | Promise<void> | Promise<{}>;

declare module "vue" {
  interface ComponentCustomOptions {
    preFetch?: PrefetchCallback;
  }
}
