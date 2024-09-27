package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistrationDAO {
    
    private final DAOFactory daoFactory;
    
    RegistrationDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public boolean create(int studentid, int termid, int crn) {
    boolean result = false;
    PreparedStatement ps = null;

    try {
        Connection conn = daoFactory.getConnection();
        
        if (conn.isValid(0)) {
            // Prepare and execute the insert query
            String query = "INSERT INTO registration (studentid, termid, crn) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, studentid);
            ps.setInt(2, termid);
            ps.setInt(3, crn);
            
            // Execute and check if 1 row was affected
            int rowsAffected = ps.executeUpdate();
            result = (rowsAffected == 1);
        }
    } catch (SQLException e) {
    } finally {
        if (ps != null) { try { ps.close(); } catch (SQLException e) {} }
    }
    return result;
}


    public boolean delete(int studentid, int termid, int crn) {
    boolean result = false;
    PreparedStatement ps = null;

    try {
        Connection conn = daoFactory.getConnection();
        
        if (conn.isValid(0)) {
            // Prepare and execute the delete query
            String query = "DELETE FROM registration WHERE studentid = ? AND termid = ? AND crn = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, studentid);
            ps.setInt(2, termid);
            ps.setInt(3, crn);
            
            // Execute and check if 1 row was affected
            int rowsAffected = ps.executeUpdate();
            result = (rowsAffected == 1);
        }
    } catch (SQLException e) {
    } finally {
        if (ps != null) { try { ps.close(); } catch (SQLException e) {} }
    }
    return result;
}

    
    public boolean delete(int studentid, int termid) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                
            }
            
        }
        
        catch (SQLException e) {}
        
        finally {

            if (ps != null) { try { ps.close(); } catch (SQLException e) {} }
            
        }
        
        return result;
        
    }

    public String list(int studentid, int termid) {
    String result = "[]";  // Default empty JSON array
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        Connection conn = daoFactory.getConnection();
        
        if (conn.isValid(0)) {
            // Prepare and execute the query
            String query = "SELECT * FROM registration WHERE studentid = ? AND termid = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, studentid);
            ps.setInt(2, termid);
            rs = ps.executeQuery();
            
            // Convert ResultSet to JSON using DAOUtility
            result = DAOUtility.getResultSetAsJson(rs);
        }
    } catch (SQLException e) {
    } finally {
        if (rs != null) { try { rs.close(); } catch (SQLException e) {} }
        if (ps != null) { try { ps.close(); } catch (SQLException e) {} }
    }
    return result;
}

    
}
