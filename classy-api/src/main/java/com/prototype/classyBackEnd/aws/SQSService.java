package com.prototype.classyBackEnd.aws;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SQSService {

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    private SQSService(AmazonSQS amazonSQS) {
        this.queueMessagingTemplate = new QueueMessagingTemplate((AmazonSQSAsync) amazonSQS);
    }

    public void sendMessage(String message) {
        Message<String> newMessage = MessageBuilder.withPayload(message).build();
        queueMessagingTemplate.send("Post", newMessage);
    }

}
