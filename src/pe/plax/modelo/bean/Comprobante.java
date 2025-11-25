/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.plax.modelo.bean;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Andy
 */
public class Comprobante {
    
    
    //Variables de Entrada
    private String in_empresa;
    private String in_punto_venta;
    private String in_tipo_doc;
    private String in_serie_doc;
    private String in_numero_doc;
    private String in_estado; /*GCHAVEZ 09.12.2021 , Nueva variable*/
    
    //Variables de Salida
    private int out_codiempr;
    private String out_serie;
    private String out_correlativo;
    private String out_rznsocemis;
    private String out_rutemis;
    private String out_diremis;
    private String out_comuemis;
    private String out_nomcomer;         
    private String out_tipodte;
    private String out_tiporutreceptor;
    private String out_rutrecep;
    private String out_rznsocrecep;
    private String out_dirrecep;
    private String out_tipomoneda ;
    private String out_codigoautorizacion ;
    private String out_sustento;
    private String out_tiponotacredito;
    private double out_mntneto;
    private double out_mntexe;
    private double out_mntexo;
    private double out_mnttotaligv;
    private double out_mnttotal;
    private double out_mnttotgrat; //ACV 25/10/2016
    private String out_fchemis;
    private int out_indservicio; // ACV 25/10/2016
    private String out_codigoimpuesto;
    private String out_codigoimpuestoexe;
    private double out_montoimpuesto;
    private double out_tasaimpuesto;
    private String out_codigoimpuesto1;
    private double out_montoimpuesto1;
    private String out_codidetraccion1;
    private String out_valordetraccion1;
    private double out_mntdetraccion1;
    private double out_porcentajedetraccion1;
    private String out_codidetraccion2;
    private String out_valordetraccion2;
    private double out_mntdetraccion2;
    private double out_porcentajedetraccion2;
    private String out_codidetraccion3;
    private String out_valordetraccion3;
    private double out_mntdetraccion3;
    private double out_porcentajedetraccion3;
    private String out_nrolinref;
    private String out_tpodocref;
    private String out_serieref;
    private String out_folioref;
    private String out_carpeta_envio;
    private String codigo_cli; // ACV 20/01/2017
    private String tipooperacion; // ACV 11/12/2017
    private String horaemision; // ACV 26/09/2018
    private String codigolocalanexo; // ACV 26/09/2018
    private String out_FechVencFact;
    private String out_formapago; //BA 17/03/21
    private double out_montonetopendpago; //BA 17/03/21
    private String out_correos;
    private String out_codencoding;
    private String out_secuenciadoc;
    private String out_secuenciadocref;
    private List<Adicional> out_adicional;
    private String out_ctadetraccion;
    //Otras Variables
    private String cadena_texto;
    private String nom_archivo;
    private String ind_txt; 
    
    
     private String cadena_log;
    private String out_codleyenda;
    private String out_leyendadetra;
    private String out_mediopagodetra;
    private double out_totallineas;
    private String out_otroconcepto1;
    private String out_leyenda; 
    private String out_codigo_leyenda;
    private String out_otroconcepto2;
    private String out_otroconcepto3;
    private String out_nomotroconcepto1;
    private String out_nomotroconcepto2;
    private String out_nomotroconcepto3;
    private String out_codotroconcepto1;
    private String out_codotroconcepto2;
    private String out_codotroconcepto3;
    private String out_nrorden;
    private String out_otroconcepto4;
    private String out_nomotroconcepto4;
    private String out_codotroconcepto4;
    private String out_idcomunicacion; //GCHAVEZ 09.12.2021 , Nueva variable de salida
    private String out_numlin; //GCHAVEZ 09.12.2021, Nueva variable de salida
    private String out_fechaemision; //GCHAVEZ 09.12.2021, Nueva variable de salida
    private String out_secuencial; //GCHAVEZ 09.12.2021, Nueva variable de salida
    private String out_motivobaja; //GCHAVEZ 09.12.2021  , Nueva variable de salida      
    private int out_contar_adjuntos; //GCHAVEZ 13.10.2022  , Nueva variable de salida  
    /*Nuevas variables para conceptos adicionales. BAVALOS 14.04.23*/
    private String out_otroconcepto5;
    private String out_nomotroconcepto5;
    private String out_codotroconcepto5;
    private String out_otroconcepto6;
    private String out_nomotroconcepto6;
    private String out_codotroconcepto6;
    private String out_otroconcepto7;
    private String out_nomotroconcepto7;
    private String out_codotroconcepto7;
    private String out_otroconcepto8;
    private String out_nomotroconcepto8;
    private String out_codotroconcepto8;
    private String out_otroconcepto9;
    private String out_nomotroconcepto9;
    private String out_codotroconcepto9;
    private String out_otroconcepto10;
    private String out_nomotroconcepto10;
    private String out_codotroconcepto10;
    private String out_otroconcepto11; /*BAVALOS 13.03.24*/
    private String out_nomotroconcepto11; /*BAVALOS 13.03.24*/
    private String out_codotroconcepto11; /*BAVALOS 13.03.24*/
    private String out_zipeado; //GCHAVEZ 15.06.2023  , Nueva variable de salida
    private int out_ind_envio_adj; //gchavez 04.07.2023
    
    
    public String getCadena_log() {
        return cadena_log;
    }

