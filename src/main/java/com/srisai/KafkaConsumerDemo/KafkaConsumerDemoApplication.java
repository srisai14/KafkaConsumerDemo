package com.srisai.KafkaConsumerDemo;

import com.srisai.KafkaConsumerDemo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@EnableBinding({Source.class, SyncSink.class})
public class KafkaConsumerDemoApplication {

	@Autowired
	private PollableMessageSource pollableMessageSource;

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerDemoApplication.class, args);
	}

	// Asynchronous method

//	@StreamListener("input")
//	public void consumeMessage(Employee employee) throws JsonProcessingException {
//		ObjectMapper mapper = new ObjectMapper();
//		String empInfo = mapper.writeValueAsString( employee );
//
//		System.out.println("^^^^^^^^^^^^^^^^^^^^ "+empInfo);
//	}


	// Synchronous Method

	@Scheduled(fixedRate=15000)
	public void getMessage() {
		pollableMessageSource.poll( message -> System.out.println( message.getPayload()), new ParameterizedTypeReference<Employee>(){}
		);
	}

}
