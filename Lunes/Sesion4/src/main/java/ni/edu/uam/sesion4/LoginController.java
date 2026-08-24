package ni.edu.uam.sesion4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Label lblUser;

    @FXML
    private Label lblPassword;

    @FXML
    private Button btnLogin;

    @FXML
    protected void loginButtonAction(ActionEvent event) throws IOException {

        String username = txtUsername.getText();
        String password = txtPassword.getText();


            if (username.equals("admin") && password.equals("admin")) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("grade-view.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else {

                lblUser.setText("Usuario incorrecto");
                lblPassword.setText("Contraseña incorrecta");

            }

    }
}