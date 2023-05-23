module com.example.aps5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.aps5 to javafx.fxml;
    exports com.example.aps5;
}