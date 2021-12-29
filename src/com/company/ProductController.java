package com.company;

import java.sql.*;
import java.util.Scanner;

public class ProductController {
    private final Scanner sc;
    private final Connection con;
    private final Statement stmt;

    public ProductController() throws SQLException {
        sc = new Scanner(System.in);
        con = DriverManager.getConnection(Env.URL, Env.USER, Env.PASSWORD);
        stmt = con.createStatement();
    }

    public ResultSet selectAll() throws SQLException {
        return stmt.executeQuery("select * from product");
    }

    public int insert() throws SQLException {
        String sql = "insert into product(" +
                "category, brand, code_name, price, remain)" +
                "values(?, ?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);

        System.out.print("Enter category: ");
        String category = sc.next();
        System.out.print("Enter brand: ");
        String brand = sc.next();
        System.out.print("Enter code_name: ");
        String codeName = sc.next();
        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        System.out.print("Enter remain: ");
        int remain = sc.nextInt();

        ps.setString(1, category);
        ps.setString(2, brand);
        ps.setString(3, codeName);
        ps.setDouble(4, price);
        ps.setInt(5, remain);

        return ps.executeUpdate();
    }

    public int update() throws SQLException {
        String sql = "update product set category = ?, brand = ?, code_name = ?, price = ?, remain = ?" +
                "where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        System.out.print("Enter product id for update: ");
        int id = sc.nextInt();
        System.out.print("Enter category: ");
        String category = sc.next();
        System.out.print("Enter brand: ");
        String brand = sc.next();
        System.out.print("Enter code_name: ");
        String codeName = sc.next();
        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        System.out.print("Enter remain: ");
        int remain = sc.nextInt();

        ps.setString(1, category);
        ps.setString(2, brand);
        ps.setString(3, codeName);
        ps.setDouble(4, price);
        ps.setInt(5, remain);
        ps.setInt(6, id);

        return ps.executeUpdate();
    }

    public int delete() throws SQLException {
        String sql = "delete from product where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        System.out.print("Enter product id to delete: ");
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
