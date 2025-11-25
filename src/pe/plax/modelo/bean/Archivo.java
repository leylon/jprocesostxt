/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.plax.modelo.bean;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;
import java.util.ResourceBundle;

import utiles.SFTPConnector;
import com.jcraft.jsch.Channel; //gchavez 15.06.23
import com.jcraft.jsch.ChannelSftp; //gchavez 15.06.23
import com.jcraft.jsch.JSch; //gchavez 15.06.23
import com.jcraft.jsch.Session; //gchavez 15.06.23
import com.jcraft.jsch.SftpATTRS;//gchavez 15.06.23
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
/**
 *
 * @author Andy Villafana 01
 */
public class Archivo {
  
    //private Comprobante YComprobante=null;
    private List<String> arreglo;
    private String ind_txt;
    private String nombre;
    private File xfile;
    private String env_bd;

    public String getEnv_bd() {
        return env_bd;
    }

    public void setEnv_bd(String env_bd) {
        this.env_bd = env_bd;
    }

    public String getInd_txt() {
        return ind_txt;
    }

    public void setInd_txt(String ind_txt) {
        this.ind_txt = ind_txt;
    }

    public void setArreglo(List<String> arreglo) {
        this.arreglo = arreglo;
    }
    
    public void EscribirTema(String titulo){
       arreglo.add(titulo);     
    }
    
    
    
    public void ValidarSeteoDato(String nomcolumna,String valor){
        
        if (valor==null || valor=="null"){
            //
        }else{
           if ("".equals(valor)) { 
               //
           }
           else{
               
              //valor=(valor.getBytes("ISO-8859-1"),"UTF-8"); 
               
               
             arreglo.add(nomcolumna+"="+valor);   
           }
            
        }
        
    }
      
    
    
     public void ValidarSeteoDecimal(String nomcolumna,String valor,String valor_ant){
                       
        
        if (valor==null){
            //
        }else{
           if ("0.0".equals(valor)) { 
               
               if("0.0".equals(valor_ant)){
                 //  
               }
               else{
                 arreglo.add(nomcolumna+"="+valor);  
               }
               
               
           }
           else{
                           
             arreglo.add(nomcolumna+"="+valor);   
           }
            
        }
        
    }
     
     public void ValidarSeteoDatoRet(String tipo,String nomcolumna,String item,String valor){
        
        if ("0".equals(valor)){
            //
        }else{
           if ("".equals(valor)) { 
               //
           }
           else{
               
              //valor=(valor.getBytes("ISO-8859-1"),"UTF-8"); 
               
               
             arreglo.add(tipo + ";"+nomcolumna+";"+item+";"+valor);   
           }
            
        }
        
    }
    
    /*GCHAVEZ 09.12.2021*/
      public void EscribirBloqueCabeceraAnu(Comprobante Ycomprobante) {
                      
        this.ValidarSeteoDato("CodEncoding",Ycomprobante.getOut_codencoding());
        this.ValidarSeteoDato("IDComunicacion",Ycomprobante.getOut_idcomunicacion());
        this.ValidarSeteoDato("FechaEmision",Ycomprobante.getOut_fechaemision());
        this.ValidarSeteoDato("FechaEmisionDocs",Ycomprobante.getOut_fchemis());
        
        this.ValidarSeteoDato("ERazonSocial",Ycomprobante.getOut_rznsocemis());
        this.ValidarSeteoDato("ENumeroRUC",Ycomprobante.getOut_rutemis());
        
        this.ValidarSeteoDato("NumLin",Ycomprobante.getOut_numlin());
        this.ValidarSeteoDato("CodDoc",Ycomprobante.getOut_tipodte());
        this.ValidarSeteoDato("SerieDoc",Ycomprobante.getIn_serie_doc());
        this.ValidarSeteoDato("Secuencial",Ycomprobante.getOut_secuencial());
        this.ValidarSeteoDato("MotivoBaja",Ycomprobante.getOut_motivobaja());

    }  
        
