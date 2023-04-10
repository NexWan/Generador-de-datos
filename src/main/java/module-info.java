module com.nexwan.generadornombres {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.nexwan.generadornombres to javafx.fxml;
    exports com.nexwan.generadornombres;
}