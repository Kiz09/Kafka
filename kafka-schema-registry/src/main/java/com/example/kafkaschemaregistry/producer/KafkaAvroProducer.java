package com.example.kafkaschemaregistry.producer;

import com.example.kafkaschemaregistry.dto.Employee;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class KafkaAvroProducer {

    @Value("${topic.name}")
    private String topicName;

    public KafkaAvroProducer(KafkaTemplate<String, Employee> template) {
        this.template = template;
    }
    private final KafkaTemplate<String, Employee> template;

    public void send(Employee employee){

        CompletableFuture<SendResult<String, Employee>> future = template.send(topicName, UUID.randomUUID().toString(), employee);
        future.whenComplete((result, ex)-> {
            if(Objects.isNull(ex)){
                System.out.println("Sent message=["+ employee +"] with offset=["+ result.getRecordMetadata().offset()+"]");
            }else {
                System.out.println("Unable to send message=["+ employee +"] due to: "+ ex.getMessage());
            }
        });
    }


}
