package thelameres.pubsub.producer;

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
import java.util.concurrent.atomic.AtomicLong;

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
                jmsTemplate.convertAndSend("queue", helloDto);
            }, 10000);
        };
    }
}
