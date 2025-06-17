package fr.afpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.afpa.Contact.Genre;

public class AppTest {
    @Test
    void firstTest() {
        System.out.println("it just works");
        assertTrue(true);
    }

    @Test
    public void whenSerializingAndDeserializing_ThenObjectIsTheSame()
            throws IOException, ClassNotFoundException {
        Contact contact = new Contact("Dampierre", "Eric", "Saint-Sernin-sur-rance", "155bc", Genre.HOMME, "", "",
                "", "", "", "", "", "");

        FileOutputStream fileOutputStream = new FileOutputStream("src/test/java/fr/afpa/test.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(contact);
        objectOutputStream.flush();
        objectOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream("src/test/java/fr/afpa/test.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Contact c2 = (Contact) objectInputStream.readObject();
        objectInputStream.close();

        System.out.println(c2);

        assertTrue(c2.getNom().equals(contact.getNom()));
        assertTrue(c2.getPrenom().equals(contact.getPrenom()));
    }

    @Test
    public void shouldWriteInBinSaveFile() {
        Contact contact = new Contact("Dampierre", "Eric", "Saint-Sernin-sur-rance", "155bc", Genre.HOMME, "", "",
                "", "", "", "", "", "");
        ContactBinarySerializer contactBinarySerializer = new ContactBinarySerializer();
        contactBinarySerializer.save("data/binary.txt", contact);

        assertTrue(true);

    }

    @Test
    public void shouldSerializeAndDesirializeContactList()
            throws IOException, ClassNotFoundException {
        Contact contact = new Contact("Dampierre", "Eric", "Saint-Sernin-sur-rance", "155bc", Genre.HOMME, "", "",
                "", "", "", "", "", "");
        Contact contact2 = new Contact("Dampierre", "Isabella de rossigni", "Saint-Sernin-sur-rance", "155bc",
                Genre.HOMME, "", "",
                "", "", "", "", "", "");
        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact);
        contacts.add(contact2);

        ContactBinarySerializer serializer = new ContactBinarySerializer();
        serializer.saveList("src/test/java/fr/afpa/test.txt", contacts);
        List<Contact> contacts2 = serializer.loadList("src/test/java/fr/afpa/test.txt");

        System.out.println(contacts2);
        assertEquals(contacts.size(), contacts2.size());
    }

    @Test
    public void shouldExportFileInJsonFormat() throws IOException, ClassNotFoundException {
        Contact contact = new Contact("Dampierre", "Eric", "Saint-Sernin-sur-rance", "155bc", Genre.HOMME, "", "",
                "", "", "", "", "", "");
        Contact contact2 = new Contact("Dampierre", "Isabella de rossigni", "Saint-Sernin-sur-rance", "155bc",
                Genre.HOMME, "", "",
                "", "", "", "", "", "");
        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact);
        contacts.add(contact2);

        ContactJsonSerializer serializer = new ContactJsonSerializer();
        serializer.saveList("src/test/java/fr/afpa/test.json", contacts);
        assertTrue(true);
    }
}