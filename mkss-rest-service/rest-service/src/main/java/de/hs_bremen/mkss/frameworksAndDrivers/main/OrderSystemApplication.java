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



/*package de.hs_bremen.mkss.frameworksAndDrivers.main;

import de.hs_bremen.mkss.domain.factory.ItemFactory;
import de.hs_bremen.mkss.domain.factory.SimpleItemFactory;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import de.hs_bremen.mkss.interfaceAdapters.controllersAndPresenters.cui.CUI;
import de.hs_bremen.mkss.interfaceAdapters.controllersAndPresenters.gui.GuiMenu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "de.hs_bremen.mkss")
@EnableJpaRepositories("de.hs_bremen.mkss.domain.repositoryInterfaces")
@EntityScan("de.hs_bremen.mkss.domain")
public class OrderSystemApplication extends Application {

	private static ConfigurableApplicationContext springContext;
	private static Stage primaryStage;
	private static ItemFactory itemFactory;
	private static IOrderRepository orderRepository;

	public static void main(String[] args) {
		springContext = SpringApplication.run(OrderSystemApplication.class, args);

		itemFactory = springContext.getBean(SimpleItemFactory.class);
		orderRepository = springContext.getBean(IOrderRepository.class);

		if (args.length == 0) {
			System.out.println("Please use ‘cli’ or ‘gui’ as an argument.");
			return;
		}

		switch (args[0].toLowerCase()) {
			case "cli":
				startCLI();
				break;
			case "gui":
				Application.launch(OrderSystemApplication.class, args);
				break;
			default:
				System.out.println("Invalid argument: Please use ‘cli’ or ‘gui’ as an argument.");
				break;
		}
	}

	@Override
	public void init() throws Exception {
	}

	private static void startCLI() {
		System.out.println("CUI is starting...");

		CUI cui = new CUI(orderRepository, itemFactory);
		cui.menuLoop();
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.primaryStage = stage;
		System.out.println("GUI is starting...");

		// JavaFX GUI wird hier gestartet
		Platform.runLater(() -> {
			GuiMenu guiMenu = springContext.getBean(GuiMenu.class);
			guiMenu.setAll(primaryStage);
		});
	}

	@Override
	public void stop() throws Exception {
		// Wenn die Anwendung stoppt, beende den Spring Context und schließe JavaFX
		springContext.close();
		Platform.exit();
	}
}*/
