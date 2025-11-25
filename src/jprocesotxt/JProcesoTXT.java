/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jprocesotxt;

import utiles.GestionArchivos;

/**
 *
 * @author Andy Villafana
 */
public class JProcesoTXT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
       // TODO code application logic here
        String env_bd = "";
        if (args.length > 0) {
            int i = 0;
            env_bd = args[0];
            do {
                /*Empieza proceso de FacturaciÃ³n ElectrÃ³nica*/
                ProcesaConsultas.ejecutar(env_bd); // Generar Archivo TXT y enviado 
                System.out.print("Espere un momento........");
                Thread.sleep(10000);
                //System.out.print("Verificando Errores........"); 
                //ProcesaArchivoLog.ejecutar("");  /**/ //Termina proceso de FacturaciÃ³n ElectrÃ³nica   
                // Verificar si el envÃ­o es OK o existe errores.

                i++;
                //  System.out.println("minutos = " +i);
            } while (i < 6000);
        } else {
            System.out.print("¡No se especificado el parametro de base datos!");
        }
       //ProcesaRetencion.ejecutar("08");
       //GestionArchivos archivo = new GestionArchivos();ppp
       //archivo.descargarFTP("/facturacion/facturacion/","080000101FL0200138871_ANULACIONES DE BOLETAS.pdf");ppp
      
         
    }
    
}
