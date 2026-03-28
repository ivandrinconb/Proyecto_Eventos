module co.edu.uniquindio.concierto.concierto {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.concierto to javafx.fxml;
    exports co.edu.uniquindio.concierto;
}