package fr.afpa;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ContactJsonSerializer implements Serializer<Contact> {
    @Override
    public void saveList(String filePath, List<Contact> objectsToSave) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(filePath);
            mapper.writeValue(file, objectsToSave);
        } catch (Exception e) {
            System.err.println("An error occurred while saving the file.");
        }
    }

    public void exportJsonToFile(Stage stage, List<Contact> contacts) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save JSON File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));

        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile != null) {
            saveList(selectedFile.getAbsolutePath(), contacts);
        }
    }

    @Override
    public void save(String filePath, Contact Object) {
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
}
