<template>
  <q-item clickable v-ripple @click="openMaterial" class="q-py-md">
    <q-item-section avatar>
      <q-icon :name="icon" :color="iconColor" size="lg" />
    </q-item-section>

    <q-item-section>
      <q-item-label class="text-weight-medium">
        {{ material.title }}
      </q-item-label>
      <q-item-label caption>
        {{ typeLabel }} • {{ formatDate(material.created_at) }}
      </q-item-label>
    </q-item-section>

    <q-item-section side>
      <q-btn
        flat
        round
        icon="download"
        size="sm"
        @click.stop="download"
        :href="material.file_link"
        target="_blank"
      />
    </q-item-section>
  </q-item>
</template>

<script setup>
import { computed } from "vue";

const props = defineProps({
  material: Object,
});

const icon = computed(() => {
  switch (props.material.type) {
    case "ARTICLE":
      return "article";
    case "MEDIA":
      return "attach_file";
    case "VIDEO":
      return "play_circle";
    default:
      return "description";
  }
});

const iconColor = computed(() => {
  switch (props.material.type) {
    case "ARTICLE":
      return "purple";
    case "MEDIA":
      return "blue";
    case "VIDEO":
      return "red";
    default:
      return "grey-7";
  }
});

const typeLabel = computed(() => {
  switch (props.material.type) {
    case "ARTICLE":
      return "Статья";
    case "MEDIA":
      return "Файл";
    case "VIDEO":
      return "Видео";
    default:
      return "Материал";
  }
});

const formatDate = (date) => {
  if (!date) return "Недавно";
  return new Date(date).toLocaleDateString("ru-RU", {
    day: "numeric",
    month: "short",
    hour: "2-digit",
    minute: "2-digit",
  });
};

const openMaterial = () => {
  if (props.material.type === "ARTICLE") {
    window.open(props.material.file_link, "_blank");
  } else {
    download();
  }
};

const download = () => {
  window.open(props.material.file_link, "_blank");
};
</script>

<style scoped>
.q-item {
  border-radius: 12px;
  transition: all 0.2s;
}
.q-item:hover {
  background: var(--q-primary-50);
}
</style>
