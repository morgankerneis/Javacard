module fr.afpa {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens fr.afpa to javafx.fxml;

    exports fr.afpa;
}
