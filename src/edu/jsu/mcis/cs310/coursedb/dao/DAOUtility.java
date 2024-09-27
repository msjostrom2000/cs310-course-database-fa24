package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static String getResultSetAsJson(ResultSet rs) {
    JsonArray records = new JsonArray();

    try {
        ResultSetMetaData metadata = rs.getMetaData();
        int columnCount = metadata.getColumnCount();

        while (rs.next()) {
            JsonObject record = new JsonObject();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metadata.getColumnName(i);
                Object value = rs.getObject(i);
                record.put(columnName, value);
            }
            records.add(record);
        }
    } catch (SQLException e) {
    }

    String jsonResult = Jsoner.serialize(records);
    System.out.println("Generated JSON: " + jsonResult);  // Debugging output
    return jsonResult;
}


    
}
