package fr.afpa;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class ContactController {

    @FXML
    
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private TableView<Contact> tableView;

}
