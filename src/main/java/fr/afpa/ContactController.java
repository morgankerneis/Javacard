package fr.afpa;

import java.io.IOException;

import javafx.scene.control.Button;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

// //**
//  * Contrôleur pour gérer l'interface de la liste de contacts.
//  * 
//  * Cette classe permet d'interagir avec la vue FXML associée
//  * et de manipuler les données des contacts affichés.
//  */

public class ContactController {

    /**
     * GridPane contenant le formulaire de saisie des contacts.
     * Permet d'ajouter dynamiquement des éléments si besoin.
     */

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private GridPane formGrid; // ajoute dynamiquement des éléments par exemple

    /**
     * Colonne affichant le nom des contacts dans la TableView.
     */

    @FXML
    private TableColumn<Contact, String> nomCol;

    /**
     * Colonne affichant le prénom des contacts dans la TableView.
     */

    @FXML
    private TableColumn<Contact, String> prenomCol;

    @FXML
    private TableColumn<Contact, String> villeCol;

    /**
     * Champ texte pour saisir le nom du contact.
     */

    @FXML
    private TextField nomChampField;

    // Buttons

    // permet d'ajouter
    @FXML
    private Button btnAjouter;

    // permet d'effacer
    @FXML
    private Button btnEffacer;

    // permet de modifier
    @FXML
    private Button btnModifier;

    // permet de supprimer
    @FXML
    private Button btnSupprimer;

    @FXML
    private MenuButton menuButtonGenre;

    @FXML
    private TextField prenomChampField;
    @FXML
    private TextField villeChampField;

    @FXML
    private TableColumn<Contact, String> telPersoCol;

    @FXML
    private TextField telProChampField;
    @FXML
    private TextField telPersoChampField;

    @FXML
    private TextField emailChampField;

    @FXML
    private TextField pseudoChampField;

    @FXML
    private TextField lienGitChampField;

    @FXML
    private TextField adresseChampField;

    @FXML
    private TableView<Contact> contactTableview;

    @FXML
    private TextField dateDeNaissanceChampField;


    @FXML
private void handleGenreHomme(ActionEvent event) {
    System.out.println("Genre sélectionné : Homme");
}

@FXML
private void handleGenreFemme(ActionEvent event) {
    System.out.println("Genre sélectionné : Femme");
}

@FXML
private void handleGenreAutre(ActionEvent event) {
    System.out.println("Genre sélectionné : Autre");
}


    @FXML
    private MenuButton comboExporter;

    private final ObservableList<Contact> contacts = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Lier les colonnes aux propriétés du modèle Contact
        // "Pour chaque ligne dans la table, récupère la valeur de la propriété `nom`
        // de l’objet `Contact` associé."
        // `new PropertyValueFactory<>("nom")` va chercher la méthode `getNom()` dans la
        // classe `Contact`.
        // - JavaFX utilise **la réflexion** pour appeler `getNom()` sur chaque objet de
        // la liste.
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        villeCol.setCellValueFactory(new PropertyValueFactory<>("ville"));
        telPersoCol.setCellValueFactory(new PropertyValueFactory<>("telPerso"));

        // Lier la combo genre à l'enum
     //   comboboxGenre.setItems(FXCollections.observableArrayList(Contact.Genre.values()));

        // Ajouter quelques contacts par défaut à la liste observable
        contacts.add(new Contact("Henri", "feru", "Nice"));
        contacts.add(new Contact("Geos", "Pierre", "Brest"));
        contacts.add(new Contact("Armelle", "Baba", "Rosti"));
        contacts.add(new Contact("Armelle", "Baba", "Rosti"));

        // Afficher la liste dans la TableView
        contactTableview.setItems(contacts);

        comboExporter.getItems().clear();

        MenuItem csvItem = new MenuItem(".csv");
        MenuItem jsonItem = new MenuItem(".json");
        MenuItem vcfItem = new MenuItem(".vcf");

        comboExporter.getItems().addAll(csvItem, jsonItem, vcfItem);

    }

    /**
     * Méthode appelée lors du clic sur le bouton Ajouter.
     * Crée un nouveau contact à partir des champs saisis et l'ajoute à la liste.
     * 
     * @param event événement de clic sur le bouton Ajouter
     */

    @FXML
    private void ajouter(ActionEvent event) {
        String nom = nomChampField.getText();
        String prenom = prenomChampField.getText();
        String ville = villeChampField.getText();
        String telPerso = telPersoChampField.getText();

        Contact nouveauContact = new Contact(nom, prenom, ville);
        nouveauContact.setTelPerso(telPerso);

        // ajouter à la liste observable
        // Action dans le code Effet dans la TableView
        // contacts.add(...) ✅ Ajoute une ligne
        // contacts.remove(...) ✅ Supprime une ligne
        // contacts.clear() ✅ Vide la table
        // contacts.set(0, unContactModifé) ✅ Met à jour la ligne 0
        contacts.add(nouveauContact);

        // Afficher les données saisies dans la console
        System.out.println("Nom : " + nom);
        System.out.println("Prénom : " + prenom);

    }

    /**
     * Méthode appelée lors du clic sur le bouton Effacer.
     * Vide tous les champs du formulaire pour une nouvelle saisie.
     * 
     * @param event événement de clic sur le bouton Effacer
     */
    @FXML
    private void effacer(ActionEvent event) {
        nomChampField.clear();
        prenomChampField.clear();
        villeChampField.clear();
        telPersoChampField.clear();
        telProChampField.clear();
        adresseChampField.clear();
        emailChampField.clear();
        pseudoChampField.clear();
        dateDeNaissanceChampField.clear();
        lienGitChampField.clear();
        adresseChampField.clear();
        // comboboxGenre.getSelectionModel().clearSelection();// ici méthode différente
        // on efface juste la sélection.

    }

    /**
     * Méthode appelée lors du clic sur le bouton Modifier.
     * À implémenter : modifier les données du contact sélectionné.
     * 
     * @param event événement de clic sur le bouton Modifier
     */

    @FXML
    private void modifier(ActionEvent event) {
        System.out.println("Fonction modifier");
    }

    /**
     * Méthode appelée lors du clic sur le bouton Supprimer.
     * Supprime le contact sélectionné dans la TableView.
     * 
     * @param event événement de clic sur le bouton Supprimer
     */
    @FXML
    private void supprimer(ActionEvent event) {

        // Récupérer le contact sélectionné dans la TableView
        Contact contactSelectionne = contactTableview.getSelectionModel().getSelectedItem();
        if (contactSelectionne != null) {
            // Supprimer de la liste observable (mise à jour auto de la TableView)
            contacts.remove(contactSelectionne);
            System.out.println("Contact supprimé : " + contactSelectionne);
        } else {
            System.out.println("Aucun contact sélectionné !");
        }

        System.out.println("Fonction supprimer");
    }

}
