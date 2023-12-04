# Apofig Blog

## Setup development environment

- Install Java 11
- Install Docker
- Install Postgres
  + Create database `apofig`
    * `CREATE DATABASE apofig;`
  + Create user `apofig` with password `apofig`
    * `CREATE USER apofig WITH PASSWORD 'apofig';`
  + Grant all privileges to user `apofig`
    * `GRANT USAGE ON SCHEMA public TO apofig;`
    * `GRANT CREATE ON SCHEMA public TO apofig;`
    * `GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO apofig;`
    * `ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL PRIVILEGES ON TABLES TO apofig;`

## Run the project locally

- (Optional) To run the project locally create a file `application-local.yml` 
  in `src/main/resources` folder and override all sensitive properties from 
  `application.yml` file. This file is ignored by git.

- Start Milvus database with docker. From project root folder run 
  `docker-compose up -d` command.

- Run `ApofigBlogApplication` class.
  + set VM options: `-Dspring.profiles.active=local,nocache`
    * `local` - to use `application-local.yml` file
    * `nocache` - to disable caching of static resources
  + set environment variables:
    ```
    DB_HOST=localhost
    DB_NAME=apofig
    DB_PASSWORD=apofig
    DB_PORT=5432
    DB_USERNAME=apofig
    DELETE_SECRET=12345
    ```
    
- Open `http://localhost:8080/blog` in browser to open list of posts.

## Run the project locally from jar 

- Please update `.env` file with correct values.

- Run `bash run-local.sh 1` command to build jar, stop/start docker-compose 
  and run application

- Run `bash run-local.sh 2` command to stop/start docker-compose and run  
  application. Expected that jar is already built.

- Run `bash run-local.sh 3` command to run application only. Expected that 
  docker-compose is already running and jar is already built.

- Open `http://localhost:8080/blog` in browser to open list of posts.