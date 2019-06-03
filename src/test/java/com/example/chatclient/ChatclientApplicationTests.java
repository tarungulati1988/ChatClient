package com.example.chatclient;


import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import com.example.chatclient.model.request.ChatRequestModel;
import com.example.chatclient.model.response.Chat;
import com.example.helper.TestHelper;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Configuration
@ActiveProfiles( {"Integration"})
public class ChatclientApplicationTests {

  @Autowired
  TestRestTemplate restTemplate;
  HttpHeaders headers = new HttpHeaders();
  @LocalServerPort
  private int port;

  @Test
  public void testGetChatByIdForChatNotExist() throws Exception {
    headers.add(ACCEPT, APPLICATION_JSON_VALUE);
    headers.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    ResponseEntity<Chat> response = restTemplate
        .exchange(createURLWithPort("/api/v1/chats/5/id"), HttpMethod.GET, entity, Chat.class);
    Chat chat = response.getBody();
    assertTrue(response.getStatusCodeValue() == 400);
    assertTrue(chat.getMessage().size() == 1);
    assertTrue(chat
        .getMessage()
        .get(0)
        .equalsIgnoreCase("no message found for id: 5"));
  }

  @Test
  public void testGetChatByUserForUserNameNotExist() throws Exception {
    headers.add(ACCEPT, APPLICATION_JSON_VALUE);
    headers.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    ResponseEntity<Chat> response = restTemplate
        .exchange(createURLWithPort("/api/v1/chats/qwerty/username"),
            HttpMethod.GET, entity, Chat.class);
    Chat chat = response.getBody();
    assertTrue(response.getStatusCodeValue() == 400);
    assertTrue(chat.getMessage().size() == 1);
    assertTrue(chat
        .getMessage()
        .get(0)
        .equalsIgnoreCase("no messages found for user: qwerty"));
  }

  @Test
  public void testPostChatByNotExistUser() throws Exception {
    headers.add(ACCEPT, APPLICATION_JSON_VALUE);
    headers.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
    ChatRequestModel requestModel = TestHelper
        .getData("postChatIncorrectData_1.json", ChatRequestModel.class);
    HttpEntity<ChatRequestModel> entity = new HttpEntity<>(requestModel, headers);
    ResponseEntity<Chat> response = restTemplate
        .postForEntity(createURLWithPort("/api/v1/chats"), entity, Chat.class);
    Chat chat = response.getBody();
    assertTrue(response.getStatusCodeValue() == 400);
    assertTrue(chat.getMessage().size() == 1);
    assertTrue(chat
        .getMessage()
        .get(0)
        .equalsIgnoreCase("User doesn't exist in the system."));
  }

  @Test
  public void testPostChatByNoUsers() throws Exception {
    headers.add(ACCEPT, APPLICATION_JSON_VALUE);
    headers.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
    ChatRequestModel requestModel = TestHelper
        .getData("postChatIncorrectData_2.json", ChatRequestModel.class);
    HttpEntity<ChatRequestModel> entity = new HttpEntity<>(requestModel, headers);
    ResponseEntity<Chat> response = restTemplate
        .postForEntity(createURLWithPort("/api/v1/chats"), entity, Chat.class);
    Chat chat = response.getBody();
    assertTrue(response.getStatusCodeValue() == 400);
    assertTrue(chat
        .getMessage()
        .get(0)
        .contains("must not be null"));
    assertTrue(chat
        .getMessage()
        .get(1)
        .contains("must not be null"));
    assertTrue(chat.getMessage().size() == 2);
  }

  @Test
  public void testPostChatDateInPast() throws Exception {
    headers.add(ACCEPT, APPLICATION_JSON_VALUE);
    headers.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
    ChatRequestModel requestModel = TestHelper
        .getData("postChatIncorrectData_3.json", ChatRequestModel.class);
    HttpEntity<ChatRequestModel> entity = new HttpEntity<>(requestModel, headers);
    ResponseEntity<Chat> response = restTemplate
        .postForEntity(createURLWithPort("/api/v1/chats"), entity, Chat.class);
    Chat chat = response.getBody();
    assertTrue(response.getStatusCodeValue() == 400);
    assertTrue(chat
        .getMessage()
        .get(0)
        .equalsIgnoreCase("Expiration date is not in the future."));
    assertTrue(chat.getMessage().size() == 1);
  }

