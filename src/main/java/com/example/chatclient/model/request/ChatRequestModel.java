package com.example.chatclient.model.request;

import com.example.chatclient.validators.InFuture;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Max;
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
   * Timeout for the incoming chat message.
   */
  @InFuture(groups = {Create.class})
  @JsonProperty("timeout")
  @Max(value = Integer.MAX_VALUE)
  private Integer timeout = 60;
}
