module co.edu.uniquindio.concierto.concierto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.base;


    opens co.edu.uniquindio.concierto to javafx.fxml;
    exports co.edu.uniquindio.concierto;
    exports co.edu.uniquindio.concierto.viewController;
    opens co.edu.uniquindio.concierto.viewController to javafx.fxml;
    exports co.edu.uniquindio.concierto.controller;
    opens co.edu.uniquindio.concierto.controller to javafx.fxml;
}