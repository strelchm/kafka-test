package ru.strelchm.kafkatest;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.strelchm.kafkatest.dto.SimpleDto;
import ru.strelchm.kafkatest.sender.Sender;

@RestController
public class TestController {
    private final Sender sender;

    @Autowired
    public TestController(Sender sender) {
        this.sender = sender;
    }

    @GetMapping("/{param}")
    public void test(@PathVariable String param) throws JsonProcessingException {
        sender.send(param);
    }
}
