FROM node:20-alpine

WORKDIR /app

# Копируем ВСЕ файлы проекта
COPY . .

# Устанавливаем зависимости
RUN npm install

EXPOSE 9001

CMD ["npx", "quasar", "dev"]