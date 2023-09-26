# Igot Dashboard API
API to fetch analytics data of child organisations

## Prerequisites
1. Java version 17
2. Maven version 3.8.8

## Run
1. Set the value of `RESPONSE_FILE_PATH` in `util/Constants.java` to the value of `RESPONSE_PATH` in https://github.com/nic-asdd-lms/Igot-Dashboard-Script/blob/main/src/main/java/igot/ehrms/util/Constants.java

2. Create tables `ehrms_users` and `ehrms_log` in Postgres:
```
CREATE TABLE IF NOT EXISTS public.ehrms_users
(
    id uuid NOT NULL,
    password text COLLATE pg_catalog."default" NOT NULL,
    org text COLLATE pg_catalog."default",
    CONSTRAINT ehrms_users_pkey PRIMARY KEY (id)
)
```

```
CREATE TABLE IF NOT EXISTS public.ehrms_log
(
    id integer NOT NULL DEFAULT nextval('ehrms_log_id_seq'::regclass),
    user_id character varying(50) COLLATE pg_catalog."default" NOT NULL,
    org_id character varying(50) COLLATE pg_catalog."default",
    action text COLLATE pg_catalog."default",
    "timestamp" time without time zone,
    CONSTRAINT ehrms_log_pkey PRIMARY KEY (id)
)
```

3. Add the following lines in `application.properties`:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/<DATABASE_NAME>
spring.datasource.username=<POSTGRES_USERNAME>
spring.datasource.password=<POSTGRES_PASSWORD>
spring.datasource.driver-class-name=org.postgresql.Driver
``` 
4. `mvn clean install`

5. `java -jar target/ehrms-0.0.1-SNAPSHOT.jar`

6. Create user for a department: 
```
curl --location --request POST 'localhost:8000/apis/igot/dashboard/user/create/<mapId>' 
```

7. Generate token for the created user:
```
curl --location --request POST 'localhost:8000/apis/igot/dashboard/authenticate' \
--header 'Content-Type: application/json' \
--data-raw '{
   "password": <password>,
    "username": <id>
}' 
```

8. Call the API to get the metrics
```
curl --location --request GET 'localhost:8000/apis/igot/analytics/<mapId>' \
--header 'id: <id>' \
--header 'Authorization: <Bearer token>' \
```
