package org.example.C;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class HelloController {

    @FXML
    private TableView<Persona> tablaPersonas;
    @FXML
    private TableColumn<Persona, String> colNombre;
    @FXML
    private TableColumn<Persona, String> colApellidos;
    @FXML
    private TableColumn<Persona, Integer> colEdad;

    // Lista observable para manejar la tabla
    private final ObservableList<Persona> listaPersonas = FXCollections.observableArrayList();

    // Inicializar la tabla y sus columnas
    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNombre()));
        colApellidos.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getApellidos()));
        colEdad.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getEdad()));

        // Asignar la lista observable a la tabla
        tablaPersonas.setItems(listaPersonas);
    }

    @FXML
    private void agregarPersona() {
        // Crear un nuevo diálogo para agregar persona
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Agregar Persona");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/C/AddPersonDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Obtén el controlador y configura el diálogo
        AddPersonDialogController controller = fxmlLoader.getController();
        controller.showDialog(dialog);

        // Muestra el diálogo y espera la respuesta
        dialog.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                // Obtener los datos ingresados
                String nombre = controller.getNombre();
                String apellidos = controller.getApellidos();
                int edad = controller.getEdad();

                // Validar que los campos no estén vacíos
                if (nombre.isEmpty() || apellidos.isEmpty()) {
                    mostrarAlerta("Error", "Nombre y apellidos son obligatorios.");
                    return;
                }

                // Validar la edad
                if (edad < 0) {
                    mostrarAlerta("Error", "La edad debe ser un número positivo.");
                    return;
                }

                // Crear objeto Persona y agregar a la lista
                Persona nuevaPersona = new Persona(nombre, apellidos, edad);
                listaPersonas.add(nuevaPersona);
                mostrarAlerta("Éxito", "Persona agregada correctamente.");
            }
        });
    }

    // Método para mostrar alertas
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}


