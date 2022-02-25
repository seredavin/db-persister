package ru.seredavin.dbpersister.transport;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.seredavin.dbpersister.dto.GitReposDTO;
import ru.seredavin.dbpersister.service.GitReposService;

@Slf4j
@Service
@ConditionalOnProperty(value = "git-license-parser.kafka.consumer-enabled", havingValue = "true")
@AllArgsConstructor
public class Consumer {
    private final GitReposService gitReposService;

    @KafkaListener(topics = {"INPUT_DATA"})
    public void consume(final @Payload String message,
                        final @Header(KafkaHeaders.OFFSET) Integer offset,
                        final @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                        final @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                        final @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        final @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts,
                        final Acknowledgment acknowledgment) {

        gitReposService.saveFromDTO(new GitReposDTO(key, key, key, message));
        log.info(message);
        acknowledgment.acknowledge();
    }
}
