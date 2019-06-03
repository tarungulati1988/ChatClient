package com.example.chatclient.service.v1;

import com.example.chatclient.model.request.ChatRequestModel;
import com.example.chatclient.model.response.Chat;
import java.util.List;

public interface IChatService {
  Chat getChatById(Long id);

  List<Chat> getChatByUsername(String username);

  Chat createChat(ChatRequestModel req);

}
