FROM openjdk:17
EXPOSE 8090
ADD target/ecomm-backend-0.0.1-SNAPSHOT.jar ecomm-backend-docker.jar
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/ecommdb
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=root
ENTRYPOINT ["java","-jar","ecomm-backend-docker.jar"]