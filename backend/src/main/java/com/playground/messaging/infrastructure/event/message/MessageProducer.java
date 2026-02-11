package com.playground.messaging.infrastructure.event.message;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public MessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void publish(String message) {
    kafkaTemplate.send("playground-topic", message)
        .whenComplete((result, ex) -> {
          if (ex == null) {
            System.out.println("Message published successfully");
          } else {
            System.out.println("Message failed to publish");
          }
        });
  }

}
