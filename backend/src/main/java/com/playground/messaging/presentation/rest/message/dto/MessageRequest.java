package com.playground.messaging.presentation.rest.message.dto;

public class MessageRequest {
  private final String sender;
  private final String message;

  public MessageRequest(String sender, String message) {
    this.sender = sender;
    this.message = message;
  }

  public String getSender() {
    return sender;
  }

  public String getMessage() {
    return message;
  }
}
