package ru.strelchm.kafkatest.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import ru.strelchm.kafkatest.dto.SimpleDto;

import java.util.concurrent.atomic.AtomicLong;

import static ru.strelchm.kafkatest.sender.SenderConfig.TEST_TOPIC_NAME;

public class Sender {
  private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);
  @Autowired
  private ObjectMapper mapper;
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;
  private static final AtomicLong COUNTER = new AtomicLong(0);

  public void send(String payload) throws JsonProcessingException {
    LOGGER.info("sending payload='{}'", payload);
    kafkaTemplate.send(TEST_TOPIC_NAME, mapper.writeValueAsString(new SimpleDto(payload, COUNTER.incrementAndGet())));
  }
}