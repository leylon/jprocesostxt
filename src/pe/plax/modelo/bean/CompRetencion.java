/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.plax.modelo.bean;

/**
 *
 * @author Andy Villafana
 */
public class CompRetencion {
    
    //Variables de Entrada:
    private String IN_EMPRESA;
    private String IN_CODIGO;
    private String IN_TIPO;
    private String IN_TIPDOC;
    private String IN_SERDOC;
    private String IN_NUMDOC;
    
    //Variables de Salida:
    private int OUT_CODI_EMPR;
    private String OUT_TIPODTE;
    private String OUT_SERIE;
    private String OUT_CORRELATIVO;
    private String OUT_FCHEMIS;
    private String OUT_TIPOMONEDA;
       //Datos del emisor
    private String OUT_RUTEMIS;
    private String OUT_TIPORUCEMIS;
    private String OUT_NOMCOMER;
    private String OUT_RZNSOCEMIS;
    private String OUT_COMUEMIS;
    private String OUT_DIREMIS;
    private String OUT_URBANIZAEMIS;
    private String PROVIEMIS;
    private String OUT_DEPAREMIS;
    private String OUT_DISTRIEMIS;
    private String OUT_PAISEMIS;
        // Datos del receptor
    private String OUT_TIPORUTRECEPTOR;
    private String OUT_RUTRECEP;
    private String OUT_RZNSOCRECEP;
    private String OUT_DIRRECEP;
       //Datos de CRE
    private String OUT_CODRETENCION;
    private double OUT_MNTIMPRETENCION;
    private String OUT_OBSRETENCION;
    private double OUT_MNTRETENCION;
    private double OUT_MNTTOTALMENOSRETENCION;
    
    private String out_tipoadicsunat;
    private String out_nmrlineasdetalle;
    private String out_nmrlineasadicsunat;
    private String out_descripcionadicsunat;

    public String getOut_tipoadicsunat() {
        return out_tipoadicsunat;
    }

    public void setOut_tipoadicsunat(String out_tipoadicsunat) {
        this.out_tipoadicsunat = out_tipoadicsunat;
    }

    public String getOut_nmrlineasdetalle() {
        return out_nmrlineasdetalle;
    }

    public void setOut_nmrlineasdetalle(String out_nmrlineasdetalle) {
        this.out_nmrlineasdetalle = out_nmrlineasdetalle;
    }

    public String getOut_nmrlineasadicsunat() {
        return out_nmrlineasadicsunat;
    }

    public void setOut_nmrlineasadicsunat(String out_nmrlineasadicsunat) {
        this.out_nmrlineasadicsunat = out_nmrlineasadicsunat;
    }

    public String getOut_descripcionadicsunat() {
        return out_descripcionadicsunat;
    }

    public void setOut_descripcionadicsunat(String out_descripcionadicsunat) {
        this.out_descripcionadicsunat = out_descripcionadicsunat;
    }

    
    
    
    public String getIN_EMPRESA() {
        return IN_EMPRESA;
    }

    public void setIN_EMPRESA(String IN_EMPRESA) {
        this.IN_EMPRESA = IN_EMPRESA;
    }

    public String getIN_CODIGO() {
        return IN_CODIGO;
    }

    public String getIN_TIPDOC() {
        return IN_TIPDOC;
    }

    public void setIN_TIPDOC(String IN_TIPDOC) {
        this.IN_TIPDOC = IN_TIPDOC;
    }

    public String getIN_SERDOC() {
        return IN_SERDOC;
    }

    public void setIN_SERDOC(String IN_SERDOC) {
        this.IN_SERDOC = IN_SERDOC;
    }

    public String getIN_NUMDOC() {
        return IN_NUMDOC;
    }

    public void setIN_NUMDOC(String IN_NUMDOC) {
        this.IN_NUMDOC = IN_NUMDOC;
    }

    public void setIN_CODIGO(String IN_CODIGO) {
        this.IN_CODIGO = IN_CODIGO;
    }

    public String getIN_TIPO() {
        return IN_TIPO;
    }

    public void setIN_TIPO(String IN_TIPO) {
        this.IN_TIPO = IN_TIPO;
    }

    public int getOUT_CODI_EMPR() {
        return OUT_CODI_EMPR;
    }

    public void setOUT_CODI_EMPR(int OUT_CODI_EMPR) {
        this.OUT_CODI_EMPR = OUT_CODI_EMPR;
    }

    public String getOUT_TIPODTE() {
        return OUT_TIPODTE;
    }

    public void setOUT_TIPODTE(String OUT_TIPODTE) {
        this.OUT_TIPODTE = OUT_TIPODTE;
    }

    public String getOUT_SERIE() {
        return OUT_SERIE;
    }

    public void setOUT_SERIE(String OUT_SERIE) {
        this.OUT_SERIE = OUT_SERIE;
    }

    public String getOUT_CORRELATIVO() {
        return OUT_CORRELATIVO;
    }

    public void setOUT_CORRELATIVO(String OUT_CORRELATIVO) {
        this.OUT_CORRELATIVO = OUT_CORRELATIVO;
    }

    public String getOUT_FCHEMIS() {
        return OUT_FCHEMIS;
    }

    public void setOUT_FCHEMIS(String OUT_FCHEMIS) {
        this.OUT_FCHEMIS = OUT_FCHEMIS;
    }

    public String getOUT_TIPOMONEDA() {
        return OUT_TIPOMONEDA;
    }

    public void setOUT_TIPOMONEDA(String OUT_TIPOMONEDA) {
        this.OUT_TIPOMONEDA = OUT_TIPOMONEDA;
    }

