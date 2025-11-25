/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.plax.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.plax.modelo.bean.CompRetencion;
import pe.plax.modelo.bean.Comprobante;

/**
 *
 * @author Andy Villafana
 */
public class PubGestionComprobantes implements GeneralDao {
    
    private Connection con;
    
    public PubGestionComprobantes(Connection con) {
        this.con = con;
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
       
        Comprobante xcomp=(Comprobante)object;
        
        try ( //Hacer Update
                PreparedStatement ps = con.prepareStatement(
                        "UPDATE ant_ventas_diarias"
                                + " SET IND_TXT = '1' "
                                + " WHERE empresa = ? AND punto_venta= ?"
                                + " AND tipo_doc=? AND serie_doc=? AND numero_doc=?")) {
            ps.setString(1,xcomp.getIn_empresa());
            ps.setString(2,xcomp.getIn_punto_venta());
            ps.setString(3,xcomp.getIn_tipo_doc());
            ps.setString(4,xcomp.getIn_serie_doc());
            ps.setString(5,xcomp.getIn_numero_doc());
            
            // call executeUpdate to execute our sql update statement
            ps.executeUpdate();
        }
        
        
    }
    /*GCHAVEZ 09.12.2021,Cambiar de estado */
        public void actualizar_anu(Object object) throws SQLException {
       
        Comprobante xcomp=(Comprobante)object;
        
        try ( //Hacer Update
                PreparedStatement ps = con.prepareStatement(
                        "UPDATE ant_ventas_diarias"
                                + " SET IND_TXT = '5' "
                                + " WHERE empresa = ? AND punto_venta= ?"
                                + " AND tipo_doc=? AND serie_doc=? AND numero_doc=?")) {
            ps.setString(1,xcomp.getIn_empresa());
            ps.setString(2,xcomp.getIn_punto_venta());
            ps.setString(3,xcomp.getIn_tipo_doc());
            ps.setString(4,xcomp.getIn_serie_doc());
            ps.setString(5,xcomp.getIn_numero_doc());
            
            // call executeUpdate to execute our sql update statement
            ps.executeUpdate();
        }
        
        
    }
   /*FIN*/
    /*gchavez 13.09.23, actualizar indicador si se envia los adjuntos*/   
        public void actualizar_ind_adj(Object object) throws SQLException {
       
       Comprobante xcomp=(Comprobante)object;
        
        try ( //Hacer Update
                PreparedStatement ps = con.prepareStatement(
                        "UPDATE ant_ventas_diarias "
                                + " SET IND_ENVIO_ADJ = 1"
                                + " WHERE empresa = ? AND punto_venta= ?"
                                + " AND tipo_doc=? AND serie_doc=? AND numero_doc=?")) {
            ps.setString(1,xcomp.getIn_empresa());
            ps.setString(2,xcomp.getIn_punto_venta());
            ps.setString(3,xcomp.getIn_tipo_doc());
            ps.setString(4,xcomp.getIn_serie_doc());
            ps.setString(5,xcomp.getIn_numero_doc());
            
            
            // call executeUpdate to execute our sql update statement
            ps.executeUpdate();
        }
      
        
    }    
        
     public void actualizar_CR(Object object) throws SQLException {
       
        CompRetencion xcomp=(CompRetencion)object;
        
        try ( //Hacer Update
                PreparedStatement ps = con.prepareStatement(
                        "UPDATE tes_comp_ret"
                                + " SET ind_txt = '1' "
                                + " WHERE empresa = ? AND tipdoc=? "
                                + "  AND serdoc=? AND numdoc=?")) {
            ps.setString(1,xcomp.getIN_EMPRESA());
            ps.setString(2,xcomp.getIN_TIPDOC());
            ps.setString(3,xcomp.getIN_SERDOC());
            ps.setString(4,xcomp.getIN_NUMDOC());
            
            
            // call executeUpdate to execute our sql update statement
            ps.executeUpdate();
        }
      
        
    }

   
    //Gestion Log Archivos
     public void actualizarInd(Object object,String estado,String mensaje_log) throws SQLException {
       
        Comprobante xcomp=(Comprobante)object;
        
        try ( //Hacer Update de Actualización de Indicador
                PreparedStatement ps = con.prepareStatement(
                        "UPDATE ant_ventas_diarias "
                                + " SET IND_TXT = '" + estado + "'"
                                + " WHERE empresa = ? AND punto_venta= ?"
                                + " AND tipo_doc=? AND serie_doc=? AND numero_doc=?")) {
            ps.setString(1,xcomp.getIn_empresa());
            ps.setString(2,xcomp.getIn_punto_venta());
            ps.setString(3,xcomp.getIn_tipo_doc());
            ps.setString(4,xcomp.getIn_serie_doc());
            ps.setString(5,xcomp.getIn_numero_doc());
            
            // call executeUpdate to execute our sql update statement
            ps.executeUpdate();
        }
        
         try ( //Hacer Update de Actualización de Mensaje Log
               PreparedStatement ps1 = con.prepareStatement(
                        "UPDATE ant_ventas_diarias "
                                + " SET MENSAJE_LOG = '" + mensaje_log + "'"
                                + " WHERE empresa = ? AND punto_venta= ?"
                                + " AND tipo_doc=? AND serie_doc=? AND numero_doc=?")) {
            ps1.setString(1,xcomp.getIn_empresa());
            ps1.setString(2,xcomp.getIn_punto_venta());
            ps1.setString(3,xcomp.getIn_tipo_doc());
            ps1.setString(4,xcomp.getIn_serie_doc());
            ps1.setString(5,xcomp.getIn_numero_doc());
            
            // call executeUpdate to execute our sql update statement
            ps1.executeUpdate();
        }
        
        
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
