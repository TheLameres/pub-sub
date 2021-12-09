package thelameres.pubsub.consumer;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;
import thelameres.pubssub.shared.models.dto.HelloDto;

import javax.jms.JMSException;
import java.util.List;

@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @JmsListener(destination = "queue")
    public void receive(HelloDto message) throws JMSException {
        LoggerFactory.getLogger(ConsumerApplication.class).info("Received message from JMS: {}", message);
    }

    @JmsListener(destination = "queue")
    public void receive(List<HelloDto> message) throws JMSException {
        LoggerFactory.getLogger(ConsumerApplication.class).info("Received message from JMS: {}", message);
    }

}
