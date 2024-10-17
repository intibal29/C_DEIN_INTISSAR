package org.example.C;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Dialog;

public class AddPersonDialogController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtEdad;

    /**
     * Este método se llama al mostrar el diálogo para agregar una persona.
     * Configura el comportamiento del diálogo.
     *
     * @param dialog El diálogo en el que se mostrarán los campos de entrada.
     */
    public void showDialog(Dialog<ButtonType> dialog) {
        // Configura el comportamiento del botón "Guardar"
        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                // Aquí puedes manejar la lógica de guardar la persona
                // Asegúrate de validar los campos antes de devolver los valores
                return ButtonType.OK;
            }
            return ButtonType.CANCEL;
        });
    }

    public String getNombre() {
        return txtNombre.getText();
    }

    public String getApellidos() {
        return txtApellidos.getText();
    }

    public int getEdad() {
        return Integer.parseInt(txtEdad.getText());
    }
}
