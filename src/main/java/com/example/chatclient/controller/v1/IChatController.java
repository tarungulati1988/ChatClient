package com.example.chatclient.controller.v1;

import com.example.chatclient.model.request.ChatRequestModel;
import com.example.chatclient.model.response.Chat;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface IChatController {
  ResponseEntity<Chat> getChatById(Long id);

  //  ResponseEntity<List<Chat>> getChatByUserName(ChatRequestModel chatRequestModel);
  ResponseEntity<List<Chat>> getChatByUserName(String username);

  ResponseEntity<Chat> postChat(ChatRequestModel chatRequestModel);
}