    /*GCHAVEZ 15.06.23*/
    public void setout_zipeado(String out_zipeado) {
        this.out_zipeado = out_zipeado;
    }
        public String getout_zipeado() {
        return out_zipeado;
    }
    /*Fin GCHAVEZ*/
        
    /*GCHAVEZ 04.07.23*/
    public Integer getout_ind_envio_adj() {
        return out_ind_envio_adj;
    }
    public void setout_ind_envio_adj(int out_ind_envio_adj) {
        this.out_ind_envio_adj = out_ind_envio_adj;
    }
    /*Fin GCHAVEZ*/     
        
        
    public void setCadena_log(String cadena_log) {
        if (this.cadena_log ==null){
        this.cadena_log = cadena_log;
        }
        else{
        this.cadena_log = this.cadena_log + "\n "+ cadena_log;   
        }
    }
    
    

    public List<Adicional> getOut_adicional() {
        return out_adicional;
    }

    public void setOut_adicional(List<Adicional> out_adicional) {
        this.out_adicional = out_adicional;
    }

    public String getTipooperacion() {
        return tipooperacion;
    }

    public void setTipooperacion(String tipooperacion) {
        this.tipooperacion = tipooperacion;
    }
    
    

    public String getOut_carpeta_envio() {
        return out_carpeta_envio;
    }

    public void setOut_carpeta_envio(String out_carpeta_envio) {
        this.out_carpeta_envio = out_carpeta_envio;
    }

       
    
    public void setInd_txt(String ind_txt) {
        this.ind_txt = ind_txt;
    }

    
    public String getInd_txt() {
        return ind_txt;
    }

    public String getIn_empresa() {
        return in_empresa;
    }

    public void setIn_empresa(String in_empresa) {
        this.in_empresa = in_empresa;
    }

    public String getIn_punto_venta() {
        return in_punto_venta;
    }

    public void setIn_punto_venta(String in_punto_venta) {
        this.in_punto_venta = in_punto_venta;
    }

    public String getIn_tipo_doc() {
        return in_tipo_doc;
    }

    public void setIn_tipo_doc(String in_tipo_doc) {
        this.in_tipo_doc = in_tipo_doc;
    }

    public String getIn_serie_doc() {
        return in_serie_doc;
    }

    public double getOut_mnttotgrat() {
        return out_mnttotgrat;
    }

    public void setOut_mnttotgrat(double out_mnttotgrat) {
        this.out_mnttotgrat = out_mnttotgrat;
    }

    public int getOut_indservicio() {
        return out_indservicio;
    }

