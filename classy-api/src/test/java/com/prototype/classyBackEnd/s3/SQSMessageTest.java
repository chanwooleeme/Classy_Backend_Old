package com.prototype.classyBackEnd.s3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prototype.classyBackEnd.aws.SQSVideoDurationMessage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

@Slf4j
public class SQSMessageTest {

    @Test
    public void test() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = "{\"sourceS3Key\": \"pexels-cup-of-couple-8473762.mp4\", \"videoDuration\": \"00:00:11.44\"}";
        SQSVideoDurationMessage message = objectMapper.readValue(s, SQSVideoDurationMessage.class);
        log.debug(message.toString());
    }
}
