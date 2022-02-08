# General picture.
![](API-implementation.png)
## How to compile.
1. With EclipseLink support: mvn clean package -Peclipselink.
2. With Hibernate support: mvn clean package -Phibernate.

## How to run.
Before running the application you need:
1. Install [PostgreSQL](https://www.postgresql.org/) server.
2. Create database "multiplication". SQL file can be found in "microservices.book.persistence" module. See multiplication_db.sql.
3. In your /etc/hosts file add line "ip-of-postgres-server   pgsrv".
4. Compile application.

Application can be found in "microservices.book.application" module target directory.  
To run it type java -jar microservices.book.application-0.0.1-SNAPSHOT.jar.
