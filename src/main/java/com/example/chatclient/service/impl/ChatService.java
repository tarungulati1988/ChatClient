package com.example.chatclient.service.impl;

import com.example.chatclient.dal.MessageDal;
import com.example.chatclient.dal.UserDal;
import com.example.chatclient.entity.Message;
import com.example.chatclient.model.request.ChatRequestModel;
import com.example.chatclient.model.response.Chat;
import com.example.chatclient.service.IChatService;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ChatService implements IChatService {

  @Autowired
  private UserDal userDal;

  @Autowired
  private MessageDal messageDal;

  @Override
  public Chat getChatById(Long id) {
    Optional<Message> message = messageDal.findById(id);
    if (message.isPresent()) {
      return Chat.builder().text(message.get().text).build();
    } else {
      throw new RuntimeException("no message found for id: " + id);
    }

  }

  @Override
  public List<Chat> getChatByUsername(String username) {
    List<Message> messages = messageDal.findByReceiver(username);
    if (!CollectionUtils.isEmpty(messages)) {
      List<Chat> chats = Lists.newArrayList();
      for (Message message : messages) {
        Chat chat = new Chat();
        chat.setId(message.id);
        chat.setText(message.text);
        //        chat.setExpiration_date(message.expirationDate);
        //        chat.setUsername(message.receiver);
        chats.add(chat);
      }
      return Lists.newArrayList(chats);
    } else {
      throw new RuntimeException("no messages found for user: " + username);
    }

  }

  @Override
  public Chat createChat(ChatRequestModel req) {
    Message message = new Message();
    message.receiver = req.getUsername();
    message.user = userDal.findByUsername(req.getSender());
    message.text = req.getText();
    message.expirationDate = req.getExpirationDate();
    Message savedMessage = messageDal.save(message);
    return Chat
        .builder()
        .id(savedMessage.id)
        .build();
  }
}
