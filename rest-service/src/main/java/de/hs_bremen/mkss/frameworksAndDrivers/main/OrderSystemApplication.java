package de.hs_bremen.mkss.frameworksAndDrivers.main;

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
		if (args.length == 0) {
			System.out.println("Please use ‘cli’ or ‘gui’ as an argument.");
			return;
		}


		switch (args[0].toLowerCase()) {
			case "cli":
				System.setProperty("spring.profiles.active", "cli");  // CLI-Profil aktivieren
				break;
			case "gui":
				System.setProperty("spring.profiles.active", "gui");  // GUI-Profil aktivieren
				break;
			case "rest":
				SpringApplication.run(OrderSystemApplication.class, args);
				return;
			default:
				System.out.println("Invalid argument: Please use ‘cli’ or ‘gui’ as an argument.");
				return;
		}


		springContext = SpringApplication.run(OrderSystemApplication.class, args);


		itemFactory = springContext.getBean(SimpleItemFactory.class);
		orderRepository = springContext.getBean(IOrderRepository.class);


		if ("cli".equals(System.getProperty("spring.profiles.active"))) {
			startCLI();
		} else {
			Application.launch(OrderSystemApplication.class, args);
		}
	}

	@Override
	public void init() throws Exception {

	}

	private static void startCLI() {
		System.out.println("CUI is starting...");

		CUI cui = springContext.getBean(CUI.class);
		cui.menuLoop();
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.primaryStage = stage;
		System.out.println("GUI is starting...");

		Platform.runLater(() -> {
			GuiMenu guiMenu = springContext.getBean(GuiMenu.class);
			guiMenu.setAll(primaryStage);
		});
	}

	@Override
	public void stop() throws Exception {
		springContext.close();
		Platform.exit();
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
