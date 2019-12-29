# CrudDemoBackend
Simple Spring based backend for Angular Crud Demo Application

## Get Token
```bash
$ export TOKEN=$(curl -H "Content-Type: application/x-www-form-urlencoded" \
  -d "client_id=spa-crud" \
  -d "username=jdoe" \
  -d "password=password" \
  -d "grant_type=password" \
  -X POST http://localhost:8080/auth/realms/crud-demo/protocol/openid-connect/token | jq -r .access_token)

$ echo $TOKEN
```

## Public Request with Token
```bash
$ curl -X GET http://localhost:9090/public/posts
```

## Authenticated Request with Token
```bash
$ curl -i -X GET -H "Authorization: Bearer $TOKEN" http://localhost:9090/api/posts
```