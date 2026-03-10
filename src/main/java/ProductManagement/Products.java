package ProductManagement;

import com.mysql.cj.jdbc.Driver;
import java.sql.*;
import java.util.Scanner;

public class Products {

     static Scanner sc = new Scanner(System.in);

    // ---------------- CONNECTION ----------------
     Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/productmanagement",
                "root",
                "Shalini@2804"
        );
    }

    // ---------------- 1. ADD PRODUCT ----------------
    public void addProduct() throws SQLException {
        Connection con = getConnection();
        Statement st = con.createStatement();

        System.out.print("Add product? (y/n): ");
        char ch = sc.next().charAt(0);

        while (ch == 'y' || ch == 'Y') {

            System.out.print("Product ID: ");
            int id = sc.nextInt();

            ResultSet rs = st.executeQuery(
                    "SELECT product_id FROM products WHERE product_id=" + id);

            if (rs.next()) {
                System.out.println("Product already exists.");
                System.out.print("Add another? (y/n): ");
                ch = sc.next().charAt(0);
                continue;
            }

            sc.nextLine();
            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Category: ");
            String cat = sc.nextLine();

            System.out.print("Price: ");
            double price = sc.nextDouble();
            if (price <= 0) {
                System.out.println("Invalid price.");
                continue;
            }

            System.out.print("Quantity: ");
            int qty = sc.nextInt();
            if (qty < 0) {
                System.out.println("Invalid quantity.");
                continue;
            }

            System.out.print("Rating (1-5): ");
            double rating = sc.nextDouble();
            if (rating < 1 || rating > 5) {
                System.out.println("Invalid rating.");
                continue;
            }

            sc.nextLine();
            System.out.print("Manufacturer: ");
            String mfg = sc.nextLine();

            int rows = st.executeUpdate(
                    "INSERT INTO products VALUES(" +
                            id + ",'" + name + "','" + cat + "'," +
                            price + "," + qty + "," + rating + ",'" + mfg + "',NOW())");

            System.out.println(rows > 0 ? "Product added." : "Insert failed.");

            System.out.print("Add another? (y/n): ");
            ch = sc.next().charAt(0);
        }
        con.close();
    }

    public static void main(String[] args) throws SQLException {

        while (true) {
            System.out.println("\n--- Product Management ---");
            System.out.println("1. Add Product");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");
            Products product = new Products();
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    product.addProduct();
                    break;

                case 2:
                    System.out.println("Exiting application...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
