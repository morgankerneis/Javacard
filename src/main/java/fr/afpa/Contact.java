package fr.afpa;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@JsonPropertyOrder({
        "nom", "prenom", "ville", "dateNaissance", "genre", "pseudo", "adresse", "telPerso", "telPro", "email",
        "departement", "codePostal", "lienGithub"
})

public class Contact extends Object implements Serializable {

    public enum Genre {
        HOMME, FEMME, AUTRE
    }

    private transient StringProperty nom;// nom est un objet spécial avec StringProperty
    // qui gère une chaîne et notifie quand elle change.

    private transient StringProperty prenom;
    private transient StringProperty ville;
    private transient StringProperty dateNaissance;
    private transient ObjectProperty<Genre> genre;
    private transient StringProperty pseudo;
    private transient StringProperty adresse;
    private transient StringProperty telPerso;
    private transient StringProperty telPro;
    private transient StringProperty email;
    private transient StringProperty departement;
    private transient StringProperty codePostal;
    private transient StringProperty lienGithub;

    // Constructeur initialisant les propriétés
    public Contact(String nom, String prenom, String ville, String dateNaissance,
            Genre genre, String pseudo, String adresse, String telPerso,
            String telPro, String email, String departement, String codePostal,
            String lienGithub) {

        // On doit créer cette propriété comme on crée un objet : SimpleStringProperty
        // (implémentation concrète de StringProperty),
        // comme on créerais un objet. Par exemple :

        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.ville = new SimpleStringProperty(ville);
        this.dateNaissance = new SimpleStringProperty(dateNaissance);
        this.genre = new SimpleObjectProperty<>(genre);
        this.pseudo = new SimpleStringProperty(pseudo);
        this.adresse = new SimpleStringProperty(adresse);
        this.telPerso = new SimpleStringProperty(telPerso);
        this.telPro = new SimpleStringProperty(telPro);
        this.email = new SimpleStringProperty(email);
        this.departement = new SimpleStringProperty(departement);
        this.codePostal = new SimpleStringProperty(codePostal);
        this.lienGithub = new SimpleStringProperty(lienGithub);
    }

    // Getters and setters (pour toString et logique métier)

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getNom() {
        return nom.getValue();
    }

    public String getPrenom() {
        return prenom.get();
    }

    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public String getVille() {
        return ville.get();
    }

    public void setVille(String ville) {
        this.ville.set(ville);
    }

    public String getDateNaissance() {
        return dateNaissance.get();
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance.set(dateNaissance);
    }

    public Genre getGenre() {
        return genre.get();
    }

    public void setGenre(Genre genre) {
        this.genre.set(genre);
    }

    public String getPseudo() {
        return pseudo.get();
    }

    public void setPseudo(String pseudo) {
        this.pseudo.set(pseudo);
    }

    public String getAdresse() {
        return adresse.get();
    }

    public void setAdresse(String adresse) {
        this.adresse.set(adresse);
    }

    public String getTelPerso() {
        return telPerso.get();
    }

    public void setTelPerso(String telPerso) {
        this.telPerso.set(telPerso);
    }

    public String getTelPro() {
        return telPro.get();
    }

    public void setTelPro(String telPro) {
        this.telPro.set(telPro);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getDepartement() {
        return departement.get();
    }

    public void setDepartement(String departement) {
        this.departement.set(departement);
        ;
    }

    public String getCodePostal() {
        return codePostal.get();
    }

    public void setCodePostal(String codePostal) {
        this.codePostal.set(codePostal);
        ;
    }

    public String getLienGithub() {
        return lienGithub.get();
    }

    public void setLienGithub(String lienGithub) {
        this.lienGithub.set(lienGithub);
    }

    @Override
    public String toString() {
        return String.format(
                "Contact : %s %s | Genre : %s | Adresse : %s %s | Téléphone : %s | Email : %s",
                // chaque %s est une case à remplir réservée pour une valeur de type String qui
                // suivent
                // ici les champs obligatoire :

                getPrenom(),
                getNom(),
                getGenre(),
                getAdresse(),
                getCodePostal(),
                getTelPerso(),
                getEmail());

    }

    private void writeObject(ObjectOutputStream stream) throws IOException {

        stream.defaultWriteObject();

        stream.writeUTF(getNom());
        stream.writeUTF(getPrenom());
        stream.writeUTF(getVille());
        stream.writeUTF(getDateNaissance());
        stream.writeObject(getGenre());
        stream.writeUTF(getPseudo());
        stream.writeUTF(getAdresse());
        stream.writeUTF(getTelPerso());
        stream.writeUTF(getTelPro());
        stream.writeUTF(getEmail());
        stream.writeUTF(getDepartement());
        stream.writeUTF(getCodePostal());
        stream.writeUTF(getLienGithub());
    }

    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {

        stream.defaultReadObject();

        this.nom = new SimpleStringProperty(stream.readUTF());
        this.prenom = new SimpleStringProperty(stream.readUTF());
        this.ville = new SimpleStringProperty(stream.readUTF());
        this.dateNaissance = new SimpleStringProperty(stream.readUTF());
        this.genre = new SimpleObjectProperty<>((Genre) stream.readObject());
        this.pseudo = new SimpleStringProperty(stream.readUTF());
        this.adresse = new SimpleStringProperty(stream.readUTF());
        this.telPerso = new SimpleStringProperty(stream.readUTF());
        this.telPro = new SimpleStringProperty(stream.readUTF());
        this.email = new SimpleStringProperty(stream.readUTF());
        this.departement = new SimpleStringProperty(stream.readUTF());
        this.codePostal = new SimpleStringProperty(stream.readUTF());
        this.lienGithub = new SimpleStringProperty(stream.readUTF());
    }
}
