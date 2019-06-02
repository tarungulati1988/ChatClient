package com.example.chatclient.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public abstract class BaseResponse implements Serializable {
  /**
   * OK or ERROR for the response, based on this client reads data from error node or data node.
   */
  @JsonProperty("status")
  private String status;
}
