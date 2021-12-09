package thelameres.pubsub.consumer;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;
import thelameres.pubssub.shared.models.dto.HelloDto;

import javax.jms.JMSException;
import javax.jms.Message;

@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @JmsListener(destination = "queue")
    public void receive(Message message) throws JMSException {
        HelloDto body = message.getBody(HelloDto.class);
        LoggerFactory.getLogger(ConsumerApplication.class).info("Received message from JMS: {}",body);
    }

}
