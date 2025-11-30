// src/services/YandexCloudService.js
import AWS from "aws-sdk";

class YandexCloudService {
  constructor() {
    this.s3 = new AWS.S3({
      endpoint: "https://storage.yandexcloud.net",
      accessKeyId: import.meta.env.VITE_YANDEX_ACCESS_KEY,
      secretAccessKey: import.meta.env.VITE_YANDEX_SECRET_KEY, // ← вот тут была ошибка
      region: "ru-central1",
      s3ForcePathStyle: true,
      signatureVersion: "v4",
    });

    this.bucket = import.meta.env.VITE_YANDEX_BUCKET;
  }
  async uploadHtmlString(htmlString, fileName = "lesson.html") {
    // Создаём File из строки (именно File, а не Blob — чтобы имя было)
    const file = new File([htmlString], fileName, {
      type: "text/html",
      lastModified: Date.now(),
    });

    // Используем твой уже существующий метод upload() — он у тебя он точно есть!
    const url = await this.upload(file, "articles");

    return url;
  }
  /**
   * Универсальная загрузка файла
   * @param {File} file — файл из input
   * @param {string} folder — папка: 'covers', 'lessons', 'homeworks', 'avatars' и т.д.
   * @param {function} onProgress — опционально, прогресс загрузки
   */
  async upload(file, folder = "misc", onProgress = null) {
    if (!file) throw new Error("Файл не передан");

    const timestamp = Date.now();
    const random = Math.random().toString(36).substring(2, 8);
    const cleanName = file.name.replace(/\s+/g, "_");
    const key = `${folder}/${timestamp}-${random}-${cleanName}`;

    const params = {
      Bucket: this.bucket,
      Key: key,
      Body: file,
      ACL: "public-read",
      ContentType: file.type || "application/octet-stream",
    };

    const managedUpload = this.s3.upload(params);

    if (onProgress) {
      managedUpload.on("httpUploadProgress", (progress) => {
        const percent = Math.round((progress.loaded / progress.total) * 100);
        onProgress(percent);
      });
    }

    const result = await managedUpload.promise();
    return result.Location; // ← прямая публичная ссылка
  }

  // Удаление (если понадобится)
  async delete(url) {
    const key = url.split(`${this.bucket}/`)[1];
    if (!key) throw new Error("Невозможно извлечь ключ из URL");
    await this.s3.deleteObject({ Bucket: this.bucket, Key: key }).promise();
  }
}

// Экспортируем один экземпляр — синглтон
export const yandexCloud = new YandexCloudService();
