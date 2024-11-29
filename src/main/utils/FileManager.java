package main.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static <T> void saveToFile(String filename, List<T> list) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(list);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> loadFromFile(String filename) throws IOException, ClassNotFoundException {
        File file = new File(filename);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<T>) in.readObject();
        }
    }
}
