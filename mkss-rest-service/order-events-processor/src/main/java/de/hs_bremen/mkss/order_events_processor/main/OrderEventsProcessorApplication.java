package de.hs_bremen.mkss.order_events_processor.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "de.hs_bremen.mkss.order_events_processor")
public class OrderEventsProcessorApplication {

	public static void main(String[] args) {

		SpringApplication.run(OrderEventsProcessorApplication.class, args);
	}

}
