# Etapa 1: Build da aplicação
FROM ubuntu:latest AS build

# Atualizar e instalar o OpenJDK 17 e Maven
RUN apt-get update && apt-get install -y openjdk-17-jdk maven

# Definir diretório de trabalho
WORKDIR /app

# Copiar arquivos para dentro do container
COPY . .

# Rodar o Maven para limpar e construir o projeto
RUN mvn clean install

# Etapa 2: Imagem final mais leve para rodar a aplicação
FROM openjdk:17-jdk-slim

# Expor a porta em que o Spring Boot vai rodar
EXPOSE 8080

# Copiar o JAR gerado na fase de build para o container final
COPY --from=build /app/target/FullCar-0.0.1-SNAPSHOT.jar app.jar

# Definir o comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
