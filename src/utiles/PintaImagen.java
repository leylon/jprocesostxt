/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utiles;


import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

/**
 *
 * @author aechavarria
 * @fecha Viernes, 02 de Octubre del 2009
 */
public class PintaImagen implements Border{

    private   BufferedImage image ;

    public PintaImagen(){
    }
    public PintaImagen(BufferedImage image ) {
        this.image=image;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        int x0 = x+ (width-image.getWidth())/2;
        int y0 = y+ (height-image.getHeight())/2;
        g.drawImage(image,x0,y0,null);
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(0,0,0,0);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public  void cargarImagen(javax.swing.JDesktopPane jDeskp,File fileImagen)
    {
        try{
            BufferedImage imageBuf = ImageIO.read(fileImagen);
            jDeskp.setBorder((Border) new PintaImagen(imageBuf));
        }
        catch (Exception e)
        {
            System.out.println("No se cargó la Imagen..");
        }
    }
}
