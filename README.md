# Simple chat service
A simple spring boot based chat api for the following problem statement:
[Requirements](https://github.com/tarungulati1988/ChatClient/blob/master/REQUIREMENTS.md)

## Api's
###  /api/v1/chats/{id}/id
HTTP Method: GET
Sample Request: http://localhost:9192/api/v1/chats/123/id
Sample Response(200):
~~~~
{
    "id": 1,
    "text": "lorem ipsumjohnwick, 12345!..",
    "expiration_date": "2019-06-02T21:59:31.000-0700"
}
~~~~
~~~~
curl -X GET "http://localhost:9192/api/v1/chats/1/id" --header "Content-Type: application/json" --header "Accept: application/json"
~~~~

###  /api/v1/chats/{username}/username
HTTP Method: GET
Sample Request: http://localhost:9192/api/v1/chats/johnwick/username
Sample Response(200):
~~~~
[
    {
        "id": 1,
        "text": "lorem ipsumjohnwick, 12345!.."
    },
    {
        "id": 2,
        "text": "lorem ipsumjohnwick, 12345!.."
    }
]
~~~~
~~~~
curl -X GET "http://localhost:9192/api/v1/chats/johnwick/username" --header "Content-Type: application/json" --header "Accept: application/json"
~~~~

###  /api/v1/chats
HTTP Method: POST
Sample Request:
~~~~
{
	"timeout": 5000000,
	"username": "johnwick",
	"sender": "glackman",
	"text": "lorem ipsumjohnwick, 12345!.."
}
~~~~
Sample Response(201):
~~~~
{
    "id": 2
}
~~~~
Curl:
~~~~
curl -X POST "http://localhost:9192/api/v1/chats" --header "Content-Type: application/json" --header "Accept: application/json" -d "{\"timeout\": 50000000, \"username\": \"johnwick\",\"sender\": \"glackman\", \"text\": \"lorem ipsumjohnwick, 12345\"}"
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


## Software Requirements
1. Java 8 minimum
2. Maven 3.3+


## DB access
The data base can be accessed using the below mentioned url
~~~
[H2 console](http://localhost:9192/api/h2-console/)
~~~
Using the above console more users can be added into the application database.

## Important api's for application health
1. [Metrics](http://localhost:9192/api/actuator/metrics)
2. [Health](http://localhost:9192/api/actuator/health)

