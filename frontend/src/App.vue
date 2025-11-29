<template>
  <q-layout view="hHh lpR fff">
    <q-header reveal elevated class="bg-primary text-white">
      <q-toolbar mt-100>
        <q-btn dense flat round icon="menu" @click="toggleLeftDrawer" />
        <q-avatar>
          <img src="https://cdn.quasar.dev/logo-v2/svg/logo-mono-white.svg" />
        </q-avatar>
        <q-toolbar-title @click="router.push('/')"> ПСБ Learn </q-toolbar-title>

        <q-tabs v-model="tab" shrink class="ml-1 absolute-center" inline-label>
          <q-tab icon="school" name="tab1" label="Курсы" @click="router.push('/courses')" />
          <q-tab icon="message" name="tab2" label="Общение" @click="router.push('/messages')" />
          <q-tab icon="calendar_month" name="tab3" label="Расписание" @click="router.push('/calendar')" />
        </q-tabs>

        <q-btn dense flat icon="account_circle" @click="router.push('/me')" label="Аккаунт" />
      </q-toolbar>
    </q-header>

    <q-drawer v-model="leftDrawerOpen" side="left" behavior="mobile" bordered>
      <q-list>
        <template v-for="(menuItem, index) in menuList" :key="index">
          <q-item clickable v-ripple>
            <q-item-section avatar>
              <q-icon :name="menuItem.icon" />
            </q-item-section>
            <q-item-section>
              {{ menuItem.label }}
            </q-item-section>
          </q-item>

          <q-separator :key="'sep' + index" v-if="menuItem.separator" />
        </template>
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>

    <q-footer class="bg-grey-1 text-white">
      <q-toolbar>
        <q-toolbar-title>
          <q-avatar>
            <img src="https://cdn.quasar.dev/logo-v2/svg/logo-mono-white.svg" />
          </q-avatar>
          <div>Title</div>
        </q-toolbar-title>
      </q-toolbar>
    </q-footer>
  </q-layout>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const leftDrawerOpen = ref(false);
const tab = ref("");

const menuList = [
  { icon: "school", label: "Курсы", separator: false },
  { icon: "message", label: "Общение", separator: false },
  { icon: "calendar_month", label: "Расписание", separator: false },
];

function toggleLeftDrawer() {
  leftDrawerOpen.value = !leftDrawerOpen.value;
}
</script>
