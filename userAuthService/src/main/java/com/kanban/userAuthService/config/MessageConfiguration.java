package com.kanban.userAuthService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
    private String exchangeName ="fmail-exchange";
    private String queueName ="fmail-queue";

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(exchangeName);
    }


    @Bean
    public Queue registerQueue(){
        return new Queue(queueName,true);
    }

    @Bean
    public Jackson2JsonMessageConverter producerConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerConverter());
        return rabbitTemplate;
    }

    @Bean
    Binding bindUser(DirectExchange directExchange, Queue queue){
        return BindingBuilder.bind(registerQueue()).to(directExchange()).with("forget-routing");
    }
}
