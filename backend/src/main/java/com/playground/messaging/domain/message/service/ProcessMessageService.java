package com.playground.messaging.domain.message.service;

import com.playground.messaging.domain.message.adapter.ProcessMessageAdapter;
import com.playground.messaging.infrastructure.event.message.MessageProducer;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;

@Service
public class ProcessMessageService implements ProcessMessageAdapter {

  private AtomicInteger messageCount = new AtomicInteger(0);
  private final MessageProducer messageProducer;

  public ProcessMessageService(MessageProducer messageProducer) {
    this.messageProducer = messageProducer;
  }

  @Override
  public void process(String message) {
    messageCount.addAndGet(1);
    System.out.println("Total messages processed: " + messageCount);
  }

  public int getMessageCount() {
    return messageCount.get();
  }

}
