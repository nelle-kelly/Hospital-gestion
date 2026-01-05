# Utiliser une image Java 17
FROM eclipse-temurin:17-jdk-alpine

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier pom.xml et les fichiers Maven wrapper
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Télécharger les dépendances
#RUN ./mvnw dependency:go-offline

# Copier le code source
COPY src ./src

# Construire l'application
RUN ./mvnw clean package -DskipTests

# Exposer le port
EXPOSE 8763

# Commande pour démarrer l'application
ENTRYPOINT ["java", "-jar", "target/eureka-register-0.0.1-SNAPSHOT.jar"]