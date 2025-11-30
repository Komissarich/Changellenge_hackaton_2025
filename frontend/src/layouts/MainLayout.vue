<template>
  <q-layout view="hHh lpR fff">
    <q-header reveal elevated class="bg-primary text-white"> 
  <q-toolbar>
    <q-btn dense flat round icon="menu" @click="toggleLeftDrawer" />

    <q-avatar>
      <img src="https://cdn.quasar.dev/logo-v2/svg/logo-mono-white.svg" />
    </q-avatar>

    <q-toolbar-title>ПСБ Learn</q-toolbar-title>

    <q-tabs
  v-model="tab"
  shrink
  class="ml-1 absolute-center top-tabs"
  @update:model-value="onTabChange"
  indicator-color="white"      
  active-color="white"         
  dense                        
>

      <q-tab icon="school" name="courses" label="Курсы" />
      <q-tab icon="message" name="chat" label="Общение" />
      <q-tab icon="schedule" name="schedule" label="Расписание" />
    </q-tabs>

    <q-btn
  flat
  no-caps
  :ripple="false"
  class="menu-account-btn"
  @click="goToAccount"
>
  <q-icon name="account_circle" size="24px" class="q-mr-sm" />
  АККАУНТ
</q-btn>

  </q-toolbar>
</q-header>


    <q-drawer v-model="leftDrawerOpen" side="left" behavior="mobile" bordered>
      <q-list>
        <q-item clickable to="/app/home">
          <q-item-section><q-icon name="home" /></q-item-section>
          <q-item-section>Главная</q-item-section>
        </q-item>

        <q-item clickable to="/app/courses">
          <q-item-section><q-icon name="school" /></q-item-section>
          <q-item-section>Курсы</q-item-section>
        </q-item>

        <q-item clickable to="/app/schedule">
          <q-item-section><q-icon name="schedule" /></q-item-section>
          <q-item-section>Расписание</q-item-section>
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
  name: "MainLayout",

  data() {
    return {
      tab: "courses",
      leftDrawerOpen: false
    };
  },

  methods: {
    toggleLeftDrawer() {
      this.leftDrawerOpen = !this.leftDrawerOpen;
    },

    goToAccount() {
      this.$router.push("/app/account");
    },

    // ВАЖНО: обработчик вкладок
    onTabChange(name) {
      if (name === "courses") this.$router.push("/app/courses");
      if (name === "chat") this.$router.push("/app/home"); // если чата пока нет
      if (name === "schedule") this.$router.push("/app/schedule");
    }
  }
};
</script>
