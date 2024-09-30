package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;

public class DAOUtility {

    public static final int TERMID_FA24 = 1;

    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();  // Create a new JSON array to hold all rows

        try {
            if (rs != null) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();

                // Loop through each row in the result set
                while (rs.next()) {
                    JsonObject record = new JsonObject();  // Create a new JSON object for each row

                    // Loop through each column in the current row
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = rsmd.getColumnName(i);
                        Object value = rs.getObject(i);

                        // Convert SQL-specific types to String for compatibility with JSON
                        if (value instanceof java.sql.Time || value instanceof java.sql.Date || value instanceof java.sql.Timestamp) {
                            value = value.toString();  // Convert to String
                        }

                        // Convert all other data types to String to ensure consistency
                        if (value != null) {
                            value = value.toString();
                        }

                        // Store the value in the JSON object with the column name as the key
                        record.put(columnName, value);
                    }

                    // Add the JSON object (representing a row) to the JSON array
                    records.add(record);
                }
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        // Debug print to check the generated JSON before returning
        System.out.println("Generated JSON: " + Jsoner.serialize(records));
        
        return Jsoner.serialize(records);  // Return the JSON array as a serialized string
    }
}
