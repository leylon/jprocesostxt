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
import pe.plax.modelo.bean.PubGestionCorreos;

/**
 *
 * @author Alex Echavarria
 */
public class PubGestionCorreosDaoImpl implements PubGestionCorreosDao{
    private Connection con;
    private ResultSet rSet = null;

    public PubGestionCorreosDaoImpl(Connection con) {
        this.con = con;
    }

    public void ingresar(Object object) throws SQLException {
        PubGestionCorreos pgc=(PubGestionCorreos)object;
        String sql="INSERT INTO PUB_GESTION_CORREOS (FECHAHORA,DE,PARA,CC,CCO,ASUNTO,CUERPO,TIPO_CUERPO,IMG_FIRMA,IP_EQUIPO,"
                + "NOM_EQUIPO,FLAG_PROCESADO,FEC_PROCESADO,CARPETA_ADJUNTOS,ADJUNTOS) " 
                + "VALUES (CURRENT_TIMESTAMP,'"+ pgc.getDE() + "','" + pgc.getPARA() + "','" + pgc.getCC() + "','"
                +           pgc.getCCO() + "','" + pgc.getASUNTO() + "','" + pgc.getCUERPO().replaceAll("'","") + "','" + pgc.getTIPO_CUERPO() + "',"
                +           pgc.getIMG_FIRMA() + ",'" + pgc.getIP_EQUIPO() + "','" + pgc.getNOM_EQUIPO() + "',0,NULL,"
                +           pgc.getCARPETA_ADJUNTOS() + "," + pgc.getADJUNTOS() + ")";
        System.out.println("sql:" + sql);
        Statement stm = con.createStatement();
        stm.executeUpdate(sql);
    }

    public void retirar(String codigo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void actualizar(Object object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object buscar(String codigo) throws SQLException {
        return null;
    }

    public ResultSet getResultSetLista() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object buscar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
