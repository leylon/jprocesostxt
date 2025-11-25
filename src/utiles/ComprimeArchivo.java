/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author aechevarria
 */
public class ComprimeArchivo {

    
    
    public static void comprimir(String source, String name) throws FileNotFoundException, IOException
    {
        // Creamos un arreglo con lo documentos que estar�n dentro del archivo zip. En este caso
        // es importante que se especifique la ruta en la que se localiza el documento a comprimir.
        //String[] 
        //source = new String[]{"/home/asghold/Escritorio/wbuilder.odt"};

        // Crear un buffer para la lectura de los archivos
        byte[] buf = new byte[1024];

        try {
           
                ZipOutputStream out = new ZipOutputStream(new FileOutputStream(name));
                FileInputStream in = new FileInputStream(source);

                // Agregar el archivo Zip creado al proyecto.
                out.putNextEntry(new ZipEntry(source));

                // mover Bytes al archivo Zip
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }

                // cierra el documento.
                out.closeEntry();
                in.close();

                // cierra el archivo Zip.
                out.close();
            //}
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}