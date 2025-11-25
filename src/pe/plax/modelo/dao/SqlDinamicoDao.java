/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.plax.modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author Alex Echavarria
 */
public interface SqlDinamicoDao extends GeneralDao{
    
    public ResultSet getResultSetSql(String archivoCFG, String key) throws SQLException;
    
    public ResultSet getResultSetSql(String archivoCFG, String key, HashMap<String, String> parametros) throws SQLException;
    
    public ResultSet getResultSetSql(String sql) throws SQLException;
    
}
