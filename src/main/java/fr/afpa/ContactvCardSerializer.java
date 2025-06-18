package fr.afpa;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ContactvCardSerializer implements Serializer<Contact> {

    @Override
    public void saveList(String filePath, List<Contact> objectsToSave) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveList'");
    }

    @Override
    public void save(String filePath, Contact object) {
        StringBuilder vCard = new StringBuilder();

        vCard.append("BEGIN:VCARD\n");
        vCard.append("VERSION:4.0\n");

        if (object.getNom() != null && !object.getNom().isEmpty()) {
            vCard.append("N:").append(object.getNom()).append(";");

            if (object.getPrenom() != null && !object.getPrenom().isEmpty()) {
                vCard.append(object.getPrenom()).append(";;");
            } else {
                vCard.append(";;");
            }

            if (object.getGenre() != null) {
                switch (object.getGenre()) {
                    case Contact.Genre.HOMME:
                        vCard.append("mr.");
                        break;

                    case Contact.Genre.FEMME:
                        vCard.append("mme.");
                        break;

                    default:
                        vCard.append("SCP-");
                        break;
                }
                vCard.append(";");

            } else {
                vCard.append(";");
            }

            vCard.append("\n");
        }

        if (object.getNom() != null && !object.getNom().isEmpty() && object.getPrenom() != null
                && !object.getPrenom().isEmpty()) {
            vCard.append("FN:").append(object.getNom()).append(" ").append(object.getPrenom()).append("\n");
        }

        if ((object.getAdresse() != null && !object.getAdresse().isEmpty())
                || (object.getVille() != null && !object.getVille().isEmpty())
                || (object.getCodePostal() != null && !object.getCodePostal().isEmpty())
                || (object.getDepartement() != null && !object.getDepartement().isEmpty())) {
            vCard.append("ADR;;;");

            if (object.getAdresse() != null && !object.getAdresse().isEmpty()) {
                vCard.append(object.getAdresse()).append(";");
            } else {
                vCard.append(";");
            }

            if (object.getVille() != null && !object.getVille().isEmpty()) {
                vCard.append(object.getVille()).append(";");
            } else {
                vCard.append(";");
            }

            if (object.getDepartement() != null && !object.getDepartement().isEmpty()) {
                vCard.append(object.getDepartement()).append(";");
            } else {
                vCard.append(";");
            }

            if (object.getCodePostal() != null && !object.getCodePostal().isEmpty()) {
                vCard.append(object.getCodePostal()).append(";");
            } else {
                vCard.append(";");
            }

            vCard.append("\n");
        }

        if (object.getTelPerso() != null && !object.getTelPerso().isEmpty()) {
            vCard.append("TEL;TYPE=perso:").append(object.getTelPerso()).append("\n");
        }

        if (object.getTelPro() != null && !object.getTelPro().isEmpty()) {
            vCard.append("TEL;TYPE=pro:").append(object.getTelPro()).append("\n");
        }

        if (object.getEmail() != null && !object.getEmail().isEmpty()) {
            vCard.append("EMAIL:").append(object.getEmail()).append("\n");
        }

        if (object.getLienGithub() != null && !object.getLienGithub().isEmpty()) {
            vCard.append("URL;TYPE=github:").append(object.getLienGithub()).append("\n");
        }

        if (object.getPseudo() != null && !object.getPseudo().isEmpty()) {
            vCard.append("NICKNAME:").append(object.getPseudo()).append("\n");
        }

        vCard.append("END:VCARD\n");

        File file = new File(filePath);

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(vCard.toString());
        } catch (Exception e) {
            System.err.println("Something went wrong with creating the file.");
        }

    }

    public void exportVCard(Stage stage, Contact contact) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save vCard File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("vCard Files", "*.vcf"));

        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile != null) {
            save(selectedFile.getAbsolutePath(), contact);
        }
    }

}
