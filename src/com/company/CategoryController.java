package com.company;

import java.sql.*;
import java.util.Scanner;

public class CategoryController {
    private final Scanner sc;
    private final Connection con;
    private final Statement stmt;

    public CategoryController() throws SQLException {
        sc = new Scanner(System.in);
        con = DriverManager.getConnection(Env.URL, Env.USER, Env.PASSWORD);
        stmt = con.createStatement();
    }

    public ResultSet selectAll() throws SQLException {
        return stmt.executeQuery("select * from category");
    }

    public int insert() throws SQLException {
        String sql = "insert into category(" +
                "name)" +
                "values(?)";

        PreparedStatement ps = con.prepareStatement(sql);

        System.out.print("Enter category: ");
        String category = sc.next();

        ps.setString(1, category);

        return ps.executeUpdate();
    }

    public int delete() throws SQLException {
        String sql = "delete from category where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        System.out.print("Enter category id to delete: ");
        int id = sc.nextInt();

        ps.setInt(1, id);

        return ps.executeUpdate();
    }

    public void disconnect() throws SQLException {
        sc.close();
        stmt.close();
        con.close();
    }
}
