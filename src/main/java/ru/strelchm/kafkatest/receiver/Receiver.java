package ru.strelchm.kafkatest.receiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import ru.strelchm.kafkatest.dto.SimpleDto;

import java.util.Date;

import static ru.strelchm.kafkatest.sender.SenderConfig.TEST_TOPIC_NAME;

public class Receiver {
  private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
  @Autowired
  private ObjectMapper mapper;


  @KafkaListener(topics = TEST_TOPIC_NAME)
  public void receive(
      String payload,
      @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
      @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Date timestamp
  ) throws JsonProcessingException {
    LOGGER.info("received payload='{}' in partition {}, thread {}'",
        mapper.readValue(payload, SimpleDto.class), partition, Thread.currentThread().getName());
    LOGGER.info("ts {}'", timestamp);
  }
}
