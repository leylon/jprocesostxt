/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import java.io.*;    
import org.apache.commons.net.ftp.*;
/**
 *
 * @author leylon
 */
public class Ftp {
    // Variables de clase.
    private static String dirActual;

    public Ftp(){
        
    }
   
    private static FTPClient cliente = new FTPClient();

    // Métodos: 
    /**
     * Nos conecta a un servidor mediante usuario y contraseña.
     * @param server Servidor al que nos queremos conectar.
     * @param user Usuario para poder acceder.
     * @param pwd Contraseña para poder acceder.
     * @return True, si la conexión se estableció.<br>
     * False, es caso contrario.
     */
    public  boolean conectar(String server, String user, String pwd){     
        try {
            // Conectarse e identificarse.
            
            cliente.connect(server);
            if(cliente.login(user, pwd)){
                // Entrando a modo pasivo
                cliente.enterLocalPassiveMode();
                // Activar recibir/enviar cualquier tipo de archivo
                cliente.setFileType(FTP.BINARY_FILE_TYPE);

                // Obtener respuesta del servidor y acceder.
                int respuesta = cliente.getReplyCode();
              /*  if ( FTPReply.isPositiveCompletion(respuesta)) {
                    return true;
                }else{
                    return false;
                }*/
                return FTPReply.isPositiveCompletion(respuesta);
            }else{
                System.out.println("Usuario o contraseña incorrectos.");
              //  Util.msjError("Usuario o contraseña incorrectos.");
               // e.printStackTrace();
                return false;
            }


        } catch (IOException e) {
            System.out.println("Host del servidor incorrecto: "+server);
           // Util.msjError("Host del servidor incorrecto: "+server);
            e.printStackTrace();
            return false;
        }           
    }