    /*FIN*/
   public void EscribirBloqueCabecera(Comprobante Ycomprobante) {
                      
        this.ValidarSeteoDato("CodEncoding",Ycomprobante.getOut_codencoding());
        this.ValidarSeteoDato("CodDoc",Ycomprobante.getOut_tipodte());
        if (Ycomprobante.getOut_tipodte().equals("07")){       
            this.ValidarSeteoDato("TipoNotaCre",Ycomprobante.getOut_tiponotacredito());
           
            }
             else
             {
             this.ValidarSeteoDato("TipoNotaDeb",Ycomprobante.getOut_tiponotacredito());
            }
        this.ValidarSeteoDato("NumDoc",Ycomprobante.getOut_secuenciadoc());
        this.ValidarSeteoDato("CodDocModificado",Ycomprobante.getOut_tpodocref());
        this.ValidarSeteoDato("NumDocModificado",Ycomprobante.getOut_secuenciadocref()); //CAMBIAR BA
        this.ValidarSeteoDato("MotivoModificacion",Ycomprobante.getOut_sustento());
        this.ValidarSeteoDato("CodMoneda",Ycomprobante.getOut_tipomoneda());
        this.ValidarSeteoDato("FechaEmision",Ycomprobante.getOut_fchemis());
        this.ValidarSeteoDato("TipoOperacion",Ycomprobante.getTipooperacion());
        this.ValidarSeteoDato("FormaPago",Ycomprobante.getOut_formapago());
        this.ValidarSeteoDecimal("PendientePago",String.valueOf(Ycomprobante.getOut_montonetopendpago()),"0.0");
    }  
   
    public void EscribirBloqueCabecera2(Comprobante Ycomprobante) {     
        
        this.ValidarSeteoDato("ERazonSocial",Ycomprobante.getOut_rznsocemis());
        this.ValidarSeteoDato("ENumeroRUC",Ycomprobante.getOut_rutemis());
        this.ValidarSeteoDato("ECodigoDirec",Ycomprobante.getCodigolocalanexo());
        this.ValidarSeteoDato("EDireccion",Ycomprobante.getOut_diremis());
        this.ValidarSeteoDato("RRazonSocial",Ycomprobante.getOut_rznsocrecep());
        this.ValidarSeteoDato("RTipoID",Ycomprobante.getOut_tiporutreceptor());
        this.ValidarSeteoDato("RNumeroID",Ycomprobante.getOut_rutrecep());
        this.ValidarSeteoDato("RDireccion",Ycomprobante.getOut_dirrecep());
        this.ValidarSeteoDato("Remail",Ycomprobante.getOut_correos());
    }  
   
