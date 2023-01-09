package com.prototype.classyBackEnd.aws;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SQSVideoDurationMessage {
    private String s3Title;
    private String videoDuration;
}
