package thelameres.pubsub.consumer;

import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import thelameres.pubssub.shared.models.dto.HelloDto;

import java.util.List;

@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Bean
    public Queue queue() {
        return new Queue("queue");
    }


    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("topic");
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }

    @RabbitListener(queues = "queue")
    public void receive(HelloDto message) {
        LoggerFactory.getLogger(ConsumerApplication.class).info("Received message from RabbitMQ: {}", message);
    }

    @RabbitListener(queues = "queue")
    public void receive(List<HelloDto> message) {
        LoggerFactory.getLogger(ConsumerApplication.class).info("Received message from RabbitMQ: {}", message);
    }

}
