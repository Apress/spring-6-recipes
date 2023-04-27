package com.apress.spring6recipes.post.config;

import com.apress.spring6recipes.post.FrontDeskImpl;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@Configuration
public class FrontOfficeConfiguration {

	@Bean
	public KafkaTemplate<Integer, String> kafkaTemplate(ProducerFactory<Integer, String> pf) {
		return new KafkaTemplate<>(pf);
	}

	@Bean
	public ProducerFactory<Integer, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerFactoryProperties());
	}

	@Bean
	public Map<String, Object> producerFactoryProperties() {
		var properties = Map.<String, Object>of(
						ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
						ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class,
						ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return properties;
	}

	@Bean
	public FrontDeskImpl frontDesk(KafkaTemplate<Integer, String> kafka) {
		return new FrontDeskImpl(kafka);
	}
}
