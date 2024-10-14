package org.example.C;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.Parent;

import java.util.Objects;

/**
 * La clase {@code HelloApplication} es la aplicación principal que extiende
 * la clase {@code Application} de JavaFX. Se encarga de inicializar y mostrar
 * la interfaz gráfica de usuario para la gestión de personas.
 */
public class HelloApplication extends Application {

    /**
     * Método que se llama al iniciar la aplicación.
     * Carga el archivo FXML que define la interfaz gráfica y configura
     * la escena principal.
     *
     * @param primaryStage el escenario primario donde se mostrará la
     *                     interfaz de usuario
     * @throws Exception si ocurre un error al cargar el archivo FXML
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carga del archivo FXML usando una ruta relativa
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/C/hello-view.fxml")));

        primaryStage.setTitle("Gestión de Personas");
        primaryStage.setScene(new Scene(root, 600, 400));

        // Cargar el icono de la aplicación usando una ruta relativa
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/agenda.png"))));

        primaryStage.show();
    }

    /**
     * Método principal que inicia la aplicación.
     * Este método es el punto de entrada de la aplicación y se encarga
     * de lanzar la aplicación JavaFX.
     *
     * @param args los argumentos de línea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        launch(args);
    }
}
