package assignment1;

import java.io.Serializable;
import java.time.LocalDate;

public class Book implements Serializable {

    private int bookId;
    private String bookName;
    private String authorNames;
    private String publication;
    private LocalDate dateOfPublication;
    private double priceOfBook;
    private int totalQuantityToOrder;
    private double totalCost;

    public Book(int bookId, String bookName, String authorNames,
                String publication, LocalDate dateOfPublication,
                double priceOfBook, int totalQuantityToOrder) {

        this.bookId = bookId;
        this.bookName = bookName;
        this.authorNames = authorNames;
        this.publication = publication;
        this.dateOfPublication = dateOfPublication;
        this.priceOfBook = priceOfBook;
        this.totalQuantityToOrder = totalQuantityToOrder;
        this.totalCost = priceOfBook * totalQuantityToOrder;
    }

    // ===== GETTERS =====
    public int getBookId() { return bookId; }
    public String getBookName() { return bookName; }
    public String getAuthorNames() { return authorNames; }
    public String getPublication() { return publication; }
    public LocalDate getDateOfPublication() { return dateOfPublication; }
    public double getPriceOfBook() { return priceOfBook; }
    public int getTotalQuantityToOrder() { return totalQuantityToOrder; }
    public double getTotalCost() { return totalCost; }

    // ===== SETTERS (REQUIRED FOR UPDATE) =====
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthorNames(String authorNames) {
        this.authorNames = authorNames;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }
}
