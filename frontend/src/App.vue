<template>
  <q-layout view="hHh lpR fff">
    <q-header reveal elevated class="bg-primary text-white">
      <q-toolbar>
        <q-btn dense flat round icon="menu" @click="toggleLeftDrawer" />

        <q-avatar class="clickable">
          <img src="./assets/icon.png" />
        </q-avatar>

        <q-toolbar-title @click="$router.push('/')" class="clickable">
          ПСБ Learn
        </q-toolbar-title>

        <!-- Табы сверху -->
        <q-tabs v-model="tab" shrink class="ml-1 absolute-center" inline-label>
          <q-tab icon="school" name="courses" label="Курсы" to="/app/courses" />

          <q-tab icon="message" name="chat" label="Общение" to="/app/chat" />

          <q-tab icon="schedule" name="schedule" label="Расписание" to="/app/schedule" />
        </q-tabs>

        <q-btn dense flat round icon="account_circle" label="Аккаунт" @click="goToAccount" />
      </q-toolbar>
    </q-header>

    <!-- Боковое меню -->
    <q-drawer v-model="leftDrawerOpen" side="left" behavior="mobile" bordered>
      <q-list>

        <q-item clickable to="/app/home">
          <q-item-section avatar>
            <q-icon name="home" />
          </q-item-section>
          <q-item-section>Главная</q-item-section>
        </q-item>

        <q-item clickable to="/app/courses">
          <q-item-section avatar>
            <q-icon name="school" />
          </q-item-section>
          <q-item-section>Курсы</q-item-section>
        </q-item>

        <q-item clickable to="/app/schedule">
          <q-item-section avatar>
            <q-icon name="schedule" />
          </q-item-section>
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
  name: "App",

  data() {
    return {
      tab: "",
      leftDrawerOpen: false,
    };
  },

  methods: {
    toggleLeftDrawer() {
      this.leftDrawerOpen = !this.leftDrawerOpen;
    },

    goToAccount() {
      this.$router.push("/app/account");
    }
  },

  watch: {
    $route(to) {
      const last = to.path.split("/").pop();
      this.tab = last;
    }
  },

  mounted() {
    const last = this.$route.path.split("/").pop();
    this.tab = last;
  }
};
</script>

<style>
.clickable {
  cursor: pointer;
}
</style>
