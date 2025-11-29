<template>
  <q-layout view="hHh lpR fFf">
    <q-header elevated class="bg-primary text-white">
      <q-toolbar>
        <q-btn flat dense round icon="menu" @click="leftDrawer = !leftDrawer" />
        <q-toolbar-title>Title</q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-drawer v-model="leftDrawer" side="left" bordered>
      <q-list>
        <q-item clickable to="/app/home">
          <q-item-section>Главная</q-item-section>
        </q-item>
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
export default {
  data() {
    return {
      leftDrawer: false
    };
  }
};
</script>

