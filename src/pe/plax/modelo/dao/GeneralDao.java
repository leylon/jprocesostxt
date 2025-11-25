/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.plax.modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Alex
 */
public interface GeneralDao {

    public void ingresar(Object object) throws SQLException;
    public void retirar(String codigo) throws SQLException;
    public void actualizar(Object object) throws SQLException;
    public Object buscar(String codigo) throws SQLException;
    public ResultSet getResultSetLista() throws SQLException;

}