    public String getOUT_RUTEMIS() {
        return OUT_RUTEMIS;
    }

    public void setOUT_RUTEMIS(String OUT_RUTEMIS) {
        this.OUT_RUTEMIS = OUT_RUTEMIS;
    }

    public String getOUT_TIPORUCEMIS() {
        return OUT_TIPORUCEMIS;
    }

    public void setOUT_TIPORUCEMIS(String OUT_TIPORUCEMIS) {
        this.OUT_TIPORUCEMIS = OUT_TIPORUCEMIS;
    }

    public String getOUT_NOMCOMER() {
        return OUT_NOMCOMER;
    }

    public void setOUT_NOMCOMER(String OUT_NOMCOMER) {
        this.OUT_NOMCOMER = OUT_NOMCOMER;
    }

    public String getOUT_RZNSOCEMIS() {
        return OUT_RZNSOCEMIS;
    }
    
    
    public void setOUT_RZNSOCEMIS(String OUT_RZNSOCEMIS) {
        this.OUT_RZNSOCEMIS = OUT_RZNSOCEMIS;
    }

    public String getOUT_DIRRECEP() {
        return OUT_DIRRECEP;
    }

    public void setOUT_DIRRECEP(String OUT_DIRRECEP) {
        this.OUT_DIRRECEP = OUT_DIRRECEP;
    }
    
    

    public String getOUT_COMUEMIS() {
        return OUT_COMUEMIS;
    }

    public void setOUT_COMUEMIS(String OUT_COMUEMIS) {
        this.OUT_COMUEMIS = OUT_COMUEMIS;
    }

    public String getOUT_DIREMIS() {
        return OUT_DIREMIS;
    }

    public void setOUT_DIREMIS(String OUT_DIREMIS) {
        this.OUT_DIREMIS = OUT_DIREMIS;
    }

    public String getOUT_URBANIZAEMIS() {
        return OUT_URBANIZAEMIS;
    }

    public void setOUT_URBANIZAEMIS(String OUT_URBANIZAEMIS) {
        this.OUT_URBANIZAEMIS = OUT_URBANIZAEMIS;
    }

    public String getPROVIEMIS() {
        return PROVIEMIS;
    }

    public void setPROVIEMIS(String PROVIEMIS) {
        this.PROVIEMIS = PROVIEMIS;
    }

    public String getOUT_DEPAREMIS() {
        return OUT_DEPAREMIS;
    }

    public void setOUT_DEPAREMIS(String OUT_DEPAREMIS) {
        this.OUT_DEPAREMIS = OUT_DEPAREMIS;
    }

    public String getOUT_DISTRIEMIS() {
        return OUT_DISTRIEMIS;
    }

    public void setOUT_DISTRIEMIS(String OUT_DISTRIEMIS) {
        this.OUT_DISTRIEMIS = OUT_DISTRIEMIS;
    }

    public String getOUT_PAISEMIS() {
        return OUT_PAISEMIS;
    }

    public void setOUT_PAISEMIS(String OUT_PAISEMIS) {
        this.OUT_PAISEMIS = OUT_PAISEMIS;
    }

    public String getOUT_TIPORUTRECEPTOR() {
        return OUT_TIPORUTRECEPTOR;
    }

    public void setOUT_TIPORUTRECEPTOR(String OUT_TIPORUTRECEPTOR) {
        this.OUT_TIPORUTRECEPTOR = OUT_TIPORUTRECEPTOR;
    }

    public String getOUT_RUTRECEP() {
        return OUT_RUTRECEP;
    }

    public void setOUT_RUTRECEP(String OUT_RUTRECEP) {
        this.OUT_RUTRECEP = OUT_RUTRECEP;
    }

    public String getOUT_RZNSOCRECEP() {
        return OUT_RZNSOCRECEP;
    }

    public void setOUT_RZNSOCRECEP(String OUT_RZNSOCRECEP) {
        this.OUT_RZNSOCRECEP = OUT_RZNSOCRECEP;
    }

    public String getOUT_CODRETENCION() {
        return OUT_CODRETENCION;
    }

    public void setOUT_CODRETENCION(String OUT_CODRETENCION) {
        this.OUT_CODRETENCION = OUT_CODRETENCION;
    }

    public double getOUT_MNTIMPRETENCION() {
        return OUT_MNTIMPRETENCION;
    }

    public void setOUT_MNTIMPRETENCION(double OUT_MNTIMPRETENCION) {
        this.OUT_MNTIMPRETENCION = OUT_MNTIMPRETENCION;
    }

    public String getOUT_OBSRETENCION() {
        return OUT_OBSRETENCION;
    }

    public void setOUT_OBSRETENCION(String OUT_OBSRETENCION) {
        this.OUT_OBSRETENCION = OUT_OBSRETENCION;
    }

    public double getOUT_MNTRETENCION() {
        return OUT_MNTRETENCION;
    }

    public void setOUT_MNTRETENCION(double OUT_MNTRETENCION) {
        this.OUT_MNTRETENCION = OUT_MNTRETENCION;
    }

    public double getOUT_MNTTOTALMENOSRETENCION() {
        return OUT_MNTTOTALMENOSRETENCION;
    }

    public void setOUT_MNTTOTALMENOSRETENCION(double OUT_MNTTOTALMENOSRETENCION) {
        this.OUT_MNTTOTALMENOSRETENCION = OUT_MNTTOTALMENOSRETENCION;
    }
    
    
    
}
