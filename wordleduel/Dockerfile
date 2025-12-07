FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY . .

# make mvnw executable, then build
RUN chmod +x mvnw && ./mvnw -DskipTests package

EXPOSE 8080

CMD ["java","-jar","target/*.jar"]