  @Test
  public void testPostChatCorrectData() throws Exception {
    headers.add(ACCEPT, APPLICATION_JSON_VALUE);
    headers.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
    ChatRequestModel requestModel = TestHelper
        .getData("postChatCorrectData_1.json", ChatRequestModel.class);
    HttpEntity<ChatRequestModel> entity = new HttpEntity<>(requestModel, headers);
    ResponseEntity<Chat> response = restTemplate
        .postForEntity(createURLWithPort("/api/v1/chats"), entity, Chat.class);
    Chat chat = response.getBody();
    assertTrue(response.getStatusCodeValue() == 201);
    assertTrue(chat
        .getId() >= 1);
  }

  @Test
  public void testGetChatByCorrectId() throws Exception {
    headers.add(ACCEPT, APPLICATION_JSON_VALUE);
    headers.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
    ChatRequestModel requestModel = TestHelper
        .getData("postChatCorrectData_1.json", ChatRequestModel.class);
    HttpEntity<ChatRequestModel> entity = new HttpEntity<>(requestModel, headers);
    restTemplate
        .postForEntity(createURLWithPort("/api/v1/chats"), entity, Chat.class);

    headers.add(ACCEPT, APPLICATION_JSON_VALUE);
    headers.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
    HttpEntity<String> getEntity = new HttpEntity<>(null, headers);
    ResponseEntity<Chat> resp = restTemplate
        .exchange(createURLWithPort("/api/v1/chats/1/id"), HttpMethod.GET, getEntity, Chat.class);
    Chat chat = resp.getBody();
    assertTrue(chat != null);
    assertTrue(chat.getText().equalsIgnoreCase("lorem ipsumjohnwick, 12345!.."));
  }

  @Test
  public void testGetChatByCorrectUsername() throws Exception {
    headers.add(ACCEPT, APPLICATION_JSON_VALUE);
    headers.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
    ChatRequestModel requestModel = TestHelper
        .getData("postChatCorrectData_1.json", ChatRequestModel.class);
    HttpEntity<ChatRequestModel> entity = new HttpEntity<>(requestModel, headers);
    restTemplate
        .postForEntity(createURLWithPort("/api/v1/chats"), entity, Chat.class);
    HttpEntity<String> getEntity = new HttpEntity<>(null, headers);
    ResponseEntity<List<Chat>> resp = restTemplate
        .exchange(createURLWithPort("/api/v1/chats/johnwick/username"),
            HttpMethod.GET, getEntity, new ParameterizedTypeReference<List<Chat>>() {
            });
    List<Chat> chat = resp.getBody();
    assertTrue(chat != null);
    assertTrue(chat.get(0).getText().equalsIgnoreCase("lorem ipsumjohnwick, 12345!.."));
    assertTrue(chat.get(0).getId() == 1);
  }

  @Test
  public void testGetChatByCorrectUsernameAndExpireMessage() throws Exception {
    headers.add(ACCEPT, APPLICATION_JSON_VALUE);
    headers.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
    ChatRequestModel requestModel = TestHelper
        .getData("postChatCorrectData_1.json", ChatRequestModel.class);
    HttpEntity<ChatRequestModel> entity = new HttpEntity<>(requestModel, headers);
    restTemplate
        .postForEntity(createURLWithPort("/api/v1/chats"), entity, Chat.class);

    HttpEntity<String> getEntity = new HttpEntity<>(null, headers);
    restTemplate.exchange(createURLWithPort("/api/v1/chats/johnwick/username"),
        HttpMethod.GET, getEntity, List.class);
    ResponseEntity<Chat> response = restTemplate
        .exchange(createURLWithPort("/api/v1/chats/johnwick/username"),
            HttpMethod.GET, entity, Chat.class);
    Chat chat = response.getBody();
    assertTrue(response.getStatusCodeValue() == 400);
    assertTrue(chat.getMessage().size() == 1);
    assertTrue(chat
        .getMessage()
        .get(0)
        .equalsIgnoreCase("no messages found for user: johnwick"));
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }

}
