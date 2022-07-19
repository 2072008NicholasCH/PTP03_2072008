module com.example.ptp03_2072008 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ptp03_2072008 to javafx.fxml;
    exports com.example.ptp03_2072008;
    exports com.example.ptp03_2072008.model;
    opens com.example.ptp03_2072008.model to javafx.fxml;
    exports com.example.ptp03_2072008.controller;
    opens com.example.ptp03_2072008.controller to javafx.fxml;
}