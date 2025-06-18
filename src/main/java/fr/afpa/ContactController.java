package fr.afpa;

import java.io.IOException;

import javafx.scene.control.Alert;
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

    // Lien entre un élément de la vue (GridPane) et le contrôleur pour permettre
    // l'accès en Java
    @FXML
    private GridPane formGrid; // ajoute dynamiquement des éléments par exemple

    /**
     * Colonne affichant les attributs des contacts dans la TableView.
     * Colonnes d'une TableView : ici, nomCol affichera le nom d'un contact.
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
    // Boutons liés au FXML. Ils appellent des méthodes via les @FXML.

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnEffacer;

    // @FXML
    // private Button btnModifier;

    @FXML
    private Button btnSupprimer;

    // Menu déroulant pour choisir un genre (Homme, Femme, Autre).
    @FXML
    private MenuButton comboboxGenre;

    /**
     * Champ texte pour saisir le nom du contact.
     * Champs de saisie pour les différents attributs d’un contact.
     * 
     * java
     * Copier
     * Modifier
     * 
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

    // pour gérer le genre dans les actions ensuite; Stocke temporairement le genre
    // sélectionné.
    private Contact.Genre genre = null;

    // Définissent la valeur du genre lorsque l’utilisateur clique sur un des choix
    // du MenuButton.

    @FXML
    private void handleGenreHomme(ActionEvent event) {
        genre = Contact.Genre.HOMME;
        genre = Contact.Genre.HOMME;
        System.out.println("Genre sélectionné : Homme");
    }

    @FXML
    private void handleGenreFemme(ActionEvent event) {
        genre = Contact.Genre.FEMME;
        genre = Contact.Genre.FEMME;
        System.out.println("Genre sélectionné : Femme");
    }

    @FXML
    private void handleGenreAutre(ActionEvent event) {
        genre = Contact.Genre.AUTRE;
        genre = Contact.Genre.AUTRE;
        System.out.println("Genre sélectionné : Autre");
    }

    // Voici la méthode clearFieldStyle à ajouter dans la classe
    // Enlève la classe CSS "error" si elle est présente.
    private void clearFieldStyle(TextField field) {
        field.getStyleClass().remove("error");
    }

    // Ajoute une surbrillance (rouge ou autre) en cas d'erreur de saisie.
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

        // Associe la colonne nomCol à l'attribut nom de l'objet Contact.

        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        villeCol.setCellValueFactory(new PropertyValueFactory<>("ville"));
        telPersoCol.setCellValueFactory(new PropertyValueFactory<>("telPerso"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));

        // on peut maintenant ajouter les contacts :
        // String nom, String prenom, Genre genre, String adresse, String telPerso,
        // String email,
        // String adressePostale, String ville
        contacts.add(new Contact(
                "Henri",
                "Feru",
                Contact.Genre.HOMME,
                "12 rue de la République",
                "0600000000",
                "henri.feru@email.com",
                "06100",
                "Nice"));

        contacts.add(new Contact(
                "Henri",
                "Feru",
                Contact.Genre.HOMME,
                "12 rue de la République",
                "0600000000",
                "henri.feru@email.com",
                "06100",
                "Nice"));

        contacts.add(new Contact(
                "Henri",
                "Feru",
                Contact.Genre.HOMME,
                "12 rue de la République",
                "0600000000",
                "henri.feru@email.com",
                "06100",
                "Nice"));

        // Afficher la liste dans la TableView
        contactTableview.setItems(contacts);
        // Attache un gestionnaire de clics à la tableview

        contactTableview.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // simple clic (ou 2 pour double-clic)
                Contact contactSelectionne = contactTableview.getSelectionModel().getSelectedItem();
                if (contactSelectionne != null) {
                    contactEnCoursEdition = contactSelectionne;

                    // Remplir les champs du formulaire
                    nomChampField.setText(contactEnCoursEdition.getNom());
                    prenomChampField.setText(contactEnCoursEdition.getPrenom());
                    villeChampField.setText(contactEnCoursEdition.getVille());
                    telPersoChampField.setText(contactEnCoursEdition.getTelPerso());
                    adresseChampField.setText(contactEnCoursEdition.getAdresse());
                    emailChampField.setText(contactEnCoursEdition.getEmail());

                    genre = contactEnCoursEdition.getGenre();
                    comboboxGenre.setText(genre.name()); // ⚠️ si c’est un MenuButton

                    System.out.println("Contact chargé pour modification : " + contactEnCoursEdition.getNom());
                }
            }
        });

        // Vide les anciens choix du menu "Exporter".
        comboExporter.getItems().clear();

        MenuItem csvItem = new MenuItem(".csv");// Crée un nouvel élément de menu (ligne dans le menu déroulant) avec le
                                                // texte .csv.
        MenuItem jsonItem = new MenuItem(".json");
        MenuItem vcfItem = new MenuItem(".vcf");

        comboExporter.getItems().addAll(csvItem, jsonItem, vcfItem);// Ajoute les trois éléments .csv, .json et .vcf
                                                                    // dans le menu déroulant comboExporter.

    }

    /**
     * Méthode appelée lors du clic sur le bouton Ajouter.
     * Crée un nouveau contact à partir des champs saisis et l'ajoute à la liste.
     * 
     * @param event événement de clic sur le bouton Ajouter
     *              Récupère le contenu des champs
     *              Valide les champs (vérifie qu’ils ne sont pas vides)
     *              Si tout est correct, crée un nouvel objet Contact et l’ajoute à
     *              la liste observable contacts.
     * 
     *              java
     *              Copier
     *              Modifier
     * 
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

        // Elle supprime simplement la classe "error" (souvent définie dans le fichier
        // CSS pour changer la couleur du champ).

        // Enlever le style d'erreur sur tous les champs au début (reset)
        clearFieldStyle(nomChampField);
        clearFieldStyle(prenomChampField);
        clearFieldStyle(villeChampField);
        clearFieldStyle(telPersoChampField);
        clearFieldStyle(adresseChampField);
        clearFieldStyle(emailChampField);

        // Aussi enlever le style d'erreur sur le MenuButton genre si nécessaire
        comboboxGenre.getStyleClass().remove("error");

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
                comboboxGenre.getStyleClass().add("error");// on ajoute l'error définit dans le ficheir css
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
        // ======= MODIFICATION ou AJOUT ==========
        // if (contactEnCoursEdition != null) {
        // // MODIFIER un contact existant
        // contactEnCoursEdition.setNom(nom);
        // contactEnCoursEdition.setPrenom(prenom);
        // contactEnCoursEdition.setVille(ville);
        // contactEnCoursEdition.setTelPerso(telPerso);
        // contactEnCoursEdition.setAdresse(adresse);
        // contactEnCoursEdition.setEmail(email);
        // contactEnCoursEdition.setGenre(genre);

        // contactTableview.refresh(); // 🔄 Forcer mise à jour
        // System.out.println("Contact modifié : " + nom + " " + prenom);
        // contactEnCoursEdition = null;
        // } else {

        // Bloquer ajout si on est en mode modification
        if (contactEnCoursEdition != null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText(
                    "Vous modifiez un contact. Cliquez sur Modifier pour enregistrer, ou Effacer pour réinitialiser.");
            alert.showAndWait();
            return; // SORTIE TOTALE DE LA MÉTHODE, pas d'ajout possible
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

        effacer(null);
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

        // Réinitialisation du style du bouton MenuButton (supprimer contour champ de
        // couleur)
        nomChampField.setStyle(""); // ça "clear" le style CSS appliqué
        nomChampField.setStyle("");
        prenomChampField.setStyle("");
        villeChampField.setStyle("");
        telPersoChampField.setStyle("");
        adresseChampField.setStyle("");
        emailChampField.setStyle("");

        contactEnCoursEdition = null;
    }

    /**
     * Méthode appelée lors du clic sur le bouton Modifier.
     * À implémenter : modifier les données du contact sélectionné.
     * 
     * @param event événement de clic sur le bouton Modifier
     */

    private Contact contactEnCoursEdition = null;

    @FXML
    private void modifier(ActionEvent event) {
        if (contactEnCoursEdition == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Modification impossible");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un contact à modifier.");
            alert.showAndWait();
            return;
        }

        // Récupérer les nouvelles valeurs des champs
        String nom = nomChampField.getText().trim();
        String prenom = prenomChampField.getText().trim();
        String ville = villeChampField.getText().trim();
        String telPerso = telPersoChampField.getText().trim();
        String adresse = adresseChampField.getText().trim();
        String email = emailChampField.getText().trim();

        // Met à jour l'objet contact
        contactEnCoursEdition.setNom(nom);
        contactEnCoursEdition.setPrenom(prenom);
        contactEnCoursEdition.setVille(ville);
        contactEnCoursEdition.setTelPerso(telPerso);
        contactEnCoursEdition.setAdresse(adresse);
        contactEnCoursEdition.setEmail(email);
        contactEnCoursEdition.setGenre(genre);

        // Rafraîchit la TableView pour voir les changements
        // met à jour l’affichage.

        contactTableview.refresh();

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
        /*
         * Début de la chaîne (obligatoire, rien avant)
         * [a-zA-Z0-9._%+-]+ Nom d'utilisateur (avant le @)
         * ➤ Lettres majuscules/minuscules, chiffres, points, tirets, underscores, etc.
         * 
         * @ Le caractère arobase (obligatoire, séparateur entre utilisateur et domaine)
         * [a-zA-Z0-9.-]+ Nom de domaine (ex: gmail, yahoo, etc.)
         * ➤ Lettres, chiffres, tirets, points autorisés
         * \\. Le point . (échappé car c’est un caractère spécial en regex)
         * [a-zA-Z]{2,6} Extension du domaine (ex: com, fr, org...)
         * 
         */
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
