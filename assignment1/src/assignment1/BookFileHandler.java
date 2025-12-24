package assignment1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookFileHandler {

    private static final String FILE_NAME = "Books.dat";

    public static void saveBooks(List<Book> books) throws IOException {
        ObjectOutputStream oos =
                new ObjectOutputStream(new FileOutputStream(FILE_NAME));
        oos.writeObject(books);
        oos.close();
    }

    @SuppressWarnings("unchecked")
    public static List<Book> loadBooks()
            throws IOException, ClassNotFoundException {

        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();

        ObjectInputStream ois =
                new ObjectInputStream(new FileInputStream(FILE_NAME));
        List<Book> books = (List<Book>) ois.readObject();
        ois.close();
        return books;
    }
}
