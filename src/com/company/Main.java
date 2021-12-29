package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        // write your code here
        ProductController productController = new ProductController();
        CategoryController categoryController = new CategoryController();

        Scanner sc = new Scanner(System.in);

        int resCode;
        String menu = " ";
        while (!menu.equals("q")) {
            printMenuMessage();

            menu = sc.next();
            menu = menu.toLowerCase();

            if (menu.equals("p")) {
                printProductMenuMessage();
                menu = sc.next();
                menu = menu.toLowerCase();
                switch (menu) {
                    case "s":
                        ResultSet resultSet = productController.selectAll();
                        printProductResult(resultSet);
                        break;
                    case "i":
                        resCode = productController.insert();
                        printStatus(resCode, "Insert success",
                                "Insert fail!");
                        break;
                    case "u":
                        resCode = productController.update();
                        printStatus(resCode, "Update success",
                                "Update fail!");
                        break;
                    case "d":
                        resCode = productController.delete();
                        printStatus(resCode, "Delete success",
                                "Delete fail!");
                        break;
                }
            }
            if (menu.equals("c")) {
                printCategoryMenuMessage();
                menu = sc.next();
                menu = menu.toLowerCase();
                switch (menu) {
                    case "s":
                        ResultSet resultSet = categoryController.selectAll();
                        printCategoryResult(resultSet);
                        break;
                    case "i":
                        resCode = categoryController.insert();
                        printStatus(resCode, "Insert success",
                                "Insert fail!");
                        break;
                    case "d":
                        resCode = categoryController.delete();
                        printStatus(resCode, "Delete success",
                                "Delete fail!");
                        break;
                }
            }
        }
        sc.close();
        productController.disconnect();
        categoryController.disconnect();

        System.out.println("BYE");
    }

    private static void printCategoryResult(ResultSet resultSet) throws SQLException {
        System.out.println("id | name ");
        System.out.println("----------------------");
        int count = 0;
        while (resultSet.next()) {
            count++;

            String id = resultSet.getString(1);
            String name = resultSet.getString(2);

            System.out.printf("%s  | %s    \n",
                    id, name);
        }
        System.out.println("(" + count + (count > 1 ? "rows" : "row") + ")");
    }

    private static void printStatus(int resCode, String message, String errMessage) {
        if (resCode > 0) {
            System.out.println(message);
        } else {
            System.out.println(errMessage);
        }
    }

    private static void printProductResult(ResultSet resultSet) throws SQLException {
        System.out.println("id | category | brand | code_name | price | remain");
        System.out.println("--------------------------------------------------");
        int count = 0;
        while (resultSet.next()) {
            count++;

            String id = resultSet.getString(1);
            String category = resultSet.getString(2);
            String brand = resultSet.getString(3);
            String codeName = resultSet.getString(4);
            String price = resultSet.getString(5);
            String remain = resultSet.getString(6);

            System.out.printf("%s  | %s    | %s  | %s      |    %s|      %s\n",
                    id, category, brand, codeName, price, remain);
        }
        System.out.println("(" + count + (count > 1 ? "rows" : "row") + ")");
    }

    private static void printMenuMessage() {
        System.out.print("Enter char to select menu\n" +
                "\tc = goto category menu\n" +
                "\tp = goto product menu\n" +
                "\tq = exit program\n" +
                "=> ");
    }

    private static void printProductMenuMessage() {
        System.out.print("Enter char to manage product\n" +
                "\ts = list products\n" +
                "\ti = insert new product\n" +
                "\tu = update exists product\n" +
                "\td = delete a product\n" +
                "\tq = exit\n" +
                "=> ");
    }

    private static void printCategoryMenuMessage() {
        System.out.print("Enter char to manage category\n" +
                "\ts = list category\n" +
                "\ti = insert new category\n" +
                "\td = delete a category\n" +
                "\tq = exit\n" +
                "=> ");
    }
}
