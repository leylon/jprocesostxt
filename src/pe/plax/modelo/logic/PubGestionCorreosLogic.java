/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.plax.modelo.logic;

import java.sql.Connection;
import pe.plax.modelo.bean.PubGestionCorreos;
import pe.plax.modelo.dao.PubGestionCorreosDao;
import pe.plax.modelo.dao.PubGestionCorreosDaoImpl;

/**
 *
 * @author Alex Echavarria
 */
public class PubGestionCorreosLogic {
    
    public static void insertPubGestionCorreos(Connection con, PubGestionCorreos pgc) throws Exception{
        try {
            //Implementar reglas de negocio
            PubGestionCorreosDao pgcDAO = new PubGestionCorreosDaoImpl(con);
            pgcDAO.ingresar(pgc);
            //Finalizar la transaccion
            con.commit();
        } catch (Exception e){
            con.rollback();
            throw e;
        }
    }
    
    
    
}
