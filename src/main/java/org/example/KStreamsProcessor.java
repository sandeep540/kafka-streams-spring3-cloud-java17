package org.example;


import org.apache.kafka.streams.kstream.KStream;
import org.example.data.InputData;
import org.example.data.OutputData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;


@Configuration
public class KStreamsProcessor {
    private final Logger logger = LoggerFactory.getLogger(KStreamsProcessor.class);

    @Bean
    public Function<KStream<String, InputData>, KStream<String, OutputData>> dataProcessor(){

        return kStream ->
                kStream
                       // .peek((k, v) -> logger.info("Messages : " + v))
                        .mapValues(v -> processFunction(v));

    };

    private OutputData processFunction(InputData t) {
        logger.info("Messages : " + t);
        return new OutputData(t.id(), t.name(), t.brand(), false );
    }

}