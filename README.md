# spring-microservice
Experimenting microservice wih various Spring Cloud and other Components.
Project uses following component.
  MicroServices:
    1) Eureka [HA]
    2) Product [Spring Boot Microservice - eureka client, monitoring using actuator+Micrometer and Prometheus]
    3) Cart [Spring Boot Microservice - eureka client, monitoring using actuator+Micrometer and Prometheus]
  Databases:
    1) MongoDB
    2) MySQL
  Other:
    1) Prometheus [Monitoring Micro Services via Eureka DS]    
Note: Please change volume path as per your local system in docker-compose.yml file.
