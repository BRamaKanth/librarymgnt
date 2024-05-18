FROM jdk-17-alpine 
COPY pom.xml .
COPY /library-mgmnt  library-mgmnt

RUN mvn clean package -DskipTests

COPY /target/library-mgmnt.jar library-mgmnt.jar 

RUN ["java","jar ","library-mgmnt.jar"]


