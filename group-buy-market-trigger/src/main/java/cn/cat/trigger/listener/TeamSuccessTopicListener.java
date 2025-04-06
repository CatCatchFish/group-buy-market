package cn.cat.trigger.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TeamSuccessTopicListener {

    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange(value = "${spring.rabbitmq.config.producer.exchange}", type = ExchangeTypes.TOPIC),
                    key = "${spring.rabbitmq.config.producer.topic_team_success.routing_key}",
                    value = @Queue(value = "${spring.rabbitmq.config.producer.topic_team_success.queue}")
            )
    )
    public void listener(String message) {
        log.info("接收消息：{}", message);
    }

}
