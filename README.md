# ПСБ Learn

Проект разработан в рамках хакатона Changellenge 2025.  
Реализована полноценная LMS с поддержкой курсов, материалов (статьи, файлы), заданий и экспортом уроков в офлайн-формат.

## Архитектура проекта

```mermaid
graph TD
    A[Клиент (браузер)] -->|HTTP| B(Frontend контейнер)
    B -->|порт 9001| C[http://localhost:9001]
    B -->|API| D(Backend контейнер)
    D -->|порт 8081| E[Spring Boot REST API]
    D -->|JDBC| F(PostgreSQL контейнер)
    F -->|порт 5432| G[PostgreSQL 16]
    B -->|прямые PUT-запросы| H[Yandex Cloud Object Storage]

    subgraph Docker Compose
        B & D & F
    end

    style B fill:#E3F2FD,stroke:#1976D2
    style D fill:#FFF3E0,stroke:#FF8F00
    style F fill:#E8F5E8,stroke:#2E7D32
    style H fill:#FCE4EC,stroke:#AD1457
```

## Технологический стек

| Слой            | Технология                  | Примечание                           |
| --------------- | --------------------------- | ------------------------------------ |
| Frontend        | Vue 3 + Quasar Framework    | Composition API, Vite, Pinia         |
| Редактор        | TipTap (ProseMirror)        | WYSIWYG-редактор статей              |
| Хранилище       | Yandex Cloud Object Storage | Прямая загрузка с клиента            |
| Backend         | Java 21 + Spring Boot 3     | REST API                             |
| База данных     | PostgreSQL 16               | Полная структура курсов и материалов |
| Контейнеризация | Docker + Docker Compose     | Полная изоляция всех компонентов     |

## Требования

- Docker ≥ 20.10

Установка Node.js, JDK или других инструментов не требуется.

## Запуск проекта

```bash
git clone https://github.com/Komissarich/Changellenge_hackaton_2025
cd Changellenge_hackaton_2025
docker compose up --build
```
