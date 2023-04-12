# K-Digital 2023 Demo

## monolithic

### 1. 준비

```bash
# pull postgres
$ docker pull postgres
```

```bash
# run postgres container
$ docker run -d --name demo-postgres -p 5432:5432 -e POSTGRES_USER=demouser -e POSTGRES_PASSWORD=demo-p@ssw0rd -e POSTGRES_DB=demo -e TZ=Asia/Seoul postgres
```

- DB Connection Info
-- username : demouser
-- password : p@ssw0rd
-- Database : demo

### 2. 실행

```bash
# execute on command line or lauch on IDE
$ ./gradlew bootRun
```