    public void setOut_indservicio(int out_indservicio) {
        this.out_indservicio = out_indservicio;
    }

    public void setIn_serie_doc(String in_serie_doc) {
        this.in_serie_doc = in_serie_doc;
    }

    public String getIn_numero_doc() {
        return in_numero_doc;
    }

    public void setIn_numero_doc(String in_numero_doc) {
        this.in_numero_doc = in_numero_doc;
    }

    /*GCHAVEZ 06.12.2021 , Se agrega variable de entrada el estado para identificar el estado anulado,*/
    public String get_estado() {
        return in_estado;
    }

    public void set_estado(String in_estado) {
        this.in_estado = in_estado;
    }   
    
    public String getOut_idcomunicacion() {
        return out_idcomunicacion;
    }

    public void setOut_idcomunicacion(String out_idcomunicacion) {
        this.out_idcomunicacion = out_idcomunicacion;
    }
    
    public String getOut_fechaemision() {
        return out_fechaemision;
    }

    public void setOut_fechaemision(String out_fechaemision) {
        this.out_fechaemision = out_fechaemision;
    } 
    
    public String getOut_numlin() {
        return out_numlin;
    }

    public void setOut_numlin(String out_numlin) {
        this.out_numlin = out_numlin;
    } 
    
    public String getOut_secuencial() {
        return out_secuencial;
    }

    public void getOut_secuencial(String out_secuencial) {
        this.out_secuencial = out_secuencial;
    } 
 
    public String getOut_motivobaja() {
        return out_motivobaja;
    }

    public void getOut_motivobaja(String out_motivobaja) {
        this.out_motivobaja = out_motivobaja;
    }    
    
    /*gchavez 17.10.22*/
    public Integer getOut_contarAdjuntos() {
        return out_contar_adjuntos;
    }

    public void getOut_contarAdjuntos(int out_contar_adjuntos) {
        this.out_contar_adjuntos = out_contar_adjuntos;
    } 
    
    /*fin*/
    public String getCadenaTXT(){
        
        cadena_texto="A;Empresa;;" + this.getIn_empresa() + "\n" ;
        cadena_texto+="A;PuntoVenta;;" + this.getIn_punto_venta() + "\n" ;
        cadena_texto+="A;TipoDoc;;" + this.getIn_tipo_doc() + "\n";
        cadena_texto+="A;SerieDoc;;" + this.getIn_serie_doc() + "\n" ;
        cadena_texto+="A;NumeroDoc;;" + this.getIn_numero_doc() + "\n" ;
        
        
        return cadena_texto;
    }
    
  

    public int getOut_codiempr() {
        return out_codiempr;
    }

    public void setOut_codiempr(int out_codiempr) {
        this.out_codiempr = out_codiempr;
    }

   

    public String getOut_serie() {
        return out_serie;
    }

    public void setOut_serie(String out_serie) {
        this.out_serie = out_serie;
    }

    public String getOut_correlativo() {
        return out_correlativo;
    }

    public void setOut_correlativo(String out_correlativo) {
        this.out_correlativo = out_correlativo;
    }

    public String getOut_rznsocemis() {
        return out_rznsocemis;
    }

    public void setOut_rznsocemis(String out_rznsocemis) {
        this.out_rznsocemis = out_rznsocemis;
    }

    public String getOut_rutemis() {
        return out_rutemis;
    }

    public void setOut_rutemis(String out_rutemis) {
        this.out_rutemis = out_rutemis;
    }

    public String getOut_diremis() {
        return out_diremis;
    }

    public void setOut_diremis(String out_diremis) {
        this.out_diremis = out_diremis;
    }

    public String getOut_comuemis() {
        return out_comuemis;
    }

    public void setOut_comuemis(String out_comuemis) {
        this.out_comuemis = out_comuemis;
    }

    public String getOut_nomcomer() {
        return out_nomcomer;
    }

