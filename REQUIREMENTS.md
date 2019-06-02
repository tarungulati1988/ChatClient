# Under Armour Connected Fitness Interview Home Exercise

Please read all prerequisites and requirements. Also note there is a small deliverable outlined in the final section titled *Follow Up Questions*.

Do not hesitate to email the hiring manager with any clarification questions you may have. We value collaboration and a "measure twice, cut once" philosophy!

## Prerequisites

- You may use any programming language, framework, and storage solutions you wish.
- Your project _must_ be buildable and runnable from the command line.
  - Please include step-by-step instructions on how to do so, we value portability and ease of use.
  - Please include instructions on how to run tests in your project.
- Upload a compressed file of your project to the hiring portal within 7 days of receiving this exercise.
- The service must be accessible via `curl`.

Also note:
- We will walk through your code during a portion of the on-site interview.
- We are looking to understand how you solve problems.

## Requirements

Design and implement a simple ephemeral text message (chat) service that must:
- Be ready for production deployment (read the *Follow Up Questions* section for an idea of what this means to Under Armour).
- Scale horizontally.
- Use HTTP.
- Handle **at least** 10 requests/second for reads and writes.
- Adhere to the API contract specified in the following sections.


---
### POST /chats
Creates a new message.

**Request body properties:**

|**Name**|**Type**|**Description**|**Required**|**Default Value**|
|---|---|---|---|---|
|username|String|The recipient of the message|Yes|N/A|
|text|String|The content of the message|Yes|N/A|
|timeout|Integer|The number of seconds the message should live before expiring|No|60|

**Response properties**:  
- Status code of **201 Created**
- A body with JSON containing the ID of the newly-created message
  - Example response body:
  ```
  {
    "id": 1234
  }
  ```
---
### GET /chats/:id
Returns the message for the given ID. This API returns a message regardless of expiration.

**Response properties:**  
A success response will include:
- A **200** status code
- A JSON object with the message's username, text, and date at which the message will expire as an ISO formatted string
  - Example response body:
  ```
  {
      "username": "kp19",
      "text": "hello, world!",
      "expiration_date": "2018-12-19T16:43:00.722498"
  }
  ```
---
### GET /chats/:username
Returns a list of all unexpired messages for a given username. Additionally, any messages that are read and unexpired are subsequently expired.

**Response properties:**  
A success response will include:
- A **200** status code
- An array of JSON objects with only the ID and text of the message
  - Example response body:
  ```
  [
      {
        "id": 1234,
        "text": "hello, world!"
      },
      {
        "id": 5678,
        "text": "greetings, earth!"
      },
      {
        "id": 52277,
        "text": "I got no dime but I got some time"
      }
  ]
  ```
---
### Follow Up Questions
Given your implementation, summarize how your solution could scale to millions of daily users and 1000 requests/second split evenly between reads and writes. Consider the following:
  - Data persistence / caching
  - Portability
  - Scalability
  - Testability
  - Framework implementation details

Additionally, consider the following:
- What questions might you have for product owners that could clarify or inform the need to scale your service?
- What technologies would you pick? Why?
- What would you keep in your service? What might you change?
- How would you monitor your service?
- How would you secure and protect your service?