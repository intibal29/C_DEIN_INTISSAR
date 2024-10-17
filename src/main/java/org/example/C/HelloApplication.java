package org.example.C;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.util.Objects;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carga del archivo FXML de la interfaz principal
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/C/hello-view.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Gestión de Personas");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
