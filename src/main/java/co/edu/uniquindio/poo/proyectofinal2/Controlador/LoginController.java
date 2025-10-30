package co.edu.uniquindio.poo.proyectofinal2.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContrasena;

    @FXML
    void ingresar(ActionEvent event) throws Exception {
        String usuario = txtUsuario.getText();
        String contrasena = txtContrasena.getText();

        if (usuario.equals("admin") && contrasena.equals("1234")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/vista/menuAdministrador.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Menú Administrador");
            stage.show();
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }
}
