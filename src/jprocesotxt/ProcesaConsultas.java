
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jprocesotxt;

//import java.io.File;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
import pe.plax.modelo.bean.Adicional;
import pe.plax.modelo.bean.Archivo;
import pe.plax.modelo.bean.Comprobante;
import pe.plax.modelo.bean.DetalleAdicionalItem;
import pe.plax.modelo.bean.DetalleComprobante;
import pe.plax.modelo.bean.DetalleTransporte;
import pe.plax.modelo.bean.DetalleCuotas;
import pe.plax.modelo.dao.Conexion;
import pe.plax.modelo.dao.PubGestionComprobantes;
import pe.plax.modelo.logic.SqlDinamicoLogic;
import utiles.Ftp;
import utiles.GestionArchivos;
import utiles.ScrollableTableModel;
//import java.io.FileWriter;
import jprocesotxt.EnvioAdjuntos; //gchavez 13.10.22

/**
 *
 * @author Andy
 */
public class ProcesaConsultas {

    private static Connection sqlca = null;
    private static ScrollableTableModel ds_Sql;
    private static ScrollableTableModel ds_correo;
    private static ScrollableTableModel ds_adjunto; // ACV 26/08/2019
    private static String lista_comprobantes;
    private static int ll_tRows;
    private static ResultSet fila;
    private static ResultSet filadet;
    private static ResultSet filatrans;
    private static ResultSet filahidrobio;
    private static ResultSet filacuotaventa;
    private static String lista_correos;
    private static ResultSet item_correo;
    private static String lista_adjuntos; // ACV 26/08/2019
    private static ResultSet fila_adjunto; // ACV 26/08/2019
    
    
    public static void ejecutar(String env_bd) {

        CallableStatement sp_cabecera = null;
        CallableStatement sp_detalle = null;
        CallableStatement sp_transporte = null;
        CallableStatement sp_embarcacion = null;
        CallableStatement sp_cuotas_venta = null;
        CallableStatement sp_contador_adjuntos = null; //gchavez 14.10.22
        Integer li_contAdjuntos=0; //gchavez 14.10.22
        String ls_zip_found=""; //GCHAVEZ 28/06/23
        String ls_zipeado=""; //GCHAVEZ 28/06/23
        Integer li_ind_adj=0; //GCHAVEZ 28/06/23
        Integer li_envio_adj=0;
        
        System.out.println("Iniciando Proceso de Generación y Envío de Archivo TXT");
        try {
            try {
                sqlca = Conexion.getConexion(env_bd);
                sqlca.setAutoCommit(false);
            } catch (Exception ex) {
                System.out.println("Error al tratar de Conectar a Firebird.." + ex.getMessage());
                throw ex;
            }

            try {
                
              /*  lista_comprobantes = "SELECT av.empresa,av.punto_venta,av.tipo_doc,av.serie_doc,av.numero_doc,av.estado";
                lista_comprobantes += " FROM ant_ventas_diarias av";
                lista_comprobantes += " inner join PUB_TABLA_PARAMETROS pt on (pt.empresa=av.empresa and pt.llave='SUITE_ELECTRONICA' and pt.item=1)";
                lista_comprobantes += " inner join PUNTO_VENTA_TD PVT on (PVT.EMPRESA = AV.EMPRESA and PVT.PUNTO_VENTA = AV.PUNTO_VENTA and";
                lista_comprobantes += " PVT.TIPO_DOCUMENTO = AV.TIPO_DOC and PVT.NRO_SERIE = AV.SERIE_DOC and PVT.TIPO_FORMATO = 'D')";
                lista_comprobantes += " join PUB_EMPRESAS PEMP ON (PEMP.CODEMP = av.empresa)";

               */
             /*GCHAVEZ 07.12.2021, Se valida que reconosca las facturas anuladas el indicador en "1" significa que aún no ha sido enviado a SERES*/
            /*    lista_comprobantes += " where ( ( ((av.ind_txt='0') or (av.ind_txt='2')) and av.estado='A' )";
                lista_comprobantes += " OR ( (av.ind_txt='6') and av.estado='X' ) )  ";
                lista_comprobantes += " AND PEMP.e_factura = 1 ";
             */

             /*   lista_comprobantes += " AND av.anio=2024";
                lista_comprobantes += " AND av.empresa IN('20', '11')";
                lista_comprobantes += " AND av.tipo_doc ='01'";
                lista_comprobantes += " AND AV.SERIE_DOC = 'F003'";
                 lista_comprobantes += " AND av.numero_doc in ('0003618')";*/

                lista_comprobantes = "-- Parte 1: Obtener ventas con estado 'T' y tipos '0' o '2'\n" +
                        "select AV.EMPRESA, AV.PUNTO_VENTA, AV.TIPO_DOC, AV.SERIE_DOC, AV.NUMERO_DOC, AV.ESTADO\n" +
                        "from ANT_VENTAS_DIARIAS AV\n" +
                        "inner join PUB_TABLA_PARAMETROS PT on (PT.EMPRESA = AV.EMPRESA and PT.LLAVE = 'SUITE_ELECTRONICA' and PT.ITEM = 1)\n" +
                        "inner join PUNTO_VENTA_TD PVT on (PVT.EMPRESA = AV.EMPRESA and PVT.PUNTO_VENTA = AV.PUNTO_VENTA and\n" +
                        " PVT.TIPO_DOCUMENTO = AV.TIPO_DOC and PVT.NRO_SERIE = AV.SERIE_DOC and PVT.TIPO_FORMATO = 'D')\n" +
                        "inner join PUB_EMPRESAS PEMP on (PEMP.CODEMP = AV.EMPRESA)\n" +
                        "where\n" +
                        "    AV.ESTADO = 'T'\n" +
                        "    and AV.IND_TXT in ('0', '2') -- Condición simple y optimizable\n" +
                        "    and PEMP.E_FACTURA = 1\n" +
                        "-- Aquí forzamos el plan, empezando por la tabla AV y usando el índice correcto\n" +
                        "PLAN JOIN (AV INDEX (ANT_VENTAS_DIARIAS_IDX10), PT INDEX(PK_PUB_TABLA_PARAMETROS), PVT INDEX(PUNTO_VENTA_TD_IDX1), PEMP INDEX(PUB_EMPRESAS_IDX1))\n" +
                        "\n" +
                        "UNION ALL\n" +
                        "\n" +
                        "-- Parte 2: Obtener ventas con estado 'X' y tipo '6'\n" +
                        "select AV.EMPRESA, AV.PUNTO_VENTA, AV.TIPO_DOC, AV.SERIE_DOC, AV.NUMERO_DOC, AV.ESTADO\n" +
                        "from ANT_VENTAS_DIARIAS AV\n" +
                        "inner join PUB_TABLA_PARAMETROS PT on (PT.EMPRESA = AV.EMPRESA and PT.LLAVE = 'SUITE_ELECTRONICA' and PT.ITEM = 1)\n" +
                        "inner join PUNTO_VENTA_TD PVT on (PVT.EMPRESA = AV.EMPRESA and PVT.PUNTO_VENTA = AV.PUNTO_VENTA and\n" +
                        "PVT.TIPO_DOCUMENTO = AV.TIPO_DOC and PVT.NRO_SERIE = AV.SERIE_DOC and PVT.TIPO_FORMATO = 'D')\n" +
                        "inner join PUB_EMPRESAS PEMP on (PEMP.CODEMP = AV.EMPRESA)\n" +
                        "where\n" +
                        "    AV.ESTADO = 'X'\n" +
                        "    and AV.IND_TXT = '6' -- Condición simple y optimizable\n" +
                        "    and PEMP.E_FACTURA = 1\n" +
                        "-- Aquí forzamos el plan, empezando por la tabla AV y usando el índice correcto\n" +
                        "PLAN JOIN (AV INDEX (ANT_VENTAS_DIARIAS_IDX10), PT INDEX(PK_PUB_TABLA_PARAMETROS), PVT INDEX(PUNTO_VENTA_TD_IDX1), PEMP INDEX(PUB_EMPRESAS_IDX1))\n";
                
                
                ds_Sql = SqlDinamicoLogic.getSTMSql(sqlca, lista_comprobantes);
                fila = ds_Sql.getResultSet();
                GestionArchivos adjunto = new GestionArchivos(); // ACV 26/08/2019
                Comprobante Xcomprobante;
                Xcomprobante = new Comprobante();
                

                
                Archivo XArchivo;
                while (fila.next()) {
                    
                    Xcomprobante.setIn_empresa(fila.getString("empresa"));
                    Xcomprobante.setIn_punto_venta(fila.getString("punto_venta"));
                    Xcomprobante.setIn_tipo_doc(fila.getString("tipo_doc"));
                    Xcomprobante.setIn_serie_doc(fila.getString("serie_doc"));
                    Xcomprobante.setIn_numero_doc(fila.getString("numero_doc"));
                    
                    /*GCHAVEZ 06.12.2021, Obtener el estado para reconocer si una factura esta anulada. */
                    Xcomprobante.set_estado(fila.getString("estado"));
                    

                    /*fin*/
                    
                    
                    //Setear Variables de Salida
                    ArrayList<String> arreglo;
                    arreglo = new ArrayList<String>();
                    XArchivo = new Archivo(); 
                    XArchivo.setArreglo(arreglo);
                    XArchivo.setEnv_bd(env_bd);
                    //GCHAVEZ 14.02.22 , Factura anulada
                    if (Xcomprobante.get_estado().equals("X")) {                
                    sp_cabecera = sqlca.prepareCall("{call SP_FACTURA_ELECTRONICA_SERES(?,?,?,?,?) }");
                    sp_cabecera.setString(1, Xcomprobante.getIn_empresa());
                    sp_cabecera.setString(2, Xcomprobante.getIn_punto_venta());
                    sp_cabecera.setString(3, Xcomprobante.getIn_tipo_doc());
                    sp_cabecera.setString(4, Xcomprobante.getIn_serie_doc());
                    sp_cabecera.setString(5, Xcomprobante.getIn_numero_doc());
                    sp_cabecera.execute();    
                    
                    Xcomprobante.setOut_codencoding(sp_cabecera.getString("codencoding"));
                    Xcomprobante.setOut_idcomunicacion(sp_cabecera.getString("ANU_IDCOMUNICACION")); 
                    Xcomprobante.setOut_fechaemision(sp_cabecera.getString("ANU_FECHAEMISION")); 
                    Xcomprobante.setOut_fchemis(sp_cabecera.getString("fchemis"));
                    Xcomprobante.setOut_rznsocemis(sp_cabecera.getString("rznsocemis"));
                    Xcomprobante.setOut_rutemis(sp_cabecera.getString("rutemis")); 
                    Xcomprobante.setOut_numlin(sp_cabecera.getString("ANU_NUMLIN"));
                    Xcomprobante.setOut_tipodte(sp_cabecera.getString("tipodte"));
                    Xcomprobante.getOut_secuencial(sp_cabecera.getString("ANU_SECUENCIAL"));
                    Xcomprobante.getOut_motivobaja(sp_cabecera.getString("ANU_MOTIVOBAJA"));
                    Xcomprobante.setOut_carpeta_envio(sp_cabecera.getString("out_carpeta_envio"));
                    sqlca.commit();
                    XArchivo.EscribirBloqueCabeceraAnu(Xcomprobante);
                    
                     if (XArchivo.GetNombre_Anu(Xcomprobante) == null) {
                        XArchivo.setInd_txt("2");
                    } else {
                       
                         
                    //GCHAVEZ 06.12.2021 , Se envía el archivo txt de la factura anulada al sftp de SERES 
                     XArchivo.GenerarTxt(Xcomprobante.getOut_carpeta_envio(), XArchivo.GetNombre_Anu(Xcomprobante) + ".txt");
                     XArchivo.SFTPConnection(XArchivo.GetNombre_Anu(Xcomprobante) + ".txt", Xcomprobante.getOut_carpeta_envio() ); //GCHAVEZ 14.02.22

       
//XArchivo.SFTPConnection("62.37.231.17", "psqrlsftp", "eefjp1gA", 2222, XArchivo.GetNombre(Xcomprobante) + ".txt", Xcomprobante.getOut_carpeta_envio() );
                             }
                        
                    } 
                    else 
                     {
                    sp_cabecera = sqlca.prepareCall("{call SP_FACTURA_ELECTRONICA_SERES(?,?,?,?,?) }");
                    sp_cabecera.setString(1, Xcomprobante.getIn_empresa());
                    sp_cabecera.setString(2, Xcomprobante.getIn_punto_venta());
                    sp_cabecera.setString(3, Xcomprobante.getIn_tipo_doc());
                    sp_cabecera.setString(4, Xcomprobante.getIn_serie_doc());
                    sp_cabecera.setString(5, Xcomprobante.getIn_numero_doc());
                    sp_cabecera.execute();

                   
                   
                    String ls_codigo_cli; // ACV 20/01/2017
                    String ls_empresa;
                    ls_empresa=Xcomprobante.getIn_empresa();

                    Xcomprobante.setOut_codencoding(sp_cabecera.getString("codencoding"));
                    Xcomprobante.setOut_tipodte(sp_cabecera.getString("tipodte"));
                    Xcomprobante.setOut_tiponotacredito(sp_cabecera.getString("tiponotacredito"));
                    Xcomprobante.setOut_secuenciadoc(sp_cabecera.getString("secuenciadoc"));
                    Xcomprobante.setOut_tpodocref(sp_cabecera.getString("tpodocref"));
                    Xcomprobante.setOut_secuenciadocref(sp_cabecera.getString("secuenciadocref"));
                    Xcomprobante.setOut_sustento(sp_cabecera.getString("sustento"));
                    Xcomprobante.setOut_tipomoneda(sp_cabecera.getString("tipomoneda"));
                    Xcomprobante.setOut_fchemis(sp_cabecera.getString("fchemis"));
                    Xcomprobante.setTipooperacion(sp_cabecera.getString("tipooperacion"));
                    Xcomprobante.setOut_formapago(sp_cabecera.getString("formapago")); 
                    Xcomprobante.setOut_montonetopendpago(sp_cabecera.getDouble("mntneto_pagar")); //BA 14/03/21
                    Xcomprobante.setOut_carpeta_envio(sp_cabecera.getString("out_carpeta_envio"));
                  /*                
                    
                    /*gchavez 14.10.22, conectarnos al sp "sp_contador_adjuntos"*/
                    //GCHAVEZ 28.06.23, Se cambio de posicion 
                    //li_contAdjuntos=0;
                    
                    ls_zipeado="";
                    li_ind_adj=0;
                    li_envio_adj=0;
                    ls_zip_found="";

                    sp_contador_adjuntos = sqlca.prepareCall("{call sp_contador_adjuntos(?,?,?,?,?) }");
                    sp_contador_adjuntos.setString(1, Xcomprobante.getIn_empresa());
                    sp_contador_adjuntos.setString(2, Xcomprobante.getIn_punto_venta());
                    sp_contador_adjuntos.setString(3, Xcomprobante.getIn_tipo_doc());
                    sp_contador_adjuntos.setString(4, Xcomprobante.getIn_serie_doc());
                    sp_contador_adjuntos.setString(5, Xcomprobante.getIn_numero_doc());
                    sp_contador_adjuntos.execute();  
                    /*gchavez 28.06.23, Se obtiene el nombre del zipeado */
                    Xcomprobante.setout_zipeado(sp_contador_adjuntos.getString("OUT_NOM_ARCHIVO_ZIPEADO"));
                    Xcomprobante.setout_ind_envio_adj(sp_contador_adjuntos.getInt("OUT_IND_ENVIO_ADJ")); //gchavez 04.07.23
                    ls_zipeado=Xcomprobante.getout_zipeado()+".zip";
                    li_ind_adj=Xcomprobante.getout_ind_envio_adj();
                    if (!".zip".equals(ls_zipeado) ) 
                    {li_contAdjuntos=1;}
                    else
                    {li_contAdjuntos=0;}
                  
                    //gchavez 13.10.22,Se realiza el envio de los adjuntos de la factura si solamente existen los adjuntos 
                    if (li_contAdjuntos>0 && li_ind_adj==0 ) 
                    {
                        EnvioAdjuntos xEnvioAdjuntos;
                        xEnvioAdjuntos=new EnvioAdjuntos();
                        xEnvioAdjuntos.setEnv_bd(env_bd);
                        li_envio_adj=xEnvioAdjuntos.SendPost(Xcomprobante.getIn_empresa(), Xcomprobante.getIn_numero_doc(),
                        Xcomprobante.getIn_punto_venta(),Xcomprobante.getIn_serie_doc(),
                        Xcomprobante.getIn_tipo_doc());

                        /*gchavez 04.07.23, Actualizar indicador si se envia adjunto*/
                        if (li_envio_adj==1)
                        {
                            PubGestionComprobantes pgc2 = new PubGestionComprobantes(sqlca);
                            pgc2.actualizar_ind_adj(Xcomprobante);
                            sqlca.commit();
                        }                    
                    }
                    
                    /*-----*/
                                     
                    ls_codigo_cli=sp_cabecera.getString("codigo_cli");  //ACV 20/01/2017  */
                    
                    Adicional Xadicional;

                    List<Adicional> lista_adi = new ArrayList<Adicional>();

                    //Setear Estructura de Texto para Cabecera de Comprobante
                    XArchivo.EscribirBloqueCabecera(Xcomprobante);
                    
                    //Inicio Setear datos de cuotas -- BA 14/03/21
                    DetalleCuotas Xdet_cuotas;
                    Xdet_cuotas = new DetalleCuotas();

                    //Ejecutar Procedimientos Almacenado para las cuotas de pago. BA 30/03/21
                    sp_cuotas_venta  = sqlca.prepareCall("{call SP_FACT_CUOTAS_VENTA(?,?,?,?,?)}");
                    
                    sp_cuotas_venta.setString(1, Xcomprobante.getIn_empresa());
                    sp_cuotas_venta.setString(2, Xcomprobante.getIn_punto_venta());
                    sp_cuotas_venta.setString(3, Xcomprobante.getIn_tipo_doc());
                    sp_cuotas_venta.setString(4, Xcomprobante.getIn_serie_doc());
                    sp_cuotas_venta.setString(5, Xcomprobante.getIn_numero_doc());
                    sp_cuotas_venta.execute();

                    filacuotaventa = sp_cuotas_venta.getResultSet();
                                 
                    while (filacuotaventa.next()) {
                        
                        Xdet_cuotas.setIdcuota(sp_cuotas_venta.getString("idcuota"));
                        Xdet_cuotas.setMontoCuota(sp_cuotas_venta.getDouble("MontoCuota"));
                        Xdet_cuotas.setFechaVecCuota(sp_cuotas_venta.getString("fechav_cuota"));
                                               
                        //Setear Estructura de Texto para Transporte
                        XArchivo.EscribirBloqueCuotas(Xdet_cuotas, filacuotaventa.getRow());
                    
                    }
                    //Fin Setear datos de cuotas de venta -- BA 14/03/21
                    
                    sp_cabecera = sqlca.prepareCall("{call SP_FACTURA_ELECTRONICA_SERES(?,?,?,?,?) }");
                    sp_cabecera.setString(1, Xcomprobante.getIn_empresa());
                    sp_cabecera.setString(2, Xcomprobante.getIn_punto_venta());
                    sp_cabecera.setString(3, Xcomprobante.getIn_tipo_doc());
                    sp_cabecera.setString(4, Xcomprobante.getIn_serie_doc());
                    sp_cabecera.setString(5, Xcomprobante.getIn_numero_doc());
                    sp_cabecera.execute();
                   
                    Xcomprobante.setOut_rznsocemis(sp_cabecera.getString("rznsocemis"));
                    Xcomprobante.setOut_rutemis(sp_cabecera.getString("rutemis"));
                    Xcomprobante.setOut_diremis(XArchivo.eliminar_ce(sp_cabecera.getString("diremis")));//direc
                    Xcomprobante.setOut_rznsocrecep(XArchivo.eliminar_ce(sp_cabecera.getString("rznsocrecep")));
                    Xcomprobante.setOut_tiporutreceptor(sp_cabecera.getString("tiporutreceptor"));
                    Xcomprobante.setOut_rutrecep(sp_cabecera.getString("rutrecep"));
                    Xcomprobante.setCodigolocalanexo(sp_cabecera.getString("codigolocalanexo"));
                    Xcomprobante.setOut_dirrecep(sp_cabecera.getString("dirrecep"));
                    Xcomprobante.setOut_correos(sp_cabecera.getString("out_correos"));
                    
                    
                    //Setear Estructura de Texto para Cabecera de Comprobante
                    XArchivo.EscribirBloqueCabecera2(Xcomprobante);
                    
                    DetalleComprobante Xdet_comp;
                    Xdet_comp = new DetalleComprobante();

                    //Ejecutar Procedimientos Almacenado Detalle
                    sp_detalle = sqlca.prepareCall("{call SP_FACT_ELECT_DET_SERES(?,?,?,?,?) }");
                    
                    sp_detalle.setString(1, Xcomprobante.getIn_empresa());
                    sp_detalle.setString(2, Xcomprobante.getIn_punto_venta());
                    sp_detalle.setString(3, Xcomprobante.getIn_tipo_doc());
                    sp_detalle.setString(4, Xcomprobante.getIn_serie_doc());
                    sp_detalle.setString(5, Xcomprobante.getIn_numero_doc());
                    sp_detalle.execute();

                    filadet = sp_detalle.getResultSet();
                                 
                    while (filadet.next()) {
                        
                        Xdet_comp.setOut_nrolindet(sp_detalle.getString("xitem"));
                        Xdet_comp.setOut_vlrcodigo(sp_detalle.getString("vlrcodigo"));
                        Xdet_comp.setCodigoproductosunat(sp_detalle.getString("codigoproductosunat"));
                        Xdet_comp.setOut_unmditem(sp_detalle.getString("unmditem"));
                        Xdet_comp.setOut_qtyitem(sp_detalle.getString("qtyitem"));
                        Xdet_comp.setOut_nmbitem(XArchivo.eliminar_ce(sp_detalle.getString("nmbitem")));
                        Xdet_comp.setOut_prcitemsinigv(sp_detalle.getDouble("prcitemsinigv"));
                        Xdet_comp.setOut_prcitem(sp_detalle.getDouble("prcitem"));
                        Xdet_comp.setOut_preciorefgratuito(sp_detalle.getDouble("preciorefgratuito"));
                        Xdet_comp.setOut_valorlin(sp_detalle.getString("valorlin")); //REVISAR
                        Xdet_comp.setOut_impuestoigv(sp_detalle.getDouble("impuestoigv"));
                        Xdet_comp.setOut_montoitem(sp_detalle.getDouble("montoitem"));
                        Xdet_comp.setOut_impuestoigv(sp_detalle.getDouble("impuestoigv"));
                        Xdet_comp.setOut_codigotipoigv(sp_detalle.getString("codigotipoigv"));
                        Xdet_comp.setOut_tasaigv(sp_detalle.getDouble("tasaigv"));
                        Xdet_comp.setOut_indexe(sp_detalle.getString("indexe"));
                        Xdet_comp.setOut_finlin(" ");
                       
                        //Setear Estructura de Texto para Detalle de Comprobante
                        XArchivo.EscribirBloqueDetalle(Xdet_comp, filadet.getRow());
                        

                            //Habilitamos datos de HIDRO dentro del detalle. BA 14.04.23
                            DetalleAdicionalItem Xdet_aditem;
                            Xdet_aditem = new DetalleAdicionalItem();

                            //Ejecutar Procedimientos Almacenado Transporte
                            sp_embarcacion  = sqlca.prepareCall("{call SP_FACT_ELECT_HIDROBIO(?,?,?,?,?,?)}");

                            sp_embarcacion.setString(1, Xcomprobante.getIn_empresa());
                            sp_embarcacion.setString(2, Xcomprobante.getIn_punto_venta());
                            sp_embarcacion.setString(3, Xcomprobante.getIn_tipo_doc());
                            sp_embarcacion.setString(4, Xcomprobante.getIn_serie_doc());
                            sp_embarcacion.setString(5, Xcomprobante.getIn_numero_doc());
                            sp_embarcacion.setString(6, sp_detalle.getString("out_item"));
                            sp_embarcacion.execute();

                            filahidrobio = sp_embarcacion.getResultSet();

                            while (filahidrobio.next()) {

                                //Xdet_aditem.setNroLinDet(sp_embarcacion.getInt(1));
                                Xdet_aditem.setCodConTrib(sp_embarcacion.getString(2));
                                //Xdet_aditem.setNomConTrib(sp_embarcacion.getString(3));
                                Xdet_aditem.setValConTrib(sp_embarcacion.getString(4));
                                Xdet_aditem.setFechIniProp(sp_embarcacion.getString(5));
                                Xdet_aditem.setCantConcepto(sp_embarcacion.getString(6));

                                //Setear Estructura de Texto para Transporte
                                XArchivo.EscribirBloqueHidrobio(Xdet_aditem, filahidrobio.getRow());

                            }
                            //Fin
                            
                            //Habilitamos datos de transporte. BAVALOS 14.04.23
                            DetalleTransporte Xdet_trans;
                            Xdet_trans = new DetalleTransporte();

                            //Ejecutar Procedimientos Almacenado Transporte
                            sp_transporte  = sqlca.prepareCall("{call SP_FACT_ELECT_TRANSP(?,?,?,?,?,?)}");

                            sp_transporte.setString(1, Xcomprobante.getIn_empresa());
                            sp_transporte.setString(2, Xcomprobante.getIn_punto_venta());
                            sp_transporte.setString(3, Xcomprobante.getIn_tipo_doc());
                            sp_transporte.setString(4, Xcomprobante.getIn_serie_doc());
                            sp_transporte.setString(5, Xcomprobante.getIn_numero_doc());
                            sp_transporte.setString(6, sp_detalle.getString("out_item"));
                            sp_transporte.execute();

                            filatrans = sp_transporte.getResultSet();


                            while (filatrans.next()) {

                                Xdet_trans.setTransPuntoOrigenUbigeo(sp_transporte.getString(3));
                                Xdet_trans.setTransPuntoOrigenDireccion(sp_transporte.getString(4));
                                Xdet_trans.setTransPuntoDestinoUbigeo(sp_transporte.getString(5));
                                Xdet_trans.setTransPuntoDestinoDireccion(sp_transporte.getString(6));
                                Xdet_trans.setTransDetalleViaje(sp_transporte.getString(7));
                                Xdet_trans.setTransValorRefServicio(Double.parseDouble(sp_transporte.getString(8)));
                                Xdet_trans.setTransValorRefCargaEfectiva(Double.parseDouble(sp_transporte.getString(9)));
                                Xdet_trans.setTransValorRefCargaUtil(Double.parseDouble(sp_transporte.getString(10)));


                                XArchivo.EscribirBloqueTransporteB3(Xdet_trans, filatrans.getRow());

                            }

                    XArchivo.EscribirBloqueDetalle1(Xdet_comp, filadet.getRow());


                    }
                    
                    sp_cabecera = sqlca.prepareCall("{call SP_FACTURA_ELECTRONICA_SERES(?,?,?,?,?) }");
                    sp_cabecera.setString(1, Xcomprobante.getIn_empresa());
                    sp_cabecera.setString(2, Xcomprobante.getIn_punto_venta());
                    sp_cabecera.setString(3, Xcomprobante.getIn_tipo_doc());
                    sp_cabecera.setString(4, Xcomprobante.getIn_serie_doc());
                    sp_cabecera.setString(5, Xcomprobante.getIn_numero_doc());
                    sp_cabecera.execute();

                   
                    Xcomprobante.setOut_mnttotaligv(sp_cabecera.getDouble("mnttotaligv")); 
                    Xcomprobante.setOut_codigoimpuesto(sp_cabecera.getString("codigoimpuesto"));
                    Xcomprobante.setOut_mntneto(sp_cabecera.getDouble("mntneto"));
                    Xcomprobante.setOut_montoimpuesto(sp_cabecera.getDouble("montoimpuesto"));
                    Xcomprobante.setOut_codigoimpuestoexe(sp_cabecera.getString("codigoimpuestoexe"));//BA 08.030.23
                    Xcomprobante.setOut_mntexe(sp_cabecera.getDouble("mntexe"));
                    //Xcomprobante.setOut_montoimpuesto(0.00);
                    Xcomprobante.setOut_totallineas(sp_cabecera.getDouble("totallineas"));
                    Xcomprobante.setOut_mnttotal(sp_cabecera.getDouble("mnttotal"));
                    Xcomprobante.setOut_mnttotal(sp_cabecera.getDouble("mnttotal"));
                    Xcomprobante.setOut_mnttotgrat(sp_cabecera.getDouble("mnttotgrat"));
                    Xcomprobante.setOut_leyendadetra(sp_cabecera.getString("leyendadetra"));
                    Xcomprobante.setOut_codleyenda(sp_cabecera.getString("codleyenda"));
                    Xcomprobante.setOut_nrorden(sp_cabecera.getString("nro_orden"));
                    Xcomprobante.setOut_leyenda(sp_cabecera.getString("out_leyenda")); //BA 270323
                    Xcomprobante.setOut_codigo_leyenda(sp_cabecera.getString("out_codigo_leyenda")); //BA 270323
                    Xcomprobante.setOut_otroconcepto1(sp_cabecera.getString("out_otroconcepto1")); 
                    Xcomprobante.setOut_nomotroconcepto1(sp_cabecera.getString("out_nomotroconcepto1"));
                    Xcomprobante.setOut_codotroconcepto1(sp_cabecera.getString("out_codotroconcepto1"));
                    Xcomprobante.setOut_otroconcepto2(sp_cabecera.getString("out_otroconcepto2"));
                    Xcomprobante.setOut_nomotroconcepto2(sp_cabecera.getString("out_nomotroconcepto2"));
                    Xcomprobante.setOut_codotroconcepto2(sp_cabecera.getString("out_codotroconcepto2"));
                    Xcomprobante.setOut_otroconcepto3(sp_cabecera.getString("out_otroconcepto3"));
                    Xcomprobante.setOut_nomotroconcepto3(sp_cabecera.getString("out_nomotroconcepto3"));
                    Xcomprobante.setOut_codotroconcepto3(sp_cabecera.getString("out_codotroconcepto3"));
                    Xcomprobante.setOut_otroconcepto4(sp_cabecera.getString("out_otroconcepto4"));
                    Xcomprobante.setOut_nomotroconcepto4(sp_cabecera.getString("out_nomotroconcepto4"));
                    Xcomprobante.setOut_codotroconcepto4(sp_cabecera.getString("out_codotroconcepto4"));
                    /*BAVALOS 270323*/
                    Xcomprobante.setOut_otroconcepto5(sp_cabecera.getString("out_otroconcepto5"));
                    Xcomprobante.setOut_nomotroconcepto5(sp_cabecera.getString("out_nomotroconcepto5"));
                    Xcomprobante.setOut_codotroconcepto5(sp_cabecera.getString("out_codotroconcepto5"));
                    Xcomprobante.setOut_otroconcepto6(sp_cabecera.getString("out_otroconcepto6"));
                    Xcomprobante.setOut_nomotroconcepto6(sp_cabecera.getString("out_nomotroconcepto6"));
                    Xcomprobante.setOut_codotroconcepto6(sp_cabecera.getString("out_codotroconcepto6"));
                    Xcomprobante.setOut_otroconcepto7(sp_cabecera.getString("out_otroconcepto7"));
                    Xcomprobante.setOut_nomotroconcepto7(sp_cabecera.getString("out_nomotroconcepto7"));
                    Xcomprobante.setOut_codotroconcepto7(sp_cabecera.getString("out_codotroconcepto7"));
                    Xcomprobante.setOut_otroconcepto8(sp_cabecera.getString("out_otroconcepto8"));
                    Xcomprobante.setOut_nomotroconcepto8(sp_cabecera.getString("out_nomotroconcepto8"));
                    Xcomprobante.setOut_codotroconcepto8(sp_cabecera.getString("out_codotroconcepto8"));
                    Xcomprobante.setOut_otroconcepto9(sp_cabecera.getString("out_otroconcepto9"));
                    Xcomprobante.setOut_nomotroconcepto9(sp_cabecera.getString("out_nomotroconcepto9"));
                    Xcomprobante.setOut_codotroconcepto9(sp_cabecera.getString("out_codotroconcepto9"));
                    Xcomprobante.setOut_otroconcepto10(sp_cabecera.getString("out_otroconcepto10"));
                    Xcomprobante.setOut_nomotroconcepto10(sp_cabecera.getString("out_nomotroconcepto10"));
                    Xcomprobante.setOut_codotroconcepto10(sp_cabecera.getString("out_codotroconcepto10")); //BA 270323
                    /*Se enviara info adicional por serie, segun lo requiera la empresa. BAVALOS 13.03.24*/
                    Xcomprobante.setOut_otroconcepto11(sp_cabecera.getString("out_otroconcepto11"));
                    Xcomprobante.setOut_nomotroconcepto11(sp_cabecera.getString("out_nomotroconcepto11")); 
                    Xcomprobante.setOut_codotroconcepto11(sp_cabecera.getString("out_codotroconcepto11"));
                    /*Fin BAVALOS*/
                    Xcomprobante.setOut_ctadetraccion(sp_cabecera.getString("var_cta_detraccion"));
                    Xcomprobante.setOut_mediopagodetra(sp_cabecera.getString("mediopagodetra"));
                    Xcomprobante.setOut_valordetraccion1(sp_cabecera.getString("valordetraccion1"));
                    Xcomprobante.setOut_porcentajedetraccion1(sp_cabecera.getDouble("porcentajedetraccion1"));
                    Xcomprobante.setOut_mntdetraccion1(sp_cabecera.getDouble("mntdetraccion1"));
                    
       
                    
                    //Setear Estructura de Texto para Cabecera de Comprobante
                    XArchivo.EscribirBloqueCabecera3(Xcomprobante);
            
                    /*gchavez 13.09.23, Valida si el archivo zipeado se encuentra en el sftp */
                //    if (li_contAdjuntos>0 &&  )
                    if (li_contAdjuntos>0 )
                    {
                     //   if (li_ind_adj==0){
                        if ("NO".equals(XArchivo.BuscarArchivoSFTP(ls_zipeado)))
                        { 
                            ls_zip_found="NO";
                        }
                    //}
                    }
                    else
                    {
                        ls_zip_found="NO";
                    }




                    if (XArchivo.GetNombre(Xcomprobante) == null) 
                    {
                        XArchivo.setInd_txt("2");
                    } else 
                    {
                    if (ls_zip_found=="NO")
                       {  
                       XArchivo.GenerarTxt(Xcomprobante.getOut_carpeta_envio(), XArchivo.GetNombre(Xcomprobante) + ".txt");
                    //   System.out.println("listo archivo txt");
                       XArchivo.SFTPConnection(XArchivo.GetNombre(Xcomprobante) + ".txt", Xcomprobante.getOut_carpeta_envio() );
                       }
                    }
    /*fin gchavez*/
    
    /*     if (XArchivo.GetNombre(Xcomprobante) == null) {
                        XArchivo.setInd_txt("2");
                    } else {
        
         XArchivo.GenerarTxt(Xcomprobante.getOut_carpeta_envio(), XArchivo.GetNombre(Xcomprobante) + ".txt");
         XArchivo.SFTPConnection(XArchivo.GetNombre(Xcomprobante) + ".txt", Xcomprobante.getOut_carpeta_envio() );
   
                            }*/
                    
                    
             }  /*GCHAVEZ 07.12.2021 , fin condición estado*/       
                    
                  
                    if ("1".equals(XArchivo.getInd_txt())) {
                        
                        
                         try {
                           //Actualizar Indicador IND_TXT='1'
                           PubGestionComprobantes pgc = new PubGestionComprobantes(sqlca);
                           
                           if (Xcomprobante.get_estado().equals("X")) {  //GCHAVEZ 06.12.2021 ,  Si el estado es anulado cambiar de evento a actualizar_anu
                           pgc.actualizar_anu(Xcomprobante);
                           }
                           else
                           {
                           if (ls_zip_found=="NO"){ /*GCHAVEZ 28/06/23*/
                           pgc.actualizar(Xcomprobante);}
                           }    
                           //Finalizar la transaccion
                        
                         sqlca.commit();
                          } catch (Exception e){
                           sqlca.rollback();
                          throw e;
                          }
                                                 
                          //System.out.println("Comprobante Generado"); 
                        
                            }                       
                  
                    else {
                        System.out.println("Error al generar archivo txt");
                        //System.out.println("No Hacer Update");
                    }

                }                    
                       
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
               // System.exit(0);
            }
        }
        

    }
   

}
