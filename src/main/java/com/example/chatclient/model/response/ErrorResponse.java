package com.example.chatclient.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ErrorResponse extends BaseResponse {
  private List<String> message;
}