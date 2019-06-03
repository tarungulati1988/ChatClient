package com.example.chatclient.controller.v1.impl;

import com.example.chatclient.controller.v1.IChatController;
import com.example.chatclient.model.request.ChatRequestModel;
import com.example.chatclient.model.response.Chat;
import com.example.chatclient.service.IChatService;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/chats")
public class ChatController implements IChatController {

  @Autowired
  private IChatService chatService;

  @Override
  @GetMapping(value = "/{id}/id", produces = "application/json", consumes = "application/json")
  public ResponseEntity<Chat>
  getChatById(@PathVariable @NotNull @Min(value = 0, message = "Value should between 0 and 10") Long id) {
    System.out.println("getChatById");
    return ResponseEntity
        .ok()
        .body(chatService.getChatById(id));
  }

  /**
   * Returns a list of all unexpired messages for a given username.
   * Additionally, any messages that are read and unexpired are subsequently expired.
   * <p>
   * <p>
   * Sample 200 response:
   * <pre>
   * [{
   *    "id": 1234,
   *    "text": "hello, world!"
   *  },
   *  {
   *    "id": 5678,
   *    "text": "greetings, earth!"
   *  },
   *  {
   *    "id": 52277,
   *    "text": "I got no dime but I got some time"
   * }]
   * </pre>
   *
   * @param chatRequestModel
   * @return
   */
  @Override
  @GetMapping(value = "/{username}/username", produces = "application/json", consumes = "application/json")
  public ResponseEntity<List<Chat>>
  getChatByUserName(@PathVariable("username") String username) {
//  public ResponseEntity<List<Chat>>
//  getChatByUserName(@Validated(BaseRequestModel.Read.class)
//                    @ModelAttribute ChatRequestModel chatRequestModel) {
    System.out.println("getChatByUserName");
    return ResponseEntity
        .ok()
        .body(chatService.getChatByUsername(username));
  }

  /**
   * Creates a new message.
   * <p>
   * Sample 201 response:
   * <pre>
   *  {
   *    "id": 1234
   *  }
   * </pre>
   *
   * @param chatRequestModel username Type: String,
   *                         Description: The recipient of the message,
   *                         Required: Yes,
   *                         Default Value: N/A
   *                         text     Type: String,
   *                         Description: The recipient of the message,
   *                         Required: Yes,
   *                         Default Value: N/A
   *                         timeout  Type: String,
   *                         Description: The recipient of the message,
   *                         Required: Yes,
   *                         Default Value: N/A
   * @return Response Entity containing a Chat object with id of newly created
   * message and a http status 201
   */
  @Override
  @PostMapping(produces = "application/json", consumes = "application/json")
  public ResponseEntity<Chat>
  postChat(@Validated(ChatRequestModel.Create.class)
           @RequestBody ChatRequestModel chatRequestModel) {
    System.out.println("postChat");
    return ResponseEntity
        .status(201)
        .body(chatService.createChat(chatRequestModel));
  }
}
