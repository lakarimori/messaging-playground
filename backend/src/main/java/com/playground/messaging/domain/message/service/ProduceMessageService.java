package com.playground.messaging.domain.message.service;

import com.playground.messaging.domain.message.adapter.ProduceMessageAdapter;
import com.playground.messaging.infrastructure.event.message.MessageProducer;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;

@Service
public class ProduceMessageService implements ProduceMessageAdapter {

  private AtomicInteger messageCount = new AtomicInteger(0);
  private final MessageProducer messageProducer;

  public ProduceMessageService(MessageProducer messageProducer) {
    this.messageProducer = messageProducer;
  }

  public int getMessageCount() {
    return messageCount.get();
  }

  @Override
  public void sendMessage(String topic, String message) {
    messageCount.addAndGet(1);
    messageProducer.publish(message);
    System.out.println("Total messages produced: " + messageCount);
  }
}
