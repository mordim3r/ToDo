# ToDo App

Консольное приложение для управления задачами на Java с базой данных PostgreSQL.

## Технологии
- Java 25
- JDBC
- PostgreSQL
- Maven

## Функциональность
- Добавление задачи
- Просмотр всех задач
- Просмотр невыполненных задач
- Отметить задачу выполненной
- Удаление задачи

## Запуск

### Требования
- Java 25
- PostgreSQL

### Настройка базы данных
Создай базу данных и таблицу:
```sql
CREATE DATABASE todo_db;

CREATE TABLE tasks (
    id           SERIAL PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    done         BOOLEAN      NOT NULL DEFAULT FALSE,
    created_date DATE         NOT NULL DEFAULT CURRENT_DATE
);
```

### Запуск приложения
java -jar ToDo-1.0-SNAPSHOT-jar-with-dependencies.jar

## Архитектура
- `model` — модель данных
- `repository` — работа с базой данных
- `ui` — консольное меню
