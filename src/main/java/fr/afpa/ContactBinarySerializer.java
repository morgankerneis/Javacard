package fr.afpa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ContactBinarySerializer implements Serializer<Contact>, Deserializer<Contact> {
    @Override
    public void saveList(String filePath, List<Contact> objectsToSave) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(objectsToSave);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            System.err.println("Binary save file not found or can't be created.");
        } catch (Exception e) {
            System.err.println("Something went wrong... also your computer got virus.");
        }
    }

    @Override
    public void save(String filePath, Contact object) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            System.err.println("Binary save file not found or can't be created.");
        } catch (Exception e) {
            System.err.println("Something went wrong... also your computer got virus.");
        }
    }

    @Override
    public ArrayList<Contact> loadList(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            @SuppressWarnings("unchecked")
            ArrayList<Contact> contacts = (ArrayList<Contact>) objectInputStream.readObject();
            objectInputStream.close();
            return contacts;

        } catch (Exception e) {
            System.err.println(
                    "This is the microsoft technical support service, we'll need full remote access to your computer for further operations. Also, please accept this virus-free arraylist object.");
        }

        return new ArrayList<Contact>();
    }
}
