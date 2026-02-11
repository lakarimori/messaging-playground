package com.playground.messaging.domain.message.adapter;

public interface ProduceMessageAdapter {
  void sendMessage(String topic, String message);
}
