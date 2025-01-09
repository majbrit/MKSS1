package de.hs_bremen.mkss.frameworksAndDrivers.main;

import de.hs_bremen.mkss.domain.factory.ItemFactory;
import de.hs_bremen.mkss.domain.factory.SimpleItemFactory;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "de.hs_bremen.mkss")
@EnableJpaRepositories("de.hs_bremen.mkss.domain.repositoryInterfaces")
@EntityScan("de.hs_bremen.mkss.domain")
public class OrderSystemApplication {

	private static ConfigurableApplicationContext springContext;
	private static ItemFactory itemFactory;
	private static IOrderRepository orderRepository;

	public static void main(String[] args) {

		springContext = SpringApplication.run(OrderSystemApplication.class, args);
		itemFactory = springContext.getBean(SimpleItemFactory.class);
		orderRepository = springContext.getBean(IOrderRepository.class);

	}

}
