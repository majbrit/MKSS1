package de.hs_bremen.mkss.interfaceAdapters.controllersAndPresenters.gui;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaFXConfig {

    private Stage primaryStage;

    @Bean
    public Stage primaryStage() {
        // Stelle sicher, dass Stage im JavaFX-Thread erstellt wird
        Platform.runLater(() -> {
            this.primaryStage = new Stage();
            // Initialisiere den Stage hier, nachdem er im richtigen Thread erstellt wurde
        });
        return this.primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


}
