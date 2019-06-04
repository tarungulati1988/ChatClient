package com.example.chatclient.service.v1.impl;

import com.example.chatclient.dal.MessageDal;
import com.example.chatclient.dal.UserDal;
import com.example.chatclient.entity.Message;
import com.example.chatclient.entity.User;
import com.example.chatclient.exception.type.ApplicationException;
import com.example.chatclient.model.request.ChatRequestModel;
import com.example.chatclient.model.response.Chat;
import com.example.chatclient.service.v1.IChatService;
import com.google.common.collect.Lists;
import java.util.Calendar;
import java.util.Date;
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
      return Chat
          .builder()
          .id(message.get().id)
          .expiration_date(message.get().expirationDate)
          .text(message.get().text)
          .build();
    } else {
      throw new ApplicationException("no message found for id: " + id);
    }
  }

  /**
   * Given a username fetch all the messages belonging to that user in the system.
   *
   * @param username - the receivers username
   * @return - Collection of chat messages belonging to that user
   */
  @Override
  public List<Chat> getChatByUsername(String username) {
    List<Message> messages = messageDal
        .findByReceiverAndAndExpirationDateAfter(username, new Date());
    if (!CollectionUtils.isEmpty(messages)) {
      List<Message> updatedMessages = Lists.newArrayList();
      List<Chat> chats = Lists.newArrayList();
      for (Message message : messages) {
        Chat chat = new Chat();
        chat.setId(message.id);
        chat.setText(message.text);
        chats.add(chat);
        message.expirationDate = new Date();
        updatedMessages.add(message);
      }
      messageDal.saveAll(updatedMessages);
      return Lists.newArrayList(chats);
    } else {
      throw new ApplicationException("no messages found for user: " + username);
    }

  }

  /**
   * Given a chat request model save the chat to the database.
   * On success return the id of the newly created chat
   *
   * @param req - char request model
   *            text - content of the chat message
   *            expirationDate - date when the chat should expire
   *            username - receivers username
   *            sender - senders user name
   * @return - Chat model containing chat id for the created chat message
   */
  @Override
  public Chat createChat(ChatRequestModel req) {
    User user = userDal.findByUsername(req.getSender());

    // check if sender and receiver exists in the system
    if (user == null || !userDal.existsByUsername(req.getUsername())) {
      throw new ApplicationException("User doesn't exist in the system.");
    }
    // build out the message dal object
    Message message = new Message();
    message.receiver = req.getUsername();
    message.user = user;
    message.text = req.getText();
    message.expirationDate = buildExpirationDate(req.getTimeout());
    // save the message
    Message savedMessage = messageDal.save(message);

    // return a chat response object containing the newly created message id
    return Chat
        .builder()
        .id(savedMessage.id)
        .build();
  }

  /**
   * Given a timeout in seconds generate the expiration date using the current date.
   * @param timeout - timeout in seconds.
   * @return Expiration date to be saved in the database
   */
  private Date buildExpirationDate(Integer timeout) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    cal.add(Calendar.SECOND, timeout);
    return cal.getTime();
  }
}
