<template>
  <q-layout view="hHh lpR fff">
    <q-header reveal elevated class="bg-primary text-white">
      <q-toolbar>
        <q-btn dense flat round icon="menu" @click="toggleLeftDrawer" />
        <q-avatar>
          <img src="https://cdn.quasar.dev/logo-v2/svg/logo-mono-white.svg" />
        </q-avatar>
        <q-toolbar-title> ПСБ Learn </q-toolbar-title>
        <q-tabs v-model="tab" shrink class="ml-1 absolute-center">
          <q-tab icon="school" name="tab1" label="Курсы" />
          <q-tab icon="message" name="tab2" label="Общение" />
          <q-tab icon="schedule" name="tab3" label="Расписание" />
        </q-tabs>
        <!-- <q-icon name="account-circle" /> -->
        <q-btn
          dense
          flat
          round
          icon="account_circle"
          @click="toggleLeftDrawer"
          label="Аккаунт"
        />
      </q-toolbar>
    </q-header>

    <q-drawer v-model="leftDrawerOpen" side="left" behavior="mobile" bordered>
      <q-list>
        <template v-for="(menuItem, index) in menuList" :key="index">
          <q-item clickable :active="menuItem.label === 'Outbox'" v-ripple>
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

const leftDrawerOpen = ref(false);
const rightDrawerOpen = ref(false);
const tab = ref("");
const menuList = [
  {
    icon: "school",
    label: "Курсы",
    separator: false,
  },
  {
    icon: "message",
    label: "Общение",
    separator: false,
  },
  {
    icon: "schedule",
    label: "Расписание",
    separator: false,
  },
];
function toggleLeftDrawer() {
  console.log(1);
  leftDrawerOpen.value = !leftDrawerOpen.value;
}
</script>
