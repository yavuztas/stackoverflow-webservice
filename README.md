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
The application runs on **H2 file** database by default. Alternatively, you can run the application with in-memory non-persistent database. 
An **H2 in-memory** database automatically configured if you just use the `dev` profile:
```
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
### Run Using Docker
To run with docker you can simply execute `./build.sh` file which contains the command below:
```
mvn clean install
docker container rm stackoverflow-webservice
docker build -t devyavuztas/stackoverflow-webservice .
docker run -p 8080:8080 --name stackoverflow-webservice devyavuztas/stackoverflow-webservice
```
If you would like to deploy into your docker hub, do not forget to change `devyavuztas/` to your docker hub user.

Alternatively, you can use the ready image from the hub directly:
```
docker run -p 8080:8080 devyavuztas/stackoverflow-webservice
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
1. We configured **H2 in-memory** embedded database for development profile in `application-dev.properties` file. It is intended for our automated tests. 
For the production profile, again the H2 database configured but in file mode. We prefer to keep our application independently runnable without an external 
database instance. However, it's possible to configure any other databases like `MySQL` or `PostgreSQL` in `application-prod.properties` file.     

2. Under the package `dev.yavuztas.stackoverflowwebservice.view` we create model classes for web service requests and remote service responses 
in order to expose our data as well as consume the remote data in a controlled way. Thus, we can keep our domain models clean. 
Certainly, there are alternatives like Jackson `@JsonView` however, we prefer not to spread our conversion logic over on domain models.

3. In the `Question` domain we define index to `TAGS` column of the collection table in order to optimize our `TAGS IN (...)` queries.

4. Ideally, `question/all` endpoint should be optimized by pagination if we have high amount of records. For now we just leave it without pagination 
to keep it simple. But, we prefer `@BatchSize` instead of `JPQL` join fetch. `@BatchSize` works well with pagination, prevents in-memory transformations
in Hibernate.

5. `QuestionImporter` service defined to import questions on application startup. Import size customizable by the property `api.remote.so.questions.importsize` 
in `application.properties` file. However, we should notice that higher import sizes can cause memory issues. If we need to import
higher amount records (like > 1000) then we should re-implement `importFeaturedQuestions` method to save our entities and flush them in a batch of smaller sizes.
Also, this class is configured to run only in production profile so that we can disable question importing when our integration tests running.

6. Since we customized error handling as a sample in `ApiErrorHandler` we have a chance to guide our consumers by more meaningful error messages 
which can be important in API development.

7. We configured method level cache for `SOApiService.fetchUser`. For the sake of simplicity we prefer [Caffeine](https://github.com/ben-manes/caffeine) java cache
library. However, Spring supports a good level of cache abstraction so we can configure a more powerful, distributed cache provider like Hazelcast later if we need.  
You can find the Caffeine configuration about caching like size, ttl, etc. in `application.properties` file.

8. Since our web service is public, we may consider to cache some other endpoints like `/question/all` and `/question/{questionId}` 
to prevent hammering over on our database. However, the other endpoints left uncached for now.

9. For documenting the API endpoints in a proper format, we use [Swagger 2](https://swagger.io). You can find its configuration in `application.properties` file.

10. In order to customize Swagger home page, no matter how we handle on our side we cannot change `swagger-ui.html` as for now. This is because 
it's hardcoded in [springfox.js](https://github.com/springfox/springfox/blob/34246cf6925ac7ea985969de8a2ced2dab3982ec/springfox-swagger-ui/src/web/js/springfox.js#L135).
We should consider to fork repository and fix the hardcoded part. Also, forking would be the best choice if we need to customize the page style 
by altering CSS and HTML files.