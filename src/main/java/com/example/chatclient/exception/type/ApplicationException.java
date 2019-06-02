package com.example.chatclient.exception.type;

public class ApplicationException extends RuntimeException {
  /**
   * Throw custom exception pertaining to the chat application with custom messages.
   *
   * @param message - message for the exception thrown explaining the reason
   */
  public ApplicationException(String message) {
    super(message);
  }
}
