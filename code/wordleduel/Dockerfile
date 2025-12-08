FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY . .

# make mvnw executable, then build
RUN chmod +x mvnw && ./mvnw -DskipTests package

EXPOSE 8080

# use sh so target/*.jar gets expanded
CMD ["sh", "-c", "java -jar target/*.jar"]

