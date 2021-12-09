package thelameres.pubsub.producer;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import thelameres.pubssub.shared.models.dto.HelloDto;
import thelameres.pubssub.shared.models.dto.Status;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }


    @Bean
    @Autowired
    public ApplicationRunner run(ThreadPoolTaskScheduler taskScheduler, JmsTemplate jmsTemplate) {
        AtomicLong atomicLong = new AtomicLong();
        return args -> {
            taskScheduler.scheduleWithFixedDelay(() -> {
                HelloDto helloDto = new HelloDto(atomicLong.incrementAndGet(), "Hello", Instant.now(), Status.SUCCESS);
                LoggerFactory.getLogger(ProducerApplication.class).info("Send to ActiveMQ: {}", helloDto);
                jmsTemplate.convertAndSend("queue", helloDto);
            }, 10000);
            taskScheduler.scheduleWithFixedDelay(() -> {
                Random random = new Random();
                Set<HelloDto> helloDtos = new HashSet<>();
                IntStream.range(1, 10).forEach(action -> {
                    String name = random.ints(48, 123)
                            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                            .limit(10)
                            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                            .toString();
                    HelloDto helloDto = new HelloDto(atomicLong.incrementAndGet(), name, Instant.now(), Status.SUCCESS);
                    helloDtos.add(helloDto);
                });
                jmsTemplate.convertAndSend("queue", helloDtos);
                helloDtos.clear();
            }, 1000);
        };
    }
}