    public void EscribirBloqueCabecera3(Comprobante Ycomprobante) { 
        this.ValidarSeteoDato("TotalImpuestos",String.valueOf(Ycomprobante.getOut_mnttotaligv()));
        /*Condicionamos los valores que tomara el impuesto. BAVALOS 14.04.23*/
        if (Ycomprobante.getOut_mntneto() > 0.00 && Ycomprobante.getOut_mntexe() == 0.00){

            this.ValidarSeteoDato("TipoImpuesto",String.valueOf(Ycomprobante.getOut_codigoimpuesto())); //Cargar tabla
            this.ValidarSeteoDato("BaseImponible",String.valueOf(Ycomprobante.getOut_mntneto()));
            this.ValidarSeteoDato("TotalImpuesto",String.valueOf(Ycomprobante.getOut_montoimpuesto()));
        } else {

            if (Ycomprobante.getOut_mntneto() > 0.00 && Ycomprobante.getOut_mntexe() > 0.00){

            this.ValidarSeteoDato("TipoImpuesto",String.valueOf(Ycomprobante.getOut_codigoimpuesto())); //Cargar tabla
            this.ValidarSeteoDato("BaseImponible",String.valueOf(Ycomprobante.getOut_mntneto()));
            this.ValidarSeteoDato("TotalImpuesto",String.valueOf(Ycomprobante.getOut_montoimpuesto()));
            this.ValidarSeteoDato("TipoImpuesto",String.valueOf(Ycomprobante.getOut_codigoimpuestoexe())); //BAVALOS 08.03.23
            this.ValidarSeteoDato("BaseImponible",String.valueOf(Ycomprobante.getOut_mntexe()));
            this.ValidarSeteoDato("TotalImpuesto",String.valueOf(0.00));

            } else {
                if (Ycomprobante.getOut_mntneto() > 0.00 || Ycomprobante.getOut_codigoimpuestoexe() == "9995"){

                this.ValidarSeteoDato("TipoImpuesto",String.valueOf(Ycomprobante.getOut_codigoimpuestoexe())); //BAVALOS 08.03.23
                this.ValidarSeteoDato("BaseImponible",String.valueOf(Ycomprobante.getOut_mntneto()));
                this.ValidarSeteoDato("TotalImpuesto",String.valueOf(0.00));

                } else {

                   this.ValidarSeteoDato("TipoImpuesto",String.valueOf(Ycomprobante.getOut_codigoimpuestoexe())); //BAVALOS 08.03.23
                   this.ValidarSeteoDato("BaseImponible",String.valueOf(Ycomprobante.getOut_mntexe()));
                   this.ValidarSeteoDato("TotalImpuesto",String.valueOf(0.00));
                   }
             }

        }
       /* this.ValidarSeteoDato("TotalImpuestos",String.valueOf(Ycomprobante.getOut_mnttotaligv()));
        this.ValidarSeteoDato("TipoImpuesto",String.valueOf(Ycomprobante.getOut_codigoimpuesto())); //Cargar tabla
        this.ValidarSeteoDato("BaseImponible",String.valueOf(Ycomprobante.getOut_mntneto()));
        this.ValidarSeteoDato("TotalImpuesto",String.valueOf(Ycomprobante.getOut_montoimpuesto()));*/
        this.ValidarSeteoDato("TotalLineas",String.valueOf(Ycomprobante.getOut_totallineas()));
        this.ValidarSeteoDato("TotalInicial",String.valueOf(Ycomprobante.getOut_mnttotal()));
        this.ValidarSeteoDato("TotalVenta",String.valueOf(Ycomprobante.getOut_mnttotal()));
        this.ValidarSeteoDato("OrdenCompra",Ycomprobante.getOut_nrorden());
        this.ValidarSeteoDato("Leyenda",Ycomprobante.getOut_leyendadetra());
        this.ValidarSeteoDato("CodLeyenda",Ycomprobante.getOut_codleyenda());
        this.ValidarSeteoDato("Leyenda",Ycomprobante.getOut_leyenda());
        this.ValidarSeteoDato("CodLeyenda",Ycomprobante.getOut_codigo_leyenda());
        this.ValidarSeteoDato("OtroConcepto",Ycomprobante.getOut_otroconcepto1());
        this.ValidarSeteoDato("NomOtroConcepto",Ycomprobante.getOut_nomotroconcepto1()); 
        this.ValidarSeteoDato("CodOtroConcepto",Ycomprobante.getOut_codotroconcepto1());
        this.ValidarSeteoDato("OtroConcepto",Ycomprobante.getOut_otroconcepto2());
        this.ValidarSeteoDato("NomOtroConcepto",Ycomprobante.getOut_nomotroconcepto2()); 
        this.ValidarSeteoDato("CodOtroConcepto",Ycomprobante.getOut_codotroconcepto2());
        this.ValidarSeteoDato("OtroConcepto",Ycomprobante.getOut_otroconcepto3());
        this.ValidarSeteoDato("NomOtroConcepto",Ycomprobante.getOut_nomotroconcepto3()); 
        this.ValidarSeteoDato("CodOtroConcepto",Ycomprobante.getOut_codotroconcepto3());
        this.ValidarSeteoDato("OtroConcepto",Ycomprobante.getOut_otroconcepto4());
        this.ValidarSeteoDato("NomOtroConcepto",Ycomprobante.getOut_nomotroconcepto4()); 
        this.ValidarSeteoDato("CodOtroConcepto",Ycomprobante.getOut_codotroconcepto4());
        this.ValidarSeteoDato("OtroConcepto",Ycomprobante.getOut_otroconcepto5());
        this.ValidarSeteoDato("NomOtroConcepto",Ycomprobante.getOut_nomotroconcepto5());
        this.ValidarSeteoDato("CodOtroConcepto",Ycomprobante.getOut_codotroconcepto5());
        this.ValidarSeteoDato("OtroConcepto",Ycomprobante.getOut_otroconcepto6());
        this.ValidarSeteoDato("NomOtroConcepto",Ycomprobante.getOut_nomotroconcepto6());
        this.ValidarSeteoDato("CodOtroConcepto",Ycomprobante.getOut_codotroconcepto6());
        this.ValidarSeteoDato("OtroConcepto",Ycomprobante.getOut_otroconcepto7());
        this.ValidarSeteoDato("NomOtroConcepto",Ycomprobante.getOut_nomotroconcepto7());
        this.ValidarSeteoDato("CodOtroConcepto",Ycomprobante.getOut_codotroconcepto7());
        this.ValidarSeteoDato("OtroConcepto",Ycomprobante.getOut_otroconcepto8());
        this.ValidarSeteoDato("NomOtroConcepto",Ycomprobante.getOut_nomotroconcepto8());
        this.ValidarSeteoDato("CodOtroConcepto",Ycomprobante.getOut_codotroconcepto8());
        this.ValidarSeteoDato("OtroConcepto",Ycomprobante.getOut_otroconcepto9());
        this.ValidarSeteoDato("NomOtroConcepto",Ycomprobante.getOut_nomotroconcepto9());
        this.ValidarSeteoDato("CodOtroConcepto",Ycomprobante.getOut_codotroconcepto9());
        this.ValidarSeteoDato("OtroConcepto",Ycomprobante.getOut_otroconcepto10());
        this.ValidarSeteoDato("NomOtroConcepto",Ycomprobante.getOut_nomotroconcepto10());
        this.ValidarSeteoDato("CodOtroConcepto",Ycomprobante.getOut_codotroconcepto10());
        this.ValidarSeteoDato("OtroConcepto",Ycomprobante.getOut_otroconcepto11()); /*BAVALOS 13.03.24*/
        this.ValidarSeteoDato("NomOtroConcepto",Ycomprobante.getOut_nomotroconcepto11()); /*BAVALOS 13.03.24*/
        this.ValidarSeteoDato("CodOtroConcepto",Ycomprobante.getOut_codotroconcepto11()); /*BAVALOS 13.03.24*/
        this.ValidarSeteoDato("CuentaBanco",Ycomprobante.getOut_ctadetraccion());
        this.ValidarSeteoDato("MedioPagoDetra",Ycomprobante.getOut_mediopagodetra());
        this.ValidarSeteoDato("CodProdDetraccion",Ycomprobante.getOut_valordetraccion1());
        this.ValidarSeteoDecimal("PorcentajeDetrac",String.valueOf(Ycomprobante.getOut_porcentajedetraccion1()),"0.0");
        this.ValidarSeteoDecimal("TotalDetrac",String.valueOf(Ycomprobante.getOut_mntdetraccion1()),"0.0");
           
       
    }
   
   
     /*Bloque detalle de cuotas de venta. BA 15/03/21*/
     public void EscribirBloqueCuotas(DetalleCuotas detcuotas,int item){
        //this.ValidarSeteoDato("IDPago",String.valueOf(detcuotas.getNroLinDet()));
        this.ValidarSeteoDato("IDPago",String.valueOf(detcuotas.getIdcuota()));
        this.ValidarSeteoDato("PagoVencimiento",String.valueOf(detcuotas.getMontoCuota()));
        this.ValidarSeteoDato("FechaVencimiento",detcuotas.getFechaVecCuota());
        
     }
     
