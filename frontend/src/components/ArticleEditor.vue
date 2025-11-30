<template>
  <div class="article-editor-container">
    <div class="toolbar shadow-2" v-if="editor">
      <q-btn
        flat
        round
        size="sm"
        icon="format_bold"
        @click="editor.chain().focus().toggleBold().run()"
        :class="{ active: editor.isActive('bold') }"
      />
      <q-btn
        flat
        round
        size="sm"
        icon="format_italic"
        @click="editor.chain().focus().toggleItalic().run()"
        :class="{ active: editor.isActive('italic') }"
      />
      <q-separator vertical inset />

      <q-btn
        flat
        round
        size="sm"
        icon="looks_one"
        @click="editor.chain().focus().toggleHeading({ level: 1 }).run()"
        :class="{ active: editor.isActive('heading', { level: 1 }) }"
        title="Заголовок 1"
      />
      <q-btn
        flat
        round
        size="sm"
        icon="looks_two"
        @click="editor.chain().focus().toggleHeading({ level: 2 }).run()"
        :class="{ active: editor.isActive('heading', { level: 2 }) }"
        title="Заголовок 2"
      />
      <q-btn
        flat
        round
        size="sm"
        icon="looks_3"
        @click="editor.chain().focus().toggleHeading({ level: 3 }).run()"
        :class="{ active: editor.isActive('heading', { level: 3 }) }"
        title="Заголовок 3"
      />
      <q-separator vertical inset />

      <q-btn
        flat
        round
        size="sm"
        icon="format_list_bulleted"
        @click="editor.chain().focus().toggleBulletList().run()"
        :class="{ active: editor.isActive('bulletList') }"
      />
      <q-btn
        flat
        round
        size="sm"
        icon="format_list_numbered"
        @click="editor.chain().focus().toggleOrderedList().run()"
        :class="{ active: editor.isActive('orderedList') }"
      />
      <q-separator vertical inset />

      <q-btn flat round size="sm" icon="image" @click="addImage" />
    </div>

    <EditorContent :editor="editor" class="editor-content" />
  </div>
</template>

<script setup>
import { useEditor, EditorContent } from "@tiptap/vue-3";
import StarterKit from "@tiptap/starter-kit";
import Image from "@tiptap/extension-image";
import { onBeforeUnmount, watch } from "vue";
import { yandexCloud } from "src/service/yandexCloudUpload";

const props = defineProps({
  initialContent: String,
});

const emit = defineEmits(["update:content"]);

const editor = useEditor({
  content: props.initialContent || "<p>Начните писать...</p>",
  extensions: [
    StarterKit,
    Image.configure({
      inline: true,
      allowBase64: false,
    }),
  ],
  editorProps: {
    attributes: {
      class: "prose prose-lg max-w-none focus:outline-none min-h-96 p-6",
    },
  },
  onUpdate: ({ editor }) => {
    emit("update:content", editor.getHTML());
  },
});

watch(
  () => props.initialContent,
  (newVal) => {
    if (editor.value && newVal !== editor.value.getHTML()) {
      editor.value.commands.setContent(newVal || "");
    }
  }
);

const addImage = () => {
  const input = document.createElement("input");
  input.type = "file";
  input.accept = "image/*";
  input.onchange = async (e) => {
    const file = e.target.files?.[0];
    if (!file || !editor.value) return;

    try {
      const url = await yandexCloud.upload(file, "articles");
      editor.value
        .chain()
        .focus()
        .deleteSelection()
        .setImage({ src: url })
        .run();
    } catch (err) {
      editor.value.chain().focus().deleteSelection().setHardBreak().run();
    }
  };
  input.click();
};

onBeforeUnmount(() => {
  editor.value?.destroy();
});
</script>

<style scoped>
.article-editor-container {
  max-width: 800px;
  margin: 0 auto;
  background: white;
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
}

.toolbar {
  display: flex;
  align-items: center;
  gap: 4px;
  padding: 8px;
  background: #f8f9fa;
  border-bottom: 1px solid #e0e0e0;
}

.toolbar .q-btn {
  color: #555;
}

.toolbar .q-btn.active {
  background: #e3f2fd !important;
  color: #1976d2 !important;
}

.editor-content :deep(.ProseMirror) {
  min-height: 500px;
  outline: none;
}

.editor-content :deep(img) {
  max-width: 100%;
  border-radius: 8px;
  margin: 16px 0;
  display: block;
}
</style>
