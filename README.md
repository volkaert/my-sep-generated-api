# My SEP Generated API

# Pre-requisites
- Java >= 11
- Maven


## Compile
```
git clone https://github.com/volkaert/my-sep-generated-api.git
cd my-sep-generated-api
mvn clean install
```


## Execute in local mode
```
windows: java -jar target\my-sep-generated-api-1.0-SNAPSHOT.jar --spring.profiles.active=local
unix: java -jar target/my-sep-generated-api-1.0-SNAPSHOT.jar --spring.profiles.active=local
```
==> will print `MyApplication started...`


## Test in local mode
```
curl http://localhost:8080§hello
```
==> will print `Hello from LOCAL env !`


## OpenAPI Spec
```
http://localhost:8080/swagger-ui.html
http://localhost:8080/v3/api-docs
```
