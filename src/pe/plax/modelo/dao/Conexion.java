/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
package pe.plax.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

/**
 *
 * @author Alex
 */

public class Conexion {

    //--El arg. gestor es el nombre del archivo Properties----------------------
    
    public static Connection getConexion(String gestor) throws Exception{

        Connection con = null;
        /*String url = "jdbc:firebirdsql:192.168.1.6/3050:andesa";
        String usuario= "sysdba";
        String clave = "serverbd";*/
        if (gestor!=null) {
              String bd_url = System.getenv("DB_URL");
        String bd_user = System.getenv("DB_USER");
        String bd_password = System.getenv("DB_PASSWORD");
        gestor = "firebird";
        

            ResourceBundle resource = ResourceBundle.getBundle("pe.plax.modelo.dao."+gestor);
            String url =bd_url; //resource.getString("url");
            String usuario= bd_user; //resource.getString("usuario");
            String clave = bd_password;// resource.getString("clave");
            /*if (gestor.equals("oracle")){
                DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
            }*/
            //if (gestor.equals("mysql")){
            //    Class.forName("com.mysql.jdbc.Driver").newInstance();
            //}
            if (gestor.equals("firebird")){
                Class.forName("org.firebirdsql.jdbc.FBDriver").newInstance();
            }
            con = DriverManager.getConnection(url,usuario,clave);
            System.out.println("Conexion a la BD OK.");
            return con;
        }
        else{
            return null;
        }

    }

}
