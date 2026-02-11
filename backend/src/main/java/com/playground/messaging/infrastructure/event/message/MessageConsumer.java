package com.playground.messaging.infrastructure.event.message;

import com.playground.messaging.domain.message.adapter.ProcessMessageAdapter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
  private final ProcessMessageAdapter processMessageAdapter;

  public MessageConsumer(ProcessMessageAdapter processMessageAdapter) {
    this.processMessageAdapter = processMessageAdapter;
  }

  @KafkaListener(topics = "${playground.topic-name}", groupId = "${spring.kafka.consumer.group-id}")
  public void consumeMessage(String message) {
    System.out.println("Received message: " + message);
    processMessageAdapter.process(message);
  }
}
