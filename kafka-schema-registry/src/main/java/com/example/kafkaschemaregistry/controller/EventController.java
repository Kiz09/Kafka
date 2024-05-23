package com.example.kafkaschemaregistry.controller;

import com.example.kafkaschemaregistry.dto.Employee;
import com.example.kafkaschemaregistry.producer.KafkaAvroProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    private KafkaAvroProducer kafkaAvroProducer;

    public EventController(KafkaAvroProducer kafkaAvroProducer) {
        this.kafkaAvroProducer = kafkaAvroProducer;
    }
    @PostMapping("/events")
    public String sendMessage(@RequestBody Employee employee){
        kafkaAvroProducer.send(employee);

        return "Message sent";
    }

}