    public void setOut_nomcomer(String out_nomcomer) {
        this.out_nomcomer = out_nomcomer;
    }

    public String getOut_tipodte() {
        return out_tipodte;
    }

    public void setOut_tipodte(String out_tipodte) {
        this.out_tipodte = out_tipodte;
    }

    public String getOut_tiporutreceptor() {
        return out_tiporutreceptor;
    }

    public void setOut_tiporutreceptor(String out_tiporutreceptor) {
        this.out_tiporutreceptor = out_tiporutreceptor;
    }

    public String getOut_rutrecep() {
        return out_rutrecep;
    }

    public void setOut_rutrecep(String out_rutrecep) {
        this.out_rutrecep = out_rutrecep;
    }

    public String getOut_rznsocrecep() {
        return out_rznsocrecep;
    }

    public void setOut_rznsocrecep(String out_rznsocrecep) {
        this.out_rznsocrecep = out_rznsocrecep;
    }

    public String getOut_dirrecep() {
        return out_dirrecep;
    }

    public void setOut_dirrecep(String out_dirrecep) {
        this.out_dirrecep = out_dirrecep;
    }

    public String getOut_tipomoneda() {
        return out_tipomoneda;
    }

    public void setOut_tipomoneda(String out_tipomoneda) {
        this.out_tipomoneda = out_tipomoneda;
    }

    public String getOut_codigoautorizacion() {
        return out_codigoautorizacion;
    }

    public void setOut_codigoautorizacion(String out_codigoautorizacion) {
        this.out_codigoautorizacion = out_codigoautorizacion;
    }

    public String getOut_sustento() {
        return out_sustento;
    }

    public void setOut_sustento(String out_sustento) {
        this.out_sustento = out_sustento;
    }

    public String getOut_tiponotacredito() {
        return out_tiponotacredito;
    }

    public void setOut_tiponotacredito(String out_tiponotacredito) {
        this.out_tiponotacredito = out_tiponotacredito;
    }

    public double getOut_mntneto() {
        return out_mntneto;
    }

    public void setOut_mntneto(double out_mntneto) {
        this.out_mntneto = out_mntneto;
    }

    public double getOut_mntexe() {
        return out_mntexe;
    }

    public void setOut_mntexe(double out_mntexe) {
        this.out_mntexe = out_mntexe;
    }

    public double getOut_mntexo() {
        return out_mntexo;
    }

    public void setOut_mntexo(double out_mntexo) {
        this.out_mntexo = out_mntexo;
    }

    public double getOut_mnttotaligv() {
        return out_mnttotaligv;
    }

    public void setOut_mnttotaligv(double out_mnttotaligv) {
        this.out_mnttotaligv = out_mnttotaligv;
    }

    public double getOut_mnttotal() {
        return out_mnttotal;
    }

    public void setOut_mnttotal(double out_mnttotal) {
        this.out_mnttotal = out_mnttotal;
    }

   

    public String getOut_fchemis() {
        return out_fchemis;
    }

    public void setOut_fchemis(String out_fchemis) {
        this.out_fchemis = out_fchemis;
    }

    public String getOut_codigoimpuesto() {
        return out_codigoimpuesto;
    }

    public void setOut_codigoimpuesto(String out_codigoimpuesto) {
        this.out_codigoimpuesto = out_codigoimpuesto;
    }

    public double getOut_montoimpuesto() {
        return out_montoimpuesto;
    }

    public void setOut_montoimpuesto(double out_montoimpuesto) {
        this.out_montoimpuesto = out_montoimpuesto;
    }

    public double getOut_tasaimpuesto() {
        return out_tasaimpuesto;
    }

    public void setOut_tasaimpuesto(double out_tasaimpuesto) {
        this.out_tasaimpuesto = out_tasaimpuesto;
    }

    public String getOut_codidetraccion1() {
        return out_codidetraccion1;
    }

    public void setOut_codidetraccion1(String out_codidetraccion1) {
        this.out_codidetraccion1 = out_codidetraccion1;
    }

