FROM maven:3.9-eclipse-temurin-23 AS builder

WORKDIR /app
# 1. Сначала копируем ТОЛЬКО pom.xml — это отдельный слой!
COPY pom.xml .
# 2. Качаем зависимости (этот слой кэшируется навсегда)
RUN mvn dependency:go-offline -B

# 3. Теперь копируем код
COPY src ./src

# 4. Собираем
RUN mvn clean package -DskipTests -B

FROM eclipse-temurin:23
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]