package co.edu.uniquindio.poo.proyectofinal2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            // CORRECCIÓN CLAVE: Usar la ruta absoluta del recurso.
            // La ruta debe empezar con '/' y seguir la estructura de paquetes:
            // /co/edu/uniquindio/poo/proyectofinal2/view/MenuView.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/proyectofinal2/view/MenuView.fxml"));
            BorderPane root = loader.load();

            Scene scene = new Scene(root, 900, 600);
            primaryStage.setTitle("Sistema de Gestión Académica");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}