    public String getOut_valordetraccion1() {
        return out_valordetraccion1;
    }

    public void setOut_valordetraccion1(String out_valordetraccion1) {
        this.out_valordetraccion1 = out_valordetraccion1;
    }

    public double getOut_mntdetraccion1() {
        return out_mntdetraccion1;
    }

    public void setOut_mntdetraccion1(double out_mntdetraccion1) {
        this.out_mntdetraccion1 = out_mntdetraccion1;
    }

    public double getOut_porcentajedetraccion1() {
        return out_porcentajedetraccion1;
    }

    public void setOut_porcentajedetraccion1(double out_porcentajedetraccion1) {
        this.out_porcentajedetraccion1 = out_porcentajedetraccion1;
    }

   

    public String getOut_codidetraccion2() {
        return out_codidetraccion2;
    }

    public void setOut_codidetraccion2(String out_codidetraccion2) {
        this.out_codidetraccion2 = out_codidetraccion2;
    }

    public String getOut_valordetraccion2() {
        return out_valordetraccion2;
    }

    public void setOut_valordetraccion2(String out_valordetraccion2) {
        this.out_valordetraccion2 = out_valordetraccion2;
    }

    public double getOut_mntdetraccion2() {
        return out_mntdetraccion2;
    }

    public void setOut_mntdetraccion2(double out_mntdetraccion2) {
        this.out_mntdetraccion2 = out_mntdetraccion2;
    }

    public double getOut_porcentajedetraccion2() {
        return out_porcentajedetraccion2;
    }

    public void setOut_porcentajedetraccion2(double out_porcentajedetraccion2) {
        this.out_porcentajedetraccion2 = out_porcentajedetraccion2;
    }

    public String getOut_codidetraccion3() {
        return out_codidetraccion3;
    }

    public void setOut_codidetraccion3(String out_codidetraccion3) {
        this.out_codidetraccion3 = out_codidetraccion3;
    }

    public String getOut_valordetraccion3() {
        return out_valordetraccion3;
    }

    public void setOut_valordetraccion3(String out_valordetraccion3) {
        this.out_valordetraccion3 = out_valordetraccion3;
    }

    public double getOut_mntdetraccion3() {
        return out_mntdetraccion3;
    }

    public void setOut_mntdetraccion3(double out_mntdetraccion3) {
        this.out_mntdetraccion3 = out_mntdetraccion3;
    }

    public double getOut_porcentajedetraccion3() {
        return out_porcentajedetraccion3;
    }

    public void setOut_porcentajedetraccion3(double out_porcentajedetraccion3) {
        this.out_porcentajedetraccion3 = out_porcentajedetraccion3;
    }

    public String getOut_nrolinref() {
        return out_nrolinref;
    }

    public void setOut_nrolinref(String out_nrolinref) {
        this.out_nrolinref = out_nrolinref;
    }

    public String getOut_tpodocref() {
        return out_tpodocref;
    }

    public void setOut_tpodocref(String out_tpodocref) {
        this.out_tpodocref = out_tpodocref;
    }

    public String getOut_serieref() {
        return out_serieref;
    }

    public void setOut_serieref(String out_serieref) {
        this.out_serieref = out_serieref;
    }

    public String getOut_folioref() {
        return out_folioref;
    }

    public void setOut_folioref(String out_folioref) {
        this.out_folioref = out_folioref;
    }
  
    public String getCodigo_cli() {
        return codigo_cli;
    }

    public void setCodigo_cli(String codigo_cli) {
        this.codigo_cli = codigo_cli;
    }

    public String getHoraemision() {
        return horaemision;
    }

    public void setHoraemision(String horaemision) {
        this.horaemision = horaemision;
    }

    public String getCodigolocalanexo() {
        return codigolocalanexo;
    }

    public void setCodigolocalanexo(String codigolocalanexo) {
        this.codigolocalanexo = codigolocalanexo;
    }

