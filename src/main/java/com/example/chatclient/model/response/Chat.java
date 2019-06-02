package com.example.chatclient.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Chat {
  /**
   * chat id to distinguish between multiple chats.
   */
  @JsonProperty("id")
  private Long id;

  /**
   * receivers username.
   */
  @JsonProperty("username")
  private String username;

  /**
   * content of the chat message.
   */
  @JsonProperty("text")
  private String text;

  /**
   * expiration date in ISO format for when a chat expires.
   */
  @JsonProperty("expiration_date")
  private Date expiration_date;

}
