package de.hs_bremen.mkss.frameworksAndDrivers.main;

import de.hs_bremen.mkss.domain.factory.ItemFactory;
import de.hs_bremen.mkss.domain.factory.SimpleItemFactory;
import de.hs_bremen.mkss.domain.repositoryInterfaces.IOrderRepository;
import de.hs_bremen.mkss.interfaceAdapters.controllersAndPresenters.cui.CUI;
import de.hs_bremen.mkss.interfaceAdapters.controllersAndPresenters.gui.GuiMenu;
import de.hs_bremen.mkss.interfaceAdapters.gateway.OrderRepository;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "de.hs_bremen.mkss")
public class OrderSystemApplication extends Application {

	private static ConfigurableApplicationContext springContext;
	private static ItemFactory itemFactory;
	private static IOrderRepository orderRepository;

	@Autowired
	private GuiMenu guiMenu;  // Spring verwaltet den GuiMenu Bean

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please use ‘cli’ or ‘gui’ as an argument.");
			return;
		}

		springContext = SpringApplication.run(OrderSystemApplication.class, args);

		itemFactory = springContext.getBean(SimpleItemFactory.class);
		orderRepository = springContext.getBean(OrderRepository.class);

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

	private static void startCLI() {
		System.out.println("CUI is starting...");
		CUI cui = new CUI(orderRepository, itemFactory);
		cui.menuLoop();
	}

	@Override
	public void start(Stage stage) throws Exception {
		System.out.println("GUI is starting...");

		Platform.runLater(() -> {
			GuiMenu guiMenu = springContext.getBean(GuiMenu.class);
			guiMenu.setStage(stage);
		});
	}
}
