# spring_graphql_playground

A poc project using Spring Boot, Jooq and `graphql-kotlin-spring-server` to create a demo server with qraphQL api.

## How to Start

1. Start up postgres database by Docker
- `$ docker pull postgres`
- `$ docker run --name postgres -e POSTGRES_PASSWORD=123456 -d postgres`

2. Run Spring Boot
- `$ gradle bootRun`

## Implemented 
- Get List
- Get Object by Id
- Search by user name, book name or keywords of either user name or book's name

## TODO
- Create, Update and delete by GraphQL api
- Authorisation of api
- More Entity
