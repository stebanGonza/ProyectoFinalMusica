

module co.edu.uniquindio.poo.proyectofinal2 {
        requires javafx.controls;
        requires javafx.fxml;
        requires javafx.graphics;

        opens co.edu.uniquindio.poo.proyectofinal2.controlador to javafx.fxml;
        opens co.edu.uniquindio.poo.proyectofinal2.modelo to javafx.base;
        exports co.edu.uniquindio.poo.proyectofinal2;
        }
