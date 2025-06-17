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

//*Le contrôleur fait le lien entre la vue FXML qui décrit l'interface, avec les boutons, champs, tableaux, etc et
//la logique(le code Java qui dit ce qui se passe quand tu cliques sur un bouton, ou remplis un champ) */
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
     * Colonne affichant les attributs des contacts dans la TableView.
     */

    @FXML
    private TableColumn<Contact, String> nomCol;

    @FXML
    private TableColumn<Contact, String> prenomCol;

    @FXML
    private TableColumn<Contact, String> villeCol;

    @FXML
    private TableColumn<Contact, String> telPersoCol;
    @FXML
    private TableColumn<Contact, String> adresseCol;

    @FXML
    private TableColumn<Contact, String> emailCol;

    @FXML
    private TableColumn<Contact, String> genreCol;

    // Buttons pour ajouter, effacer, modifier, supprimer(CRUD)

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnEffacer;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnSupprimer;

    @FXML
    private MenuButton comboboxGenre;

    /**
     * Champ texte pour saisir le nom du contact.
     */

    @FXML
    private TextField nomChampField;
    @FXML
    private TextField prenomChampField;
    @FXML
    private TextField villeChampField;

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

    // pour gérer le genre dans les actions ensuite
    private Contact.Genre genre = null;

    @FXML
    private void handleGenreHomme(ActionEvent event) {
        genre = Contact.Genre.HOMME;
        System.out.println("Genre sélectionné : Homme");
    }

    @FXML
    private void handleGenreFemme(ActionEvent event) {
        genre = Contact.Genre.FEMME;
        System.out.println("Genre sélectionné : Femme");
    }

    @FXML
    private void handleGenreAutre(ActionEvent event) {
        genre = Contact.Genre.AUTRE;
        System.out.println("Genre sélectionné : Autre");
    }

    // Voici la méthode clearFieldStyle à ajouter dans la classe
    private void clearFieldStyle(TextField field) {
        field.getStyleClass().remove("error");
    }

    private void highlightField(TextField field) {
        if (!field.getStyleClass().contains("error")) {
            field.getStyleClass().add("error");
        } // Rouge clair pour signaler une erreur (dans le css avec le cas error)
    }

    @FXML
    private MenuButton comboExporter;

    // liste qui contient les contacts. Quand on ajoute ou modifie la TableView qui
    // affiche la liste
    // se met à jour automatiquement : pas besoin de rafraichir la vue manuellement
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
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));

        // on peut maintenant ajouter les contacts :
        contacts.add(new Contact(
                "Henri",
                "Feru",
                Contact.Genre.HOMME,
                "12 rue de la République",
                "0600000000",
                "henri.feru@email.com",
                "Nice",
                "Nice"));

        contacts.add(new Contact(
                "Geos",
                "Pierre",
                Contact.Genre.HOMME,
                "5 avenue des Champs",
                "0611111111",
                "geos.pierre@email.com",
                "Brest",
                "Brest"));

        contacts.add(new Contact(
                "Armelle",
                "Baba",
                Contact.Genre.FEMME,
                "45 boulevard de la Liberté",
                "0622222222",
                "armelle.baba@email.com",
                "Rosti",
                "Rosti"));

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
        boolean isValid = true;

        // Récupération des champs
        String nom = nomChampField.getText().trim();
        String prenom = prenomChampField.getText().trim();
        String ville = villeChampField.getText().trim();
        String telPerso = telPersoChampField.getText().trim();
        String adresse = adresseChampField.getText().trim();
        String email = emailChampField.getText().trim();
        String adressePostale = adresse; // même champ ici, si pas séparé

        clearFieldStyle(emailChampField);

        // Vérification des champs obligatoires
        if (nom.isEmpty()) {
            highlightField(nomChampField);
            isValid = false;
        }

        if (prenom.isEmpty()) {
            highlightField(prenomChampField);
            isValid = false;
        }

        if (ville.isEmpty()) {
            highlightField(villeChampField);
            isValid = false;
        }

        if (adresse.isEmpty()) {
            highlightField(adresseChampField);
            isValid = false;
        }

        if (telPerso.isEmpty()) {
            highlightField(telPersoChampField);
            isValid = false;
        }

        if (genre == null) {// si l'utilisateur a sélectionné un genre ou non car définit dans la variable
            // ici :@FXML
            // //private void handleGenreHomme(ActionEvent event) {
            // genre = Contact.Genre.Homme;
            // }

            isValid = false;
            if (!comboboxGenre.getStyleClass().contains("error")) {// si error n'est pas déjà dans la liste
                comboboxGenre.getStyleClass().add("error");// on ajoute
            }

        } else {
            // Si un genre est sélectionné, on remet le style à vide (ou neutre)
            comboboxGenre.getStyleClass().remove("error");
        }

        // Vérification email avec message d’erreur spécifique

        // Validation email avec message d'erreur
        if (!checkEmail()) {
            isValid = false;

            // Affichage alerte d’erreur spécifique email
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                    javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle("Email invalide");
            alert.setHeaderText(null);
            alert.setContentText("Le format de l'adresse email n'est pas valide. Merci de corriger.");
            alert.showAndWait();
        }

        if (genre == null) {
            isValid = false;

        }

        if (!isValid) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                    javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle("Champs invalides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs obligatoires correctement, \n"
                    + "s'il vous plaît\n"
                    + "please\n"
                    + "bitte\n"
                    + "por favor\n"
                    + "per favore"
                    + "per favore\n" // italien
                    + "xin vui lòng\n" // vietnamien
                    + "தயவுசெய்து" // tamoul

            );

            alert.showAndWait();
            return;
        }

        // Création du contact
        Contact nouveauContact = new Contact(nom, prenom, genre, adresse, telPerso, email, ville, adressePostale);

        // ajouter à la liste observable
        // Action dans le code Effet dans la TableView
        // contacts.add(...) ✅ Ajoute une ligne
        // contacts.remove(...) ✅ Supprime une ligne
        // contacts.clear() ✅ Vide la table
        // contacts.set(0, unContactModifé) ✅ Met à jour la ligne 0
        contacts.add(nouveauContact);

        System.out.println("Contact ajouté : " + nom + " " + prenom);
        effacer(null); // Efface le formulaire
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

        // Remise à zéro du genre
        genre = null;

        comboboxGenre.setText("Sélectionner un genre");// texte par défaut

        // Réinitialisation du style du bouton MenuButton (supprimer surbrillance)
        nomChampField.setStyle(""); // ça "clear" le style CSS appliqué

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

    // email est une chaîne de caractères (par exemple "mon.email@example.com").

    // matches(regex) est une méthode intégrée de Java qui teste si la chaîne
    // entière correspond exactement au modèle défini par la regex (expression
    // régulière) regex.

    // Si tout le contenu de email correspond à ce que la regex décrit, la méthode
    // retourne true.

    // Sinon, elle retourne false.

    public boolean checkEmail() {
        System.out.println("Email saisi : '" + emailCol + "'");

        String email = emailChampField.getText().trim();// enlève espace
        // ------------------------REGEX----------------------------------------------------------------
        // Regex simple pour vérifier la présence d'un '@', un point, et un nom
        // d'hébergeur
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

        boolean isValid = email.matches(regex);

        if (!isValid) {
            if (!emailChampField.getStyleClass().contains("error")) {
                emailChampField.getStyleClass().add("error");
            }
            System.out.println("Format email invalide: '" + email + "'");
        } else {
            emailChampField.getStyleClass().remove("error");
        }

        return isValid;
    }

    @FXML
    private void supprimer(ActionEvent event) {

        // Récupérer le contact sélectionné dans la TableView
        Contact contactSelectionne = contactTableview.getSelectionModel().getSelectedItem();
        if (contactSelectionne != null) {
            // Supprimer de la liste observable (mise à jour auto de la TableView)
            contacts.remove(contactSelectionne);
            System.out.println("Contact supprimé : " + contactSelectionne);
        } else {
            // System.out.println("Aucun contact sélectionné !");
            // Boîte d'alerte qui s'affiche à l'utilisateur
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                    javafx.scene.control.Alert.AlertType.WARNING);// Avertissement (ex: attention avant action)
            alert.setTitle("Suppression impossible");
            alert.setHeaderText(null);
            alert.setContentText("Aucun contact sélectionné ! Veuillez sélectionner un contact à supprimer.");
            alert.showAndWait();
        }

        System.out.println("Fonction supprimer");
    }

}
