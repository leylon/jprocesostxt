/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jprocesotxt;

//import java.io.File;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pe.plax.modelo.bean.Archivo;
import pe.plax.modelo.bean.Comprobante;
import pe.plax.modelo.bean.DetalleComprobante;
import pe.plax.modelo.dao.Conexion;
import pe.plax.modelo.dao.PubGestionComprobantes;
import pe.plax.modelo.logic.SqlDinamicoLogic;
import utiles.ScrollableTableModel;
//import java.io.FileWriter;

/**
 *
 * @author Andy
 */
public class ProcesaArchivoLog {

    private static Connection sqlca = null;
    private static ScrollableTableModel ds_Sql;
    private static ScrollableTableModel ds_Sql1;
    private static String lista_comprobantes;
    private static String carpeta_error; // Variable para la setencia de la carpeta de envío
    private static String carpeta; // Variable para setear la carpeta de envío.
    private static int ll_tRows;
    private static ResultSet fila;
    private static ResultSet fila_carpeta;
    //private static ResultSet filadet;

     
        
    public static void ejecutar(String codemp) {

        CallableStatement sp_cabecera = null;
        CallableStatement sp_detalle = null;
     
          System.out.println("Iniciando Proceso de Envío Correcto y detección de Errores");
        
       try {
            try {
                sqlca = Conexion.getConexion("firebird");
                sqlca.setAutoCommit(false);
            } catch (Exception ex) {
                System.out.println("Error al tratar de Conectar a Firebird.." + ex.getMessage());
                throw ex;
            }

            try {
                //Seleccionamos la carpeta de envío
                carpeta_error ="SELECT trim(valor)";
                carpeta_error += " FROM parametros_sistemas";
                carpeta_error +=" WHERE empresa='08' AND sistema='008'";
                carpeta_error += " AND llave='SERVIDOR_TXT'";
                
                ds_Sql1 = SqlDinamicoLogic.getSTMSql(sqlca,carpeta_error);
                fila_carpeta = ds_Sql1.getResultSet();
                
                
                while (fila_carpeta.next()) {
                   
                  carpeta=fila_carpeta.getString(1); 
                 
                }
                
                
                lista_comprobantes = "SELECT av.empresa,av.punto_venta,av.tipo_doc,av.serie_doc,av.numero_doc";
                lista_comprobantes += " FROM ant_ventas_diarias av";
                lista_comprobantes += " inner join pub_tabla_parametros pt on (pt.empresa=av.empresa and pt.llave='SUITE_ELECTRONICA' and pt.item=1)";
                //lista_comprobantes += " where av.empresa ='" + codemp + "' AND (av.ind_txt='3') ";
                lista_comprobantes += " where av.ind_txt='3' ";
                lista_comprobantes += " AND av.tipo_doc IN('01','03','07','08')";
               
               
                ds_Sql = SqlDinamicoLogic.getSTMSql(sqlca, lista_comprobantes);
                fila = ds_Sql.getResultSet();
                
                Comprobante Xcomprobante;
                Xcomprobante = new Comprobante();
                
                while (fila.next()) {
                    Xcomprobante.setIn_empresa(fila.getString("empresa"));
                    Xcomprobante.setIn_punto_venta(fila.getString("punto_venta"));
                    Xcomprobante.setIn_tipo_doc(fila.getString("tipo_doc"));
                    Xcomprobante.setIn_serie_doc(fila.getString("serie_doc"));
                    Xcomprobante.setIn_numero_doc(fila.getString("numero_doc"));         
                 
                 //Buscamos el Archivo que aparezca en el directorio
                 Archivo XArchivo;
                 
                 XArchivo=new Archivo();
                 PubGestionComprobantes XGestionArchivo;
                 XGestionArchivo= new PubGestionComprobantes(sqlca);
                 
                 //carpeta = "D:\\archivos_txt";
                 
                 if (XArchivo.Buscar(carpeta, Xcomprobante)==true) { //true: si encuentra archivo log
                    XGestionArchivo.actualizarInd(Xcomprobante,"2", Xcomprobante.getCadena_log());
                }
                 else{
                    XGestionArchivo.actualizarInd(Xcomprobante,"1",""); 
                 }
                     
                             
                   
                         
                   

                }
                sqlca.commit(); 
                ll_tRows = ds_Sql.getRowCount();
                System.out.println("filas:" + ll_tRows);
            } catch (Exception ex) {
                System.out.println("Error al utilizar el metodo SqlDinamicoLogic.getSTMSql() - " + ex.getMessage());
                ex.printStackTrace();
                throw ex;
            }

        } catch (Exception e) {
            System.out.println("Error identificado: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (sqlca != null) {
                //Cerramos las conexiones a las BD
                try {
                    sqlca.close();
                    System.out.println("**Desconectado de la BD Correctamente!");
                } catch (Exception ex) {
                    System.out.println(" - Excepcion al Cerrar la conexion Firebird (" + ex.getMessage() + ")");
                }
                //System.out.println(cl.getTime() + " ** Fin del Proceso ** ");
                //System.exit(0);
            }
        }

    }
   
      
}
