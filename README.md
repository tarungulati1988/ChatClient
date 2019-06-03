# Simple chat service
A simple spring boot based chat api for the following problem statement:
[Requirements](https://github.com/tarungulati1988/ChatClient/blob/master/REQUIREMENTS.md)

## Api's
###  /api/v1/chats/{id}/id
HTTP Method: GET
Sample Request: http://localhost:9192/api/v1/chats/123/id
~~~~
curl -X GET "http://localhost:9192/api/v1/chats/1/id" --header "Content-Type: application/json" --header "Accept: application/json"
~~~~

###  /api/v1/chats/{username}/username
HTTP Method: GET
Sample Request: http://localhost:9192/api/v1/chats/johnwick/username
~~~~
curl -X GET "http://localhost:9192/api/v1/chats/johnwick/username" --header "Content-Type: application/json" --header "Accept: application/json"
~~~~

###  /api/v1/chats
HTTP Method: POST
Sample Request: http://localhost:9192/api/v1/chats
~~~~
curl -X POST "http://localhost:9192/api/v1/chats" --header "Content-Type: application/json" --header "Accept: application/json" -d "{\"expirationDate\": \"2020-01-17T22:43:31.410Z\", \"username\": \"johnwick\",\"sender\": \"glackman\", \"text\": \"lorem ipsumjohnwick, 12345\"}"
~~~~

## Api postman collection
Postman collection can be imported in to postman to test the api using postman or else using curl
- [Postman collection](https://github.com/tarungulati1988/ChatClient/blob/master/Chat%20Client.postman_collection.json)


## Steps to run the server and test the api's
1. Run ```mvn -U clean install``` for all dependencies
2. Run ```mvn spring-boot:run``` to start the server
3. On startup users johnwick and glackman are created as there aren't any api's to create users yet.
4. Use only glackman or johnwick as the sender and receiver for the api's
5. More users can be added to the db once the server is up by accessing the db using: ```http://localhost:{port}/h2-console``` currently the application starts up on port 9192.
6. To run the integration tests run ```mvn clean test```



