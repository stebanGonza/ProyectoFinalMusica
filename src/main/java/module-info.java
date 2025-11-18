module co.edu.uniquindio.poo.proyectofinal2 {

    // 1. Dependencias Externas (Módulos de JavaFX)
    // Necesitas los controles y el cargador de FXML
    requires javafx.controls;
    requires javafx.fxml;

    // 2. Exportar Paquetes
    // Indica qué paquetes son accesibles para otros módulos (necesario para el MainApp)
    exports co.edu.uniquindio.poo.proyectofinal2;
    exports co.edu.uniquindio.poo.proyectofinal2.modelo;

    // 3. Abrir Paquetes (Para Reflexión FXML)
    // Esto es CRUCIAL. Permite que JavaFX (el módulo javafx.fxml) pueda
    // acceder a tus clases de la vista para instanciar los controladores
    // y usar las anotaciones @FXML.

    // Abrir el paquete principal (donde está MainApp)
    opens co.edu.uniquindio.poo.proyectofinal2 to javafx.fxml;

    // Abrir el paquete de los ViewControllers (Controladores de la UI)
    // ¡Aquí está la corrección del typo! Es 'viewcontroller', no 'controlador'.
    opens co.edu.uniquindio.poo.proyectofinal2.viewcontroller to javafx.fxml;

    // Abrir el paquete de los Controllers (Lógica de Negocio)
    // Aunque solo se usa internamente, es buena práctica si pudiera haber reflexión.
    opens co.edu.uniquindio.poo.proyectofinal2.controller to javafx.fxml;
}