    public String getOut_FechVencFact() {
        return out_FechVencFact;
    }

    public void setOut_FechVencFact(String out_FechVencFact) {
        this.out_FechVencFact = out_FechVencFact;
    }

    /*Se agrega las dos siguientes variables. BA 14/03/21*/
    public String getOut_formapago() {
        return out_formapago;
    }

    public void setOut_formapago(String out_formapago) {
        this.out_formapago = out_formapago;
    }
    
    public double getOut_montonetopendpago() {
        return out_montonetopendpago;
    }

    public void setOut_montonetopendpago(double out_montonetopendpago) {
        this.out_montonetopendpago = out_montonetopendpago;
    }
    /*Fin BA 30/03/21*/
    /*Se agrega las dos siguientes variables. BA 14/03/21*/
    public String getOut_correos() {
        return out_correos;
    }

    public void setOut_correos(String out_correos) {
        this.out_correos = out_correos;
    }
    public String getOut_codencoding() {
        return out_codencoding;
    }

    public void setOut_codencoding(String out_codencoding) {
        this.out_codencoding = out_codencoding;
    }
    public String getOut_secuenciadoc() {
        return out_secuenciadoc;
    }

    public void setOut_secuenciadoc(String out_secuenciadoc) {
        this.out_secuenciadoc = out_secuenciadoc;
    }
    public String getOut_secuenciadocref() {
        return out_secuenciadocref;
    }

    public void setOut_secuenciadocref(String out_secuenciadocref) {
        this.out_secuenciadocref = out_secuenciadocref;
    }
    
    public String getOut_ctadetraccion() {
        return out_ctadetraccion;
    }

    public void setOut_ctadetraccion(String out_ctadetraccion) {
        this.out_ctadetraccion = out_ctadetraccion;
    }
    
    public String getOut_mediopagodetra() {
        return out_mediopagodetra;
    }

    public void setOut_mediopagodetra(String out_mediopagodetra) {
        this.out_mediopagodetra = out_mediopagodetra;
    }
    
    public String getOut_leyendadetra() {
        return out_leyendadetra;
    }

    public void setOut_leyendadetra(String out_leyendadetra) {
        this.out_leyendadetra = out_leyendadetra;
    }
    
    public String getOut_codleyenda() {
        return out_codleyenda;
    }

    public void setOut_codleyenda(String out_codleyenda) {
        this.out_codleyenda = out_codleyenda;
    }
    
    public void setOut_totallineas(double out_totallineas) {
        this.out_totallineas = out_totallineas;
    }

    public double getOut_totallineas() {
        return out_totallineas;
    }

    public void setOut_otroconcepto1(String out_otroconcepto1) {
        this.out_otroconcepto1 = out_otroconcepto1;
    }
    
    public String getOut_otroconcepto1() {
        return out_otroconcepto1;
    }
    
    public void setOut_leyenda(String out_leyenda) {
        this.out_leyenda = out_leyenda;
    }
    
    public String getOut_leyenda() {
        return out_leyenda;
    }
    
    public void setOut_codigo_leyenda(String out_codigo_leyenda) {
        this.out_codigo_leyenda = out_codigo_leyenda;
    }
    public String getOut_codigo_leyenda() {
        return out_codigo_leyenda;
    }
    
    public void setOut_otroconcepto2(String out_otroconcepto2) {
        this.out_otroconcepto2 = out_otroconcepto2;
    }
    
    public String getOut_otroconcepto2() {
        return out_otroconcepto2;
    }
    
    public void setOut_otroconcepto3(String out_otroconcepto3) {
        this.out_otroconcepto3 = out_otroconcepto3;
    }
    
    public String getOut_otroconcepto3() {
        return out_otroconcepto3;
    }
    
    public void setOut_nomotroconcepto1(String out_nomotroconcepto1) {
        this.out_nomotroconcepto1 = out_nomotroconcepto1;
    }
    
