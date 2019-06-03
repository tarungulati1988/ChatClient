package com.example.chatclient.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseRequestModel implements Serializable {
  /**
   * userId/username for the sender for the incoming request to map to a particular user.
   */
  @JsonProperty("username")
  @NotNull(groups = {Create.class, Read.class})
  private String username;

  /**
   * userId/username for the sender for the incoming request to map to a particular user.
   */
  @JsonProperty("sender")
  @NotNull(groups = {Create.class, Read.class})
  private String sender;

  public interface Create {

  }

  public interface Read {

  }
}
