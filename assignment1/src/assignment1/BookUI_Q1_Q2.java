package assignment1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookUI_Q1_Q2 extends JFrame {

    private JTextField txtId, txtName, txtAuthor, txtPublication, txtPrice, txtQty, txtSearch;
    private JTable table;
    private DefaultTableModel model;

    private List<Book> bookList = new ArrayList<>();

    public BookUI_Q1_Q2() {

        setTitle("Book Management System (Q1 + Q2)");
        setSize(1000, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ================= FORM PANEL =================
        JPanel form = new JPanel(new GridLayout(7, 2, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Book Details"));

        txtId = new JTextField();
        txtName = new JTextField();
        txtAuthor = new JTextField();
        txtPublication = new JTextField();
        txtPrice = new JTextField();
        txtQty = new JTextField();

        form.add(new JLabel("Book ID")); form.add(txtId);
        form.add(new JLabel("Book Name")); form.add(txtName);
        form.add(new JLabel("Author Names")); form.add(txtAuthor);
        form.add(new JLabel("Publication")); form.add(txtPublication);
        form.add(new JLabel("Price")); form.add(txtPrice);
        form.add(new JLabel("Quantity")); form.add(txtQty);

        JButton btnAdd = new JButton("Add");
        JButton btnUpdate = new JButton("Update");

        form.add(btnAdd);
        form.add(btnUpdate);

        // ================= TABLE =================
        String[] cols = {"ID","Name","Author","Publication","Price","Qty","Total"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // ================= SEARCH PANEL =================
        JPanel searchPanel = new JPanel();
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search / Delete"));

        txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Search");
        JButton btnDelete = new JButton("Delete");
        JButton btnLoadAll = new JButton("Load All");

        searchPanel.add(new JLabel("Keyword:"));
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        searchPanel.add(btnDelete);
        searchPanel.add(btnLoadAll);

        // ================= EVENTS =================
        btnAdd.addActionListener(e -> addBook());
        btnUpdate.addActionListener(e -> updateBook());
        btnSearch.addActionListener(e -> searchBooks());
        btnDelete.addActionListener(e -> deleteBook());
        btnLoadAll.addActionListener(e -> loadAllBooks());

        table.getSelectionModel().addListSelectionListener(e -> fillFormFromTable());

        // ================= LAYOUT =================
        add(form, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.SOUTH);

        loadAllBooks(); // Load from file on startup
    }

    // ================= METHODS =================

    private void addBook() {
        try {
            Book book = new Book(
                    Integer.parseInt(txtId.getText()),
                    txtName.getText(),
                    txtAuthor.getText(),
                    txtPublication.getText(),
                    LocalDate.now(),
                    Double.parseDouble(txtPrice.getText()),
                    Integer.parseInt(txtQty.getText())
            );

            bookList.add(book);
            BookFileHandler.saveBooks(bookList);
            loadTable(bookList);
            clearFields();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Input");
        }
    }

    private void updateBook() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a record to update");
            return;
        }

        try {
            Book book = bookList.get(row);
            book.setBookName(txtName.getText());
            book.setAuthorNames(txtAuthor.getText());
            book.setPublication(txtPublication.getText());

            BookFileHandler.saveBooks(bookList);
            loadTable(bookList);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Update failed");
        }
    }

    private void deleteBook() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a record to delete");
            return;
        }

        try {
            bookList.remove(row);
            BookFileHandler.saveBooks(bookList);
            loadTable(bookList);
            clearFields();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Delete failed");
        }
    }

    private void searchBooks() {
        String key = txtSearch.getText().toLowerCase();
        List<Book> result = new ArrayList<>();

        for (Book b : bookList) {
            if (String.valueOf(b.getBookId()).equals(key)
                    || b.getBookName().toLowerCase().contains(key)
                    || b.getAuthorNames().toLowerCase().contains(key)
                    || b.getPublication().toLowerCase().contains(key)) {
                result.add(b);
            }
        }
        loadTable(result);
    }

    private void loadAllBooks() {
        try {
            bookList = BookFileHandler.loadBooks();
            loadTable(bookList);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "File load error");
        }
    }

    private void loadTable(List<Book> list) {
        model.setRowCount(0);
        for (Book b : list) {
            model.addRow(new Object[]{
                    b.getBookId(),
                    b.getBookName(),
                    b.getAuthorNames(),
                    b.getPublication(),
                    b.getPriceOfBook(),
                    b.getTotalQuantityToOrder(),
                    b.getTotalCost()
            });
        }
    }

    private void fillFormFromTable() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            txtId.setText(model.getValueAt(row, 0).toString());
            txtName.setText(model.getValueAt(row, 1).toString());
            txtAuthor.setText(model.getValueAt(row, 2).toString());
            txtPublication.setText(model.getValueAt(row, 3).toString());
            txtPrice.setText(model.getValueAt(row, 4).toString());
            txtQty.setText(model.getValueAt(row, 5).toString());
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAuthor.setText("");
        txtPublication.setText("");
        txtPrice.setText("");
        txtQty.setText("");
    }

    public static void main(String[] args) {
        new BookUI_Q1_Q2().setVisible(true);
    }
}
