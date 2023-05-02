module com.example.studerlucasevaljavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.json;


    opens com.example.studerlucasevaljavafx to javafx.fxml;
    exports com.example.studerlucasevaljavafx;
}