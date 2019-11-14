package com.srisai.KafkaConsumerDemo;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.binder.PollableMessageSource;

public interface SyncSink {

    @Input("input")
    PollableMessageSource pollableMessageService();
}
