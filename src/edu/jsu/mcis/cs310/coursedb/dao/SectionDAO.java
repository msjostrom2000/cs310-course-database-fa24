package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SectionDAO {
    
    private static final String QUERY_FIND = "SELECT * FROM section WHERE termid = ? AND subjectid = ? AND num = ? ORDER BY crn";
    
    private final DAOFactory daoFactory;
    
    SectionDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public String find(int termid, String subjectid, String num) {
        
        String result = "[]";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // Change to create a scrollable ResultSet
                ps = conn.prepareStatement(QUERY_FIND, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ps.setInt(1, termid);            // Set termid parameter
                ps.setString(2, subjectid);       // Set subjectid parameter
                ps.setString(3, num);             // Set course number parameter

                rs = ps.executeQuery();
                
                // Optional: Remove the call to rs.last(), as it is not needed for processing rows
                
                // Convert ResultSet to JSON format using the utility method
                result = DAOUtility.getResultSetAsJson(rs);
                System.out.println("JSON Result from find(): " + result); // Debugging print
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
        }
        
        return result;
        
    }
    
}