     public void EscribirBloqueDetalle(DetalleComprobante Ydetcomp,int item){
         
        this.ValidarSeteoDato("NumLin",Ydetcomp.getOut_nrolindet());
        this.ValidarSeteoDato("CodProducto",Ydetcomp.getOut_vlrcodigo()); 
        this.ValidarSeteoDato("CodProdSUNAT",Ydetcomp.getCodigoproductosunat()); 
        this.ValidarSeteoDato("UMedida",Ydetcomp.getOut_unmditem());
        this.ValidarSeteoDato("NumUnidades",String.valueOf(Ydetcomp.getOut_qtyitem()));
        this.ValidarSeteoDato("Descripcion",Ydetcomp.getOut_nmbitem());
        this.ValidarSeteoDecimal("ValorUnidad",String.valueOf(Ydetcomp.getOut_prcitemsinigv()),"0.00");
        this.ValidarSeteoDecimal("PrecioVentaUnidad",String.valueOf(Ydetcomp.getOut_prcitem()),"0.00");
        this.ValidarSeteoDecimal("PrecioRefGratuito",String.valueOf(Ydetcomp.getOut_preciorefgratuito()),"0.00");
        this.ValidarSeteoDato("ValorLin",String.valueOf(Ydetcomp.getOut_valorlin()));  //cambiar variable
        this.ValidarSeteoDato("ImpuestosLin",String.valueOf(Ydetcomp.getOut_impuestoigv()));
        this.ValidarSeteoDato("BaseImpLin",String.valueOf(Ydetcomp.getOut_montoitem())); 
        this.ValidarSeteoDato("TotalImpLin",String.valueOf(Ydetcomp.getOut_impuestoigv()));
        this.ValidarSeteoDato("TipoImpLin",Ydetcomp.getOut_codigotipoigv());
        this.ValidarSeteoDato("PorcentajeLin",String.valueOf(Ydetcomp.getOut_tasaigv()));
        this.ValidarSeteoDato("IGVTipo",String.valueOf(Ydetcomp.getOut_indexe()));
        //this.ValidarSeteoDato("FinLin",String.valueOf(Ydetcomp.getOut_finlin()));

     }

