package org.example.C;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtEdad;
    @FXML
    private TableView<Persona> tablaPersonas;
    @FXML
    private TableColumn<Persona, String> colNombre;
    @FXML
    private TableColumn<Persona, String> colApellidos;
    @FXML
    private TableColumn<Persona, Integer> colEdad;

    private final ObservableList<Persona> listaPersonas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNombre()));
        colApellidos.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getApellidos()));
        colEdad.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getEdad()));

        tablaPersonas.setItems(listaPersonas);

        // Detectar cuando se selecciona una persona y cargar sus datos en los campos de texto
        tablaPersonas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> seleccionarPersona());
    }

    @FXML
    private void agregarPersona() {
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String edadTexto = txtEdad.getText();

        if (nombre.isEmpty() || apellidos.isEmpty()) {
            mostrarAlerta("Error", "Nombre y apellidos son obligatorios.");
            return;
        }

        int edad;
        try {
            edad = Integer.parseInt(edadTexto);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "La edad debe ser un número entero.");
            return;
        }

        Persona nuevaPersona = new Persona(nombre, apellidos, edad);

        if (listaPersonas.contains(nuevaPersona)) {
            mostrarAlerta("Error", "Esta persona ya existe.");
        } else {
            listaPersonas.add(nuevaPersona);
            mostrarAlerta("Éxito", "Persona agregada correctamente.");
        }
    }

    @FXML
    private void seleccionarPersona() {
        Persona personaSeleccionada = tablaPersonas.getSelectionModel().getSelectedItem();
        if (personaSeleccionada != null) {
            txtNombre.setText(personaSeleccionada.getNombre());
            txtApellidos.setText(personaSeleccionada.getApellidos());
            txtEdad.setText(String.valueOf(personaSeleccionada.getEdad()));
        }
    }

    @FXML
    private void modificarPersona() {
        Persona personaSeleccionada = tablaPersonas.getSelectionModel().getSelectedItem();

        if (personaSeleccionada == null) {
            mostrarAlerta("Error", "No hay ninguna persona seleccionada.");
            return;
        }

        String nuevoNombre = txtNombre.getText();
        String nuevosApellidos = txtApellidos.getText();
        String nuevaEdadTexto = txtEdad.getText();

        if (nuevoNombre.isEmpty() || nuevosApellidos.isEmpty()) {
            mostrarAlerta("Error", "Nombre y apellidos son obligatorios.");
            return;
        }

        int nuevaEdad;
        try {
            nuevaEdad = Integer.parseInt(nuevaEdadTexto);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "La edad debe ser un número entero.");
            return;
        }

        personaSeleccionada.setNombre(nuevoNombre);
        personaSeleccionada.setApellidos(nuevosApellidos);
        personaSeleccionada.setEdad(nuevaEdad);

        tablaPersonas.refresh();
        mostrarAlerta("Éxito", "Persona modificada correctamente.");
    }

    @FXML
    private void eliminarPersona() {
        Persona personaSeleccionada = tablaPersonas.getSelectionModel().getSelectedItem();

        if (personaSeleccionada == null) {
            mostrarAlerta("Error", "No hay ninguna persona seleccionada.");
            return;
        }

        listaPersonas.remove(personaSeleccionada);
        txtNombre.clear();
        txtApellidos.clear();
        txtEdad.clear();

        mostrarAlerta("Éxito", "Persona eliminada correctamente.");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
