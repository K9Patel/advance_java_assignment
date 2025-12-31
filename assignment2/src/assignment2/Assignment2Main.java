package assignment2;

public class Assignment2Main {

    public static void main(String[] args) {

        try {
            BookDAO.insertBook();
            BookDAO.updateBook();
            BookDAO.displayBooks();
            BookDAO.deleteBook();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
