cd /home/peda/Atit/Practice/spring-microservice
#eurka
cd eureka/
mvn clean package -Dmaven.test.skip=true
docker image build -t sc/eureka:latest .
cd ../
#cart
cd cart/cart/
./gradlew build -x test
docker image build -t sc/cart:latest .
cd ../../
#product
cd product/product/
mvn clean package -Dmaven.test.skip=true
docker image build -t sc/product:latest .
cd ../../