     public void EscribirBloqueDetalle1(DetalleComprobante Ydetcomp,int item){

        this.ValidarSeteoDato("FinLin",String.valueOf(Ydetcomp.getOut_finlin()));
         
     }

    public void EscribirBloqueTransporteB3(DetalleTransporte dettrans,int item){
        this.ValidarSeteoDato("CodUbiSalida",String.valueOf(dettrans.getTransPuntoOrigenUbigeo()));
        this.ValidarSeteoDato("DirSalida",String.valueOf(dettrans.getTransPuntoOrigenDireccion()));
        this.ValidarSeteoDato("CodUbiEntrega",String.valueOf(dettrans.getTransPuntoDestinoUbigeo()));
        this.ValidarSeteoDato("DirEntrega",String.valueOf(dettrans.getTransPuntoDestinoDireccion()));
        this.ValidarSeteoDato("DetTranspor",String.valueOf(dettrans.getTransDetalleViaje()));
        this.ValidarSeteoDato("TipoValor",String.valueOf("01"));
        this.ValidarSeteoDato("ValorTranspor",String.valueOf(dettrans.getTransValorRefServicio()));
        this.ValidarSeteoDato("TipoValor",String.valueOf("02"));
        this.ValidarSeteoDato("ValorTranspor",String.valueOf(dettrans.getTransValorRefCargaEfectiva()));
        this.ValidarSeteoDato("TipoValor",String.valueOf("03"));
        this.ValidarSeteoDato("ValorTranspor",String.valueOf(dettrans.getTransValorRefCargaUtil()));

    }
    
    public void EscribirBloqueHidrobio(DetalleAdicionalItem hidrobio,int item){
        this.ValidarSeteoDato("CodPropiedad",String.valueOf(hidrobio.getCodConTrib()));
        this.ValidarSeteoDato("ValorPropiedad",String.valueOf(hidrobio.getValConTrib()));
        this.ValidarSeteoDato("FechIniProp",String.valueOf(hidrobio.getFechIniProp()));
        this.ValidarSeteoDato("CantConcepto",String.valueOf(hidrobio.getCantConcepto()));
        //this.ValidarSeteoDato("CantConcepto",String.valueOf(hidrobio.getCantConcepto()),"0.0");

    }

    public List<String> getArreglo() {
        return arreglo;
    }
    
