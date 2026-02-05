package com.playground.messaging.controller;

import com.playground.messaging.controller.dto.MessageRequest;
import com.playground.messaging.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

  @Autowired
  private final MessageService messageService;

  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @PostMapping
  public void sendMessage(@RequestBody MessageRequest messageRequest) {
    System.out.println("Sender: " + messageRequest.getSender() + ", Message: " + messageRequest.getMessage());
    messageService.processMessage(messageRequest);
  }
}
