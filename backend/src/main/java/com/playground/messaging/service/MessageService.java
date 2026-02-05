package com.playground.messaging.service;

import com.playground.messaging.controller.dto.MessageRequest;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

  private AtomicInteger messageCount = new AtomicInteger(0);

  public void processMessage(MessageRequest message) {
    System.out.println("Processing message from " + message.getSender() + ": " + message.getMessage());

    messageCount.addAndGet(1);
    System.out.println("Total messages processed: " + messageCount);
  }

  public int getMessageCount() {
    return messageCount.get();
  }
}
