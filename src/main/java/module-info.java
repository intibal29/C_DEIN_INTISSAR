module org.example.c {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.C to javafx.fxml;
    exports org.example.C;
}