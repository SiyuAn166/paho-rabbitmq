package com.petrobest.pahorabbitmq.fbox.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class FboxPropertiesConfig {

    @Value("${fbox.topic_prefix}")
    private String topicPrefix;

    @Value("${fbox.topic_suffix}")
    private String topicSuffix;
}
