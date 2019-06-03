package com.example.chatclient.model.request;

import static com.example.chatclient.constant.DataContstants.PST;
import com.example.chatclient.validators.InFuture;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatRequestModel extends BaseRequestModel {
  /**
   * chat id to query a particular chat
   */
  @JsonProperty("chatId")
  private Long chatId;

  /**
   * text containing the text message for the chat.
   */
  @JsonProperty("text")
  private String text;

  /**
   * Expiration date for the incoming chat message.
   */
  @JsonProperty("expirationDate")
  @InFuture(groups = {Create.class})
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = PST)
  private Date expirationDate;
}
