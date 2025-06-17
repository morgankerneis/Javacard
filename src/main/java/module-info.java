module fr.afpa {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires java.desktop;

    opens fr.afpa to javafx.fxml;

    exports fr.afpa;
}
