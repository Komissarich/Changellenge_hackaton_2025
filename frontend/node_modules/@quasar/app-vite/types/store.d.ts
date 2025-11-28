import { HasStore } from "quasar";
import { Pinia } from "pinia";
import { HasSsrParam } from "./ssr";

type StoreInstance = Pinia;

export type HasStoreParam = HasStore<{
  /**
   * The store instance.
   */
  store: StoreInstance;
}>;

export type StoreParams = {} & HasSsrParam;

export type StoreCallback = (
  params: StoreParams
) => StoreInstance | Promise<StoreInstance>;
