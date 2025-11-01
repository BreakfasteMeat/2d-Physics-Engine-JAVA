module com.example.appdevf1.physicsengine2d {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.incubator.vector;


    opens com.example.appdevf1.physicsengine2d to javafx.fxml;
    exports com.example.appdevf1.physicsengine2d;
}