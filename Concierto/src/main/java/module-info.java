module co.edu.uniquindio.concierto.concierto {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens co.edu.uniquindio.concierto to javafx.fxml;
    exports co.edu.uniquindio.concierto;
    exports co.edu.uniquindio.concierto.controller;
    opens co.edu.uniquindio.concierto.controller to javafx.fxml;
}