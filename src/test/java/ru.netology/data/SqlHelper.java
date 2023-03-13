package ru.netology.data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlHelper {


    public static Connection getConn() throws SQLException {
        String url = System.getProperty("url");
       // String url = "jdbc:postgresql://localhost:5432/app";
        String username = System.getProperty("username");
        String password = System.getProperty("password");

        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException err) {
            //System.out.println("йцуке");
            err.printStackTrace(); // java.sql.SQLException: No suitable driver found for  -
        }
        return null;
    }

    @SneakyThrows
    public static void cleanDataBase() {
        val deleteCreditRequest = "DELETE FROM credit_request_entity";
        val deleteOrderEntity = "DELETE FROM order_entity";
        val deletePaymentEntity = "DELETE FROM payment_entity";
        val runner = new QueryRunner();
        try (val conn = getConn();
        ) {
            runner.update(conn, deleteCreditRequest);
            runner.update(conn, deleteOrderEntity);
            runner.update(conn, deletePaymentEntity);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @SneakyThrows
    public static String getPaymentEntity() {
        try (val conn = getConn();
             val countStmt = conn.createStatement()) {
            val paymentStatus = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
            val resultSet = countStmt.executeQuery(paymentStatus);
            if (resultSet.next()) {
                return resultSet.getString("status");
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public static String getCreditEntity() {
        try (val conn = getConn();
             val countStmt = conn.createStatement()) {
            val creditStatus = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
            val resultSet = countStmt.executeQuery(creditStatus);
            if (resultSet.next()) {
                return resultSet.getString("status");
            }
        } catch (SQLException err) {
            System.out.println("Не получилось подключиться к базе данных");
            err.printStackTrace();
        }
        return null;
    }
}