    public String getOut_nomotroconcepto1() {
        return out_nomotroconcepto1;
    }
    
    public void setOut_nomotroconcepto2(String out_nomotroconcepto2) {
        this.out_nomotroconcepto2 = out_nomotroconcepto2;
    }
    
    public String getOut_nomotroconcepto2() {
        return out_nomotroconcepto2;
    }
    
    public void setOut_nomotroconcepto3(String out_nomotroconcepto3) {
        this.out_nomotroconcepto3 = out_nomotroconcepto3;
    }
    
    public String getOut_nomotroconcepto3() {
        return out_nomotroconcepto3;
    }
    
    public void setOut_codotroconcepto1(String out_codotroconcepto1) {
        this.out_codotroconcepto1 = out_codotroconcepto1;
    }
    
    public String getOut_codotroconcepto1() {
        return out_codotroconcepto1;
    }
    
    public void setOut_codotroconcepto2(String out_codotroconcepto2) {
        this.out_codotroconcepto2 = out_codotroconcepto2;
    }
    
    public String getOut_codotroconcepto2() {
        return out_codotroconcepto2;
    }
    
    public void setOut_codotroconcepto3(String out_codotroconcepto3) {
        this.out_codotroconcepto3 = out_codotroconcepto3;
    }
    
    public String getOut_codotroconcepto3() {
        return out_codotroconcepto3;
    }
    
    public void setOut_nrorden(String out_nrorden) {
        this.out_nrorden = out_nrorden;
    }
    
    public String getOut_nrorden() {
        return out_nrorden;
    }
    
    public String getOut_otroconcepto4() {
        return out_otroconcepto4;
    }
    
    public void setOut_otroconcepto4(String out_otroconcepto4) {
        this.out_otroconcepto4 = out_otroconcepto4;
    }
    
    public String getOut_nomotroconcepto4() {
        return out_nomotroconcepto4;
    }
    
    public void setOut_nomotroconcepto4(String out_nomotroconcepto4) {
        this.out_nomotroconcepto4 = out_nomotroconcepto4;
    }
    
    public String getOut_codotroconcepto4() {
        return out_codotroconcepto4;
    }
    
    public void setOut_codotroconcepto4(String out_codotroconcepto4) {
        this.out_codotroconcepto4 = out_codotroconcepto4;
    }
    
    public void setOut_otroconcepto5(String out_otroconcepto5) {
        this.out_otroconcepto5 = out_otroconcepto5;
    }
    
    public String getOut_otroconcepto5() {
        return out_otroconcepto5;
    }
    
    public void setOut_otroconcepto6(String out_otroconcepto6) {
        this.out_otroconcepto6 = out_otroconcepto6;
    }
    
    public String getOut_otroconcepto6() {
        return out_otroconcepto6;
    }
    
    public void setOut_otroconcepto7(String out_otroconcepto7) {
        this.out_otroconcepto7 = out_otroconcepto7;
    }
    
    public String getOut_otroconcepto7() {
        return out_otroconcepto7;
    }
    
    public void setOut_otroconcepto8(String out_otroconcepto8) {
        this.out_otroconcepto8 = out_otroconcepto8;
    }
    
    public String getOut_otroconcepto8() {
        return out_otroconcepto8;
    }
    
    public void setOut_otroconcepto9(String out_otroconcepto9) {
        this.out_otroconcepto9 = out_otroconcepto9;
    }
    
    public String getOut_otroconcepto9() {
        return out_otroconcepto9;
    }
    
    public void setOut_otroconcepto10(String out_otroconcepto10) {
        this.out_otroconcepto10 = out_otroconcepto10;
    }
    
    public String getOut_otroconcepto10() {
        return out_otroconcepto10;
    }
    
    public void setOut_otroconcepto11(String out_otroconcepto11) {
        this.out_otroconcepto11 = out_otroconcepto11;
    }
    
