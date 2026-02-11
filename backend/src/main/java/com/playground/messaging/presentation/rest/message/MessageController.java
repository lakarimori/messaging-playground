package com.playground.messaging.presentation.rest.message;

import com.playground.messaging.domain.message.adapter.ProcessMessageAdapter;
import com.playground.messaging.domain.message.adapter.ProduceMessageAdapter;
import com.playground.messaging.presentation.rest.message.dto.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

  private final ProduceMessageAdapter produceMessageAdapter;

  public MessageController(ProduceMessageAdapter produceMessageAdapter) {
    this.produceMessageAdapter = produceMessageAdapter;
  }

  @PostMapping
  public void sendMessage(@RequestBody MessageRequest messageRequest) {
    System.out.println("Sender: " + messageRequest.getSender() + ", Message: " + messageRequest.getMessage());
    String payload = messageRequest.getSender() + ":" + messageRequest.getMessage();

    produceMessageAdapter.sendMessage("",payload);
  }
}
