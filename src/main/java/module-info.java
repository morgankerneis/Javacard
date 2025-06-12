module fr.afpa {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.desktop;

    opens fr.afpa to javafx.fxml;

    exports fr.afpa;
}