    public String GetNombre(Comprobante YComprobante){
        
        
        this.nombre=YComprobante.getIn_empresa() + YComprobante.getIn_punto_venta() + YComprobante.getIn_tipo_doc(); 
        this.nombre+=YComprobante.getIn_serie_doc() + YComprobante.getIn_numero_doc();
      
           
        return nombre;
    }
    //GCHAVEZ 14.02.22 , Setear nombre del archivo del txt anulado
    public String GetNombre_Anu(Comprobante YComprobante){
        
        
        this.nombre=YComprobante.getIn_empresa() + YComprobante.getIn_punto_venta() + YComprobante.getIn_tipo_doc(); 
        this.nombre+=YComprobante.getIn_serie_doc() + YComprobante.getIn_numero_doc()+"-"+"ANU";
      
           
        return nombre;
    }
    
    
    public String GetNombreRet(CompRetencion Ycompretencion){
        this.nombre=Ycompretencion.getIN_EMPRESA() + Ycompretencion.getIN_TIPDOC() + Ycompretencion.getIN_SERDOC() + Ycompretencion.getIN_NUMDOC();
        return nombre;
    }

    public File getXfile() {
        return xfile;
    }

    public void setXfile(File xfile) {
        this.xfile = xfile;
    }
    
       
    
     public void GenerarTxt(String directorio,String archivo){
        File f;
        
        FileWriter w;
       BufferedWriter bw;
        //PrintWriter wr;
        
        try{
          
            f=new File(directorio,archivo);
                     
            w=new FileWriter(f);                     
            
            this.setXfile(f);
            
            //new FileOutputStream(f);
            /*Habilitamos el tipo de codificacion (UTF-8) para cualquier empresa. BAVALOS14.03.24*/
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
                         
            for (int i=0;i<this.getArreglo().size();i++){
            
            bw.write(this.getArreglo().get(i)); 
            bw.newLine();   
            
            }
             
               if (f.exists()){       
            System.out.print("Archivo Generado Satisfactoriamente\n");
            this.ind_txt="1";
            bw.close();
             }
             else
             {
              this.ind_txt="0";   
             }
            
            //writer.close(); 
        }catch(Exception e){
           System.out.print("Ha sucedido un error" + e);
           //this.ind_txt="2";
           this.ind_txt="0";
        }
    }
     
     
      public boolean Buscar(String ls_directorio,Comprobante Ycomprobante){
        
        boolean band; // Flag: si es 1 se encontró, si es 0 log no encontrado
        String nombre; // Nombre de Concatenación de Archivo a partir de datos del Comprobante
       
       String path =ls_directorio; // Aquí la carpeta donde queremos buscar
       nombre=Ycomprobante.getIn_empresa() + Ycomprobante.getIn_punto_venta()+Ycomprobante.getIn_tipo_doc();
       nombre+=Ycomprobante.getIn_serie_doc()+Ycomprobante.getIn_numero_doc() + ".log" ;

        String files;
        String cadena; // Contenido por línea de texto del archivo
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles(); 
        band=false;

        for (int i = 0; i < listOfFiles.length; i++)         {
               
            if (listOfFiles[i].isFile())             {
                files = listOfFiles[i].getName();
                 //System.out.println(nombre);
                 if (files.equals(nombre)){                          
                     
                 band=true; //Indica si fue encontrado
                
                  File log_encontrado = new File(path,nombre);
                     System.out.println("Verificando");
                 //Lectura del contenido del archivo log encontrado
                 try{
                       FileReader fr = new FileReader (log_encontrado);
                 
                       BufferedReader b = new BufferedReader(fr);
                       while((cadena = b.readLine())!=null) {
                        Ycomprobante.setCadena_log(cadena);
                      
                       
                  }
                       
                       b.close();
                  }catch(Exception e){
                     System.out.println(e);
      }
     

                }
            // System.out.println(String.valueOf(band));   
            
        }
        

    }
           
     return band;   
    }
      
