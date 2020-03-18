# Stack Overflow Web Service

### Getting Started
After you installed `Java 8+` and `Maven 3+`, the fastest way to run **Stack Overflow Web Service** without any configuration:
```
mvn spring-boot:run
```
After successfully launched, you can see the API endpoints by requesting the url below:
```
http://localhost:8080/api
```
If you need to change the default port other than **8080**, you can start server with additional `server.port=8000`:
```
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8000
```

### Running Options
To run User Webservice Application over on **Mysql**:
- Make sure you have a running Mysql instance on `localhost:3306`
- Create an empty schema `stackoverflow_webservice` if you haven't already
- Simply navigate to the project folder and execute command below:
```
mvn spring-boot:run -Dspring-boot.run.profiles=prod,import -Dspring-boot.run.arguments=--spring.datasource.username=root,--spring.datasource.password=password
```
Certainly, you should provide your own database username and password instead of `root` and `password` parts in the above command.

If you have already data populated in your database you should skip `import` profile:
```
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

Alternatively, you can run the application without any external database. 
An **H2 in-memory** database and some amount of test data automatically configured if you just use the `dev` profile:
```
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

Additionaly, the default fallback profile is also `dev` profile so you can easily skip `-Dspring-boot.run.profiles=dev` part and run directly:
```
mvn spring-boot:run
```
### Run Using Docker
To run with docker you can simply execute `./build.sh` file which contains the command below:
```
mvn clean install
docker container rm stackoverflow-webservice
docker build --build-arg USER=webservice -t dockerhub/stackoverflow-webservice-0.0.1 .
docker run -p 8080:8000 --name stackoverflow-webservice -e JAVA_OPTS=-Dserver.port=8000 dockerhub/stackoverflow-webservice-0.0.1
```
If you would like to deploy into your docker hub, do not forget to change `dockerhub/` to your docker hub user.

Alternatively, you can use the ready image from the hub directly:
```
docker run -p 8080:8000 --name stackoverflow-webservice -e JAVA_OPTS=-Dserver.port=8000 devyavuztas/stackoverflow-webservice-0.0.1
```
 
### Automated Tests 
To run automated tests for the application you can simply execute the command below:
```
mvn test
```

### Service Endpoints
You can access the API documentation page which was set up by [Swagger 2](https://swagger.io):
```
http://localhost:8080/api
```
### Design Notes
1. Under the package `dev.yavuztas.stackoverflowwebservice.view` we create model classes for web service requests and remote service responses 
in order to expose our data as well as consume the remote data in a controlled way. Besides, we aim to keep our domain models clean. 
Certainly, there are alternatives like Jackson `@JsonView` however, we prefer not to spread our conversion logic over on domain models.

2. Since we customized error handling as a sample in `ApiErrorHandler` we have a chance to guide our consumers by more meaningful error messages 
which can be important in API development.

3. Even though more tests can be implemented to increase test coverage, we implement only integration tests for `QuestionService` 
which was considered the most critical part for this application.

4. In order to customize Swagger home page, no matter how we handle on our side we cannot change `swagger-ui.html` as for now. This is because 
it's hardcoded in [springfox.js](https://github.com/springfox/springfox/blob/34246cf6925ac7ea985969de8a2ced2dab3982ec/springfox-swagger-ui/src/web/js/springfox.js#L135).
We should consider to fork repository and fix the hardcoded part. Also, forking would be the best choice if we need to customize style, CSS and HTML structure. 

### Notes About Security
This web service designed without security. Ideally in production, accessing end points should be restricted in network level (like: DMZ, firewall configurations, ip restriction, etc.) 
unless there is a requirement to make them public.