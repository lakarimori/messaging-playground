package com.playground.messaging.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.playground.messaging.domain.message.service.ProcessMessageService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ProcessProduceMessageServiceTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ProcessMessageService processMessageService;

  @Test
  void testRaceCondition() throws InterruptedException {
    int numberOfRequests = 100;
    ExecutorService executor = Executors.newFixedThreadPool(10);

    // CountDownLatch helps us start all threads at the exact same time
    CountDownLatch latch = new CountDownLatch(numberOfRequests);

    for (int i = 0; i < numberOfRequests; i++) {
      executor.submit(() -> {
        try {
          mockMvc.perform(post("/api/messages")
              .contentType(MediaType.APPLICATION_JSON)
              .content("{\"sender\":\"test\", \"message\":\"hello\"}"));
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          latch.countDown();
        }
      });
    }

    // Wait for all tasks to finish
    latch.await(10, TimeUnit.SECONDS);
    executor.shutdown();

    // ASSERT: Should be exactly 100 now!
    assertEquals(100, processMessageService.getMessageCount());
  }
}
