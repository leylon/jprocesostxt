/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.plax.modelo.dao;

import java.sql.SQLException;

/**
 *
 * @author Alex Echavarria
 */
public interface PubGestionCorreosDao extends GeneralDao {
    public Object buscar() throws SQLException;
}

