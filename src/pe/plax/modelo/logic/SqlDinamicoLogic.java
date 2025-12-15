/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.plax.modelo.logic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import pe.plax.modelo.dao.SqlDinamicoDao;
import pe.plax.modelo.dao.SqlDinamicoDaoImpl;
import utiles.ScrollableTableModel;

/**
 *
 * @author Alex Echavarria
 */
public class SqlDinamicoLogic {
    
    public static ScrollableTableModel getSTMSql( Connection sqlca, String archivoCFG, String key) throws Exception{
        //Connection con=null;
        ScrollableTableModel sTM = null;
        ResultSet rs = null;
        try {
            SqlDinamicoDao sqlDAO = new SqlDinamicoDaoImpl(sqlca);
            rs = sqlDAO.getResultSetSql(archivoCFG, key);
            sTM = new ScrollableTableModel(rs);
            //Finalizar la transaccion
            sqlca.commit();
            return sTM;
        } catch (Exception e){
            sqlca.rollback();
            e.printStackTrace();
            throw e;
        }
    }
    
    public static ScrollableTableModel getSTMSql( Connection sqlca, String archivoCFG, String key, HashMap<String, String> parametros) throws Exception{
        //Connection con=null;
        ScrollableTableModel sTM = null;
        ResultSet rs = null;
        try {
            SqlDinamicoDao sqlDAO = new SqlDinamicoDaoImpl(sqlca);
            rs = sqlDAO.getResultSetSql(archivoCFG, key, parametros);
            sTM = new ScrollableTableModel(rs);
            //Finalizar la transaccion
            sqlca.commit();
            return sTM;
        } catch (Exception e){
            sqlca.rollback();
            e.printStackTrace();
            throw e;
        }
    }
    
    public static ScrollableTableModel getSTMSql( Connection sqlca, String sql) throws Exception{
        //Connection con=null;
        ScrollableTableModel sTM = null;
        ResultSet rs = null;
        try {
            SqlDinamicoDao sqlDAO = new SqlDinamicoDaoImpl(sqlca);
            rs = sqlDAO.getResultSetSql(sql);
            sTM = new ScrollableTableModel(rs);
            //Finalizar la transaccion
            if(!sql.contains("select")){
                sqlca.commit();
            }else{
                System.out.println("sin commit...");
            }
            //sqlca.commit();
            return sTM;
        } catch (Exception e){
            sqlca.rollback();
            e.printStackTrace();
            throw e;
        }
    }
    
}
