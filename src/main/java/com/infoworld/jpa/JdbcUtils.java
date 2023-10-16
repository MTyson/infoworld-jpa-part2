package com.infoworld.jpa;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JdbcUtils {
    public static void dumpTableNames(Connection connection) {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            List<String> tableNames = new ArrayList<>();
            ResultSet rs = metaData.getTables(null, null, "%", null);
            while (rs.next()) {
                tableNames.add(rs.getString(3));
            }
            System.out.println("Table Names: ");
            tableNames.forEach(System.out::println);
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dumpTables(Connection connection, String... tableNames) {
        try {
            Arrays.stream(tableNames).forEach((String tableName) -> {
                try {
                    // Query the table
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName);

                    // Get information about the columns in this table
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();

                    // Dump this table to the standard output
                    System.out.println("Table: " + tableName);
                    while (rs.next()) {
                        StringBuilder sb = new StringBuilder("\t{");
                        for (int i = 1; i <= columnCount; i++) {
                            Object value = rs.getObject(i);
                            sb.append(rsmd.getColumnName(i) + ": " + value);
                            if (i < columnCount) {
                                sb.append(", ");
                            } else {
                                sb.append("}, ");
                            }
                        }
                        System.out.println(sb);
                    }

                    // Clean up, not as safe as it could be: if the queries throw a SQL exception then the
                    // statement and result set will be left open
                    rs.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
