package com.test.config.amqp;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.env.Environment;


import com.test.commons.utils.QueueNames;



@Configuration
@EnableRabbit
public class RabbitConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitConfiguration.class);

	@Autowired
	private Environment env;
	
	@Autowired
	private ApplicationContext context;
	
	//@Autowired
	//private ConfigurableApplicationContext context;

	@Bean
	public ConnectionFactory connectionFactory() {
		
		
		//String host = this.env.getProperty("SPRING_RABBITMQ_HOST", this.env.getProperty("spring.rabbitmq.host"));
		
		String host1 = env.getProperty("SPRING_RABBITMQ_HOST", env.getProperty("spring.rabbitmq.host"));
		LOGGER.debug(">>>>connectionFactory().host1= {}", host1);
		LOGGER.debug(">>>>connectionFactory().context.getApplicationName()=", context.getApplicationName());

		
		
		////ConfigurableEnvironment env = context.getEnvironment();		
		String host2 = env.getProperty("SPRING_RABBITMQ_HOST", env.getProperty("spring.rabbitmq.host"));
		LOGGER.debug(">>>>connectionFactory().host2=", host2);
		
		host1 = "localhost";
		return new CachingConnectionFactory(host1);
	}

	@Bean
	public AmqpAdmin amqpAdmin() {
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		return new RabbitTemplate(connectionFactory());
	}

	@Bean
	public Queue myQueue() {
		return new Queue(QueueNames.TESTCLEBERQUEUE.queueName());
	}
}