/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

/**
 *
 * @author Alex Echavarria
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.regex.Pattern;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.rtf.RTFEditorKit;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;


public class Funciones {


    public static int envioCorreo(Connection sqlca, String de, String para, String cc, String bcc, String sub, String msj){
        CallableStatement sp = null;
        try {
            sp = sqlca.prepareCall("{call F_SEND_MAIL(?,?,?,?,?,?) }");
            sp.setString(1, de);
            sp.setString(2, para);
            sp.setString(3, cc);
            sp.setString(4, bcc);
            sp.setString(5, sub);
            sp.setString(6, msj);
            sp.execute();
            sqlca.commit();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
    
    
    public static String rtftohtml(String cadena){
        String s;
        s=cadena;
        RTFEditorKit rtfEditorKit = new RTFEditorKit();
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        Document doc = rtfEditorKit.createDefaultDocument();
        StringWriter writer = new StringWriter();
        try {
            StringReader sr1=new StringReader(s);
            rtfEditorKit.read(sr1, doc, 0);
            htmlEditorKit.write(writer, doc, 0, doc.getLength());
            return writer.toString();
        }
        catch (Exception ex){
            //Logger.getLogger(Rtf2Html.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return "";
     }

public static String validaFecha(String ls_fecha){
        //"09/03/2011 46:82:02";
        String time=ls_fecha, hora=null, min=null, seg=null, date=null;
        boolean lbo_cambio=false;
        int li_pos;
        li_pos=time.indexOf(" ",1);
        date=time.substring(0,li_pos);
        //System.out.println("Fecha:" + date);
        li_pos++;
        if (li_pos>0) {
            time=time.substring(li_pos,li_pos + 8);
            //System.out.println(time);
        }

        hora=time.substring(0,2);
        //System.out.println("HORA 1: " +hora);
        if (Integer.parseInt(hora)>24) {
            hora="1"+hora.substring(1);
            lbo_cambio=true;
            //System.out.println("HORA 2: " + hora);
        }
        min=time.substring(3,5);
        //System.out.println("MIN 1: " + min);
        if (Integer.parseInt(min)>59) {
            min="31";
            lbo_cambio=true;
            //System.out.println("MIN 2: " + min);
        }
        seg=time.substring(6, 8);
        //System.out.println("SEG 1: " + seg);
        if (Integer.parseInt(seg)>59) {
            seg="55";
            lbo_cambio=true;
            //System.out.println("SEG 2:" + seg);
        }

        //System.out.println("Fecha Nueva: " +date+" "+hora+":"+min+":"+seg);
        if (lbo_cambio=true) {
            return date+" "+hora+":"+min+":"+seg;
        }else{
            return ls_fecha;
        }

    }

    public static boolean verificarProceso (String nombreProceso) {
        String ls_resultado = ejecutarComando("ps -aux",false);
        if (ls_resultado.indexOf(nombreProceso)>0){
            return true;
        }else{
            return false;
        }
    }


    public static String ejecutarComando ( String command, boolean flagbackground)
    {
        // Definimos la cadena del interprete de comandos del sistema
        String commandShell=null;
        String resultado="";
        // Recuperamos el sistema operativo
        String osName = System.getProperty ( "os.name" );
        //System.out.println("Sistema Operativo:" + osName);

        // Cargamos la cadena del interprete de comandos seg�n el sistema operativo y el comando a ejecutar
        if ( osName.equals ("Windows XP") )
            commandShell = "cmd.exe /C " + command;
        else if ( osName.equals ("Windows 95") || osName.equals ("Windows 98") )
            commandShell = "start " + command;
        else
        {
        // En UNIX y LINUX podemos lanzar el proceso en background sufijandolo con &
        if (flagbackground)
            commandShell = "" + command +" &" ;
        else
            commandShell = "" + command ;
        }
        System.out.println("Comando: " + commandShell);
        // Lanzamos el proceso
        try{
            Process proc = Runtime.getRuntime().exec (commandShell);
            BufferedReader brStdOut = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            BufferedReader brStdErr = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            int i=0;
            String str=null;
            while ((str = brStdOut.readLine())!=null) {
                i++;
                if (i==1){
                    resultado = str;
                }else{
                    resultado = resultado +"\n"+ str;
                }
               System.out.println ("ADENTROOO>> " + str);
            }
            brStdOut.close();
            brStdErr.close();
        }catch (IOException eproc){
            System.out.println ("Error al ejecutar el comando : "+eproc.getMessage());
            eproc.printStackTrace();
            return " ";
        }
        return resultado;
    }


    /*
     *Retorna la linea del proceso que esta ejecutandose indicado como parametro
     * command = ps -aux  (Linux)
     * proceso = nombre del Proceso (JManifiesto, JNotificaciones, etc.)
     */
    public static String lineaProceso ( String command, boolean flagbackground, String proceso)
    {
        // Definimos la cadena del interprete de comandos del sistema
        String commandShell=null;
        String resultado="";
        // Recuperamos el sistema operativo
        String osName = System.getProperty ( "os.name" );
        //System.out.println("Sistema Operativo:" + osName);

        // Cargamos la cadena del interprete de comandos seg�n el sistema operativo y el comando a ejecutar
        if ( osName.equals ("Windows XP") )
            commandShell = "cmd.exe /C " + command;
        else if ( osName.equals ("Windows 95") || osName.equals ("Windows 98") )
            commandShell = "start " + command;
        else
        {
        // En UNIX y LINUX podemos lanzar el proceso en background sufijandolo con &
        if (flagbackground)
            commandShell = "" + command +" &" ;
        else
            commandShell = "" + command ;
        }

        // Lanzamos el proceso
        try{
            Process proc = Runtime.getRuntime().exec (commandShell);
            BufferedReader brStdOut = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            BufferedReader brStdErr = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            int i=0;
            String str=null;
            while ((str = brStdOut.readLine())!=null) {
                if(str.indexOf(proceso.trim())>0){
                    i++;
                    if (i==1){
                        resultado = str;
                    }else{
                        resultado = resultado +"\n"+ str;
                    }
                }
               //System.out.println (str);
            }
            brStdOut.close();
            brStdErr.close();
        }catch (IOException eproc){
            System.out.println ("Error al ejecutar el comando : "+eproc.getMessage());
            eproc.printStackTrace();
            return " ";
        }
        return resultado;
    }



    public static String getResponseGet(String _url) {
        String resultado = null;
        try
        {
             //URL url = new URL("http://www.aduanet.gob.pe/ol-ad-ao/LevanteDuaServlet?nume_corre=002624&codi_aduan=118&ano_prese=2009&codi_regi=10&tipo_doc=01&digi_veri=&nume_sufi=00&Prov=1");
            URL url = new URL(_url);
            //System.out.println("URL : "+url);
            URLConnection conexion = url.openConnection();
            conexion.setRequestProperty("Content-Type","xml/text");
            conexion.setDoOutput(true);
            conexion.setDoInput(true);
            //System.out.println(conexion.getContent());
            BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            String line;
            int i = 0;
            while ((line = in.readLine()) != null && i<=200) {
                resultado = resultado + line;
                i++;
                //System.out.println(line);
            }
        }catch(UnknownHostException e){
            System.out.println("Error la pagina no respondio:"+e.getMessage());
            //e.printStackTrace();
        }catch(MalformedURLException e){
            System.out.println("Error URL no reconocido:"+e.getMessage());
            //e.printStackTrace();
        }catch(IOException e){
            System.out.println("Error IO de la pagina:"+e.getMessage());
            //e.printStackTrace();
        }

        return resultado;
    }

    public static String getResponseGetFull(String _url) {
        String resultado = null;
        try
        {
            //Calendar calendario = new GregorianCalendar();
             //URL url = new URL("http://www.aduanet.gob.pe/ol-ad-ao/LevanteDuaServlet?nume_corre=002624&codi_aduan=118&ano_prese=2009&codi_regi=10&tipo_doc=01&digi_veri=&nume_sufi=00&Prov=1");
            URL url = new URL(_url);
            //System.out.println("URL : "+url);
            URLConnection conexion = url.openConnection();
            conexion.setRequestProperty("Content-Type","xml/text");
            conexion.setDoOutput(true);
            conexion.setDoInput(true);
            //System.out.println(conexion.getContent());
            BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            String line;
            int i = 0;
            while ((line = in.readLine()) != null && i<=999999) { //i<)200
                //System.out.println(i + "->" + line);
                resultado = resultado + line;
                i++;
                //System.out.println(line);
            }
            //Calendar calendario2 = new GregorianCalendar();
            //long diff = 0;
            //diff = calendario2.getTimeInMillis() - calendario.getTimeInMillis();
            //Calendar diferencia = new GregorianCalendar();
            //diferencia.setTimeInMillis(diff);
            //System.out.println("Time: " + diferencia.get(Calendar.MINUTE) + "min. " + diferencia.get(Calendar.SECOND) + "sec. " + diferencia.get(Calendar.MILLISECOND) + "millisec."); 
        }catch(UnknownHostException e){
            System.out.println("Error la pagina no respondio:"+e.getMessage());
            //e.printStackTrace();
        }catch(MalformedURLException e){
            System.out.println("Error URL no reconocido:"+e.getMessage());
            //e.printStackTrace();
        }catch(IOException e){
            System.out.println("Error IO de la pagina:"+e.getMessage());
            //e.printStackTrace();
        }

        return resultado;
    }

    public static String getResponsePOST (URL _url, String _request)throws Exception{
        HttpURLConnection c = null;
        InputStream is = null;
        OutputStream os = null;
        // b is to collect the repsonse from the server
        StringBuffer b = new StringBuffer( );

        // open the connection
        c = (HttpURLConnection)_url.openConnection();
        // set the http headers
        c.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        c.setRequestProperty("Content-Length", "" + Integer.toString(_request.getBytes().length) );
        c.setDoOutput(true);
        c.setDoInput(true);
        //  set the method (i.e. GET or POST)
        c.setRequestMethod("POST");

        // get the output stream - this is for writing the post data to
        os = c.getOutputStream();
        // write the data
        os.write(_request.getBytes());
        os.flush();
        os.close();
        // Get HTTP response
        is = c.getInputStream( );

        System.out.println("Response code = "+ c.getResponseCode());
        int ch;
        while ((ch = is.read( )) != -1){
          b.append((char) ch);
        }
        is.close();
        c.disconnect();
        // b contains the data returned from the server
        return(b.toString());

    }

    public static String fechaYMD(String fecha){
        //Convertir dd/mm/yyyy a yyyy-mm-dd
        String anio, mes, dia;
        anio = fecha.substring(6, 10);
        mes = fecha.substring(3, 5);
        dia = fecha.substring(0, 2);

        return anio+"-"+mes+"-"+dia ;
    }