    public String getOut_otroconcepto11() {
        return out_otroconcepto11;
    }
    
    public void setOut_nomotroconcepto5(String out_nomotroconcepto5) {
        this.out_nomotroconcepto5 = out_nomotroconcepto5;
    }
    
    public String getOut_nomotroconcepto5() {
        return out_nomotroconcepto5;
    }
    
    public void setOut_nomotroconcepto6(String out_nomotroconcepto6) {
        this.out_nomotroconcepto6 = out_nomotroconcepto6;
    }
    
    public String getOut_nomotroconcepto6() {
        return out_nomotroconcepto6;
    }
    
    public void setOut_nomotroconcepto7(String out_nomotroconcepto7) {
        this.out_nomotroconcepto7 = out_nomotroconcepto7;
    }
    
    public String getOut_nomotroconcepto7() {
        return out_nomotroconcepto7;
    }
    
    public void setOut_nomotroconcepto8(String out_nomotroconcepto8) {
        this.out_nomotroconcepto8 = out_nomotroconcepto8;
    }
    
    public String getOut_nomotroconcepto8() {
        return out_nomotroconcepto8;
    }
    
    public void setOut_nomotroconcepto9(String out_nomotroconcepto9) {
        this.out_nomotroconcepto9 = out_nomotroconcepto9;
    }
    
    public String getOut_nomotroconcepto9() {
        return out_nomotroconcepto9;
    }
    
    public void setOut_nomotroconcepto10(String out_nomotroconcepto10) {
        this.out_nomotroconcepto10 = out_nomotroconcepto10;
    }
    
    public String getOut_nomotroconcepto10() {
        return out_nomotroconcepto10;
    }
    
    public void setOut_nomotroconcepto11(String out_nomotroconcepto11) {
        this.out_nomotroconcepto11 = out_nomotroconcepto11;
    }
    
    public String getOut_nomotroconcepto11() {
        return out_nomotroconcepto11;
    }
    
    public void setOut_codotroconcepto5(String out_codotroconcepto5) {
        this.out_codotroconcepto5 = out_codotroconcepto5;
    }
    
    public String getOut_codotroconcepto5() {
        return out_codotroconcepto5;
    }
    
    public void setOut_codotroconcepto6(String out_codotroconcepto6) {
        this.out_codotroconcepto6 = out_codotroconcepto6;
    }
    
    public String getOut_codotroconcepto6() {
        return out_codotroconcepto6;
    }
    
    public void setOut_codotroconcepto7(String out_codotroconcepto7) {
        this.out_codotroconcepto7 = out_codotroconcepto7;
    }
    
    public String getOut_codotroconcepto7() {
        return out_codotroconcepto7;
    }
    
    public void setOut_codotroconcepto8(String out_codotroconcepto8) {
        this.out_codotroconcepto8 = out_codotroconcepto8;
    }
    
    public String getOut_codotroconcepto8() {
        return out_codotroconcepto8;
    }
    
    public void setOut_codotroconcepto9(String out_codotroconcepto9) {
        this.out_codotroconcepto9 = out_codotroconcepto9;
    }
    
    public String getOut_codotroconcepto9() {
        return out_codotroconcepto9;
    }
    
    public void setOut_codotroconcepto10(String out_codotroconcepto10) {
        this.out_codotroconcepto10 = out_codotroconcepto10;
    }
    
    public String getOut_codotroconcepto10() {
        return out_codotroconcepto10;
    }
    
    public void setOut_codotroconcepto11(String out_codotroconcepto11) {
        this.out_codotroconcepto11 = out_codotroconcepto11;
    }
    
    public String getOut_codotroconcepto11() {
        return out_codotroconcepto11;
    }
    
    public String getOut_codigoimpuestoexe() {
        return out_codigoimpuestoexe;
    }

    public void setOut_codigoimpuestoexe(String out_codigoimpuestoexe) {
        this.out_codigoimpuestoexe = out_codigoimpuestoexe;
    }
}

