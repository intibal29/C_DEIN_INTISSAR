package org.example.C;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

/**
 * La clase {@code HelloController} actúa como el controlador para la interfaz
 * gráfica de la aplicación de gestión de personas. Maneja la interacción entre
 * los elementos de la interfaz de usuario y la lógica de la aplicación.
 */
public class HelloController {

    @FXML
    private TextField txtNombre; // Campo de texto para ingresar el nombre
    @FXML
    private TextField txtApellidos; // Campo de texto para ingresar los apellidos
    @FXML
    private TextField txtEdad; // Campo de texto para ingresar la edad
    @FXML
    private TableView<Persona> tablaPersonas; // Tabla para mostrar la lista de personas
    @FXML
    private TableColumn<Persona, String> colNombre; // Columna para el nombre
    @FXML
    private TableColumn<Persona, String> colApellidos; // Columna para los apellidos
    @FXML
    private TableColumn<Persona, Integer> colEdad; // Columna para la edad

    // Lista observable para manejar la tabla
    private final ObservableList<Persona> listaPersonas = FXCollections.observableArrayList();

    /**
     * Método llamado para inicializar el controlador.
     * Configura las columnas de la tabla y asigna la lista observable
     * como modelo de datos para la tabla.
     */
    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNombre()));
        colApellidos.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getApellidos()));
        colEdad.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getEdad()));

        // Asignar la lista observable a la tabla
        tablaPersonas.setItems(listaPersonas);
    }

    /**
     * Método que se llama al agregar una nueva persona.
     * Valida la entrada del usuario y añade la persona a la lista si es válida.
     */
    @FXML
    private void agregarPersona() {
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String edadTexto = txtEdad.getText();

        // Validar que los campos nombre y apellidos no estén vacíos
        if (nombre.isEmpty() || apellidos.isEmpty()) {
            mostrarAlerta("Error", "Nombre y apellidos son obligatorios.");
            return;
        }

        // Validar que la edad sea un número entero
        int edad;
        try {
            edad = Integer.parseInt(edadTexto);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "La edad debe ser un número entero.");
            return;
        }

        // Crear objeto Persona
        Persona nuevaPersona = new Persona(nombre, apellidos, edad);

        // Verificar si la persona ya existe en la lista
        if (listaPersonas.contains(nuevaPersona)) {
            mostrarAlerta("Error", "Esta persona ya existe.");
        } else {
            // Agregar a la lista y actualizar la tabla
            listaPersonas.add(nuevaPersona);
            mostrarAlerta("Éxito", "Persona agregada correctamente.");
        }
    }

    /**
     * Método para mostrar alertas en la interfaz de usuario.
     *
     * @param titulo  el título de la alerta
     * @param mensaje el mensaje a mostrar en la alerta
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
