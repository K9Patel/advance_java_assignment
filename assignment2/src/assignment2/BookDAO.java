package assignment2;

import java.sql.*;

public class BookDAO {

    // INSERT
    public static void insertBook() throws Exception {
        Connection con = DBConnection.getConnection();

        String sql =
          "INSERT INTO book VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, 1);
        ps.setString(2, "Core Java");
        ps.setString(3, "Herbert Schildt");
        ps.setString(4, "McGraw Hill");
        ps.setDouble(5, 650);

        ps.executeUpdate();
        con.close();

        System.out.println("Book Inserted");
    }

    // UPDATE
    public static void updateBook() throws Exception {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps =
          con.prepareStatement(
            "UPDATE book SET price=? WHERE book_id=?");

        ps.setDouble(1, 700);
        ps.setInt(2, 1);

        ps.executeUpdate();
        con.close();

        System.out.println("Book Updated");
    }

    // DELETE
    public static void deleteBook() throws Exception {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps =
          con.prepareStatement(
            "DELETE FROM book WHERE book_id=?");

        ps.setInt(1, 2);

        ps.executeUpdate();
        con.close();

        System.out.println("Book Deleted");
    }

    // QUERY
    public static void displayBooks() throws Exception {
        Connection con = DBConnection.getConnection();

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM book");

        System.out.println("Book Records:");
        while (rs.next()) {
            System.out.println(
                rs.getInt(1) + " | " +
                rs.getString(2) + " | " +
                rs.getString(3) + " | " +
                rs.getString(4) + " | " +
                rs.getDouble(5)
            );
        }
        con.close();
    }
}