    /**
     * Cierra sesión del usuario y se desconecta del servidor.
     * @return True, si se ha desconectado correctamente.<br>
     * False, en caso contrario.
     */
    public  boolean desconectar() {
        try {
            // Cerrar sesion y desconectar.
            cliente.logout();
            cliente.disconnect();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Cambia de directorio dentro de un servidor.
     * @param dirCarpeta Dirección completa de la carpeta a la cual queremos acceder
     * dentro del servidor.
     * @return True, si ha cambiado de directorio.<br>
     * False, en caso contrario.
     */ 
    public  boolean cambiarDirectorio(String dirCarpeta) {        
        try {
            //creamos el directorio en el ftp
             cliente.makeDirectory(dirCarpeta);
            // Ubicarse en directorio: ftp  
           
            cliente.changeWorkingDirectory(dirCarpeta);
            dirActual =  cliente.printWorkingDirectory();
        //    return dirCarpeta.equals(dirActual);
 // if(dirCarpeta.equals(dirActual)){
            
            if(dirCarpeta.compareTo(dirActual) == 0){
                // Ha cambiado de forma satisfactoria.
                return true;
            }else{
                return false;
            }

        } catch (IOException e) {
         //   Util.msjError("Error al cambiar de directorio.");
            System.out.println("Error al cambiar de directorio."+e.getMessage());
        //    e.printStackTrace();
            return false;
        }       
    }

    /**
     * Descarga un fichero zip del servidor y lo almacena en una ruta específica.
     * @param nomFich Nombre del fichero zip a descargar.
     * @param destino Dirección donde se quiere almacenar el zip.
     * @return True, si la descarga y almacenamiento ha sido correcto.<br>
     * False, en caso contrario.
     */
    public  boolean descargaFichZip(String nomFich, String destino){
        String extZIP = ".ZIP";// El fichero de extensión que tu desees.
        OutputStream os;
        boolean fichDescargado = false;

        // Asegurarse que es ".ZIP".
        if(nomFich.length() < 4){
            return false;
        }       
        String extension = nomFich.substring(nomFich.length() - 4, nomFich.length());
        extension = extension.toUpperCase();

        if (extension.compareTo(extZIP) == 0) {
            try {
                // Descargar el ZIP. 
                os = new BufferedOutputStream(new FileOutputStream(destino +"/"+ nomFich));
                fichDescargado = cliente.retrieveFile(nomFich, os);
                os.close();

            } catch (IOException e) {
               // Util.msjError("No ha sido posible encontrar la carpeta: "+destino);
                System.out.println("No ha sido posible encontrar la carpeta: "+destino);
                e.printStackTrace();
            }
        }

        return fichDescargado;      
    }

    /**
     * Borra un fichero ZIP de la ubicación actual del servidor.
     * @param nomFich Nombre del fichero ZIP a borrar.
     * @return True, si se ha borrado del servidor.<br>
     * False, en caso contrario.
     */
    public  boolean borrarFichero(String nomFich){
        boolean borrado = false;
     //   String extZIP = ".ZIP";// El fichero de extensión que tu desees.

        try {
       //     String extension = nomFich.substring(nomFich.length() - 4, nomFich.length());
         //   extension = extension.toUpperCase();
           // if (extension.compareTo(extZIP) == 0) {
                // Borramos el zip.
                borrado = cliente.deleteFile(nomFich);
           // }           

        } catch (IOException e) {
            e.printStackTrace();
        }
        return borrado;
    }
    
     public  long Peso(String nomFich){
        long peso=0;
     //   String extZIP = ".ZIP";// El fichero de extensión que tu desees.

        try {
       //     String extension = nomFich.substring(nomFich.length() - 4, nomFich.length());
         //   extension = extension.toUpperCase();
           // if (extension.compareTo(extZIP) == 0) {
                // Borramos el zip.
                FTPFile[] files = cliente.listFiles(nomFich);
                for (FTPFile file : files) {
				if (file.isFile()) {
                                        peso = file.getSize();
					
				}
			}
           // }           

        } catch (IOException e) {
            e.printStackTrace();
        }
        return peso;
    }
     
    public boolean DescargarFichero(String ruta,String nombre){
       
        
        OutputStream os;
        boolean fichDescargado = false;
        //String destino ="D:\\Fuentes_Portal\\PLXPlanning\\src\\main\\webapp\\archivos";
        String destino = "../docroot/booking/";
        // Asegurarse que es ".ZIP".
        if(nombre.length() < 4){
            return false;
        }       
        /*String extension = nomFich.substring(nomFich.length() - 4, nomFich.length());
        extension = extension.toUpperCase();*/

        
            try {
                // Descargar el ZIP. 
                os = new BufferedOutputStream(new FileOutputStream(destino +"/"+ nombre));
                fichDescargado = cliente.retrieveFile(ruta + nombre, os);
                os.close();

            } catch (IOException e) {
               // Util.msjError("No ha sido posible encontrar la carpeta: "+destino);
                System.out.println("No ha sido posible encontrar la carpeta: "+destino);
                e.printStackTrace();
            }
       

        return fichDescargado; 
    }

    /**
     * Sube un fichero al servidor en la ubicación actual.
     * @param pathFich Dirección del fichero, incluido el fichero. Ejm: /hola/queHay/pepe.txt
     * @param fich Nombre del fichero y su extensión. Ejm: pepe.txt
     * @return True, si ha subido de forma satisfactoria el fichero.<br>
     * False, en caso contrario.
     */
    public  boolean subirFichero(String pathFich, String fich){
        InputStream is;
        boolean fichSubido = false;
        try {           
            // Capturar el fichero de su ruta.
           
            is = new BufferedInputStream(new FileInputStream(pathFich));

            // Subir el fichero en sí.
            fichSubido = cliente.storeFile(fich, is); 
            
            is.close();

        } catch (IOException e) {
            System.out.println("Error: "+e.getMessage());
        }

        return fichSubido;
    }


    // Getters y setters:
    /**
     * Nos dice en que ubicación del servidor estamos actualmente.
     * @return Un String que contiene el path de ubicación actual.
     */
    public  String dameDirActual() {
        return dirActual;
    }
    
   
}