/*
    public static String encriptar(String contrasena){
        MessageDigest mD = null;
        try
        {   mD = MessageDigest.getInstance("SHA"); }
        catch(NoSuchAlgorithmException e)
        {   System.out.println(e.getMessage());    }

        try
        { mD.update(contrasena.getBytes("UTF-8")); }
        catch(UnsupportedEncodingException e)
        { System.out.println(e.getMessage());    }

        byte raw[] = mD.digest();
        String hash = (new BASE64Encoder()).encode(raw);
        return hash;
    }

    private static byte[] getBytes( String str )
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        StringTokenizer st = new StringTokenizer( str, "-", false );
        while( st.hasMoreTokens() )
        {
            int i = Integer.parseInt( st.nextToken() );
            bos.write( ( byte )i );
        }
        return bos.toByteArray();
    }
*/

    public static String getRutaImagenes(){
        ResourceBundle resource = ResourceBundle.getBundle("com.aisj.siscajabancos.vista.parametros");
        String rutaImagenes = resource.getString("rutaImagenes");
        return rutaImagenes;
    }

    /*
    public static void aplicaDynamicProperties(JTable jTable, ArrayList al_List) throws Exception{
        ArrayList dynProp=new ArrayList();
        TableColumnModel tColMod = jTable.getColumnModel();
        JTableHeader jTH = jTable.getTableHeader();
        ConsultaDinamica cD = null;
        try {
            dynProp = al_List;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }
        if(dynProp!=null){
            for(int i=0; i<dynProp.size();i++){
                ConsultaDinamicaDet cDD;
                cDD = (ConsultaDinamicaDet)dynProp.get(i);
                if(i==0){cD=cDD.getConsultaDinamica();}
                tColMod.getColumn(i).setPreferredWidth(cDD.getAncho());
            }
            jTH.setBackground(new Color(cD.getColorFondoCab()));
            jTH.setForeground(new Color(cD.getColorTextoCab()));
        }
    }
*/
    public static boolean isNumeric(String cadena){
	try {
            Integer.parseInt(cadena);
            return true;
	} catch (NumberFormatException nfe){
            return false;
	}
    }

    public static boolean isFloat(String cadena){
	try {
            Float.parseFloat(cadena);
            return true;
	} catch (NumberFormatException nfe){
            return false;
	}
    }
    
    public static boolean isDate(CharSequence date) {

        // Expresion regular
        String time = "(\\s(([01]?\\d)|(2[0123]))[:](([012345]\\d)|(60))[:](([012345]\\d)|(60)))?"; // with a space before, zero or one time

        // no check for leap years (Schaltjahr)
        // and 31.02.2006 will also be correct
        String day = "(([12]\\d)|(3[01])|(0?[1-9]))"; // 01 up to 31
        String month = "((1[012])|(0\\d))"; // 01 up to 12
        String year = "\\d{4}";

        // Definimos todos los formatos de fecha
        ArrayList patterns = new ArrayList();
        patterns.add(Pattern.compile(day + "[-.]" + month + "[-.]" + year + time));
        patterns.add(Pattern.compile(year + "-" + month + "-" + day + time));
        patterns.add(Pattern.compile(day + "/" + month + "/" + year + time));
        // here you can add more date formats if you want

        // check dates
        for (int i = 0; i < patterns.size(); i++) {
            if ( ((Pattern)patterns.get(i)).matcher(date).matches() ){
                return true;
            }
        }
        /*for (Pattern p : patterns){
            if (p.matcher(date).matches())
            return true;
        }*/
        return false;

    }
    
    
    /*
    //Este metodo nos devuelve el correlativo para un nuevo registro------------
    public static String getCorrelativo(Empresa emp, Libro lib, int anio, int mes) throws Exception{
        String nrocom=null, codigo=null;
        try {
            //--Debemos obtener el correlativo si es Nuevo----------------------
            LibrosIndices lInd = new LibrosIndices();
            lInd=LibrosIndicesLogic.buscarLibroIndice(emp, lib, anio, mes);
            nrocom=rellenaString(String.valueOf(Integer.parseInt(lInd.getNrocom())+1),4,'0');
            codigo=emp.getCodEmpresa()+String.valueOf(lib.getCodLibro())+String.valueOf(anio)+rellenaString(String.valueOf(mes),2,'0')+nrocom;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        return codigo;
    }

    public static String getCorrelativoVentas(PuntoVenta pV, TiposDocumento tD) throws Exception{
        PuntoVentaCorrelativos pVC;
        int iD;
        try {
            pVC = PuntoVentaCorrelativosLogic.buscarPtoVtaCorrelativo(pV, tD);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        iD = pVC.getNumero();
        iD++;
        return rellenaString(String.valueOf(iD),7,'0');
    }

    public static void aumentaCorrelativo(Empresa emp, Libro lib, int anio, int mes) throws Exception{
        String codigo=getCorrelativo(emp, lib, anio, mes);
        LibrosIndices libInd = new LibrosIndices();
            libInd.setmEmpresa(emp);
            libInd.setmLibro(lib);
            libInd.setAnio(anio);
            libInd.setMes(mes);
            libInd.setNrocom(codigo.substring(10));
        LibrosIndicesLogic.actualizarLibroIndice(libInd);
    }

    public static void aumentaCorrelativoVenta(PuntoVenta pV, TiposDocumento tD) throws Exception{
        int iD = Integer.parseInt(getCorrelativoVentas(pV, tD));
        PuntoVentaCorrelativos pVC = new PuntoVentaCorrelativos();
            pVC.setmPuntoVenta(pV);
            pVC.setmTipDoc(tD);
            pVC.setNumero(iD);
        PuntoVentaCorrelativosLogic.actualizarPtoVtaCorrelativo(pVC);
    }
*/
    public static String rellenaString(String cadena, int largo, char eq){
        int i=0, j=cadena.length();
        while(i < largo - j){
            cadena=String.valueOf(eq)+cadena;
            i++;
        }
        return cadena;
    }

    public static String genLog(String logfile, String incidencia) throws IOException {
        Date fecha=new Date();
        Logger log = Logger.getLogger(Funciones.class);
        SimpleDateFormat formato = new SimpleDateFormat("dd.MM.yyyy");
        String fechaAc = formato.format(fecha);
        //System.out.println(fechaAc);
        PatternLayout defaultLayout = new PatternLayout("%p %c,line %L,%d{dd.MM.yyyy/HH:mm:ss},%m%n");
        RollingFileAppender rollingFileAppender = new RollingFileAppender();
        rollingFileAppender.setFile(logfile, true, false, 0);
        //rollingFileAppender.setMaxFileSize("10MB");
        //rollingFileAppender.setMaxBackupIndex(5);
        rollingFileAppender.setLayout(defaultLayout);

        log.removeAllAppenders();
        log.addAppender(rollingFileAppender);
        log.setAdditivity(false);

        log.info(incidencia);

        return incidencia;

    }
    
    
    public static String valorConfig(String archivo, String key){
        
        /*
         * Fecha: 29.03.2012 - Alex Echavarria
         * archivo: Nombre del archivo de configuracion completo, ejemplo: sql_orden.cfg
         * key: Nombre de la propiedad a consultar
         */
    
        String so = System.getProperty("os.name");
        String pathApp = System.getProperty("user.dir");
        String separador;
        if (so.indexOf("Window")>=0){
            separador = "\\";
        }else{
            separador = "/";
        }
        //System.out.println("Ruta Aplicacion: " + pathApp);
        
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(pathApp + separador + archivo));
        } catch (IOException e) {
            try {
                System.out.println(Funciones.genLog(pathApp + separador + "log_error.aep","Error: No se encontr� el archivo <sql_manifiesto.cfg> ** Valores: sql, archivo_log"));
                System.out.println(Funciones.genLog(pathApp + separador + "log_error.aep","Error: " + e.getMessage()));
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return properties.getProperty(key);
    }

    public static String colorWeb(String color){
        String colorWeb;
        colorWeb = color.substring(4) + color.substring(2,4) + color.substring(0,2);
        return colorWeb;
    }

}
