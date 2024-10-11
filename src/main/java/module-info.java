module org.example.c {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.c to javafx.fxml;
    exports org.example.c;
}