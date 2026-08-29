package ni.edu.uam.registro_app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblMensaje;
    @FXML
    private void ingresarOnClick(ActionEvent event) {
        String usuario = txtUsuario.getText().trim();
        String password = txtPassword.getText().trim();

        if (usuario.equals("admin") && password.equals("admin")) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ni/edu/uam/registro_app/estudiante-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Registro de Estudiantes");
                stage.setScene(scene);
                stage.centerOnScreen();
            } catch (IOException e) {
                e.printStackTrace();
                lblMensaje.setText("Error al cargar la vista principal.");
            }
        } else {
            lblMensaje.setText("Usuario o contraseña incorrectos.");
        }
    }
}