      public String eliminar_ce(String input) {
    // Cadena de caracteres original a sustituir.
    String texto[]={"Â","Â¡","Â¢","Â£","Â¤","Â¥","Â¦","Â§","Â¨","Â©","Âª","Â«","Â","Â­","Â®","Â¯","Â°","Â±","Â²","Â³","Â´","Âµ","Â","Â·","Â¸","Â¹","Âº","Â»","Â¼","Â½","Â¾","Â¿","Ã€","Ã","Ã‚","Ãƒ","Ã„","Ã…","Ã†","Ã‡","Ãˆ","Ã‰","ÃŠ","Ã‹","ÃŒ","Ã","ÃŽ","Ã","Ã","Ã‘","Ã’","Ã“","Ã”","Ã•","Ã–","Ã—","Ã˜","Ã™","Ãš","Ã›","Ãœ","Ã","Ãž","ÃŸ","Ã","Ã¡","Ã¢","Ã£","Ã¤","Ã¥","Ã¦","Ã§","Ã¨","Ã©","Ãª","Ã«","Ã","Ã­","Ã®","Ã¯","Ã°","Ã±","Ã²","Ã³","Ã´","Ãµ","Ã","Ã·","Ã¸","Ã¹","Ãº","Ã»","Ã¼","Ã½","Ã¾","Ã¿"};
    String tes[]=  {"","¡","¢","£","¤","¥","¦","§","¨","©","ª","«","","" ,"®","¯","°","±","²","³","´","µ","","·","¸","¹","º","»","¼","½","¾","¿","À","Á","Â","Ã","Ä","Å","Æ","Ç","È","É","Ê","Ë","Ì","Í","Î","Ï","Ð","Ñ","Ò","Ó","Ô","Õ","Ö","×","Ø","Ù","Ú","Û","Ü","Ý","Þ","ß","à","á","â","ã","ä","å","æ","ç","è","é","ê","ë","ì","í","î","ï","ð","ñ","ò","ó","ô","õ","ö","÷","ø","ù","ú","û","ü","ý","þ","ÿ"} ;
      String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
    // Cadena de caracteres ASCII que reemplazarán los originales.
         // System.out.println("tamaño original: "+texto.length);
          //System.out.println("tamaño tes: "+tes.length);
    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
    String output = input;
    for (int i=0; i<texto.length; i++) {

        output = output.replace(texto[i], tes[i]);
    }//for i
    
   
    
    return output;  
}
      
/*GCHAVEZ 15.06.23, Buscar nombre del archivo en el sftp*/   
public String BuscarArchivoSFTP(String Archivo){
     String Res; 
     Res="NO";
     try {
            ResourceBundle resource = ResourceBundle.getBundle("pe.plax.modelo.dao."+env_bd);
            JSch jsch = new JSch();
            Session session = jsch.getSession(resource.getString("user.ftp.seres"),
            resource.getString("url.ftp.seres"),
            Integer.parseInt(resource.getString("port.ftp.seres")));

            session.setPassword(resource.getString("pass.ftp.seres"));
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

 

            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

 

            SftpATTRS attrs = channelSftp.lstat("/efactura/adjuntos/"+Archivo);
            if (attrs != null) {

                 Res="SI";
            } else {

                Res= "NO";
            }

 

            channelSftp.disconnect();
            session.disconnect();
        } catch (JSchException | SftpException e) {

            Res= "NO";
        }

        return Res;
    }  
/*Fin GCHAVEZ*/

    /*Proceso para cargar documentos por SFTP. BA 14/05/21*/
 public void SFTPConnection(String nom_archivo, String rutaServer) {
        System.out.println("env_bd: "+this.env_bd);
        ResourceBundle resource = ResourceBundle.getBundle("pe.plax.modelo.dao."+env_bd);
        String user = resource.getString("user.ftp.seres");
        String server = resource.getString("url.ftp.seres"); //PRODUCCION
        String pwd = resource.getString("pass.ftp.seres"); //PRODUCCION
        //String server = "62.37.231.17"; //DESARROLLO
        //String pwd = "eefjp1gA"; //DESARROLLO
        int port  = Integer.parseInt(resource.getString("port.ftp.seres"));
           
        try {
            SFTPConnector sshConnector = new SFTPConnector();
           
            sshConnector.connect(user, pwd, server, port);
            sshConnector.addFile("/envio/documento", rutaServer+nom_archivo,nom_archivo);
            sshConnector.disconnect();
            
        } catch (JSchException ex) {
            ex.printStackTrace();
             
            System.out.println(ex.getMessage());
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
             
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
             
            System.out.println(ex.getMessage());
        } catch (SftpException ex) {
            ex.printStackTrace();
             
            System.out.println(ex.getMessage());
        }
    }      

}
