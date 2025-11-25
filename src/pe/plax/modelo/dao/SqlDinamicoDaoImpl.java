/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.plax.modelo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import utiles.Funciones;

/**
 *
 * @author Alex Echavarria
 */
public class SqlDinamicoDaoImpl implements SqlDinamicoDao{
    
    private Connection con;
    private ResultSet rSet = null;
    //private Statement stm = null;
    //PreparedStatement pstm = null;

    public SqlDinamicoDaoImpl(Connection con) {
        this.con = con;
    }
    
    @Override
    public ResultSet getResultSetSql(String archivoConf, String key) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql;
        sql = Funciones.valorConfig(archivoConf, key);
        //System.out.println(" *** SQL: " + sql);
        //sql = sql.replaceAll(":empresa", empresa);
        
        Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rSet = stm.executeQuery(sql);
        
        return rSet;
    }

    @Override
    public ResultSet getResultSetSql(String archivoCFG, String key, HashMap<String, String> parametros) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql;
        sql = Funciones.valorConfig(archivoCFG, key);
        //System.out.println(" *** SQL: " + sql);

        boolean isEmpty = parametros.isEmpty();
        if (!isEmpty) {
            Iterator<String> keySetIterator = parametros.keySet().iterator();
            while(keySetIterator.hasNext()){
                String argumento = keySetIterator.next();
                String valor = parametros.get(argumento);
                System.out.println(" ** Argumento: [" + argumento + "]  -> Valor: [" + valor + "]");
                sql = sql.replaceAll(argumento, valor);
                System.out.println(" *** SQL parametros reemp: " + sql);
            }
        }
        Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rSet = stm.executeQuery(sql);
        return rSet;
    }
    
    @Override
    public ResultSet getResultSetSql(String sql) throws SQLException {
        //System.out.println(" *** SQL: " + sql);
        Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rSet = stm.executeQuery(sql);
        return rSet;
    }
    
    @Override
    public void ingresar(Object object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void retirar(String codigo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(Object object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object buscar(String codigo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet getResultSetLista() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
